<!-- omit in toc -->
# Restful apps - Backend application

<!-- omit in toc -->
## TOC

- [Commands](#commands)
    - [Generate Dao, Entity, and SQL files by Doma CodeGen](#generate-dao-entity-and-sql-files-by-doma-codegen)
    - [Test](#test)
    - [Build](#build)
    - [Clean Build](#clean-build)
    - [Run for Development](#run-for-development)
    - [Run for Production](#run-for-production)

## Commands

### Generate Dao, Entity, and SQL files by [Doma CodeGen](https://docs.domaframework.org/ja/stable/codegen/)

```shell
./gradlew :todos-infrastructure:domaCodeGenPostgresqlAll
```

### Test

```shell
./gradlew test
```

### Build

```shell
./gradlew build
```

### Clean Build

```shell
./gradlew clean build
```

### Run for Development

```shell
./gradlew :todos-api:bootRun
```

### Run for Production

```shell
java -jar modules/todos-api/build/libs/todos-api-0.0.1-SNAPSHOT.jar
```
