package com.estimplytics.backend.dto.redmine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedmineIssueDTO {
	
    private Integer id;

	private NameIdPair project;

	private NameIdPair tracker;

	private NameIdPair status;

	private NameIdPair priority;

	private NameIdPair author;

	@JsonProperty("assigned_to")
	private NameIdPair assignedTo;

	private String subject;

	private String description;

	@JsonProperty("start_date")
	private String startDate;

	@JsonProperty("due_date")
	private String dueDate;

	@JsonProperty("created_on")
	private String createdOn;

	@JsonProperty("updated_on")
	private String updatedOn;

	@JsonProperty("closed_on")
	private String closedOn;

	@JsonProperty("done_ratio")
	private Integer doneRatio;

	@JsonProperty("estimated_hours")
	private Double estimatedHours;

	@JsonProperty("spent_hours")
	private Double spentHours;

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class NameIdPair {
		private Integer id;
		private String name;
	}
}
