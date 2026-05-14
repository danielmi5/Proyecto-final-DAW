# Desarrollo

## Secuencia de desarrollo seguida

El proceso de desarrollo se ha organizado en bloques de trabajo iterativos. La planificación inicial comenzó definiendo la estructura del proyecto y el flujo de los usuarios. En paralelo, elaboré el modelo Entidad-Relación para tener clara la estructura de la base de datos antes de escribir código.

Una vez cerrada la planificación, empecé con el backend. Levanté el proyecto con Spring Boot y conecté la base de datos PostgreSQL. El primer paso fue implementar la seguridad mediante tokens JWT para proteger los endpoints. Después, programé los controladores y servicios CRUD para gestionar entidades principales como proyectos y usuarios. La parte final del backend consistió en desarrollar la integración con la API de Redmine para sincronizar tareas y programar el algoritmo encargado de calcular las estimaciones.

Con la API documentada y funcionando, pasé al desarrollo del frontend en Angular. Configuré la arquitectura de estilos siguiendo la metodología ITCSS mediante SASS. Primero programé los componentes base (inputs, botones, tarjetas) y después monté las vistas completas y los formularios. También configuré los interceptores HTTP para enviar el token en cada petición al backend.

La etapa final se centró en la configuración de la infraestructura. Preparé los archivos Docker y Docker Compose para empaquetar los contenedores. Además, configuré las plantillas en GitHub, escribí las pruebas unitarias y terminé de redactar esta documentación.

## Dificultades encontradas y soluciones

**Gestión de formularios reactivos complejos**
Al desarrollar los formularios de registro y login en Angular, necesité aplicar validaciones cruzadas (como comprobar que las contraseñas coinciden) y manejar estados de error visuales de forma dinámica. Esto provocaba que el HTML de las vistas quedara ilegible. La solución fue encapsular la lógica implementando la interfaz `ControlValueAccessor` en los componentes de input personalizados. De este modo, los componentes gestionan sus propios mensajes de error y estados de validación, manteniendo las vistas principales limpias.

**Inconsistencia en los datos de la API de Redmine**
Al consumir peticiones desde Redmine, me encontré con que los datos de respuesta a menudo traían campos nulos o formatos de fecha impredecibles, lo que provocaba errores al guardar en la base de datos. Para solucionarlo, desarrollé un mapeador específico (`RedmineIssueMapper`) que aplica validaciones estrictas y formatea las fechas de forma segura. Si falta un campo obligatorio, el sistema lanza excepciones controladas (`RedmineMandatoryFieldException`) que el controlador captura para evitar fallos en el servidor.

**Cálculo automático de estimaciones**
El requerimiento de sugerir estimaciones basadas en tareas pasadas generaba valores poco fiables si existían datos atípicos (por ejemplo, tareas resueltas en 5 horas frente a otras idénticas resueltas en 50). Para mitigar este problema, añadí una capa de cálculo estadístico en el servicio de algoritmos. El sistema ahora calcula la desviación estándar de las horas registradas; si la volatilidad es superior al 30%, el algoritmo penaliza el porcentaje de fiabilidad que se muestra en el frontend.

## Decisiones técnicas clave y justificación

**Gestión de estado reactivo mediante Signals**
En el desarrollo del frontend he optado por utilizar la API de Signals de Angular (`signal`, `computed`, `input()`) para el manejo del estado local de los componentes. Esta elección permite abandonar las suscripciones manuales de RxJS para el estado de interfaz síncrono, reduciendo el consumo de memoria y permitiendo activar la estrategia de detección de cambios `OnPush`. Esto optimiza el rendimiento de renderizado ya que Angular solo actualiza las partes de la vista que realmente han cambiado.

**Control centralizado de excepciones en backend**
Diseñé una arquitectura donde los controladores REST no manejan bloques `try-catch`. En su lugar, delegan esta responsabilidad a una clase anotada con `@RestControllerAdvice` (`ApiExceptionHandler`). Cuando un servicio lanza una excepción de negocio (como `RedmineIntegrationException` o `UserNotFoundException`), este componente global la intercepta y construye una respuesta HTTP con un formato estandarizado (`ApiErrorDTO`), garantizando contratos de error uniformes para el consumo del frontend.

**Arquitectura de estilos ITCSS con SASS**
En lugar de depender de librerías externas de estilos, decidí escribir el CSS desde cero utilizando la metodología ITCSS (Inverted Triangle CSS). Esto proporciona un control absoluto sobre el diseño, facilita la implementación de temas visuales mediante variables CSS y asegura que solo se carguen los estilos estrictamente necesarios para el proyecto.

**Almacenamiento de historiales en formato JSONB**
Para auditar los cambios en los análisis y las estimaciones, necesitaba guardar un registro inmutable en el tiempo. En vez de crear tablas relacionales adicionales con relaciones complejas, opté por guardar un volcado de los datos utilizando el tipo `jsonb` de PostgreSQL. Esto permite mantener capturas de estado exactas de forma eficiente sin complicar el esquema principal de la base de datos.

## Herramientas de control de versiones utilizadas

El control de versiones del proyecto se gestiona con Git, alojado en un repositorio de GitHub. 

