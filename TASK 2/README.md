## Database Description

The project involves two main tables in the MySQL database: `login` and `employees`. These tables store essential data for user authentication and employee management.

### 1. login Table

The `login` table is used to store the credentials for users who can log into the system. Below is the structure and description of the table:

#### Table Structure:

| Column Name   | Data Type | Description                               |
|---------------|-----------|-------------------------------------------|
| id            | INT       | Primary Key, Auto-incremented, Unique ID for each record |
| username      | VARCHAR   | The username for login (e.g., "admin")    |
| password      | VARCHAR   | The password for login (e.g., "admin")    |

#### Sample Data:

| id  | username  | password  |
|-----|-----------|-----------|
| 1   | admin     | admin     |

#### Login Process:

A user can log in by entering their `username` and `password` into a login form.  
For example, to log in as an admin:  
- **Username**: admin  
- **Password**: admin  

---

### 2. employees Table

The `employees` table stores the information related to the employees within the organization. Below is the structure and description of the table:

#### Table Structure:

| Column Name   | Data Type  | Description                                       |
|---------------|------------|---------------------------------------------------|
| employeeid    | INT        | Primary Key, Unique ID for each employee          |
| name          | VARCHAR    | The name of the employee                          |
| designation   | VARCHAR    | The job designation of the employee               |
| department    | VARCHAR    | The department where the employee works           |
| salary        | DECIMAL    | The salary of the employee                        |
| hire_date     | DATE       | The date the employee was hired                   |

#### Sample Data:

| employeeid | name      | designation | department | salary | hire_date   |
|------------|-----------|-------------|------------|--------|-------------|
| 101        | John Doe  | Manager     | HR         | 60000  | 2021-03-15  |
| 102        | Jane Smith| Developer   | IT         | 50000  | 2022-05-01  |

#### Usage:

The `employees` table is used to store employee records. Employees' details like name, designation, department, salary, and hire date are stored for internal use.

---
