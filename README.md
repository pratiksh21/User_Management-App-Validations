# <h1 align = "center"> User Management System </h1>
___ 
<p align="center">
<a href="Java url">
    <img alt="Java" src="https://img.shields.io/badge/Java->=8-darkblue.svg" />
</a>
<a href="Maven url" >
    <img alt="Maven" src="https://img.shields.io/badge/maven-4.0-brightgreen.svg" />
</a>
<a href="Spring Boot url" >
    <img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-3.1.1-brightgreen.svg" />
</a>
    <a href="License url" >
    <img alt = "GPL v3" src="https://img.shields.io/badge/License-GPLv3-blue.svg" />
    </a>
</p>


---

<p align="left">


## Overview

This project, named "User Management," is a robust Spring Boot application designed for managing user data efficiently. It provides a set of RESTful API endpoints that allow you to perform various operations on user records, such as adding, retrieving, updating, and deleting user information. 

## Technologies Used

- **Framework:** Spring Boot
- **Language:** Java
- **Build Tool:** Maven

## Data Validation in the User Class

The `User` class in our User Management System model is equipped with comprehensive data validation to ensure the accuracy and consistency of user data. Here are the key points about the validations applied to the fields:

### User ID

- **Validation:** `@NotNull`
- **Description:** The `userId` field is required and cannot be null.

### User Name

- **Validation:** `@NotBlank`, `@Pattern(regexp = "^[a-zA-Z ]+$")`
- **Description:** The `userName` field must not be blank, and it must consist of alphabetic characters and spaces only.

### User Type

- **Validation:** None
- **Description:** The `type` field is not subject to additional validation as it represents an enumeration of predefined user types.

### Date of Birth

- **Validation:** `@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of birth must be in yyyy-mm-dd format")`
- **Description:** The `dateOfBirth` field is validated to ensure it adheres to the "yyyy-mm-dd" format.

### Email

- **Validation:** `@Email(message = "Invalid email format. Please enter a valid Email!")`
- **Description:** The `userEmail` field is validated to ensure it contains a valid email address.

### Phone Number

- **Validation:** `@Size(min = 12, max = 12)`, `@Pattern(regexp = "91[0-9]{10}$", message = "Enter a Valid Phone Number,Phone number must start with 91 and be followed by 10 digits")`
- **Description:** The `userContactNo` field is validated to ensure it is exactly 12 characters long and starts with "91," followed by 10 digits.

### Date

- **Validation:** `@NotNull(message = "Date cannot be null")`, `@Past(message = "Date must be in the past")`
- **Description:** The `date` field is validated to ensure it is not null and represents a date in the past.

### Time

- **Validation:** `@NotNull(message = "Time cannot be null")`
- **Description:** The `time` field is validated to ensure it is not null.

### Additional Data Validation

- **Dynamic Default Values:** Both the `date` and `time` fields have dynamic default values. The `date` field is set to the current date, and the `time` field is set to the current time when a new `User` object is created.

- **Custom Validation:** We have implemented custom validation annotations for `LocalDate` and `LocalTime` fields to enforce specific format and logic requirements. These custom annotations are used for more fine-grained validation.

These validations ensure that user data is accurately captured and meets specified criteria, contributing to the reliability and integrity of our User Management System. Users can trust that their information is handled with care and precision.


## Data Flow

### Controller

The Controller layer is responsible for handling incoming HTTP requests and delegating them to the appropriate services. It defines API endpoints for the following operations:

1. **Add User:** `POST /user`
2. **Add Users:** `POST /users`
3. **Get All Users:** `GET /users`
4. **Get User by ID:** `GET /user/{userID}`
5. **Update User Phone Number:** `PUT /user/{userID}/number/{phoneNumber}`
6. **Update User Address:** `PUT /user/{userID}/address/{email}`
7. **Delete User by ID:** `DELETE /user/{userID}`

```java
@RestController
@Validated
public class UserController {
    @Autowired
    UserService userService;

    // Add a user
    @PostMapping("user")
    public String addUser(@Valid @RequestBody User user) {
        return userService.inputUser(user);
    }

    // Get all users
    @GetMapping("users")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    // ...
}
```

## Services

The Services layer implements the core business logic, data processing, and interaction with the data repository. Key responsibilities include:

- Validating input data.
- Performing CRUD operations on user data.
- Handling data transformations and interactions with external systems (if applicable).

```java
@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public List<User> getAllUser() {
        return userRepo.getUsers();
    }

    // ...
}
```

## Repository

