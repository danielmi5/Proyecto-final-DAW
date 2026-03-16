# Guía de commits

Esta guía explica la convención de commits recomendada para este proyecto y cómo validarlos automáticamente.

## Resumen

Usamos **Conventional Commits**. Son mensajes estructurados y legibles que facilitan la generación de changelogs y el versionado semántico.

Formato principal:

```
<tipo>(<alcance opcional>): <resumen corto>

<cuerpo opcional>

BREAKING CHANGE: <descripción de cambio incompatible> (si existe)
```

Línea de resumen: máxima responsabilidad y 50 caracteres aproximadamente. Deja una línea en blanco antes del cuerpo si hay más detalles.

## Tipos comunes

- `feat`: Nueva funcionalidad.
- `fix`: Corrección de bug.
- `docs`: Cambios en documentación.
- `style`: Formato, espacios, sin cambios en la lógica.
- `refactor`: Refactorización sin añadir ni corregir comportamiento.
- `perf`: Cambios que mejoran rendimiento.
- `test`: Añadir o corregir tests.
- `chore`: Tareas de mantenimiento (scripts, build tools).
- `build`: Cambios que afectan el sistema de build o dependencias.
- `ci/cd`: Cambios en CI/CD.

## Alcance (scope)

El `alcance` es opcional y sirve para indicar el área afectada, por ejemplo `api`, `auth`, `ui`, `db`.

Ejemplo: `fix(api): corregir validación de parámetros`

## Ejemplos completos

- `feat(auth): añadido endpoint de registro de usuarios`

  Implementa la validación del email y contraseña, además de las pruebas asociadas.

- `fix(ui): corregida alineación en pantalla de login`

  Se corrige la clase CSS y se añade test visual básico.

- `chore(deps): actualizado lodash a 4.17.21`

## BREAKING CHANGES

Si un cambio rompe compatibilidad, usa la sección `BREAKING CHANGE:` en el cuerpo del commit. También puedes usar `!` después del tipo: `feat!: cambio incompatible`.

## Plantilla de commit

Puedes usar este texto como plantilla para tu commit:

```
<tipo>(<alcance>): <resumen corto>

Descripción detallada del cambio. Incluir referencias a issues (#123), notas sobre testing y cualquier otra información útil.

BREAKING CHANGE: descripción si aplica
```

## Buenas prácticas

- Haz commits pequeños y con un único propósito.
- Escribe el resumen usando participio: por ejemplo `fix: corregido fallo en X` o `feat: añadido soporte para X`.
- Incluye referencias a issues y tests relacionados en el cuerpo.
- Usa ramas por feature o chore y abre PRs pequeños.