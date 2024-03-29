# Bookstore Application

## Overview The Bookstore Application is a Spring Boot-based web application for managing books, authors, and orders. It provides functionalities such as book listing, adding books, searching, and handling customer orders

## Technologies Used

* Spring Boot: A Java-based framework for creating stand-alone, production-grade Spring-based applications.
* Spring Data JPA: Simplifies database access using the Java Persistence API (JPA).
* Spring MVC: Framework for building robust and scalable web applications.
* Spring Cache: Enables caching to improve application performance.
* Spring Validation: Implements server-side validation for input data.
* Spring @Async: Supports asynchronous processing, e.g., for sending emails.

## Getting Started

Prerequisites Java Development Kit (JDK) 8 or higher Maven Docker (optional, for containerization)

## Installation

## Clone the repository and run the application

* git clone <https://github.com/Rna-Tariq/Bookstore.git>
* cd Bookstore
* ./mvnw spring-boot:run

## Configuration

Database: The application uses an embedded H2 database by default. You can configure a different database in the application.properties file. Internationalization (i18n): Localized messages are stored in the messages directory. Additional languages can be added for localization.

## Usage

* Book Listing: Navigate to <http://localhost:8080/book> to view the list of books.
* Author Information: Visit <http://localhost:8080/author> to explore author details.
* Order Management: Access <http://localhost:8080/order> to manage customer orders.
* Customer Management: Access <http://localhost:8080/customer> to manage customers data.
* Greetig: Access <http://localhost:8080/customers/greeting> to get a greeting message.

## Contributing

## Contributions are welcome! Feel free to open issues or submit pull requests

## P.S. Project is still under processing
