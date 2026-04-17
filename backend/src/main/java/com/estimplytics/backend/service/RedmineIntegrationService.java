package com.estimplytics.backend.service;

import com.estimplytics.backend.config.RedmineProperties;
import com.estimplytics.backend.dto.redmine.RedmineIssueDTO;
import com.estimplytics.backend.dto.redmine.RedmineIssueResponseDTO;
import com.estimplytics.backend.entity.Request;
import com.estimplytics.backend.exception.RedmineIntegrationException;
import com.estimplytics.backend.exception.RedmineIntegrationException.ErrorType;
import com.estimplytics.backend.mapper.RedmineIssueMapper;
import com.estimplytics.backend.repository.RequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class RedmineIntegrationService {
	private final RestClient restClient;
	private final RequestRepository requestRepository;
	private final RedmineProperties redmineProperties;
	private final RedmineIssueMapper redmineIssueMapper;

	public RedmineIntegrationService(RestClient.Builder restClientBuilder, RequestRepository requestRepository, RedmineProperties redmineProperties, RedmineIssueMapper redmineIssueMapper) {
		this.restClient = restClientBuilder.build();
		this.requestRepository = requestRepository;
		this.redmineProperties = redmineProperties;
		this.redmineIssueMapper = redmineIssueMapper;
	}

	@Transactional
	public int syncIssues() {
		int offset = 0;
		int limit = 100;
		int totalSynced = 0;
		Integer totalCount = null;

		try {
			do {
				String apiUrl = "%s/issues.json?status_id=*&limit=%d&offset=%d".formatted(
					redmineProperties.getUrl(), limit, offset);

				RedmineIssueResponseDTO response = restClient.get()
					.uri(apiUrl)
					.header("X-Redmine-API-Key", redmineProperties.getKey())
					.retrieve()
					.body(RedmineIssueResponseDTO.class);

				if (response == null || response.getIssues() == null || response.getIssues().isEmpty()) {
					break;
				}

				if (totalCount == null) {
					totalCount = response.getTotalCount();
				}

				List<RedmineIssueDTO> issues = response.getIssues();
				for (RedmineIssueDTO issue : issues) {
					upsertIssue(issue);
					totalSynced++;
				}

				offset += limit;

			} while (totalCount != null && offset < totalCount);

			return totalSynced;
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().value() == 401 || e.getStatusCode().value() == 403) {
				throw new RedmineIntegrationException("Invalid Redmine API credentials or key", e, ErrorType.INVALID_CREDENTIALS);
			}
			throw new RedmineIntegrationException("HTTP client error: %s".formatted(e.getStatusCode()), e, ErrorType.NETWORK_ERROR);
		} catch (ResourceAccessException e) {
			throw new RedmineIntegrationException("Network error: unable to reach Redmine server", e, ErrorType.NETWORK_ERROR);
		} catch (HttpServerErrorException e) {
			throw new RedmineIntegrationException("Redmine server error: %s".formatted(e.getStatusCode()), e, ErrorType.NETWORK_ERROR);
		} catch (Exception e) {
			throw new RedmineIntegrationException("Failed to process Redmine response: %s".formatted(e.getMessage()), e, ErrorType.INVALID_PAYLOAD);
		}
	}

	private void upsertIssue(RedmineIssueDTO dto) {
		Optional<Request> optionalRequest = requestRepository.findByRedmineId(dto.getId());

		Request request;
		if (optionalRequest.isPresent()) {
			request = optionalRequest.get();
		} else {
			request = new Request();
		}

		redmineIssueMapper.updateEntityFromDto(dto, request);

		requestRepository.save(request);
	}
}
