import { ErrorHandler, Injectable, inject } from '@angular/core';
import { AppStateService } from './app-state.service';

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {
  private readonly appState = inject(AppStateService);

  handleError(error: unknown): void {
    const message = error instanceof Error ? error.message : 'Error inesperado';

    if (message.includes('Loading chunk') || message.includes('Failed to fetch dynamically imported module')) {
      this.appState.setError('No se pudo cargar un módulo. Recarga la página.');
      console.error('[ChunkLoadError]', error);
      return;
    }

    console.error('[GlobalErrorHandler]', error);
    this.appState.setError(message);
  }
}
