# Java Developer Internship (JD-INT004) at UnizzTech  

## Overview  
This repository contains all the tasks and projects completed during my 1-month **Java Developer Internship** (21/12/2024 to 20/01/2025) at **UnizzTech**. The internship focused on gaining hands-on experience in Java development, covering various aspects of programming and web development.  

## Internship Details  
- **Internship Duration**: 1 Month (21/12/2024 to 20/01/2025)  
- **Domain**: Java Development  
- **Tasks Completed**:  
  - Week 1: Currency Converter
  - Week 2: Employee Management System
  - Week 3: Library Management System
  - Week 4: Hotel Reservation System

## Week 1: Currency Converter  

### Task Description  
The **Currency Converter** is a Java application that converts an amount from one currency to another using real-time exchange rates from the **ExchangeRate-API**.  

### Features  
1. Fetches live exchange rates from the API.  
2. Converts a specified amount from a source currency to a target currency.  
3. Displays the exchange rate and converted amount with proper formatting.  
4. Handles errors gracefully, including invalid inputs and API issues.  

### Tools and Technologies  
- **Language**: Java  
- **Libraries**:  
  - `org.json.JSONObject` for JSON parsing.  
  - `HttpURLConnection` for API requests.  
  - `DecimalFormat` for formatted output.  
- **API**: ExchangeRate-API

### Sample Output  
```text
Welcome to the Currency Converter!  
Enter the currency you want to convert from (e.g., USD): USD  
Enter the currency you want to convert to (e.g., INR): INR  
Enter the amount you want to convert: 100  
Exchange rate (USD to INR): 83.45  
Converted amount: 8345.00 INR
```

## Week 2: Employee Management System  

### Task Description  
The **Employee Management System** is a Java application that uses **Swing**, **AWT**, and **MySQL** to manage employee data, including adding, removing, updating, and viewing employee information. This system provides a user-friendly GUI for handling employee records, allowing the user to perform various actions like adding new employees, updating existing records, and removing employees from the database.

### Features  
1. **Add Employee**: Allows users to enter employee details such as name, age, position, and salary.
2. **Remove Employee**: Enables the user to remove an employee by entering their unique employee ID.
3. **Update Employee**: Allows users to update employee details based on their employee ID.
4. **View Employees**: Displays a list of all employees currently in the database.
5. **Error Handling**: Gracefully handles invalid input and database errors.
6. **Database Interaction**: Uses MySQL for storing and retrieving employee data.

### Tools and Technologies  
- **Language**: Java  
- **Libraries**:  
  - **Swing** for GUI components.  
  - **AWT** for event handling and layout management.  
- **Database**: MySQL  
- **JDBC**: For database connection and operations.

## Week 3: Library Management System  

### Task Description  
The **Library Management System** is a Java application designed to manage books in a library. The system allows users to perform operations such as adding new books, updating book information, deleting books, and searching for books. It provides a GUI for user interactions and uses file handling for data persistence.

### Features  
1. **Add Book**: Adds new books to the system with details like title, author, publisher, and ISBN.
2. **Update Book**: Updates information of an existing book by selecting its unique ISBN.
3. **Delete Book**: Deletes a book from the system based on its ISBN.
4. **Search Book**: Search for a book by title, author, or ISBN.
5. **Dark Mode UI**: The system uses a modern dark mode interface for improved user experience.

### Tools and Technologies  
- **Language**: Java  
- **Libraries**:  
  - **Swing** for the GUI.  
  - **AWT** for layout management and event handling.  
  - **File Handling** for data persistence (can be extended to use a database).  
- **File Formats**: Text files for storing book data.

## Week 4: Hotel Reservation System  

### Task Description  
The **Hotel Reservation System** is a Java-based desktop application developed using **Swing** for the graphical user interface (GUI) and **MySQL** for the database management. The system is designed for hotel receptionists and administrators to manage hotel room bookings, check room availability, assign services, and handle checkouts. The application also provides an admin login panel for secure access and efficient management.

### Features  
1. **Admin Login Panel**: A secure login page for admins to access and manage the hotel system.
2. **Reception Panel**: Allows receptionists to view, add, and manage bookings, including customer details, room type, check-in, and check-out dates.
3. **Booking Management**: Receptionists can create new bookings, view existing bookings, and manage customer information.
4. **Room Details**: Displays available rooms and room types for customers to choose from.
5. **Services Panel**: Allows receptionists to assign additional services to customers, such as room service or special requests.
6. **Check Status**: Receptionists can check the status of bookings and services, including service requests and checkout status.
7. **Checkout Panel**: Allows receptionists to mark bookings as completed and perform checkout procedures for customers.
8. **Database Interaction**: The system is connected to a MySQL database for storing and retrieving customer data, bookings, services, and checkout details.
9. **Admin Dashboard**: The admin dashboard allows viewing of all hotel bookings, services, and checkouts.

### Tools and Technologies  
- **Language**: Java  
- **Libraries**:  
  - **Swing** for GUI components.  
  - **AWT** for event handling and layout management.  
  - **JDBC** for MySQL database connection and operations.
- **Database**: MySQL  
- **Tools**:  
  - **MySQL Workbench** for database management.
  - **IntelliJ IDEA** for Java development.

### Database Schema  
The system uses the following tables in the **HotelDB** database:

1. **Bookings**: Stores booking information, including customer name, room type, and check-in/check-out dates.
2. **Services**: Records services requested by customers, including service type and room number.
3. **Checkouts**: Tracks customer checkouts, including status and time of checkout.

## Contact

For further inquiries, you can contact me at manthansachdev444@gmail.com
