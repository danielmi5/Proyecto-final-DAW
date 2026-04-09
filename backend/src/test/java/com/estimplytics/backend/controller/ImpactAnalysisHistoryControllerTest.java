package com.estimplytics.backend.controller;

import com.estimplytics.backend.dto.ImpactAnalysisHistoryRequestDTO;
import com.estimplytics.backend.dto.ImpactAnalysisHistoryResponseDTO;
import com.estimplytics.backend.dto.ImpactAnalysisHistoryUpdateDTO;
import com.estimplytics.backend.service.IImpactAnalysisHistoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImpactAnalysisHistoryControllerTest {

    @Mock
    private IImpactAnalysisHistoryService service;

    @InjectMocks
    private ImpactAnalysisHistoryController controller;

    @Test
    void getAll_shouldReturnOkWithPage() {
        Pageable pageable = PageRequest.of(0, 10);
        ImpactAnalysisHistoryResponseDTO dto = mock(ImpactAnalysisHistoryResponseDTO.class);
        Page<ImpactAnalysisHistoryResponseDTO> page = new PageImpl<>(List.of(dto));
        when(service.findAll(pageable)).thenReturn(page);

        ResponseEntity<Page<ImpactAnalysisHistoryResponseDTO>> response = controller.getAll(pageable);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isSameAs(page);
    }

    @Test
    void getById_shouldReturnOkWhenExists() {
        Long id = 1L;
        ImpactAnalysisHistoryResponseDTO dto = mock(ImpactAnalysisHistoryResponseDTO.class);
        when(service.findById(id)).thenReturn(Optional.of(dto));

        ResponseEntity<ImpactAnalysisHistoryResponseDTO> response = controller.getById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isSameAs(dto);
    }

    @Test
    void getById_shouldReturnNotFoundWhenMissing() {
        Long id = 1L;
        when(service.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<ImpactAnalysisHistoryResponseDTO> response = controller.getById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void create_shouldReturnCreated() {
        ImpactAnalysisHistoryRequestDTO request = mock(ImpactAnalysisHistoryRequestDTO.class);
        ImpactAnalysisHistoryResponseDTO dto = mock(ImpactAnalysisHistoryResponseDTO.class);
        when(service.create(request)).thenReturn(dto);

        ResponseEntity<ImpactAnalysisHistoryResponseDTO> response = controller.create(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isSameAs(dto);
    }

    @Test
    void update_shouldReturnOk() {
        Long id = 1L;
        ImpactAnalysisHistoryUpdateDTO request = mock(ImpactAnalysisHistoryUpdateDTO.class);
        ImpactAnalysisHistoryResponseDTO dto = mock(ImpactAnalysisHistoryResponseDTO.class);
        when(service.update(id, request)).thenReturn(dto);

        ResponseEntity<ImpactAnalysisHistoryResponseDTO> response = controller.update(id, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isSameAs(dto);
    }

    @Test
    void delete_shouldReturnNoContent() {
        Long id = 1L;

        ResponseEntity<Void> response = controller.delete(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(service).delete(id);
    }
}
