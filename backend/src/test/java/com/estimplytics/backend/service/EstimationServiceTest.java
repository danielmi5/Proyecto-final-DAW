package com.estimplytics.backend.service;

import com.estimplytics.backend.dto.EstimationRequestDTO;
import com.estimplytics.backend.dto.EstimationResponseDTO;
import com.estimplytics.backend.dto.EstimationUpdateDTO;
import com.estimplytics.backend.dto.EstimationAlgorithmResultDTO;
import com.estimplytics.backend.entity.Estimation;
import com.estimplytics.backend.exception.EstimationNotFoundException;
import com.estimplytics.backend.mapper.EstimationMapper;
import com.estimplytics.backend.repository.EstimationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EstimationServiceTest {

    @Mock
    private EstimationRepository repository;

    @Mock
    private EstimationMapper mapper;

    @Mock
    private EstimationAlgorithmService estimationAlgorithmService;

    @InjectMocks
    private EstimationService service;

    @Test
    void findAll_shouldMapPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Estimation entity = mock(Estimation.class);
        EstimationResponseDTO response = mock(EstimationResponseDTO.class);
        Page<Estimation> page = new PageImpl<>(List.of(entity));
        when(repository.findAll(pageable)).thenReturn(page);
        when(mapper.toResponseDTO(entity)).thenReturn(response);

        Page<EstimationResponseDTO> result = service.findAll(pageable);

        assertThat(result.getContent()).containsExactly(response);
    }

    @Test
    void findById_shouldReturnMappedOptionalWhenExists() {
        UUID id = UUID.randomUUID();
        Estimation entity = mock(Estimation.class);
        EstimationResponseDTO response = mock(EstimationResponseDTO.class);
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toResponseDTO(entity)).thenReturn(response);

        Optional<EstimationResponseDTO> result = service.findById(id);

        assertThat(result).contains(response);
    }

    @Test
    void create_shouldPersistAndMapEntity() {
        EstimationRequestDTO request = mock(EstimationRequestDTO.class);
        Estimation entity = mock(Estimation.class);
        Estimation saved = mock(Estimation.class);
        EstimationResponseDTO response = mock(EstimationResponseDTO.class);
        UUID analysisId = UUID.randomUUID();
        when(request.getAnalysisId()).thenReturn(analysisId);
        EstimationAlgorithmResultDTO algorithmResult = EstimationAlgorithmResultDTO.builder().suggestedTotalHours(20).fiabilityPercentage(80).build();
        when(estimationAlgorithmService.calculateSuggestionForAnalysisId(analysisId)).thenReturn(algorithmResult);
        when(mapper.toEntity(request)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(saved);
        when(mapper.toResponseDTO(saved)).thenReturn(response);

        EstimationResponseDTO result = service.create(request);

        assertThat(result).isSameAs(response);
    }

    @Test
    void update_shouldPersistAndMapWhenEntityExists() {
        UUID id = UUID.randomUUID();
        EstimationUpdateDTO update = mock(EstimationUpdateDTO.class);
        Estimation entity = mock(Estimation.class);
        Estimation saved = mock(Estimation.class);
        EstimationResponseDTO response = mock(EstimationResponseDTO.class);
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(saved);
        when(mapper.toResponseDTO(saved)).thenReturn(response);

        EstimationResponseDTO result = service.update(id, update);

        assertThat(result).isSameAs(response);
        verify(mapper).updateEntityFromDTO(update, entity);
    }

    @Test
    void update_shouldThrowWhenEntityMissing() {
        UUID id = UUID.randomUUID();
        EstimationUpdateDTO update = mock(EstimationUpdateDTO.class);
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.update(id, update)).isInstanceOf(EstimationNotFoundException.class);
    }

    @Test
    void delete_shouldDeleteByIdWhenExists() {
        UUID id = UUID.randomUUID();
        when(repository.existsById(id)).thenReturn(true);

        service.delete(id);

        verify(repository).deleteById(id);
    }

    @Test
    void delete_shouldThrowWhenEntityMissing() {
        UUID id = UUID.randomUUID();
        when(repository.existsById(id)).thenReturn(false);

        assertThatThrownBy(() -> service.delete(id)).isInstanceOf(EstimationNotFoundException.class);
    }
}
