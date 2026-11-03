# API de Gestión de Solicitudes de Soporte Técnico

Esta es una API RESTful desarrollada con **Spring Boot** para gestionar el registro, consulta, actualización y eliminación de solicitudes de soporte técnico. El proyecto aplica buenas prácticas de diseño de software y arquitectura de capas.

## 🚀 Características Principales

- **CRUD Completo:** Permite crear, leer, actualizar y eliminar solicitudes de soporte.
- **Almacenamiento en Memoria:** Utiliza colecciones de Java (`List`) para simular una base de datos temporal durante la ejecución de la aplicación.
- **Validaciones Rigurosas:** Implementación del patrón **DTO (Data Transfer Object)** junto con `jakarta.validation` para asegurar la integridad de los datos entrantes (ej. campos obligatorios no vacíos).
- **Manejo Global de Excepciones:** Uso de `@RestControllerAdvice` para capturar errores y devolver respuestas JSON limpias y controladas (errores 400 y 404).
- **Documentación Automática:** Integración con **Swagger (OpenAPI)** para explorar y probar los endpoints visualmente.

## 🛠️ Tecnologías Utilizadas

- **Java 21**
- **Spring Boot** (Web, Validation)
- **Lombok** (Para reducir el código repetitivo o boilerplate)
- **Springdoc OpenAPI (Swagger)** (Para la documentación de la API)
- **Maven** (Gestor de dependencias)

## 📁 Arquitectura del Proyecto (Capas)

El proyecto sigue una arquitectura de capas estricta para garantizar la escalabilidad y separación de responsabilidades:

- `controller/`: Define los endpoints (rutas) de la API y gestiona las peticiones HTTP.
- `service/`: Contiene la lógica de negocio. Mapea la información de los DTOs a Entidades y viceversa, asigna estados por defecto ("Pendiente") y fechas de creación.
- `repository/`: Administra el almacenamiento de los datos en memoria.
- `model/`: Define la estructura real de los datos (Entidades puras).
- `dto/`: Objetos de transferencia para recibir/enviar datos al cliente, protegiendo el modelo interno y aplicando reglas de validación.
- `exception/`: Centraliza y maneja todos los errores generados por la aplicación.

## ⚙️ Instrucciones de Ejecución

1. Clonar o descomprimir el proyecto en tu máquina local.
2. Abrir el proyecto en tu IDE preferido (IntelliJ IDEA, Eclipse, etc.).
3. Ejecutar la descarga de las dependencias de Maven manualmente (`pom.xml`).
4. Ejecutar la clase principal: `SoporteApiApplication.java`.
5. El servidor iniciará en el puerto **8080**. (en caso de generar conflicto cambiar al puerto 8081)
6. Resources/application.properties (agregar la linea server.port:8081)

## 📖 Documentación y Pruebas (Swagger)

Una vez que la aplicación esté corriendo, puedes acceder a la interfaz interactiva de Swagger para ver y probar todos los endpoints disponibles navegando a:


👉 **http://localhost:8080/swagger-ui.html**

## 🌐 Endpoints Principales (Ruta base: `/api/solicitudes`)

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/` | Registra una nueva solicitud de soporte (requiere body JSON). |
| `GET` | `/` | Retorna la lista de todas las solicitudes registradas. |
| `GET` | `/{id}` | Busca y retorna una solicitud específica por su ID. |
| `PUT` | `/{id}/estado` | Actualiza el estado de una solicitud (mediante un RequestParam `nuevoEstado`). |
| `DELETE` | `/{id}` | Elimina una solicitud del sistema. |

## 📝 Ejemplo de JSON para POST (Creación)

```json
{
  "descripcion": "El equipo no enciende y emite 3 pitidos cortos.",
  "cliente": {
    "nombre": "Juan Pérez",
    "correo": "juan.perez@empresa.com"
  },
  "tecnico": {
    "nombre": "Ana Gómez",
    "especialidad": "Soporte de Hardware"
  }
}
```
*(Nota: El sistema autogenerará el ID, establecerá el estado en "Pendiente" y asignará la fecha y hora de creación de forma automática).*