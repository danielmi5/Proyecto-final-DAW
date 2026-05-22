package com.estimplytics.backend.controller;

import com.estimplytics.backend.dto.redmine.CreateCredentialCmd;
import com.estimplytics.backend.dto.redmine.UserRedmineCredentialDto;
import com.estimplytics.backend.entity.RedmineInstance;
import com.estimplytics.backend.entity.User;
import com.estimplytics.backend.entity.UserRedmineCredential;
import com.estimplytics.backend.exception.RedmineInstanceNotFoundException;
import com.estimplytics.backend.repository.RedmineInstanceRepository;
import com.estimplytics.backend.repository.UserRedmineCredentialRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/redmine-credentials")
@RequiredArgsConstructor
@Tag(name = "Redmine Credentials", description = "User Redmine credential management")
public class UserRedmineCredentialController {

    private final UserRedmineCredentialRepository repository;
    private final RedmineInstanceRepository instanceRepository;

    @GetMapping
    @Operation(summary = "List current user Redmine credentials", description = "Returns the authenticated user's Redmine credentials with masked API keys.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Credentials retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthenticated"),
            @ApiResponse(responseCode = "403", description = "Unauthorised")
    })
    public List<UserRedmineCredentialDto> getMyCredentials(@AuthenticationPrincipal User user) {
        return repository.findByUser(user).stream()
                .map(cred -> new UserRedmineCredentialDto(
                        cred.getId(),
                        cred.getRedmineInstance().getId(),
                        cred.getRedmineInstance().getName(),
                        "********",
                        cred.getLastSyncAt()
                ))
                .toList();
    }

    @PostMapping
    @Operation(summary = "Add a Redmine credential", description = "Stores an encrypted Redmine API key for the authenticated user.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Credential created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "401", description = "Unauthenticated"),
            @ApiResponse(responseCode = "403", description = "Unauthorised"),
            @ApiResponse(responseCode = "404", description = "Redmine instance not found")
    })
    public ResponseEntity<Void> addCredential(@AuthenticationPrincipal User user,
                                              @Valid @RequestBody CreateCredentialCmd cmd) {
        RedmineInstance instance = instanceRepository.findById(cmd.redmineInstanceId())
                .orElseThrow(() -> new RedmineInstanceNotFoundException(
                        "Redmine instance not found with id %s".formatted(cmd.redmineInstanceId())));

        UserRedmineCredential credential = new UserRedmineCredential();
        credential.setUser(user);
        credential.setRedmineInstance(instance);
        credential.setApiKey(cmd.plainApiKey());

        repository.save(credential);
        return ResponseEntity.ok().build();
    }
}
