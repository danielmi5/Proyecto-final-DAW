import { Directive, ElementRef, Input, OnChanges, Renderer2, SimpleChanges, OnDestroy } from '@angular/core';

import * as feather from 'feather-icons';
@Directive({
  selector: '[feather]',
  standalone: true
})
export class FeatherIconDirective implements OnChanges, OnDestroy {
  @Input('feather') iconName!: string;
  @Input('ancho') width?: string | number;
  @Input('alto') height?: string | number;
  @Input('trazo') stroke?: string;
  @Input('variant') variant?: 'header' | 'submenu' | 'botones';

  constructor(private element: ElementRef<HTMLElement>, private renderer: Renderer2) {}

  private insertedNode: HTMLElement | null = null;

  ngOnChanges(_: SimpleChanges): void {
    this.applyVariant();
    this.renderIcon();
  }

  private applyVariant(): void {
    const variants = ['header', 'submenu', 'botones'];
    variants.forEach((variant) => this.renderer.removeClass(this.element.nativeElement, `feather--${variant}`));

    if (!this.variant) return;

    this.renderer.addClass(this.element.nativeElement, `feather--${this.variant}`);

    if (!this.width && !this.height) {
      switch (this.variant) {
        case 'header':
          this.width = this.width || '36px';
          this.height = this.height || '36px';
          break;
        case 'submenu':
          this.width = this.width || '24px';
          this.height = this.height || '24px';
          break;
        case 'botones':
          this.width = this.width || '20px';
          this.height = this.height || '20px';
          break;
      }
    }
  }

  private renderIcon(): void {
    if (!this.iconName) {
      if (this.insertedNode) {
        this.renderer.removeChild(this.element.nativeElement, this.insertedNode);
        this.insertedNode = null;
      }
      return;
    }

    const iconMap = (feather as any).icons || {};
    const icon = iconMap[this.iconName];
    if (!icon) {
      if (this.insertedNode) {
        this.renderer.removeChild(this.element.nativeElement, this.insertedNode);
        this.insertedNode = null;
      }
      return;
    }

    const attributes: any = {};
    if (this.width) attributes.width = this.width;
    if (this.height) attributes.height = this.height;
    if (this.stroke) attributes.stroke = this.stroke;

    const svg = typeof icon.toSvg === 'function'
      ? icon.toSvg(attributes)
      : (feather as any).toSvg
        ? (feather as any).toSvg(this.iconName, attributes)
        : '';

    if (!svg) {
      if (this.insertedNode) {
        this.renderer.removeChild(this.element.nativeElement, this.insertedNode);
        this.insertedNode = null;
      }
      return;
    }

    if (this.insertedNode) {
      this.renderer.setProperty(this.insertedNode, 'innerHTML', svg);
    } else {
      const wrapper = this.renderer.createElement('span');
      this.renderer.addClass(wrapper, 'feather-icon');
      this.renderer.setProperty(wrapper, 'innerHTML', svg);
      this.renderer.appendChild(this.element.nativeElement, wrapper);
      this.insertedNode = wrapper as HTMLElement;
    }
  }

  ngOnDestroy(): void {
    if (this.insertedNode) {
      try {
        this.renderer.removeChild(this.element.nativeElement, this.insertedNode);
      } catch {}
      this.insertedNode = null;
    }
  }
}
