import { Injectable, inject, signal } from '@angular/core';
import { PageParams } from '../dtos/pagination.dto';
import { ImpactAnalysisRequest, ImpactAnalysisResponse, ImpactAnalysisUpdate } from './impact-analysis.dto';
import { ImpactAnalysesApiService } from './impact-analyses-api.service';

@Injectable()
export class ImpactAnalysesStateService {
  private readonly api = inject(ImpactAnalysesApiService);

  readonly items = signal<ImpactAnalysisResponse[]>([]);
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
        this.error.set(err instanceof Error ? err.message : 'Error loading impact analyses');
        this.isLoading.set(false);
      }
    });
  }

  create(body: ImpactAnalysisRequest): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.api.create(body).subscribe({
      next: (created) => {
        this.items.update((list) => [...list, created]);
        this.totalElements.update((n) => n + 1);
        this.isLoading.set(false);
      },
      error: (err: unknown) => {
        this.error.set(err instanceof Error ? err.message : 'Error creating impact analysis');
        this.isLoading.set(false);
      }
    });
  }

  update(id: string, body: ImpactAnalysisUpdate): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.api.update(id, body).subscribe({
      next: (updated) => {
        this.items.update((list) => list.map((a) => (a.id === id ? updated : a)));
        this.isLoading.set(false);
      },
      error: (err: unknown) => {
        this.error.set(err instanceof Error ? err.message : 'Error updating impact analysis');
        this.isLoading.set(false);
      }
    });
  }

  delete(id: string): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.api.delete(id).subscribe({
      next: () => {
        this.items.update((list) => list.filter((a) => a.id !== id));
        this.totalElements.update((n) => n - 1);
        this.isLoading.set(false);
      },
      error: (err: unknown) => {
        this.error.set(err instanceof Error ? err.message : 'Error deleting impact analysis');
        this.isLoading.set(false);
      }
    });
  }
}
