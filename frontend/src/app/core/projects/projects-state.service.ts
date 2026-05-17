import { Injectable, inject, signal } from '@angular/core';
import { PageParams } from '../dtos/pagination.dto';
import { ProjectRequest, ProjectResponse, ProjectUpdate } from './project.dto';
import { ProjectsApiService } from './projects-api.service';

@Injectable()
export class ProjectsStateService {
  private readonly api = inject(ProjectsApiService);

  readonly items = signal<ProjectResponse[]>([]);
  readonly totalElements = signal<number>(0);
  readonly isLoading = signal<boolean>(false);
  readonly error = signal<string | null>(null);

  load(params?: PageParams): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.api.getAll(params).subscribe({
      next: (page) => {
        this.items.set(page.content);
        this.totalElements.set(page.totalElements);
        this.isLoading.set(false);
      },
      error: (err: unknown) => {
        this.error.set(err instanceof Error ? err.message : 'Error loading projects');
        this.isLoading.set(false);
      }
    });
  }

  create(body: ProjectRequest): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.api.create(body).subscribe({
      next: (created) => {
        this.items.update((list) => [...list, created]);
        this.totalElements.update((n) => n + 1);
        this.isLoading.set(false);
      },
      error: (err: unknown) => {
        this.error.set(err instanceof Error ? err.message : 'Error creating project');
        this.isLoading.set(false);
      }
    });
  }

  update(id: string, body: ProjectUpdate): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.api.update(id, body).subscribe({
      next: (updated) => {
        this.items.update((list) => list.map((p) => (p.id === id ? updated : p)));
        this.isLoading.set(false);
      },
      error: (err: unknown) => {
        this.error.set(err instanceof Error ? err.message : 'Error updating project');
        this.isLoading.set(false);
      }
    });
  }

  delete(id: string): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.api.delete(id).subscribe({
      next: () => {
        this.items.update((list) => list.filter((p) => p.id !== id));
        this.totalElements.update((n) => n - 1);
        this.isLoading.set(false);
      },
      error: (err: unknown) => {
        this.error.set(err instanceof Error ? err.message : 'Error deleting project');
        this.isLoading.set(false);
      }
    });
  }
}
