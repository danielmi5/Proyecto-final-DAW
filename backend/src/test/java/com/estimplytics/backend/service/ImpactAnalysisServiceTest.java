package com.estimplytics.backend.service;

import com.estimplytics.backend.dto.ImpactAnalysisRequestDTO;
import com.estimplytics.backend.dto.ImpactAnalysisResponseDTO;
import com.estimplytics.backend.dto.ImpactAnalysisUpdateDTO;
import com.estimplytics.backend.entity.ImpactAnalysis;
import com.estimplytics.backend.exception.ImpactAnalysisNotFoundException;
import com.estimplytics.backend.mapper.ImpactAnalysisMapper;
import com.estimplytics.backend.repository.ImpactAnalysisRepository;
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
class ImpactAnalysisServiceTest {

    @Mock
    private ImpactAnalysisRepository repository;

    @Mock
    private ImpactAnalysisMapper mapper;

    @InjectMocks
    private ImpactAnalysisService service;

    @Test
    void findAll_shouldMapPage() {
        Pageable pageable = PageRequest.of(0, 10);
        ImpactAnalysis entity = mock(ImpactAnalysis.class);
        ImpactAnalysisResponseDTO response = mock(ImpactAnalysisResponseDTO.class);
        Page<ImpactAnalysis> page = new PageImpl<>(List.of(entity));
        when(repository.findAll(pageable)).thenReturn(page);
        when(mapper.toResponseDTO(entity)).thenReturn(response);

        Page<ImpactAnalysisResponseDTO> result = service.findAll(pageable);

        assertThat(result.getContent()).containsExactly(response);
    }

    @Test
    void findById_shouldReturnMappedOptionalWhenExists() {
        UUID id = UUID.randomUUID();
        ImpactAnalysis entity = mock(ImpactAnalysis.class);
        ImpactAnalysisResponseDTO response = mock(ImpactAnalysisResponseDTO.class);
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toResponseDTO(entity)).thenReturn(response);

        Optional<ImpactAnalysisResponseDTO> result = service.findById(id);

        assertThat(result).contains(response);
    }

    @Test
    void create_shouldPersistAndMapEntity() {
        ImpactAnalysisRequestDTO request = mock(ImpactAnalysisRequestDTO.class);
        ImpactAnalysis entity = mock(ImpactAnalysis.class);
        ImpactAnalysis saved = mock(ImpactAnalysis.class);
        ImpactAnalysisResponseDTO response = mock(ImpactAnalysisResponseDTO.class);
        when(mapper.toEntity(request)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(saved);
        when(mapper.toResponseDTO(saved)).thenReturn(response);

        ImpactAnalysisResponseDTO result = service.create(request);

        assertThat(result).isSameAs(response);
    }

    @Test
    void update_shouldPersistAndMapWhenEntityExists() {
        UUID id = UUID.randomUUID();
        ImpactAnalysisUpdateDTO update = mock(ImpactAnalysisUpdateDTO.class);
        ImpactAnalysis entity = mock(ImpactAnalysis.class);
        ImpactAnalysis saved = mock(ImpactAnalysis.class);
        ImpactAnalysisResponseDTO response = mock(ImpactAnalysisResponseDTO.class);
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(saved);
        when(mapper.toResponseDTO(saved)).thenReturn(response);

        ImpactAnalysisResponseDTO result = service.update(id, update);

        assertThat(result).isSameAs(response);
        verify(mapper).updateEntityFromDTO(update, entity);
    }

    @Test
    void update_shouldThrowWhenEntityMissing() {
        UUID id = UUID.randomUUID();
        ImpactAnalysisUpdateDTO update = mock(ImpactAnalysisUpdateDTO.class);
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.update(id, update)).isInstanceOf(ImpactAnalysisNotFoundException.class);
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

        assertThatThrownBy(() -> service.delete(id)).isInstanceOf(ImpactAnalysisNotFoundException.class);
    }
}
