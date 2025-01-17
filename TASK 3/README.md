# Library Management System

## Overview

The **Library Management System** is a Java-based application designed to help manage various aspects of a library, including adding, updating, deleting, and searching books, as well as user login functionality. The application follows a **dark mode** design for better user experience, with modern UI elements and intuitive interactions.

This system is primarily built using **Java** and **Swing** for the graphical user interface, and it implements key functionality such as CRUD operations for managing books and user authentication.

## Features

- **User Login**: Secure login system for users to access the library management dashboard.
- **Book Management**:
  - **Add Book**: Allows the addition of new books to the library catalog.
  - **Update Book**: Update details of an existing book in the catalog.
  - **Delete Book**: Delete a book from the library catalog.
  - **Search Book**: Search for a book by title or author.
- **Modern Dark Mode UI**: Clean and modern user interface with a dark theme for ease of use.

## Technologies Used

- **Java**: Main programming language.
- **Swing**: Used for building the graphical user interface (GUI).
- **MySQL**: Database for storing book and user details (not yet implemented but can be added for data persistence).
- **Java AWT**: For basic event handling and layout management.
  
## Installation

### Prerequisites

1. **Java 8 or higher** installed on your machine.
2. **MySQL** (optional for adding database functionality).
3. **IDE** (such as IntelliJ IDEA, Eclipse, or NetBeans).

### Steps to Install

1. Clone or download the repository.
2. Open the project in your preferred Java IDE.
3. Ensure that **Java** is correctly configured in your IDE.
4. If you wish to implement a MySQL database, make sure MySQL is installed and running on your system.
5. Compile and run the project.

## How to Use

1. **Login**:
   - Open the application and enter your **username** and **password** to log in.
   - After successful login, the user will be directed to the main dashboard.

2. **Main Dashboard**:
   - From the main dashboard, you can:
     - **Add a Book**: Enter details like title, author, and category.
     - **Update a Book**: Select a book and modify its information.
     - **Delete a Book**: Select a book to delete.
     - **Search for a Book**: Search using keywords like title or author.
     - **Logout**: Exit the application.

3. **Buttons**:
   - The UI has buttons to perform each task in a compact, easily accessible manner.
   - The interface follows a **dark mode** design for better usability.

## Future Enhancements

- **Database Integration**: Currently, the system does not store data persistently. Integrating a database (like MySQL) would allow data to be saved and retrieved.
- **User Management**: Allow for admin or user roles with varying levels of access to functionality.
- **Advanced Search**: Add filters such as genre, publication year, and more.
- **Book Reservation**: Implement functionality for reserving books.


## Contact

For further inquiries, you can contact me at manthansachdev444@gmail.com

