# Despliegue

## Entorno de despliegue utilizado

Todo el ecosistema se apoya en una arquitectura basada en contenedores usando Docker. El entorno de producción está alojado en DigitalOcean, utilizando su servicio App Platform.

El sistema completo se divide en tres bloques principales aislados:
- Un contenedor web con Nginx sirviendo los archivos compilados de Angular y haciendo de proxy inverso para las peticiones de la API.
- Un contenedor con un entorno de ejecución Java (JRE) ultraligero corriendo el backend.
- Una base de datos PostgreSQL gestionada directamente en la nube.

Las imágenes Docker creadas se almacenan en un registro de DockerHub para tenerlas centralizadas antes de mandarlas a producción.

## Configuración de CI/CD

El proyecto tiene un pipeline de integración y despliegue continuo automatizado usando GitHub Actions. Está dividido en tres flujos de trabajo paralelos:

**Pipeline de integración (CI)**
Se dispara automáticamente cada vez que alguien hace push o abre un pull request contra las ramas `main` o `develop`.
- Lanza un entorno Ubuntu con Java 21 y ejecuta los tests del backend usando Maven.
- Lanza otro entorno con Node 22, instala las dependencias usando npm y ejecuta los tests del frontend con Angular.

**Pipeline de construcción y publicación (Hub)**
Se activa cuando se hace un push directo a la rama `main`.
Usa QEMU y Docker para construir las imágenes del backend y del frontend basándose en sus Dockerfiles respectivos. Una vez construidas, hace login en DockerHub usando los secretos del repositorio y sube las imágenes etiquetadas como `latest`.

**Pipeline de despliegue (Deploy)**
Este flujo de trabajo depende del anterior. Solo arranca si el pipeline de publicación termina sin errores.
Se conecta a DigitalOcean a través de su herramienta de consola (`doctl`) usando un token de acceso seguro. Lanza los comandos necesarios para forzar el redespliegue de los dos componentes (frontend y backend) usando los identificadores únicos de las aplicaciones en DigitalOcean.

## Proceso de despliegue documentado

El proceso para subir el proyecto a producción no requiere pasos manuales. Funciona con esta secuencia:

1. Un desarrollador termina una tarea y crea un pull request hacia la rama `main`.
2. GitHub ejecuta el pipeline CI. Si algún test del backend o del frontend falla, el sistema bloquea la fusión de código.
3. Si los tests pasan en verde y el código se aprueba, se hace el merge.
4. Ese merge activa el pipeline de publicación, creando las imágenes Docker actualizadas y subiéndolas a DockerHub.
5. Al terminar de subir las imágenes, el pipeline de despliegue avisa a DigitalOcean para que descargue las versiones nuevas y reemplace los contenedores antiguos.

## URL de la aplicación en producción

La aplicación está alojada en DigitalOcean: ![url](https://estimplytics.com)