# Employee Management API
### Manage employee records through an authenticated REST API.

An employee directory needs a clear path from signing in to changing a record. This Spring Boot API lets a user register, log in, and use a JWT to create, view, or update employees. Deletion additionally requires an administrator account.

For example, register a user in Swagger, log in, paste the returned token into **Authorize**, and call `GET /api/employes` or create a record with `POST /api/employes`.

## Why explore the implementation?

- **Authentication is part of the request path.** A JWT filter resolves the signed-in user before the request reaches the controller; passwords are BCrypt-encoded and sessions are stateless. Public registration always creates a `USER`, even if a client supplies an administrator role.
- **Responsibilities stay separate.** Controllers validate requests, services perform employee operations, repositories handle JPA persistence, and centralized exception handlers produce API errors. OpenAPI documents the routes for interactive exploration.

| Action | Route | Access |
| --- | --- | --- |
| Register / log in | `POST /api/auth/register`, `POST /api/auth/login` | Public |
| List / read employees | `GET /api/employes`, `GET /api/employes/{id}` | Authenticated |
| Create / update | `POST /api/employes`, `PUT /api/employes/{id}` | Authenticated |
| Delete | `DELETE /api/employes/{id}` | `ADMIN` |

Explore [security rules](src/main/java/com/springboot/employee/config/SecurityConfig.java), [registration](src/main/java/com/springboot/employee/auth/AuthService.java), and [employee routes](src/main/java/com/springboot/employee/controller/EmployeController.java).

## Run locally

Prerequisites: Git, **JDK 21**, and Maven 3.6+ (or the included Maven wrapper).

```bash
git clone https://github.com/Jabour-Salahddine/Spring-Boot-Employee-Management-Complete-REST-API.git
cd Spring-Boot-Employee-Management-Complete-REST-API
export JWT_SECRET_KEY="$(openssl rand -base64 32)"
sh ./mvnw clean install
sh ./mvnw spring-boot:run
```

On PowerShell, generate the key without printing it:

```powershell
$bytes = New-Object byte[] 32
$rng = [System.Security.Cryptography.RandomNumberGenerator]::Create()
$rng.GetBytes($bytes)
$env:JWT_SECRET_KEY = [Convert]::ToBase64String($bytes)
$rng.Dispose()
.\mvnw.cmd spring-boot:run
```

Keep the same private key across restarts if existing tokens must remain valid. Changing it invalidates previously issued tokens. [.env.example](.env.example) documents the variable; Spring Boot does not automatically load that file.

- API and Swagger UI: http://localhost:8080/docs
- Development H2 console: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:file:./data/employeedb` — user `sa`, blank development password

H2 is file-backed, so employee data persists across restarts. The console and blank database password are intended for local development. Administrator roles must be assigned through a trusted administrative process, never the public registration request.

## Stack and checks

Java 21 · Spring Boot 3.5 · Spring Security · JPA/Hibernate · H2 · JJWT · Springdoc OpenAPI · Maven.

```bash
sh ./mvnw test
mvn clean install
mvn spring-boot:run
```

The registration regression test verifies that a request for `ADMIN` still saves a `USER` with an encoded password.
