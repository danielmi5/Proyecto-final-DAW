package com.estimplytics.backend.service;

import com.estimplytics.backend.dto.ImpactAnalysisHistoryRequestDTO;
import com.estimplytics.backend.dto.ImpactAnalysisHistoryResponseDTO;
import com.estimplytics.backend.dto.ImpactAnalysisHistoryUpdateDTO;
import com.estimplytics.backend.entity.ImpactAnalysisHistory;
import com.estimplytics.backend.mapper.ImpactAnalysisHistoryMapper;
import com.estimplytics.backend.repository.ImpactAnalysisHistoryRepository;
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
class ImpactAnalysisHistoryServiceTest {

    @Mock
    private ImpactAnalysisHistoryRepository repository;

    @Mock
    private ImpactAnalysisHistoryMapper mapper;

    @InjectMocks
    private ImpactAnalysisHistoryService service;

    @Test
    void findAll_shouldMapPage() {
        Pageable pageable = PageRequest.of(0, 10);
        ImpactAnalysisHistory entity = mock(ImpactAnalysisHistory.class);
        ImpactAnalysisHistoryResponseDTO response = mock(ImpactAnalysisHistoryResponseDTO.class);
        Page<ImpactAnalysisHistory> page = new PageImpl<>(List.of(entity));
        when(repository.findAll(pageable)).thenReturn(page);
        when(mapper.toResponseDTO(entity)).thenReturn(response);

        Page<ImpactAnalysisHistoryResponseDTO> result = service.findAll(pageable);

        assertThat(result.getContent()).containsExactly(response);
    }

    @Test
    void findById_shouldReturnMappedOptionalWhenExists() {
        ImpactAnalysisHistory entity = mock(ImpactAnalysisHistory.class);
        ImpactAnalysisHistoryResponseDTO response = mock(ImpactAnalysisHistoryResponseDTO.class);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toResponseDTO(entity)).thenReturn(response);

        Optional<ImpactAnalysisHistoryResponseDTO> result = service.findById(1L);

        assertThat(result).contains(response);
    }

    @Test
    void create_shouldPersistAndMapEntity() {
        ImpactAnalysisHistoryRequestDTO request = mock(ImpactAnalysisHistoryRequestDTO.class);
        ImpactAnalysisHistory entity = mock(ImpactAnalysisHistory.class);
        ImpactAnalysisHistory saved = mock(ImpactAnalysisHistory.class);
        ImpactAnalysisHistoryResponseDTO response = mock(ImpactAnalysisHistoryResponseDTO.class);
        when(mapper.toEntity(request)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(saved);
        when(mapper.toResponseDTO(saved)).thenReturn(response);

        ImpactAnalysisHistoryResponseDTO result = service.create(request);

        assertThat(result).isSameAs(response);
    }

    @Test
    void update_shouldThrowUnsupportedOperationException() {
        ImpactAnalysisHistoryUpdateDTO update = mock(ImpactAnalysisHistoryUpdateDTO.class);

        assertThatThrownBy(() -> service.update(1L, update)).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void delete_shouldThrowUnsupportedOperationException() {
        assertThatThrownBy(() -> service.delete(1L)).isInstanceOf(UnsupportedOperationException.class);
    }
}
