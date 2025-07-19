<!-- omit in toc -->
# Restful Apps - API Layer Application

<!-- omit in toc -->
## TOC

- [Requirements](#requirements)
    - [Required](#required)
    - [Recommended](#recommended)
- [Commands](#commands)
    - [Test](#test)
    - [Run for Development](#run-for-development)
    - [Build](#build)
    - [Generate Dao, Entity, and SQL files by Doma CodeGen](#generate-dao-entity-and-sql-files-by-doma-codegen)
    - [Run](#run)

## Requirements

### Required

- [JDK 21](https://openjdk.org/projects/jdk/21/)
- [Docker](https://www.docker.com/)

### Recommended

- [SDKMAN!](https://sdkman.io/)
- [Visual Studio Code](https://azure.microsoft.com/ja-jp/products/visual-studio-code)

## Commands

### Test

```shell
./gradlew test
```

### Run for Development

```shell
./gradlew bootRun
```

### Build

```shell
./gradlew build
```

### Generate Dao, Entity, and SQL files by [Doma CodeGen](https://docs.domaframework.org/ja/stable/codegen/)

```shell
./gradlew domaCodeGenPostgresqlAll
```

### Run

```shell
java -jar build/libs/todos-api-0.0.1-SNAPSHOT.jar
```
