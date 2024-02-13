<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bookstore Project - README</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            line-height: 1.6;
            margin: 20px;
        }

        h1 {
            color: #333;
        }

        p {
            color: #666;
        }

        code {
            background-color: #f4f4f4;
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 2px 4px;
            font-family: Monaco, monospace;
        }
    </style>
</head>
<body>

    <h1>Bookstore Project</h1>

    <h2>Overview:</h2>
    <p>The Bookstore Application is a Spring Boot-based web application for managing books, authors, and orders. It provides functionalities such as book listing, adding books, searching, and handling customer orders.</p>

    <h2>Getting Started</h2>

    <p>Follow these instructions to set up the project on your local machine for development and testing purposes.</p>

    <h2>Technologies Used</h2>

    <pre><code>
        Spring Boot: A Java-based framework for creating stand-alone, production-grade Spring-based applications.
        Spring Data JPA: Simplifies database access using the Java Persistence API (JPA).
        Spring MVC: Framework for building robust and scalable web applications.
        Spring Cache: Enables caching to improve application performance.
        Spring Validation: Implements server-side validation for input data.
        Spring @Async: Supports asynchronous processing, e.g., for sending emails.
    </code></pre>

    <h3>Prerequisites</h3>

    <p>Make sure you have the following installed:</p>

    <pre><code>
        - Java SDK (version 19.0.2)
        - Spring Boot (version 3.2.2)
        - Your favorite IDE (IntelliJ, Eclipse, etc.)
    </code></pre>

    <h3>Installation</h3>

    <p>Clone the repository and run the application:</p>

    <pre><code>
        git clone https://github.com/Rna-Tariq/Bookstore.git
        cd Bookstore
        ./mvnw spring-boot:run
    </code></pre>

    <h2>Configuration</h2>

    <p>Database: The application uses an embedded H2 database by default. You can configure a different database in the application.properties file.
    Internationalization (i18n): Localized messages are stored in the messages directory. Additional languages can be added for localization.</p>

    <h2>Usage</h2>

    <p>Visit the following endpoints to interact with the application:</p>

    <pre><code>
        - Books: http://localhost:8080/book
        - Authors: http://localhost:8080/author
        - Orders: http://localhost:8080/order
        - Customers: http://localhost:8080/customer
        - Greetig: http://localhost:8080/customers/greeting
    </code></pre>

    <h2>Contributing</h2>

    <p>Contributions are welcome! Feel free to open issues or submit pull requests.</p>

</body>
</html>