He seguido un flujo de trabajo basado en ramas semánticas para separar el desarrollo de nuevas características, la corrección de errores y las refactorizaciones. Los commits se han realizado de forma atómica para facilitar el seguimiento de los cambios introducidos.

Para estandarizar el trabajo, configuré plantillas específicas dentro de la carpeta `.github/ISSUE_TEMPLATE` y un archivo `pull_request_template.md`. Esto asegura que antes de integrar código en la rama principal, el desarrollador deba confirmar que el código pasa las validaciones del linter, no rompe pruebas existentes y está correctamente documentado.

## Fragmentos de código relevantes

A continuación se explican algunas de las implementaciones más críticas del código fuente.

**Renderizado dinámico de iconos mediante directivas**
Esta directiva permite utilizar la librería Feather Icons inyectando el código SVG directamente en el árbol DOM. Utiliza la clase `Renderer2` de Angular en lugar de manipular el elemento nativo directamente mediante la propiedad `innerHTML`. Esto es una práctica de seguridad fundamental para prevenir vulnerabilidades XSS (Cross-Site Scripting). Además, gestiona la limpieza de nodos en el método `ngOnDestroy` para evitar fugas de memoria cuando el componente se destruye.

```typescript
@Directive({
  selector: '[feather]',
  standalone: true
})
export class FeatherIconDirective implements OnChanges, OnDestroy {
  private insertedNode: HTMLElement | null = null;

  constructor(private element: ElementRef<HTMLElement>, private renderer: Renderer2) {}

  private renderIcon(): void {
    // ... obtención del código SVG según el nombre del icono
    
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
      this.renderer.removeChild(this.element.nativeElement, this.insertedNode);
      this.insertedNode = null;
    }
  }
}
```

**Cálculo de penalización por volatilidad**
Este método pertenece al servicio de estimaciones del backend. Demuestra cómo se aborda la incertidumbre en los registros de la base de datos. No se limita a calcular una media aritmética, sino que utiliza la desviación estándar para entender cómo de dispersos están los valores. Si la diferencia entre los valores históricos es muy grande, se resta fiabilidad a la estimación propuesta.

```java
private Integer calculateFiability(List<Integer> historicalHours) {
    Integer records = historicalHours.size();
    Integer fiabilityBase = Math.min(BASE_FIABILITY_PERCENTAGE + records * FIABILITY_INCREMENT_PER_RECORD, MAXIMUM_FIABILITY_PERCENTAGE);

    Double average = historicalHours.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
    if (average <= 0.0) return fiabilityBase;

    Double standardDeviation = calculateStandardDeviation(historicalHours, average);
    Double volatility = standardDeviation / average;

    if (volatility > VOLATILITY_RANGE) {
        return Math.max(fiabilityBase - HIGH_VOLATILITY_PENALIZATION, 0);
    }
    return fiabilityBase;
}
```

**Guardado de datos históricos en formato JSONB**
Esta es la entidad encargada de guardar el historial de un análisis de impacto. El uso de la anotación `@JdbcTypeCode(SqlTypes.JSON)` de Hibernate permite mapear un `Map` de Java directamente a una columna JSON nativa de PostgreSQL. Esta técnica permite congelar datos de entidades asociadas, asegurando que si un componente cambia de nombre en el futuro, el registro histórico mantenga el valor original de forma permanente.

```java
@Entity
@Table(name = "impact_analysis_histories")
public class ImpactAnalysisHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "snapshot_data", nullable = false, columnDefinition = "jsonb")
    private Map<String, Object> snapshotData;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "components_snapshot", nullable = false, columnDefinition = "jsonb")
    private Map<String, Object> componentsSnapshot;
}
```

**Filtro de autenticación JWT sin estado**
Este componente intercepta las peticiones HTTP del backend para verificar la identidad del usuario. Extrae el token de la cabecera, verifica que no esté revocado mediante el servicio de listas negras y comprueba su firma criptográfica. Destaca el diseño sin estado: el servidor no guarda variables de sesión en memoria, sino que construye el contexto de seguridad al vuelo basándose exclusivamente en la información del token, lo que mejora la escalabilidad de la API.

```java
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
    }

    final String jwt = authHeader.substring(7);
    if (tokenBlacklistService.isBlacklisted(jwt)) {
        filterChain.doFilter(request, response);
        return;
    }

    final String userEmail = jwtService.extractUsername(jwt);
    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
        if (jwtService.isTokenValid(jwt, userDetails)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }
    filterChain.doFilter(request, response);
}
```

**Integración de inputs personalizados con ReactiveForms**
Esta implementación en el frontend permite que los componentes de diseño propio actúen como inputs estándar para Angular. Al inyectar el proveedor `NG_VALUE_ACCESSOR`, el componente `FormInput` se puede enlazar con la directiva `formControlName`. Esto delega la sincronización de valores, eventos de cambio y estados al framework, evitando propagar eventos manualmente por toda la jerarquía de componentes.

```typescript
@Component({
  selector: 'app-form-input',
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => FormInput),
      multi: true
    }
  ]
})
export class FormInput implements ControlValueAccessor {
  value: string = '';
  onChange: any = () => {};
  onTouched: any = () => {};

  writeValue(value: any): void {
    this.value = value || '';
  }

  onInput(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    this.value = inputElement.value;
    this.onChange(this.value);
  }
}
```