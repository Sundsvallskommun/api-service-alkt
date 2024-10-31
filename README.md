# Alkt

_A service for retrieving owners, their establishments and ongoing cases regarding alcohol permits._

## Getting Started

### Prerequisites

- **Java 21 or higher**
- **Maven**
- **MSSQL DB**
- **Git**
- **[Dependent Microservices](#dependencies)**

### Installation

1. **Clone the repository:**

   ```bash
   git clone git@github.com:Sundsvallskommun/api-service-alkt.git
   ```
2. **Configure the application:**

   Before running the application, you need to set up configuration settings.
   See [Configuration](#Configuration)

   **Note:** Ensure all required configurations are set; otherwise, the application may fail to start.

3. **Ensure dependent services are running:**

   If this microservice depends on other services, make sure they are up and accessible. See [Dependencies](#dependencies) for more details.

4. **Build and run the application:**

   ```bash
   mvn spring-boot:run
   ```

## Dependencies

### Database

The application uses an MSSQL database, which can be started with the following command:

```bash
docker run -p1433:1433 --name AlkTSundsvall -e ACCEPT_EULA=yes -e MSSQL_SA_PASSWORD=P@ssword -d mcr.microsoft.com/mssql/server:2019-latest
```

### Services

This microservice depends on the following services:

- **Party**
  - **Purpose:** For translating partyId to legalId.
  - **Repository:** [Party](https://github.com/Sundsvallskommun/api-service-party)
  - **Setup Instructions:** Refer to its documentation for installation and configuration steps.

Ensure that these services are running and properly configured before starting this microservice.

## API Documentation

Access the API documentation via Swagger UI:

- **Swagger UI:** [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

Alternatively, refer to the `openapi.yml` file located in the project's root directory for the OpenAPI specification.

## Usage

### API Endpoints

Refer to the [API Documentation](#api-documentation) for detailed information on available endpoints.

### Example Request

```bash
curl -X GET http://localhost:8080/api/resource
```

## Configuration

Configuration is crucial for the application to run successfully. Ensure all necessary settings are configured in `application.yml`.

### Key Configuration Parameters

- **Server Port:**

  ```yaml
  server:
    port: 8080
  ```
- **Database Settings:**

  ```yaml
  spring:
    datasource:
      url: jdbc:sqlserver://localhost:3306;your_database;trustServerCertificate=true
      username: your_db_username
      password: your_db_password
  ```
- **Party integration:**

  ```yaml
  integration:
    party:
      url: https://api-service-url

  spring:
    security:
      oauth2:
        client:
          registration:
            party:
              authorization-grant-type: client_credentials
              client-id: the-client-id
              client-secret: the-client-secret
          provider:
            party:
              token-uri: https://your-token-server

  ```

### Database Initialization

DDL and test data are located in the standard schema `dbo`.
SQL scripts for creating the tables and inserting test data can be found in the folder `src/test/resources/db/`.
Run the sql-scripts `schema.sql` and `testdata.sql` (in that order).

### Additional Notes

- **Application Profiles:**

  Use Spring profiles (`dev`, `prod`, etc.) to manage different configurations for different environments.

- **Logging Configuration:**

  Adjust logging levels if necessary.

## Contributing

Contributions are welcome! Please see [CONTRIBUTING.md](https://github.com/Sundsvallskommun/.github/blob/main/.github/CONTRIBUTING.md) for guidelines.

## License

This project is licensed under the [MIT License](LICENSE).

## Code status

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-alkt&metric=alert_status)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-alkt)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-alkt&metric=reliability_rating)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-alkt)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-alkt&metric=security_rating)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-alkt)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-alkt&metric=sqale_rating)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-alkt)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-alkt&metric=vulnerabilities)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-alkt)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-alkt&metric=bugs)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-alkt)

---

© 2024 Sundsvalls kommun
