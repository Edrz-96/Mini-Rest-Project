# Project Name

This project is a Spring Boot application that allows users to perform CRUD operations on CSV data.

### Prerequisites

- Java 11 +
- Maven
- Curl (optional, for testing)
- Postman (testing without curl)

### Installing

Clone the repository to your local machine. Enter CLI after cloning in the root directory. Then run these steps:
- mvn clean install
- mvn spring-boot:run

Once running attemp the following:
curl -X POST -H " -d '{"customerRef":1,"customerName":"John Doe","addressLineOne":"123 Main St","town":"City","country":"Country","postcode":"12345"}' http://localhost:1000/csv/create

For CSV parsing find a local file and run the following:

curl -X POST -F "filePath=/path/to/your/file.csv" http://localhost:1000/upload

If you're planning on testing via postman please see swagger-url: http://localhost:1000/swagger-ui/

Otherwise, try http://localhost:1000/hello/

