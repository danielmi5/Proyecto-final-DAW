# Guía de Contribución

¡Gracias por tu interés en contribuir! Este proyecto está estructurado siguiendo los estándares de la industria del software corporativo y Open Source.

Tanto si quieres reportar un bug, sugerir una nueva funcionalidad o enviar un *Pull Request* (PR), por favor revisa estas directrices.

## 📑 Índice
1. [Código de Conducta](#1-código-de-conducta)
2. [Flujo de Trabajo (Git Flow)](#2-flujo-de-trabajo-git-flow)
3. [Convención de Commits](#3-convención-de-commits)
4. [Estándares de Código](#4-estándares-de-código)
5. [Proceso para Pull Requests](#5-proceso-para-pull-requests)

---

### 1. Código de Conducta
Se espera que todos los contribuyentes respeten nuestro [Código de Conducta](CODE_OF_CONDUCT.md). Por favor, léelo antes de interactuar con el repositorio.

### 2. Flujo de Trabajo (Git Flow)
Este proyecto utiliza una adaptación de **Git Flow**. Nunca hagas *commits* directamente en la rama `main`. 

Nuestra estructura de ramas es la siguiente:
- `main`: Código en producción. Totalmente estable.
- `develop`: Rama principal de desarrollo. Contiene las últimas integraciones.
- `feature/<nombre>`: Para nuevas funcionalidades (ej. `feature/redmine-api`).
- `bugfix/<nombre>`: Para corrección de errores (ej. `bugfix/jwt-expiration`).
- `hotfix/<nombre>`: Para parches críticos directos a producción.

**Pasos para contribuir:**
1. Haz un *Fork* del repositorio.
2. Crea tu rama desde `develop`: `git checkout -b feature/mi-nueva-funcionalidad`
3. Escribe tu código y haz *commits*.
4. Sube los cambios: `git push origin feature/mi-nueva-funcionalidad`
5. Abre un *Pull Request* hacia la rama `develop` de este repositorio.

### 3. Convención de Commits
Utilizamos [Conventional Commits](https://www.conventionalcommits.org/) para mantener un historial limpio y generar *Changelogs* automáticos.

El formato debe ser: `<tipo>(<alcance opcional>): <descripción>`

**Tipos permitidos:**
- `feat:` Nueva funcionalidad para el usuario.
- `fix:` Corrección de un bug.
- `docs:` Cambios en la documentación (README, etc.).
- `style:` Formateo, punto y coma faltante, etc. (sin cambios lógicos).
- `refactor:` Refactorización de código en producción.
- `test:` Añadir o corregir pruebas (JUnit/Jasmine).
- `chore:` Actualización de dependencias, configuración de GitHub Actions, etc.

*Ejemplo:* `feat(auth): implementar protección CSRF en los endpoints de Spring Boot`

### 4. Estándares de Código
Para garantizar la calidad y mantenibilidad del proyecto, nos adherimos a las siguientes guías de estilo:

**Frontend (Angular):**
- Seguir la [guía de estilos del proyecto](./docs/04-guia-estilos.md).
- Usar `eslint` y `prettier` antes de hacer commit.
- Tipado estricto en TypeScript (evitar el uso de `any`).

**Backend (Spring Boot / Java):**
- Seguir las convenciones de nomenclatura de Java (CamelCase para métodos/variables, PascalCase para clases).
- Mantener los controladores REST limpios (delegar la lógica de negocio a los `@Service`).
- Utilizar *Lombok* para reducir el código *boilerplate* (Getters, Setters, Constructors).

### 5. Proceso para Pull Requests (PR)
1. Asegúrate de que tu código compila y que no hay errores en el *linter* (SonarQube).
2. Si has añadido funcionalidades que cambian la base de datos, incluye el script de migración SQL necesario.
3. Rellena la plantilla del PR en GitHub explicando claramente **qué** problema resuelve y **cómo** lo hace.
4. Espera a que los *tests* de Integración Continua (GitHub Actions) pasen exitosamente (Check verde ✅).
5. Un revisor (el autor original) aprobará y fusionará el código.

¡Gracias por hacer este proyecto mejor!