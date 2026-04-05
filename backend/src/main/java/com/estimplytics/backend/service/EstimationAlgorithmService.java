package com.estimplytics.backend.service;

import com.estimplytics.backend.dto.EstimationAlgorithmResultDTO;
import com.estimplytics.backend.exception.ImpactAnalysisNotFoundException;
import com.estimplytics.backend.repository.ComponentAnalysisRepository;
import com.estimplytics.backend.repository.EstimationRepository;
import com.estimplytics.backend.repository.ImpactAnalysisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EstimationAlgorithmService {

    private static final Integer BASE_FIABILITY_PERCENTAGE = 50;
    private static final Integer FIABILITY_INCREMENT_PER_RECORD = 10;
    private static final Integer MAXIMUM_FIABILITY_PERCENTAGE = 95;
    private static final Integer HIGH_VOLATILITY_PENALIZATION = 15;
    private static final Double VOLATILITY_RANGE = 0.30;

    private final ImpactAnalysisRepository impactAnalysisRepository;
    private final ComponentAnalysisRepository componentAnalysisRepository;
    private final EstimationRepository estimationRepository;

    public EstimationAlgorithmService(
            ImpactAnalysisRepository impactAnalysisRepository,
            ComponentAnalysisRepository componentAnalysisRepository,
            EstimationRepository estimationRepository
    ) {
        this.impactAnalysisRepository = impactAnalysisRepository;
        this.componentAnalysisRepository = componentAnalysisRepository;
        this.estimationRepository = estimationRepository;
    }

    @Transactional(readOnly = true)
    public EstimationAlgorithmResultDTO calculateSuggestionForAnalysisId(UUID analysisId) {
        if (!impactAnalysisRepository.existsById(analysisId)) {
            throw new ImpactAnalysisNotFoundException("Impact analysis not found with id %s".formatted(analysisId));
        }

        List<UUID> componentIds = componentAnalysisRepository.findComponentIdsByAnalysisId(analysisId);
        if (componentIds.isEmpty()) {
            return EstimationAlgorithmResultDTO.builder()
                .suggestedTotalHours(0)
                .fiabilityPercentage(0)
                .build();
        }

        List<UUID> historicalAnalysisIds = componentAnalysisRepository.findAnalysisIdsWithExactComponentSet(componentIds,componentIds.size())
            .stream()
            .filter(candidateAnalysisId -> !candidateAnalysisId.equals(analysisId))
            .toList();

        if (historicalAnalysisIds.isEmpty()) {
            return EstimationAlgorithmResultDTO.builder()
                .suggestedTotalHours(0)
                .fiabilityPercentage(0)
                .build();
        }

        List<Integer> historicalHours = estimationRepository.findActualHoursFeedbackByAnalysisIds(
                historicalAnalysisIds
        );

        if (historicalHours.isEmpty()) {
            return EstimationAlgorithmResultDTO.builder()
                .suggestedTotalHours(0)
                .fiabilityPercentage(0)
                .build();
        }

        Integer suggestedTotalHours = calculateAverageHours(historicalHours);
        Integer fiability = calculateFiability(historicalHours);

        return EstimationAlgorithmResultDTO.builder()
            .suggestedTotalHours(suggestedTotalHours)
            .fiabilityPercentage(fiability)
            .build();
    }

    private Integer calculateAverageHours(List<Integer> historicalHours) {
        Double average = historicalHours.stream()
            .mapToDouble(Integer::doubleValue)
            .average()
            .orElse(0.0);

        if (average <= 0.0) {
            return 0;
        }
        return (int) Math.round(average);
    }

    private Integer calculateFiability(List<Integer> historicalHours) {
        Integer records = historicalHours.size();
        Integer fiabilityBase = Math.min(BASE_FIABILITY_PERCENTAGE + records * FIABILITY_INCREMENT_PER_RECORD, MAXIMUM_FIABILITY_PERCENTAGE );

        Double average = historicalHours.stream()
            .mapToDouble(Integer::doubleValue)
            .average()
            .orElse(0.0);

        if (average <= 0.0) {
            return fiabilityBase;
        }

        Double standardDeviation = calculateStandardDeviation(historicalHours, average);
        Double volatility = standardDeviation / average;

        if (volatility > VOLATILITY_RANGE) {
            return Math.max(fiabilityBase - HIGH_VOLATILITY_PENALIZATION, 0);
        }

        return fiabilityBase;
    }

    private Double calculateStandardDeviation(List<Integer> historicalHours, Double average) {
        Double variance = historicalHours.stream()
            .mapToDouble(hours -> Math.pow(hours - average, 2))
            .average()
            .orElse(0.0);

        if (variance <= 0.0) {
            return 0.0;
        }
        return Math.sqrt(variance);
    }
}
