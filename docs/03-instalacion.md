# Instalación y preparación

# Instalación y preparación

Aquí tienes la guía para montar Estimplytics. Con estos pasos puedes levantar la infraestructura del proyecto (PostgreSQL, Spring Boot y Angular) de forma rápida y pasar del repo a un entorno funcional en un par de minutos.

## Instrucciones paso a paso

El proceso de despliegue es bastante directo, ya que le dejamos la configuración pesada a Docker. Sigue estos pasos:

**Paso 1: Descargar el código**
Bájate el proyecto a tu ordenador.
```bash
git clone <url-del-repositorio>
cd danielmi5-proyecto-final-daw
```

**Paso 2: Configurar el entorno**
El sistema necesita leer las variables de entorno antes de arrancar. Duplica el archivo de ejemplo para crear un archivo nuevo.
```bash
cp .env.example .env
```
(Asegúrate de rellenar los valores necesarios en el archivo `.env`. Tienes la explicación de cada variable en la última sección de este documento).

**Paso 3: Levantar los contenedores**
Usamos Docker para compilar y enlazar los servicios. Tienes dos opciones según lo que vayas a hacer:

**Modo desarrollo:** Expone los puertos de la base de datos para que puedas revisarla con algún cliente.
```bash
docker compose -f docker-compose.dev.yml up -d --build
```
**Modo producción:** Usa una red cerrada con seguridad alta.
```bash
docker compose up -d --build
```

**Paso 4: Comprobar que funciona (Local)**
Revisa que los servicios responden bien en el navegador:
- Interfaz web (Angular): `http://localhost:4200` (o el puerto que definas para el frontend).
- API: `http://localhost:8080`
- Documentación API (Swagger): `http://localhost:8080/swagger-ui/index.html`.

## Requisitos previos

Dependiendo de si quieres levantar el proyecto con contenedores o si vas a tocar el código directamente, los requisitos cambian:

**Con Docker**
- **Docker Engine:** El motor principal para virtualizar.
- **Docker Compose:** Para leer nuestros archivos .yml.

**Desarrollo sin Docker**
- **Java 21 (JDK):** Necesario para el backend construido en Spring Boot.
- **Maven 3.9+:** El gestor de dependencias (el proyecto trae `mvnw` por si no lo tienes instalado a nivel global).
- **Node.js 20+ y npm 11+:** Entorno de ejecución para compilar el frontend en Angular 21.
- **PostgreSQL 16:** Base de datos relacional instalada y corriendo en tu pc.

## Scripts de instalación o Dockerfiles

No hace falta lanzar scripts de bash raros, todo el despliegue se maneja con estos tres archivos:

**docker-compose.yml**
Define la red de contenedores. Levanta la base de datos (Postgres 16) con un healthcheck para asegurar que está lista antes de seguir. Luego levanta el backend inyectando las credenciales, y por último el frontend, que expone la web hacia afuera.

**backend/Dockerfile**
Usa un proceso de construcción dividido en dos etapas (multi-stage build):
1. **Fase build:** Usa la imagen `maven:3.9-eclipse-temurin-21`, copia el código y lanza `mvn package` saltando los tests para compilar el archivo `.jar` más rápido.
2. **Fase runtime:** Pilla una imagen ligera `eclipse-temurin:21-jre`, coge solo el archivo `.jar` compilado y lo ejecuta. Así evitamos dejar dependencias inútiles en el contenedor final.

**frontend/Dockerfile + nginx.conf**
1. **Fase builder:** Usa la imagen `node:22-alpine`, instala las dependencias con `npm ci` y lanza la compilación estricta de Angular (`npm run build`).
2. **Fase server:** Monta un servidor `nginx:alpine`, copia los archivos estáticos y mete un archivo `nginx.conf` a medida. Lo más importante de este Nginx es que actúa como proxy inverso (`location /api/`), redirigiendo el tráfico al backend para evitar los problemas típicos de CORS.

## Variables de entorno necesarias

El archivo `.env` tiene toda la configuración base. Aquí tienes las variables que debes definir, separadas por partes:

**Base de datos (PostgreSQL)**
- `POSTGRES_DB`: Nombre de la base de datos (Ej: estimplytics_db).
- `POSTGRES_USER`: Usuario administrador.
- `POSTGRES_PASSWORD`: Contraseña segura.
- `POSTGRES_PORT`: Puerto expuesto (solo se usa en el compose de desarrollo, normalmente 5432).

**Backend (Spring Boot)**
- `BACKEND_PORT`: Puerto para la API (normalmente 8080).
- `SPRING_JPA_HIBERNATE_DDL_AUTO`: Comportamiento del esquema (usa `update` para desarrollo o `validate`/`none` para producción).
- `JWT_SECRET`: Semilla criptográfica para firmar los tokens. Tiene que ser una cadena Base64 o texto plano de 32 bytes como mínimo.
- `JWT_EXPIRATION`: Tiempo de vida para la sesión en milisegundos (Ej: 3600000 para una hora).

**Frontend (Angular/Nginx)**
- `FRONTEND_PORT`: Puerto donde se servirá la aplicación web para los usuarios (normalmente 4200 en desarrollo o 80 en producción).