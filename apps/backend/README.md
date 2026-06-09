# TP Java Museo — Backend

API REST hecha con Spring Boot 4.0 para el TP de Java del museo.

## Stack

- Java 21
- Spring Boot 4.0.6
- Spring Web (REST)
- Spring Data JPA + Hibernate
- PostgreSQL
- Gradle 9.5.1 (Kotlin DSL)
- JSpecify (anotaciones de nullability)

## Estructura del proyecto

```
src/main/java/com/museo/
├── MuseoApplication.java    ← Entry point de la app
├── api/                      ← Controladores REST (endpoints)
│   ├── CategoryController.java
│   ├── CorsConfig.java
│   └── HealthController.java
├── model/                    ← Entidades JPA (tablas de la DB)
│   └── Category.java
├── repository/               ← Repositorios Spring Data JPA
│   └── CategoryRepository.java
└── service/                  ← Lógica de negocio
    └── CategoryService.java
```

## Cómo levantar el proyecto

### 1. Base de datos

Con Docker:

```bash
docker-compose up -d
```

Esto levanta PostgreSQL en `localhost:5432`, base `museo`.

### 2. Backend

```bash
./gradlew bootRun
```

Arranca en `http://localhost:8080`.

### 3. Verificar que funciona

```bash
curl http://localhost:8080/api/v1/health
```

## Endpoints disponibles

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/v1/health` | Health check |
| GET | `/api/v1/categories` | Listar categorías |
| GET | `/api/v1/categories/{id}` | Obtener categoría por ID |
| POST | `/api/v1/categories` | Crear categoría |
| PUT | `/api/v1/categories/{id}` | Actualizar categoría |
| DELETE | `/api/v1/categories/{id}` | Eliminar categoría |

## Reglas del proyecto

- **Inyección por constructor**, nada de `@Autowired`
- **API versionada** (`/api/v1/...`)
- **Null-safety** con JSpecify: `@NullMarked` en las clases, `@Nullable` donde haga falta
- **Respuestas HTTP coherentes**: 200 OK, 201 Created, 204 No Content, 404 Not Found
- Las entidades NO se exponen directamente en los endpoints (cuando haya más de una, se usan DTOs)

## Cómo agregar una entidad nueva

1. Crear la clase en `model/` con `@Entity`
2. Crear el `*Repository` en `repository/` extendiendo `JpaRepository`
3. Crear el `*Service` en `service/` con `@Service` y `@Transactional`
4. Crear el `*Controller` en `api/` con `@RestController` y `@RequestMapping("/api/v1/...")`
5. Agregar la migración o dejar que Hibernate haga `ddl-auto: update` (solo para desarrollo)