The Repository layer manages data access to the underlying database. It handles database operations such as Create, Read, Update, and Delete (CRUD) for user data. Additionally, it may include data mapping and conversion between Java objects and database entities.

```java
@Repository
public class UserRepo {
    @Autowired
    private List<User> userList;

    public List<User> getUsers() {
        return userList;
    }

    // ...
}
```


## Database Design

The project's database design includes tables for user management, with fields such as:

- `userId` (User ID)
- `userName` (User Name)
- `type` (User Type)
- `userEmail` (Email Address)
- `userContactNo` (Phone Number)
- `dob` (Date of Birth)
- Timestamps for record creation and modification

### User Table

| Column Name    | Data Type      | Description                        |
| -------------- | -------------- | ---------------------------------- |
| userId         | INT            | Unique identifier for each user    |
| userName       | VARCHAR(255)   | User's full name                   |
| type           | ENUM           | User type (ADMIN, INTERNAL, EXTERNAL) |
| userEmail      | VARCHAR(255)   | User's email address               |
| userContactNo  | VARCHAR(12)    | User's phone number (e.g., 911234567890) |
| dob            | DATE           | User's date of birth               |
| created_at     | TIMESTAMP      | Timestamp of record creation       |
| updated_at     | TIMESTAMP      | Timestamp of record modification   |

The "User" table stores user-related information, including user IDs, names, types, contact information, date of birth, and timestamps for record creation and modification.

This database design ensures data integrity and provides a structured approach to managing user information within the application.


## Data Structures Used

The project utilizes the following data structures:

### User Class

The `User` class defines the structure for user data and includes the following fields:

- `userId` (User ID): An integer that serves as a unique identifier for each user.
- `userName` (User Name): A string representing the user's full name.
- `type` (User Type): An enumeration specifying the user type, including ADMIN, INTERNAL, and EXTERNAL.
- `userEmail` (Email Address): A string containing the user's email address.
- `userContactNo` (Phone Number): A string representing the user's phone number (e.g., 911234567890).
- `dob` (Date of Birth): A date field indicating the user's date of birth.
- Timestamps for record creation and modification.

### Type Enum

The `Type` enum enumerates the possible user types:

- `ADMIN`: Represents an administrator user.
- `INTERNAL`: Represents an internal user.
- `EXTERNAL`: Represents an external user.

### ArrayList

The project utilizes the `ArrayList` data structure to store and manage lists of `User` objects in various parts of the application. `ArrayList` provides dynamic sizing and efficient element retrieval, making it suitable for storing user records and performing operations on them.

These data structures enable the application to organize and manipulate user data efficiently while maintaining data integrity.


## Project Summary

The User Management project is a robust Spring Boot application designed for efficient user data management. It offers a set of RESTful API endpoints for various user-related operations, including adding, retrieving, updating, and deleting user information.

### Key Technologies Used

- **Framework:** Spring Boot
- **Language:** Java
- **Build Tool:** Maven

### Data Flow

#### Controller

The Controller layer handles incoming HTTP requests and routes them to the appropriate services. It defines API endpoints for operations such as adding, retrieving, updating, and deleting user records.

#### Services

The Services layer implements core business logic and data processing, including input validation, CRUD operations on user data, and data transformations.

#### Repository

The Repository layer manages data access to the underlying database, performing Create, Read, Update, and Delete (CRUD) operations for user data.

#### Database Design

The database design includes tables for user management, featuring fields like `userId`, `userName`, `type`, `userEmail`, `userContactNo`, `dob`, and timestamps for record creation and modification.

### Data Structures Used

- **User Class:** Defines the structure for user data, including user attributes and timestamps.
- **Type Enum:** Enumerates user types, such as ADMIN, INTERNAL, and EXTERNAL.
- **ArrayList:** Utilized for managing lists of `User` objects efficiently.


### Key Features

- RESTful API endpoints for user management.
- Data validation to ensure data integrity.
- Clean code separation with a layered architecture (Controller, Services, Repository).
- Robust error handling for improved reliability.
- Java-based backend and Maven for build management.

The User Management project serves as a practical example of Spring Boot application development, demonstrating best practices in API design and user data management. It offers a solid foundation for building and extending user management systems in various applications.

<!-- License -->
## License
This project is licensed under the [GNU General Public License v3.0](LICENSE).

<!-- Acknowledgments -->
## Acknowledgments
Thank you to the Spring Boot and Java communities for providing excellent tools and resources.

<!-- Contact -->
## Contact
For questions or feedback, please contact [Amit Ashok Swain](mailto:business.amitswain@gmail.com).
