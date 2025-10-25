# Domus Challenge - Backend API

## Descripción General

El proyecto **Domus Challenge** es una aplicación backend desarrollada en **Java 17** con **Spring Boot**, diseñada para gestionar productos mediante endpoints RESTful, paginación, validaciones y pruebas automatizadas.  
El proyecto sigue una arquitectura modular basada en **Domain-Driven Design (DDD)** simplificado, priorizando la mantenibilidad y separación clara de responsabilidades.
![Screenshot 2025-10-24 at 11.26.54 PM.png](Screenshot%202025-10-24%20at%2011.26.54%E2%80%AFPM.png)
---

## Estructura del Proyecto

```
src/
 ├── main/
 │   ├── java/
 │   │   └── domus/global/challenge/
 │   │       ├── application/
 │   │       │   ├── controller/        # Controladores REST
 │   │       │   └── dto/               # Objetos de transferencia de datos (DTOs)
 │   │       ├── domain/
 │   │       │   ├── model/             # Entidades del dominio
 │   │       │   └── service/           # Lógica de negocio
 │   │       ├── infrastructure/
 │   │       │   ├── config/            # Inicialización de datos y configuración
 │   │       │   └── repository/        # Implementaciones de almacenamiento
 │   │       └── ChallengeApplication.java  # Clase principal del proyecto
 │   └── resources/
 │       ├── application.yml            # Configuración de entorno
 │       └── application.properties
 ├── test/
 │   └── java/
 │       └── domus/global/challenge/    # Pruebas unitarias y de integración
 ├── pom.xml                            # Dependencias y configuración de Maven
 ├── Dockerfile                         # Configuración para construir la imagen Docker
 └── README.md                          # Documentación
```

---

## Arquitectura

El proyecto se estructura en tres capas principales siguiendo los principios de **DDD**:

- **Application:** contiene los controladores y DTOs, gestionando la comunicación con el cliente.
- **Domain:** encapsula la lógica de negocio a través de entidades y servicios.
- **Infrastructure:** contiene los componentes técnicos como repositorios y configuraciones de inicialización.

### Patrones Utilizados
- **Repository Pattern:** para el manejo abstracto de datos (ej. `InMemoryProductRepository`).
- **Service Layer Pattern:** para centralizar la lógica de negocio (`ProductService`).
- **DTO Pattern:** para desacoplar las entidades del modelo de las peticiones HTTP.
- **Factory/Initializer Pattern:** para inicializar datos por defecto (`DataInitializer`).

---

## Endpoints Principales

| Método | Endpoint | Descripción |
|:--------|:----------|:-------------|
| `GET` | `/api/products` | Lista paginada de productos existentes |
| `GET` | `/api/products/{id}` | Obtiene un producto por su ID |
| `POST` | `/api/products` | Crea o actualiza un producto (upsert) |
| `DELETE` | `/api/products/{id}` | Elimina un producto por su ID |

### Ejemplo de Paginación

```
GET /api/products?page=0&size=10&sort=name,asc
```

El endpoint retorna un objeto estructurado con dos secciones:
```json
{
  "data": [ ... resultados ... ],
  "metadata": {
    "page": 0,
    "size": 10,
    "totalElements": 25,
    "totalPages": 3
  }
}
```

---

## Ejecución Local

### Requisitos Previos
- **Java 17**
- **Maven 3.8+**
- **Docker (opcional)**

### Compilación y Ejecución

```bash
# Compilar el proyecto
./mvnw clean package -DskipTests

# Ejecutar localmente
java -jar target/app.jar
```

La aplicación quedará disponible en:
```
http://localhost:8080
```

Para visualizar la documentación Swagger UI:
```
http://localhost:8080/swagger-ui/index.html
```

---

## Despliegue con Docker

### Construir la Imagen

```bash
docker build -t domus-challenge .
```

### Ejecutar el Contenedor

```bash
docker run -p 8080:8080 domus-challenge
```

---

## Pruebas

El proyecto incluye pruebas unitarias utilizando **JUnit 5** y **Mockito**, cubriendo la lógica de negocio y controladores principales.

### Ejecutar Pruebas

```bash
./mvnw test
```

---

## Buenas Prácticas Aplicadas

- Validaciones con anotaciones (`@Valid`, `@NotNull`, `@Positive`) en los DTOs.
- Paginación implementada mediante un objeto `PagedResponse` con `data` y `metadata`.
- Lógica desacoplada entre capas (`controller → service → repository`).
- Estructura limpia conforme a DDD.
- Imagen Docker optimizada con `eclipse-temurin:17-jdk-alpine`.
- Swagger UI integrado mediante `springdoc-openapi-starter-webmvc-ui`.

---
