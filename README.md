# Database Service

## Overview
The **s-arpcoders-database_service** is a Spring Boot application designed to manage inventory data. It connects to a MySQL database and provides RESTful APIs for handling businesses, products, reports, and users. This service is responsible for fetching sales, revenue, and inventory data.

## Features
- **Business Management:** Create, update, and retrieve business details.
- **Product Management:** CRUD operations for products.
- **Report Generation:** Generate reports on sales, inventory, and suppliers.
- **User Management:** Manage user accounts with CRUD operations.
- **Stock Management:** Monitor stock movements and expiration dates.

## Technologies Used
- **Java 23**
- **Spring Boot 3.4.3**
- **Spring Data JDBC**
- **MySQL**
- **Maven**

## Project Structure
```
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/example/inventory/
│   │   │   ├── InventoryApplication.java
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   └── resources/application.properties
│   └── test/java/com/example/inventory/InventoryApplicationTests.java
└── .mvn/
```

## Installation & Setup
### Prerequisites
- Java 23
- Maven
- MySQL Database

### Steps to Run
1. Clone the repository:
   ```sh
   git clone <repository-url>
   cd s-arpcoders-database_service
   ```
2. Configure **application.properties** (if necessary):
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/retailedge
   spring.datasource.username=root
   spring.datasource.password=12345
   ```
3. Build the project:
   ```sh
   mvn clean install
   ```
4. Run the application:
   ```sh
   mvn spring-boot:run
   ```

## API Endpoints
### Business
- `POST /businesses/add` - Add a new business.
- `GET /businesses/all` - Get all businesses.

### Products
- `POST /product` - Add a product.
- `GET /product/{id}` - Get a product by ID.
- `PUT /product/{id}` - Update a product.
- `DELETE /product/{id}` - Delete a product.

### Reports
- `GET /reports/sales` - Get sales report.
- `GET /reports/inventory` - Get inventory report.
- `GET /reports/stock-movements` - Get stock movement report.
- `GET /reports/suppliers` - Get supplier report.

### Users
- `POST /users/add` - Add a new user.
- `GET /users/all` - Get all users.
- `GET /users/email/{email}` - Get user by email.
- `PUT /users/update` - Update user details.
- `DELETE /users/delete/{id}` - Delete a user.

## Contributing
Feel free to contribute by submitting pull requests or reporting issues.

## License
This project is licensed under the MIT License.

