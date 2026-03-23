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