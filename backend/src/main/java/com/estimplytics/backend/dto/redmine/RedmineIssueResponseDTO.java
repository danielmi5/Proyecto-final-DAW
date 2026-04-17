package com.estimplytics.backend.dto.redmine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedmineIssueResponseDTO {
    @JsonProperty("issues")
	private List<RedmineIssueDTO> issues;

	@JsonProperty("total_count")
	private Integer totalCount;

	@JsonProperty("offset")
	private Integer offset;

	@JsonProperty("limit")
	private Integer limit;
}
