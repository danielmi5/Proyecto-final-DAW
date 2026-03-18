# Introducción, objetivos y antecedentes

## Origen de la idea y motivación

El origen de este proyecto nace directamente de mi experiencia real durante el periodo de formación en una empresa del sector tecnológico. Durante mi incorporación, observé de primera mano un cuello de botella crítico y recurrente en la fase de ingeniería de software conocida como "pre-desarrollo": el análisis de requisitos y la planificación técnica.

Cuando una nueva petición entra en el gestor de la empresa (Redmine), el proceso actual exige una intervención manual y ofimática excesiva. El analista debe leer el ticket, deducir la solución, abrir un documento Word en blanco para redactar qué módulos de código se van a modificar, y posteriormente abrir una plantilla Excel para calcular, basándose puramente en su memoria o intuición, cuántas horas costará el desarrollo. 

La motivación de este proyecto es erradicar este desgaste administrativo. Como desarrollador, me frustraba ver a perfiles técnicos altamente cualificados perdiendo hasta un 30% de su jornada copiando y pegando textos o dando formato a tablas, en lugar de estar diseñando arquitecturas. El proyecto busca transformar un proceso manual y subjetivo en un flujo automatizado, rápido y fundamentado en datos.

## Expectativas y objetivos específicos

La expectativa principal de la aplicación es reducir drásticamente el tiempo de análisis (de horas a minutos) y proteger a la empresa de desviaciones presupuestarias por malas estimaciones.

Para alcanzar esta meta, se plantean los siguientes objetivos específicos:
1. **Automatizar la obtención de datos:** Conectar la aplicación con el ecosistema de la empresa (API de Redmine) para eliminar el *Data Entry* manual.
2. **Estandarizar el análisis de impacto:** Sustituir la redacción totalmente manual por un sistema de clasificación guiado donde el analista seleccione los componentes arquitectónicos afectados de un catálogo predefinido.
3. **Eliminar la subjetividad mediante algoritmos:** Desarrollar un motor de estimación que busque en el histórico de la base de datos y sugiera horas de esfuerzo basadas en peticiones pasadas reales, aportando además un porcentaje de fiabilidad matemática.
4. **Automatizar el proceso al documentar:** Procesar todos los datos desde el backend y devolver al usuario los documentos Word y Excel oficiales corporativos, listos para enviar al cliente.
5. **Garantizar la trazabilidad:** Implementar un sistema de versiones inmutables (*Snapshots*) que audite cada cambio realizado en un presupuesto.

## Análisis comparativo de aplicaciones similares

Para validar la oportunidad del proyecto, se ha analizado el software existente en la industria. Actualmente, se ofrecen distintas soluciones que cubren partes fragmentadas del flujo de la aplicación, pero ninguna lo centraliza con el enfoque propuesto:

- **Gestores de Proyectos (Jira, Redmine):** Son gestores de proyectos y manejan tickets (*To Do, In Progress, Done*). Su sistema de estimación es completamente pasivo. Tienen un campo vacío donde el analista escribe "15 horas" basándose en su intuición. No poseen algoritmos de sugerencia basados en el impacto arquitectónico real ni generan los entregables ofimáticos corporativos (Word/Excel) que exige la burocracia del cliente.

- **Herramientas de análisis (IBM Engineering Requirements Management DOORS, Sparx Enterprise Architect):** Permiten una trazabilidad exhaustiva del impacto y los requisitos de software a un nivel ingenieril profundo. Son plataformas masivas, con licencias extremadamente costosas y una curva de aprendizaje inabarcable. Están diseñadas para la industria aeroespacial o la banca core, resultando sobredimensionadas, lentas y poco rentables para trabajo rápido.

- **Generador de documentos (Word / Excel):** No tienen lógica negocio orientada al software. Solo rellenan texto, no pueden calcular la desviación de una estimación técnica ni conectarse a un histórico de componentes de código.