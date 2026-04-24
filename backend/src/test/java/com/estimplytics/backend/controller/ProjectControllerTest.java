package com.estimplytics.backend.controller;

import com.estimplytics.backend.dto.ProjectRequestDTO;
import com.estimplytics.backend.dto.ProjectResponseDTO;
import com.estimplytics.backend.dto.ProjectUpdateDTO;
import com.estimplytics.backend.service.IProjectService;
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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

    @Mock
    private IProjectService service;

    @InjectMocks
    private ProjectController controller;

    @Test
    void getAll_shouldReturnOkWithPage() {
        Pageable pageable = PageRequest.of(0, 10);
        ProjectResponseDTO dto = mock(ProjectResponseDTO.class);
        Page<ProjectResponseDTO> page = new PageImpl<>(List.of(dto));
        when(service.findAll(pageable)).thenReturn(page);

        ResponseEntity<Page<ProjectResponseDTO>> response = controller.getAll(pageable);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isSameAs(page);
    }

    @Test
    void getById_shouldReturnOkWhenExists() {
        UUID id = UUID.randomUUID();
        ProjectResponseDTO dto = mock(ProjectResponseDTO.class);
        when(service.findById(id)).thenReturn(Optional.of(dto));

        ResponseEntity<ProjectResponseDTO> response = controller.getById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isSameAs(dto);
    }

    @Test
    void getById_shouldReturnNotFoundWhenMissing() {
        UUID id = UUID.randomUUID();
        when(service.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<ProjectResponseDTO> response = controller.getById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void create_shouldReturnCreated() {
        ProjectRequestDTO request = mock(ProjectRequestDTO.class);
        ProjectResponseDTO dto = mock(ProjectResponseDTO.class);
        when(service.create(request)).thenReturn(dto);

        ResponseEntity<ProjectResponseDTO> response = controller.create(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isSameAs(dto);
    }

    @Test
    void update_shouldReturnOk() {
        UUID id = UUID.randomUUID();
        ProjectUpdateDTO request = mock(ProjectUpdateDTO.class);
        ProjectResponseDTO dto = mock(ProjectResponseDTO.class);
        when(service.update(id, request)).thenReturn(dto);

        ResponseEntity<ProjectResponseDTO> response = controller.update(id, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isSameAs(dto);
    }

    @Test
    void delete_shouldReturnNoContent() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = controller.delete(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(service).delete(id);
    }
}
