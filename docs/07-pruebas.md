# Pruebas

**Metodología de pruebas empleada**
El proyecto sigue un enfoque pragmático. Durante las primeras fases de desarrollo se priorizaron las pruebas manuales para iterar rápido sobre la interfaz y los modelos de datos. Una vez consolidada la lógica de negocio, se escribieron pruebas automatizadas para blindar los flujos de trabajo críticos, implementando los test después de escribir el código funcional.

**Tipos de pruebas realizadas**
- **Pruebas unitarias:** En el backend se utilizan JUnit 5 y Mockito para testear servicios y controladores de forma aislada. Se simulan los repositorios para verificar que el algoritmo de estimación y los mapeadores devuelvan resultados precisos. En el frontend se emplea Vitest junto con jsdom para montar y validar la instanciación de los componentes de interfaz.
- **Pruebas de integración:** Se utiliza MockMvc dentro del contexto de Spring Boot para lanzar peticiones HTTP simuladas. Esto permite probar flujos completos, como la protección de rutas mediante el filtro de seguridad JWT o el rechazo de credenciales inválidas.

**Cobertura de código alcanzada**
La cobertura en el backend alcanza un nivel superior al 80% en las capas de servicios y controladores. Se han cubierto todas las operaciones CRUD y los cálculos de algoritmos. En el frontend, la cobertura se limita a pruebas estructurales para asegurar que las vistas y los componentes reactivos se construyen sin errores de dependencias.

**Resultados y estadísticas de las pruebas**
La ejecución de la suite de pruebas del backend toma escasos segundos, ya que se apoya en una base de datos en memoria (H2) que elimina tiempos de espera por escritura en disco. En la actualidad, la totalidad de los test se ejecuta con éxito y sirve como sistema de prevención de regresiones antes de fusionar ramas de desarrollo.
