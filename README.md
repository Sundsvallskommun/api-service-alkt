# Alkt
A service for retrieving owners, their establishments and onging cases regarding alcohol permits.

# Setting up the database
The application uses an MSSQL database, which can be started with the following command:

```docker run -p1433:1433 --name AlkTSundsvall -e ACCEPT_EULA=yes -e MSSQL_SA_PASSWORD=P@ssword -d mcr.microsoft.com/mssql/server:2019-latest```

## Populating the database
DDL and test data are located in the standard schema `dbo`.
SQL scripts for creating the tables and inserting test data can be found in the folder `src/test/resources/db/`.
Run the sql-scripts `schema.sql` and `testdata.sql` (in that order).

## Status
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-alkt&metric=alert_status)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-alkt)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-alkt&metric=reliability_rating)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-alkt)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-alkt&metric=security_rating)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-alkt)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-alkt&metric=sqale_rating)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-alkt)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-alkt&metric=vulnerabilities)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-alkt)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-alkt&metric=bugs)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-alkt)

## 
Copyright &copy; 2024 Sundsvalls kommun
