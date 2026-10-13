# Eduhome Education Platform

A Java and Spring Boot web application for an education website, with public content pages and an administration interface. The project uses Thymeleaf templates and separates controllers, services, repositories, models, and DTOs.

## Features in the source

- Course listings, course details, search, and pagination.
- Administration pages for creating, updating, removing, and changing the activity of courses.
- Controllers and services for blogs, events, teachers, speakers, categories, and comments.
- Registration and login flows, password-related pages, and user-management components.
- Form-based authentication through Spring Security and a custom user-details service.
- Contact-message and email-service components.

## Technology

| Component | Technology |
| --- | --- |
| Language | Java 21 |
| Framework | Spring Boot 3.2.4 |
| Views | Thymeleaf |
| Authentication | Spring Security |
| Persistence | Spring Data JPA and PostgreSQL |
| Build | Maven |

## Source layout

| Directory | Responsibility |
| --- | --- |
| `src/main/java/org/sb/eduhome2/controllers` | Public and administration routes |
| `src/main/java/org/sb/eduhome2/services` | Service interfaces and implementations |
| `src/main/java/org/sb/eduhome2/repositories` | Database access |
| `src/main/resources/templates` | Thymeleaf pages |
| `src/main/resources/static` | Frontend assets |

## Getting started

Use Java 21 and the project's Maven build. Review `src/main/resources/application.properties` and configure the database and any email functionality you plan to use.

Some image-upload code contains local Windows paths. Update those paths for your own development environment before using the upload features.

The original development checklist is preserved in [`docs/development-notes.md`](docs/development-notes.md). That checklist records development topics and does not imply that every listed item is implemented.

After configuring the environment, start the application from the repository root:

```bash
mvn spring-boot:run
```
