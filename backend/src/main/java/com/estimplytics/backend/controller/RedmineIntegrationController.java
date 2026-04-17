package com.estimplytics.backend.controller;

import com.estimplytics.backend.service.RedmineIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedmineIntegrationController implements IRedmineIntegrationController {
    private final RedmineIntegrationService redmineIntegrationService;

	@Override
	public ResponseEntity<String> syncIssues() {
		try {
			int syncedCount = redmineIntegrationService.syncIssues();
			return ResponseEntity.ok("Synchronization successful. %d records synchronized.".formatted(syncedCount));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Synchronization failed: %s".formatted(e.getMessage()));
		}
	}
}
