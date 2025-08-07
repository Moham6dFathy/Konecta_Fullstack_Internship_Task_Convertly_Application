# Convertly – REST API Unit Conversion Application

Convertly is a REST API designed for converting values across different measurement categories, such as temperature, length, and more. This project was built as part of the Konecta Fullstack Internship task, showcasing API design, validation, exception handling.

---


## 📌 Features

- 🔁 A Unit Converter REST API clean and organized API that converts units
  across different measurement categories (like temperature, weight,
  length, time).

- 🧪 Input validation and clean error handling
- 📦 Track session-based conversions
- 📄 Export conversion history as JSON
- 🔍 Swagger UI for interactive API testing

---

## 🛠️ Tech Stack

| Layer       | Technology                    |
|-------------|-------------------------------|
| Backend     | Java, Spring Boot             |
| API Docs    | Springdoc OpenAPI (Swagger)   |
| Validation  | Jakarta Validation (Bean Validation) |
| Build Tool  | Maven                         |
| Data Model  | Session-based, in-memory JSON |
| Version Control | Git + GitHub              |

---

## 📁 Project Structure

```
├── src
│   ├── main
│   │   ├── java/org/example/convertly
│   │   │   ├── controller
│   │   │   |   └── ConverterController.java
│   │   │   ├── service
│   │   │   |   ├── ConversionService.java
│   │   │   |   ├── TemperatureService.java
│   │   │   |   ├── LengthService.java
│   │   │   |   ├── WeightService.java
│   │   │   |   └── TimeService.java
│   │   │   ├── model
│   │   │   |   ├── ConversionRequest.java
│   │   │   |   ├── ConversionResponse.java
│   │   │   |   └── ConversionSession.java
│   │   │   ├── exception
│   │   │   |   ├── GloabalExceptionHandler.java
│   │   │   |   ├── InvalidUnitException.java
│   │   │   |   ├── NegativeValueException.java
│   │   │   |   └── ErrorResponse.java
│   │   │   ├── interface
│   │   │   |   └── UnitCategory.java
│   │   │   ├── enums
│   │   │   |   ├── Category.java
│   │   │   |   ├── TemperatureUnit.java
│   │   │   |   ├── LengthUnit.java
│   │   │   |   ├── WeightUnit.java
│   │   │   |   └── TimeUnit.java
│   │   │   └── ConvertlyApplication.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── templates 
├── pom.xml
```

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- IDE (IntelliJ, VSCode, etc.)

### Run Locally

```bash
# Clone the repo
git clone https://github.com/Moham6dFathy/Konecta_Fullstack_Internship_Task_Convertly_Application.git
cd Konecta_Fullstack_Internship_Task_Convertly_Application

# Build and run the Spring Boot app
mvn clean install
mvn spring-boot:run
```

Then open: `http://localhost:8080` in your browser.

---

## 📄 API Documentation

After running the app, access Swagger UI at:

```
http://localhost:8080/swagger-ui/index.html
```

Use this interface to test endpoints like:

- `POST /convert`
- `GET /conversion/{sessionId}/download`

---

## 🧪 Example API Request

```json
POST /convert
{
    "category": "temperature",
    "fromUnit": "celsius",
    "toUnit": "fahrenheit",
    "value": 100,
}
```

Response:
```json
{
    "convertedResult": 212.0,
    "formula": "(100.0 * 9/5) + 32 = 212.0 F",
    "input": {
        "category": "temperature",
        "fromUnit": "celsius",
        "toUnit": "fahrenheit",
        "value": 100.0
    },
    "status": "success"
}
```

---

## 🧰 Custom Exception Handling

Robust global exception handling for:
- Invalid units
- Missing parameters
- Unsupported categories

---

## 📂 Download Conversion History

```http
GET /conversion/{sessionId}/download
```

Returns a JSON file containing the conversion session history for a given session ID.

