# Base de datos

## Entidades

1. **Usuario (Analista):** Quien realiza el análisis cualitativo y valida las estimaciones.
2. **Peticion:** La tarea o incidencia original.
3. **Analisis_Impacto:** El análisis técnico redactado por el usuario y asociado a una petición específica.
4. **Componente:** El catálogo estructurado de componentes afectados en el análisis.
5. **Componente_Analisis** (Tabla Intermedia): Relación N:M que asocia un análisis concreto con los componentes que le corresponden, permitiendo que el algoritmo busque patrones.
6. **Estimacion:** El resultado cuantitativo (horas de desarrollo, horas de planificación, etc.) derivado de un análisis de impacto.
7. **Historico_Analisis_Impacto**: Tabla de auditoría. Guarda una copia inmutable del análisis cada vez que este sufre una modificación, permitiendo trazar la evolución técnica de la petición.
8. **Historico_Estimacion**: Tabla de auditoría. Guarda una copia inmutable de la estimación cada vez que este sufre una modificación, protegiendo el cálculo frente a re-estimaciones futuras.


## Relaciones Principales

- Un **Usuario** realiza muchos **Analisis_Impacto** (1:N).
- Una **Peticion** tiene un único **Analisis_Impacto** (1:1).
- Un **Analisis_Impacto** tiene una única **Estimación** (1:1).
- Un **Analisis_Impacto** tiene muchos **Historico_Analisis_Impacto** (1:N).
- Un **Analisis_Impacto** se clasifica mediante múltiples **Componente**, y **Componente_Analisis** es utilizada para clasificar múltiples Análisis (Relación N:M, resuelta con la tabla **Componente_Analisis**).
- Una **Estimacion** tiene muchos **Historico_Estimacion** (1:N).


## Tablas

### User

| Campo | Tipo | Descripción | Obligatorio |
|---|---|---|---:|
| id | UUID | Identificador único (clave primaria). Por ejemplo: `DEFAULT gen_random_uuid()` en Postgres. | sí |
| name | VARCHAR(255) | Nombre completo del usuario o analista. | no |
| email | VARCHAR(255) | Correo electrónico del usuario; debe ser único. | sí |
| password_hash | TEXT | Hash seguro de la contraseña utilizado para autenticación. | sí |
| role | VARCHAR(50) | Rol del usuario en el sistema. | sí |
| created_at | TIMESTAMP WITH TIME ZONE | Fecha y hora en que se creó el registro. | sí |


### Request

| Campo | Tipo | Descripción | Obligatorio |
|---|---|---|---:|
| id | UUID | Identificador único (clave primaria). | sí |
| redmine_id | INTEGER | Identificador proporcionado por un tracker externo. | no |
| title | VARCHAR(255) | Título o resumen de la petición. | sí |
| description | TEXT | Descripción detallada de la petición o incidencia. | no |
| status | VARCHAR(20) | Estado de la petición. | sí |
| created_date | DATE | Fecha en que se creó la petición en el sistema. | sí |


### Impact_analysis

| Campo | Tipo | Descripción | Obligatorio |
|---|---|---|---:|
| id | UUID | Identificador único del análisis (clave primaria). | sí |
| request_id | UUID | Referencia a la petición asociada (`request.id`). | sí |
| user_id | UUID | Referencia al usuario/analista autor del análisis (`user.id`). | sí |
| version_number | INTEGER | Número de versión del análisis. | sí |
| maintenance_type | VARCHAR(20) | Tipo de mantenimiento. | sí |
| short_description | TEXT | Descripción abreviada del análisis técnico. | no |
| test_cases | TEXT | Casos de prueba relevantes identificados durante el análisis. | no |
| complexity | VARCHAR(10) | Nivel de complejidad. | sí |
| updated_at | TIMESTAMP WITH TIME ZONE | Fecha y hora de la última actualización del análisis. | sí |


### Component

| Campo | Tipo | Descripción | Obligatorio |
|---|---|---|---:|
| id | UUID | Identificador único del componente (clave primaria). | sí |
| name | VARCHAR(255) | Nombre del componente o unidad de software. | sí |
| category | VARCHAR(50) | Categoría del componente. | sí |
| active | BOOLEAN | Indica si el componente está activo en el catálogo. | sí |
| created_at | TIMESTAMP WITH TIME ZONE | Fecha de alta del componente. | sí |


### Component_analysis

| Campo | Tipo | Descripción | Obligatorio |
|---|---|---|---:|
| analysis_id | UUID | Referencia al análisis asociado (`impact_analysis.id`). Parte de la PK compuesta. | sí |
| component_id | UUID | Referencia al componente asociado (`component.id`). Parte de la PK compuesta. | sí |
| created_by | UUID | Usuario que realizó la asociación (`user.id`). | no |
| created_at | TIMESTAMP WITH TIME ZONE | Fecha y hora en que se creó la asociación. | sí |

Notas: la clave primaria debe ser compuesta `(analysis_id, component_id)`.


### Estimation

| Campo | Tipo | Descripción | Obligatorio |
|---|---|---|---:|
| id | UUID | Identificador único de la estimación (clave primaria). | sí |
| analysis_id | UUID | Referencia al análisis asociado (`impact_analysis.id`). | sí |
| version_number | INTEGER | Número de versión de la estimación. | sí |
| suggested_hours_algo | NUMERIC(10,2) | Horas sugeridas por el algoritmo. | no |
| adjusted_hours_analyst | NUMERIC(10,2) | Horas ajustadas por el analista. | no |
| actual_hours_feedback | NUMERIC(10,2) | Horas reales registradas tras la ejecución. | no |
| updated_at | TIMESTAMP WITH TIME ZONE | Fecha y hora de la última actualización de la estimación. | sí |



### Impact_analysis_history

| Campo | Tipo | Descripción | Obligatorio |
|---|---|---|---:|
| id | SERIAL | Identificador numérico autoincremental del registro de historial (clave primaria). | sí |
| analysis_id | UUID | Referencia al análisis original al que se asocia este snapshot (`impact_analysis.id`). | sí |
| frozen_version | INTEGER | Versión congelada del análisis en el momento del snapshot. | sí |
| frozen_short_description | TEXT | Descripción abreviada congelada del análisis. | no |
| frozen_test_cases | TEXT | Casos de prueba congelados en el snapshot. | no |
| components_snapshot | JSONB | JSON con los nombres exactos de los componentes/etiquetas afectados en ese momento. | no |
| modified_at | TIMESTAMP WITH TIME ZONE | Fecha y hora en que se registró la modificación/historial. | sí |




### Estimation_history

| Campo | Tipo | Descripción | Obligatorio |
|---|---|---|---:|
| id | SERIAL | Identificador numérico autoincremental del registro de historial de estimación (clave primaria). | sí |
| estimation_id | UUID | Referencia a la estimación original a la que corresponde este snapshot (`estimation.id`). | sí |
| frozen_version | INTEGER | Versión congelada de la estimación. | sí |
| frozen_suggested_hours | NUMERIC(10,2) | Horas sugeridas por el algoritmo en el momento del snapshot. | no |
| frozen_adjusted_hours | NUMERIC(10,2) | Horas ajustadas por analista en el momento del snapshot. | no |
| modified_at | TIMESTAMP WITH TIME ZONE | Fecha y hora en que se registró la modificación/historial. | sí |