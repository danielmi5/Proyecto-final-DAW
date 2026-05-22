import { Injectable, inject, signal } from '@angular/core';
import { PageParams } from '../dtos/pagination.dto';
import { RequestRequest, RequestResponse, RequestUpdate } from './request.dto';
import { RequestsApiService } from './requests-api.service';

@Injectable()
export class RequestsStateService {
  private readonly api = inject(RequestsApiService);

  readonly items = signal<RequestResponse[]>([]);
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
        this.error.set(err instanceof Error ? err.message : 'Error loading requests');
        this.isLoading.set(false);
      }
    });
  }

  create(body: RequestRequest): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.api.create(body).subscribe({
      next: (created) => {
        this.items.update((list) => [...list, created]);
        this.totalElements.update((n) => n + 1);
        this.isLoading.set(false);
      },
      error: (err: unknown) => {
        this.error.set(err instanceof Error ? err.message : 'Error creating request');
        this.isLoading.set(false);
      }
    });
  }

  update(id: string, body: RequestUpdate): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.api.update(id, body).subscribe({
      next: (updated) => {
        this.items.update((list) => list.map((r) => (r.id === id ? updated : r)));
        this.isLoading.set(false);
      },
      error: (err: unknown) => {
        this.error.set(err instanceof Error ? err.message : 'Error updating request');
        this.isLoading.set(false);
      }
    });
  }

  delete(id: string): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.api.delete(id).subscribe({
      next: () => {
        this.items.update((list) => list.filter((r) => r.id !== id));
        this.totalElements.update((n) => n - 1);
        this.isLoading.set(false);
      },
      error: (err: unknown) => {
        this.error.set(err instanceof Error ? err.message : 'Error deleting request');
        this.isLoading.set(false);
      }
    });
  }
}
