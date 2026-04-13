# Frontend Angular

Proyecto Angular 21 con componentes standalone, SCSS y TypeScript en modo estricto.

## Requisitos

- Node.js 20 o superior
- npm 11 o superior

## Instalación

```bash
npm install
```

## Desarrollo

```bash
npm run dev
```

Abre `http://localhost:4200/` cuando el servidor termine de compilar.

## Build

```bash
npm run build:prod
```

## Análisis de bundle

```bash
npm run build:stats
npm run analyze:bundle
```

## TypeScript strict mode

Comprueba el tipado con:

```bash
npm run typecheck
```

Para validar que strict está activo, introduce un error intencionado en cualquier archivo `.ts`, por ejemplo:

```ts
const value: string = null;
```

La compilación debe fallar con un error de tipos.

## Scripts principales

- `npm run dev`: servidor de desarrollo
- `npm run build`: build estándar
- `npm run build:prod`: build de producción
- `npm run build:stats`: build con estadísticas
- `npm run analyze:bundle`: análisis del bundle
- `npm run typecheck`: comprobación estricta de TypeScript
