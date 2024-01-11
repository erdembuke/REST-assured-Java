# REST-assured-Java API Automation Test Project

This project is an API automation test project created using Java's Rest Assured library, based on the API documentation from [restful-booker.herokuapp.com](https://restful-booker.herokuapp.com/apidoc/index.html).

## Project Details

- **Java Version:** JDK 17
- **IDE:** IntelliJ IDEA
- **Project Type:** Maven Project

## Used Libraries

The project includes the following Maven dependencies:

1. **io.rest-assured:** 5.3.0
2. **junit.jupiter.api:** 5.10.0
3. **json in java:** 20231013
4. **jackson-databind:** 2.14.2

## Test Classes

### 1. BaseTest.class

A class where essential methods for tests are defined to enhance project architecture and code readability.

### 2. CreateBookingTests

A class containing tests for creating reservations. There are two different tests:
- Creating a reservation using a POJO class
- Creating a reservation without using a POJO class

### 3. DeleteBookingTests

A test class with a DELETE HTTP request to delete the created reservation.

### 4. GetAllBookingsTests

A test that retrieves IDs of all reservations in the database using an HTTP GET method. There are two different tests:
- Retrieving all IDs
- Retrieving only the ID of the created reservation using a Query parameter

### 5. GetBookingByIdTests

A test that retrieves all information of a reservation with a given ID using an HTTP GET request.

### 6. PartialUpdateBookingTests

A test that partially updates the information of a reservation with a given ID using an HTTP PATCH request.

### 7. UpdateBookingTests

A test that updates the information of a reservation with a given ID using an HTTP PUT request.

## How to Run?

The project can be opened in IntelliJ IDEA and run as a Maven project. You can run each test class separately or run all tests together using the `mvn test` command.

Don't forget to install the required dependencies for the project to run successfully:
