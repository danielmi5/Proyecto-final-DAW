import { Injectable, inject, signal } from '@angular/core';
import { PageParams } from '../dtos/pagination.dto';
import { EstimationHistoryRequest, EstimationHistoryResponse, EstimationHistoryUpdate } from './estimation-history.dto';
import { EstimationHistoriesApiService } from './estimation-histories-api.service';

@Injectable()
export class EstimationHistoriesStateService {
  private readonly api = inject(EstimationHistoriesApiService);

  readonly items = signal<EstimationHistoryResponse[]>([]);
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
        this.error.set(err instanceof Error ? err.message : 'Error loading estimation histories');
        this.isLoading.set(false);
      }
    });
  }

  create(body: EstimationHistoryRequest): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.api.create(body).subscribe({
      next: (created) => {
        this.items.update((list) => [...list, created]);
        this.totalElements.update((n) => n + 1);
        this.isLoading.set(false);
      },
      error: (err: unknown) => {
        this.error.set(err instanceof Error ? err.message : 'Error creating estimation history');
        this.isLoading.set(false);
      }
    });
  }

  update(id: number, body: EstimationHistoryUpdate): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.api.update(id, body).subscribe({
      next: (updated) => {
        this.items.update((list) => list.map((h) => (h.id === id ? updated : h)));
        this.isLoading.set(false);
      },
      error: (err: unknown) => {
        this.error.set(err instanceof Error ? err.message : 'Error updating estimation history');
        this.isLoading.set(false);
      }
    });
  }

  delete(id: number): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.api.delete(id).subscribe({
      next: () => {
        this.items.update((list) => list.filter((h) => h.id !== id));
        this.totalElements.update((n) => n - 1);
        this.isLoading.set(false);
      },
      error: (err: unknown) => {
        this.error.set(err instanceof Error ? err.message : 'Error deleting estimation history');
        this.isLoading.set(false);
      }
    });
  }
}
