# Quarkus Social Network API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Quarkus](https://img.shields.io/badge/quarkus-%234794EB.svg?style=for-the-badge&logo=quarkus&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![Prometheus](https://img.shields.io/badge/Prometheus-E6522C?style=for-the-badge&logo=Prometheus&logoColor=white)
![Grafana](https://img.shields.io/badge/grafana-%23F46800.svg?style=for-the-badge&logo=grafana&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)

This project is an API built useing Quarkus, the Supersonic Subatomic Java Framework.
If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

A simple social network for users to send short texts and follow each other.
Contains a Users, Posts and Followers API

- Java 17
- Quarkus 3.14.2
- H2 (tests)
- PostgreSQL (production)
- OpenAPI (Swagger)
- API unit testes
- Telemetry with Prometheus and Grafana


## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [Screenshots](#screenshots)

## Installation

1. Clone the repository:

```bash
git clone https://github.com/jonasfschuh/quarkus-social-network-api.git
```

Notes: 


## Configuration

### Quarkus starter
https://code.quarkus.io/

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

### Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

### Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-social-network-api-1.0-runner`

## Usage

### Running the application in dev mode

1. Start your PostgreSQL database using Docker or Local

2. You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

## API Endpoints

The API provides the following endpoints:

#### Users
```markdown
    GET /users - Retrieve a list of all registered users 
   POST /users - Create a new user and return the create user´s data
    PUT /users - Update the data of an existing user based on its ID
 DELETE /users - Delete an existing user based on its ID
```
#### Posts
```markdown
    GET /users/{userId}/posts - Retrieve a list of all posts from a specific user
   POST /users/{userId}/posts - Create a new post for a specific user
```

#### Followers
```markdown
    GET /users/{userId}/followers - Retrieve a list of all followers from a specific user
    PUT /users/{userId}/followers - Add a new follower to a specific user
 DELETE /users/{userId}/followers - Delete an existed follower from a specific user 
```

NOTES:
More details about the API endpoints can be found in the Swagger UI, which is available at <http://localhost:8080/q/swagger-ui/>.


## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request to the repository.

When contributing to this project, please follow the existing code style, [commit conventions](https://www.conventionalcommits.org/en/v1.0.0/), and submit your changes in a separate branch.

## Screenshots

Quarkus dev User Interface
![](https://github.com/jonasfschuh/quarkus-social-network-api/blob/master/docs/img/dev-ui.gif?raw=true&sanitize=true)

API Tests
![](https://github.com/jonasfschuh/quarkus-social-network-api/blob/master/docs/img/api_tests.gif?raw=true&sanitize=true)

Swagger-ui
![](https://github.com/jonasfschuh/quarkus-social-network-api/blob/master/docs/img/swagger-ui.gif?raw=true&sanitize=true)

Metrics

![](https://github.com/jonasfschuh/quarkus-social-network-api/blob/master/docs/img/metrics.gif?raw=true&sanitize=true)

Prometheus
![](https://github.com/jonasfschuh/quarkus-social-network-api/blob/master/docs/img/metrics_prometheus.gif?raw=true&sanitize=true)

Grafana Dashboard
![](https://github.com/jonasfschuh/quarkus-social-network-api/blob/master/docs/img/metrics_grafana.gif?raw=true&sanitize=true)
