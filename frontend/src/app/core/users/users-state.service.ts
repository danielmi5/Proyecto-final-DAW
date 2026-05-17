import { Injectable, inject, signal } from '@angular/core';
import { PageParams } from '../dtos/pagination.dto';
import { UserRequest, UserResponse, UserUpdate } from './user.dto';
import { UsersApiService } from './users-api.service';

@Injectable()
export class UsersStateService {
  private readonly api = inject(UsersApiService);

  readonly items = signal<UserResponse[]>([]);
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
        this.error.set(err instanceof Error ? err.message : 'Error loading users');
        this.isLoading.set(false);
      }
    });
  }

  create(body: UserRequest): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.api.create(body).subscribe({
      next: (created) => {
        this.items.update((list) => [...list, created]);
        this.totalElements.update((n) => n + 1);
        this.isLoading.set(false);
      },
      error: (err: unknown) => {
        this.error.set(err instanceof Error ? err.message : 'Error creating user');
        this.isLoading.set(false);
      }
    });
  }

  update(id: string, body: UserUpdate): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.api.update(id, body).subscribe({
      next: (updated) => {
        this.items.update((list) => list.map((u) => (u.id === id ? updated : u)));
        this.isLoading.set(false);
      },
      error: (err: unknown) => {
        this.error.set(err instanceof Error ? err.message : 'Error updating user');
        this.isLoading.set(false);
      }
    });
  }

  delete(id: string): void {
    this.isLoading.set(true);
    this.error.set(null);
    this.api.delete(id).subscribe({
      next: () => {
        this.items.update((list) => list.filter((u) => u.id !== id));
        this.totalElements.update((n) => n - 1);
        this.isLoading.set(false);
      },
      error: (err: unknown) => {
        this.error.set(err instanceof Error ? err.message : 'Error deleting user');
        this.isLoading.set(false);
      }
    });
  }
}
