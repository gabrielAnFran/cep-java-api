# CEP API Project

This project is a RESTful API that allows users to retrieve information based on a given postal code (CEP) in Brazil. The API is built using Spring Boot and provides a simple interface for accessing address data.

## Table of Contents

- [Project Structure](#project-structure)
- [Controllers](#controllers)
- [Services](#services)
- [Models](#models)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [License](#license)

## Project Structure

```
src
└── main
    ├── java
    │   └── com
    │       └── cepApi
    │           └── demo
    │               ├── config
    │               ├── controller
    │               ├── models
    │               └── services
    └── resources
```

## Controllers

### `CepController`

Located in `com.cepApi.demo.controller`, the `CepController` class is responsible for handling incoming HTTP requests related to postal code (CEP) information.

- **Endpoint:** `/api/cep/{cep}`
- **Method:** `GET`
- **Description:** This method takes a postal code as a path variable and returns the corresponding address information wrapped in a `CepResponse` object.
- **Dependency:** It uses the `CepService` to fetch the data.

```java
@GetMapping("/{cep}")
public ResponseEntity<CepResponse> getCepInfo(@PathVariable("cep") String cep) {
    return ResponseEntity.ok(cepService.getCepInfo(cep));
}
```

## Services

### `CepApiApplication`

Located in `com.cepApi.demo`, the `CepApiApplication` class is the entry point of the Spring Boot application. It contains the `main` method that launches the application.

```java
@SpringBootApplication
public class CepApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CepApiApplication.class, args);
    }
}
```

### `CepService`

Located in `com.cepApi.demo.services`, the `CepService` class is responsible for the business logic of retrieving postal code information from an external API.

- **Fields:**
    - `apiUrl`: The base URL of the external API, loaded from the application properties.
    - `restTemplate`: A Spring `RestTemplate` used to make HTTP requests.

- **Methods:**
    - `getCepInfo(String cep)`: Constructs the URL for the external API using the provided postal code and fetches the corresponding address information, returning a `CepResponse` object.

```java
public CepResponse getCepInfo(String cep) {
    String url = String.format("%s/%s/json", apiUrl, cep);
    return this.restTemplate.getForObject(url, CepResponse.class);
}
```

## Models

### `CepResponse`

Located in `com.cepApi.demo.models`, the `CepResponse` class is a data model representing the response structure for the postal code information.

- **Fields:**
    - `cep`: The postal code.
    - `logradouro`: The street name.
    - `bairro`: The neighborhood.
    - `localidade`: The locality (city).
    - `uf`: The state.

This class is used to serialize and deserialize JSON data sent to and from the API.

```java
public class CepResponse {
    @JsonProperty("cep")
    private String cep;
    // Other fields with getters and setters
}
```

## Configuration

### `AppConfig`

Located in `com.cepApi.demo.config`, the `AppConfig` class provides application configuration, particularly the creation of beans.

- **RestTemplate Bean:** A `RestTemplate` bean is configured for making HTTP requests to external services that may provide the postal code data.

```java
@Bean
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```

## Running the Application

To run the application, ensure you have Java and Maven installed. Use the following commands in your terminal:

```bash
mvn clean install
mvn spring-boot:run
```

## License

This project is licensed under the MIT License. See the LICENSE file for more details.

---

Feel free to customize any parts of this README as needed or let me know if there are any additional details you'd like to include!