# AgroMercado - Backend

Backend de AgroMercado: plataforma de compra y venta de productos agrícolas rurales.

Construido con **Spring Boot 3.4.5**, **Java 21** y **MySQL 8**.

## Requisitos previos

- Java 21+
- Maven 3.9+
- Docker (para base de datos MySQL)

## Configuración del entorno

Levantar base de datos MySQL con Docker:

```bash
docker run --name mysql-agromercado -e MYSQL_ROOT_PASSWORD=admin123 -e MYSQL_DATABASE=agromercado -p 3307:3306 -d mysql:8
```

Configurar src/main/resources/application.properties
```bash
spring.datasource.url=jdbc:mysql://localhost:3307/agromercado
spring.datasource.username=root
spring.datasource.password=admin123
```
Ejecutar el proyecto
```bash
mvn spring-boot:run
```


La API estará disponible en:
```bash
http://localhost:8080
```
Swagger UI (documentación interactiva):
```bash
http://localhost:8080/swagger-ui.html
```

## Estructura de módulos

Organización del backend de AgroMercado basada en áreas funcionales:

- 📦 **`/catalog/`**  
  Gestión de productos agrícolas: crear, listar y eliminar productos.

- 🛒 **`/orders/`**  
  Gestión de pedidos realizados por compradores.

- 💵 **`/sales/`**  
  Registro y administración de ventas confirmadas.

- ⭐ **`/ratings/`**  
  Sistema de calificación de productos y vendedores.

- 🔔 **`/notifications/`**  
  Generación y manejo de notificaciones automáticas.

- 📊 **`/dashboard/`**  
  Consolida información para el resumen de actividad del usuario.

- 🔐 **`/security/`**  
  Registro, autenticación e inicio de sesión de usuarios.

## Tecnologías utilizadas

| Tecnología        | Versión / Detalles                       |
|--------------------|------------------------------------------|
| Spring Boot        | 3.4.5                                    |
| Spring Data JPA    | Acceso a datos con repositorios JPA       |
| MySQL              | 8.0 - Base de datos relacional           |
| Docker             | Contenedor para la base de datos MySQL   |
| Swagger / OpenAPI  | Documentación de API REST (`/swagger-ui.html`) |
| Lombok             | Reducción de boilerplate (Getters, Setters, Builders) |
| Maven              | 3.9+ - Gestión de dependencias y builds  |
| Java               | 21 - Versión utilizada para el proyecto  |
