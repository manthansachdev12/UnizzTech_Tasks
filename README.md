# Java Developer Internship (JD-INT004) at UnizzTech  

## Overview  
This repository contains all the tasks and projects completed during my 1-month **Java Developer Internship** (21/12/2024 to 20/01/2025) at **UnizzTech**. The internship focused on gaining hands-on experience in Java development, covering various aspects of programming and web development.  

## Internship Details  
- **Internship Duration**: 1 Month (21/12/2024 to 20/01/2025)  
- **Domain**: Java Development  
- **Tasks Completed**:  
  - Week 1: Currency Converter  

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
