# Conclusiones

## Evaluación crítica respecto a los objetivos iniciales

El proyecto cuenta con una base técnica sólida que cubre gran parte de los objetivos planteados a nivel de lógica de negocio. El backend dispone de las entidades, servicios y controladores principales siguiendo el patrón MVC, con una arquitectura separada por capas y aplicando principios SOLID. Además, incluye cobertura de tests y un sistema de auditoría que almacena versiones inmutables en PostgreSQL.

La sincronización con la API de Redmine funciona correctamente y el algoritmo de estimación procesa los datos históricos de forma adecuada para generar cálculos estadísticos.

Sin embargo, la aplicación no alcanza completamente el objetivo funcional previsto debido a la falta de desarrollo de varias partes del frontend. Las funcionalidades más importantes de la interfaz, como el análisis de impacto, la visualización de estimaciones y la generación automática de documentos, no llegaron a implementarse. Aunque se preparó la arquitectura visual, el sistema de estilos y las pantallas de autenticación, el flujo principal que seguirá el usuario todavía no está desarrollado.

## Grado de cumplimiento del alcance propuesto

El alcance técnico del proyecto se ha cubierto de forma parcial.

Por un lado, el backend se encuentra en un estado avanzado de desarrollo. El sistema CRUD, la autenticación mediante tokens, el motor de cálculo estadístico y la integración con la API externa de Redmine funcionan. Además, el diseño de la base de datos permite almacenar toda la información necesaria para futuras ampliaciones.

Por otro lado, la interfaz de usuario se encuentra incompleta. Aunque los componentes base siguen un sistema de diseño coherente y se tuvieron en cuenta criterios de accesibilidad, todavía faltan las pantallas principales del flujo de trabajo de los analistas.

## Mejoras futuras propuestas

La prioridad principal es completar las vistas pendientes en Angular para conectar todo el flujo de análisis de impacto y estimación. Además, será necesario integrar librerías de generación de documentos para exportar automáticamente los resultados de las estimaciones. Todo con el objetivo de llegar a la defensa con el MVP terminado.

Como posibles mejoras futuras:

- Mejorar el diseño visual de la aplicación, priorizando una interfaz más atractiva sin perder funcionalidad.
- Añadir pruebas de interfaz que simulen el comportamiento de los usuarios en el navegador.
- Implementar subida automática de estimaciones y documentos a Redmine.
- Incorporar plantillas personalizadas para la generación de documentos.
- Implementar compatibilidad con otros gestores de incidencias, como Jira.
- Mejorar progresivamente el nivel de accesibilidad siguiendo las recomendaciones WCAG.
- Incorporar mecanismos de caché para reducir llamadas repetidas a APIs externas.
- Incorporar monitorización y trazabilidad mediante logs centralizados y métricas de rendimiento.
- Añadir notificaciones automáticas ante cambios relevantes en estimaciones o incidencias.


## Lecciones aprendidas

Durante el desarrollo del proyecto aprendí que la planificación inicial del proyecto no se ajusta al proyecto final. Al principio subestimé el tiempo necesario para la preparación del proyecto, ya que no le di tan importancia al principio suponiendo que ya lo haría más adelante. Además, comprobé que gran parte del tiempo estuve resolviendo problemas de configuración, compatibilidad y organización del propio proyecto. 

También entendí la importancia de mantener una arquitectura bien separada desde el inicio. A medida que el proyecto fue creciendo, trabajar con capas diferenciadas y servicios independientes me permitió localizar errores más rápido y modificar partes concretas del sistema sin afectar al resto de la aplicación. Otro aspecto que aprendí fue la diferencia entre desarrollar la lógica de negocio y completar una aplicación funcional de cara al usuario. Aunque el backend avanzó correctamente, el desarrollo del frontend implicaba UI/UX, lógica, etc.

Además, aprendí a apoyarme más en la documentación oficial de las tecnologías utilizadas. En muchas ocasiones resultó más útil consultar directamente la documentación de Angular, Spring o PostgreSQL que depender de tutoriales externos, ya que permitía entender mejor el funcionamiento real y estaba mucho más actualizado. El proyecto me ayudó a entender mejor la importancia de priorizar tareas y seguir el plan establecido para no perjudicar el desarrollo.

En algunos momentos intenté abarcar más de lo que tenía pensado, lo que perjudicó aún más el tiempo que tenía disponible, lo que terminó afectando a partes importantes de la interfaz de usuario. Con el paso de las semanas fui acumulando tareas pendientes y dejando partes importantes para más adelante, lo que acabó afectando tanto a la planificación como al resultado final del proyecto.

Y, sobre todo, aprendí una lección a nivel personal. En varios momentos del proyecto volví a caer en hábitos de organización y gestión del tiempo que pensaba haber dejado atrás. Lo que inicialmente debía ser una oportunidad para profundizar en el desarrollo backend y dedicar más tiempo a aprender y mejorar en frontend, así como utilizar herramientas nuevas, terminó convirtiéndose en una carrera constante contra el tiempo.
Esta experiencia me hizo darme cuenta de que tengo mucho que mejorar en términos de constancia, organización y planificación. Aun así, considero que el desarrollo del proyecto me permitió ganar experiencia práctica tanto a nivel técnico como organizativo. Aunque el resultado final podría haber sido mucho mejor con una gestión diferente del tiempo y del trabajo, el proceso me dejó en claro, aspectos que necesito mejorar de cara al futuro.