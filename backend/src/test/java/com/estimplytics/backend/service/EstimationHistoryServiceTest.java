package com.estimplytics.backend.service;

import com.estimplytics.backend.dto.EstimationHistoryRequestDTO;
import com.estimplytics.backend.dto.EstimationHistoryResponseDTO;
import com.estimplytics.backend.dto.EstimationHistoryUpdateDTO;
import com.estimplytics.backend.entity.EstimationHistory;
import com.estimplytics.backend.mapper.EstimationHistoryMapper;
import com.estimplytics.backend.repository.EstimationHistoryRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EstimationHistoryServiceTest {

    @Mock
    private EstimationHistoryRepository repository;

    @Mock
    private EstimationHistoryMapper mapper;

    @InjectMocks
    private EstimationHistoryService service;

    @Test
    void findAll_shouldMapPage() {
        Pageable pageable = PageRequest.of(0, 10);
        EstimationHistory entity = mock(EstimationHistory.class);
        EstimationHistoryResponseDTO response = mock(EstimationHistoryResponseDTO.class);
        Page<EstimationHistory> page = new PageImpl<>(List.of(entity));
        when(repository.findAll(pageable)).thenReturn(page);
        when(mapper.toResponseDTO(entity)).thenReturn(response);

        Page<EstimationHistoryResponseDTO> result = service.findAll(pageable);

        assertThat(result.getContent()).containsExactly(response);
    }

    @Test
    void findById_shouldReturnMappedOptionalWhenExists() {
        EstimationHistory entity = mock(EstimationHistory.class);
        EstimationHistoryResponseDTO response = mock(EstimationHistoryResponseDTO.class);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toResponseDTO(entity)).thenReturn(response);

        Optional<EstimationHistoryResponseDTO> result = service.findById(1L);

        assertThat(result).contains(response);
    }

    @Test
    void create_shouldPersistAndMapEntity() {
        EstimationHistoryRequestDTO request = mock(EstimationHistoryRequestDTO.class);
        EstimationHistory entity = mock(EstimationHistory.class);
        EstimationHistory saved = mock(EstimationHistory.class);
        EstimationHistoryResponseDTO response = mock(EstimationHistoryResponseDTO.class);
        when(mapper.toEntity(request)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(saved);
        when(mapper.toResponseDTO(saved)).thenReturn(response);

        EstimationHistoryResponseDTO result = service.create(request);

        assertThat(result).isSameAs(response);
    }

    @Test
    void update_shouldThrowUnsupportedOperationException() {
        EstimationHistoryUpdateDTO update = mock(EstimationHistoryUpdateDTO.class);

        assertThatThrownBy(() -> service.update(1L, update)).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void delete_shouldThrowUnsupportedOperationException() {
        assertThatThrownBy(() -> service.delete(1L)).isInstanceOf(UnsupportedOperationException.class);
    }
}
