# Descripción

## Funcionalidades principales

- **Sincronización API y volcado de datos automatizado:** Módulo de comunicación en el backend que consume los *endpoints* de Redmine. Mapea los JSON de respuesta y vuelca en la base de datos local las peticiones pendientes de analizar.
- **Gestor de peticiones propio (Fallback CRUD):** Para garantizar que la herramienta sea autosuficiente en entornos sin Redmine, incluye un gestor interno completo para crear, leer, actualizar y borrar peticiones manualmente.
- **Asistente de Análisis Estructurado:** Formulario dinámico que permite al analista catalogar la petición. En lugar de redactar, el usuario despliega un árbol de componentes (Frontend, BBDD, DAOs, Servicios) y marca mediante etiquetas qué partes del código sufrirán cambios, definiendo su complejidad.
- **Motor algorítmico de estimación:** Lógica de negocio avanzada en Spring Boot. Al solicitar una estimación, cruza las etiquetas marcadas con el histórico de peticiones cerradas en la base de datos. Calcula la media de horas invertidas en el pasado y devuelve un valor sugerido acompañado de un "Índice de Fiabilidad" (basado en el volumen de coincidencias encontradas).
- **Generador de documentos automático:** Módulo del servidor que utiliza librerías de procesamiento binario (Apache POI y Docx4j). Inyecta los textos y cálculos en plantillas corporativas vacías, devolviendo al usuario los archivos `.docx` y `.xlsx` ensamblados.
- **Trazabilidad de revisiones (Snapshots):** Sistema de auditoría transparente. Al modificar una estimación ya generada, la base de datos no sobrescribe el dato (`UPDATE`), sino que crea una versión nueva (v1.0, v1.1), manteniendo el histórico legal de los presupuestos.

## Interfaz de usuario y experiencia de usuario (UI/UX)

La aplicación web, desarrollada como una *Single Page Application* (SPA) en Angular, se ha diseñado priorizando la eficiencia, la limpieza visual y, sobre todo, la accesibilidad universal para entornos de trabajo prolongados.

- **Flujo del usuario:** Para evitar sobrecargar al usuario, el análisis de impacto se divide en pantallas guiadas secuenciales: 
    1. Selección de Petición 
    2. Marcado de Componentes 
    3. Revisión de la Estimación 
    4. Descarga de Documentos. 
El estado se mantiene en memoria sin recargas molestas de página.

- **Accesibilidad Nivel AAA (WCAG 2.1):** La interfaz cumple con el máximo estándar internacional. Se garantiza un contraste estricto (mínimo 7:1) con soporte para Modo Oscuro, previniendo la fatiga visual. Toda la plataforma es operable 100% mediante teclado.
- **Asistencia y prevención de errores:** Los formularios reactivos validan los datos en tiempo real. Si el usuario comete un error, el sistema no solo bloquea el avance, sino que proporciona descripciones precisas (*Aria-describedby*) sugiriendo cómo corregirlo.

## Usuarios objetivo y casos de uso

La herramienta está pensada para varios roles dentro de equipos de producto, consultoría y soporte técnico que necesitan estandarizar la documentación, mejorar la precisión de las estimaciones y mantener trazabilidad. Estos son los usuarios objetivos:

- **Analista técnico:** recibe peticiones de cliente o de producto, necesita convertirlas en documentación técnica y en especificaciones para desarrollo. El proyecto reduce el tiempo de redacción y genera plantillas listas para entregar al cliente.
- **Project Manager:** planifica sprints y elabora presupuestos. Usa las estimaciones empíricas para asignar capacidad, justificar costes ante clientes y reducir desviaciones de presupuesto.
- **Estimador de recursos:** evalúa el impacto de cambios y valida estimaciones para tareas complejas. La app le ofrecería datos históricos para apoyar decisiones sobre asignación de recursos y riesgos técnicos.
- **Product Owner:** necesita documentos claros y trazables para aprobar alcance y costes; la generación automática de documentos facilita la documentación y deja un registro de auditoría por versión.

Su caso de uso principal es "Realizar análisis y estimación":
1. El Analista inicia sesión y ve su *Dashboard* con 3 peticiones nuevas importadas de Redmine.
2. Selecciona la petición *"Añadir campo sexo a la tabla"*.
3. En el formulario, redacta una breve solución técnica y despliega el catálogo de arquitectura, marcando las etiquetas: `BBDD`, `MiembroService` y `Frontend`.
4. Pulsa "Estimar". El servidor busca en el histórico, detecta que tareas similares tardaron 14 horas de media (Fiabilidad: 85%) y le sugiere ese valor.
5. El Analista valida la estimación y pulsa "Generar Entregables".
6. El navegador descarga instantáneamente el *Análisis de Impacto.docx* y la *Planificación de Recursos.xlsx*, listos para enviar al cliente o al Project Manager.