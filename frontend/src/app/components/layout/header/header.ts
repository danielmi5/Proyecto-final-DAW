import { Component, computed, inject, signal } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { FeatherIconDirective } from '../../../directives/feather-icon.directive';
import { fromEvent } from 'rxjs';
import { distinctUntilChanged, map } from 'rxjs/operators';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

interface NavItem {
  label: string;
  path: string;
  ariaLabel: string;
}

@Component({
  selector: 'app-header',
  imports: [RouterLink, RouterLinkActive, FeatherIconDirective],
  templateUrl: './header.html',
  host: {
    class: 'header-host'
  }
})
export class Header {
  private readonly document = inject(DOCUMENT);

  readonly isScrolled = signal(false);
  readonly mobileMenuOpen = signal(false);

  readonly mobileMenuAriaExpanded = computed(() => String(this.mobileMenuOpen()));

  readonly navItems: NavItem[] = [
    { label: 'Inicio', path: '/', ariaLabel: 'Ir a Inicio' },
    { label: 'Peticiones', path: '/peticiones', ariaLabel: 'Ir a Peticiones' },
    { label: 'Análisis de Impacto', path: '/analisis-impacto', ariaLabel: 'Ir a Análisis de Impacto' },
    { label: 'Historial', path: '/historial', ariaLabel: 'Ir a Historial' },
    { label: 'Configuración', path: '/configuracion', ariaLabel: 'Ir a Configuración' },
    { label: 'Catálogo', path: '/catalogo', ariaLabel: 'Ir a Catálogo' }
  ];

  constructor() {
    fromEvent(this.document.defaultView!, 'scroll')
      .pipe(
        map(() => this.document.defaultView!.scrollY > 20),
        distinctUntilChanged(),
        takeUntilDestroyed()
      )
      .subscribe((scrolled) => {
        this.isScrolled.set(scrolled);
      });
  }

  toggleMobileMenu(): void {
    this.mobileMenuOpen.update((open) => !open);
  }

  closeMobileMenu(): void {
    this.mobileMenuOpen.set(false);
  }
}