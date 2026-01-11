# Blood Bank Management System  
**Java + JDBC + MySQL Console Application**

A complete console-based **Blood Bank Management System** built using **Core Java**, **JDBC**, and **MySQL**.  
This project demonstrates good understanding of **OOP concepts**, **database connectivity**, **CRUD operations**, **exception handling**, and **layered architecture**.

Perfect mini-project for freshers to showcase in resumes, interviews, and GitHub portfolio.

## Features

- Register new blood donors (auto-updates blood stock)
- View complete list of all donors
- Search donors by blood group
- Update donor contact number & city
- Delete donor records
- Manually add blood units to stock
- View current blood inventory (only groups with stock > 0)
- Request/remove blood units with proper availability validation
- Clean console-based user interface with proper validations

## Technologies Used

| Technology          | Version / Usage                     |
|---------------------|-------------------------------------|
| Java                | JDK 8+                              |
| JDBC                | MySQL Connector                     |
| Database            | MySQL                               |
| IDE (Recommended)   | IntelliJ IDEA / Eclipse / VS Code   |
| Build Tool          | None (plain Java project)           |

## Project Structure
blood-bank-management-system/
│
├── src/
│   ├── config/
│   │   └── DBConnection.java
│   ├── entity/
│   │   ├── Donor.java
│   │   └── BloodStock.java
│   └── services/
│       ├── BloodBankDAO.java
│       └── BloodBankApp.java
│
├── database/
│   └── blood_bank_schema.sql     ← (create manually)
│
├── README.md
└── .gitignore
## Database Setup (MySQL)

```sql
CREATE DATABASE blood_bank;

USE blood_bank;

CREATE TABLE donor (
    donor_id          INT PRIMARY KEY AUTO_INCREMENT,
    name              VARCHAR(100) NOT NULL,
    age               INT NOT NULL,
    gender            VARCHAR(10),
    blood_group       VARCHAR(10) NOT NULL,
    contact_number    VARCHAR(15) NOT NULL,
    city              VARCHAR(50),
    donated_units     INT DEFAULT 0,
    last_donation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE blood_stock (
    blood_group  VARCHAR(10) PRIMARY KEY,
    total_units  INT DEFAULT 0
);

-- Insert default blood groups
INSERT INTO blood_stock (blood_group, total_units) VALUES
('A+', 0), ('A-', 0), ('B+', 0), ('B-', 0),
('AB+', 0), ('AB-', 0), ('O+', 0), ('O-', 0);
```
- Important: Update DBConnection.java with your actual MySQL username and password.
# How to Run the Project
1. Clone the repository
- git clone https://github.com/zishan9282/blood-bank-management-system.git
- cd blood-bank-management-system
2. Create the database and tables (use above SQL script)
3. Update database credentials in src/config/DBConnection.java
4. Run the application:
- Using IDE (Recommended)
- Open project in IntelliJ / Eclipse
- Right-click → Run BloodBankApp.java
# Sample Usage Flow
1. Choose option 1 → Add new donor
2. Choose option 2 → See all donors with last donation date
3. Choose option 7 → Check current blood stock
4. Choose option 8 → Simulate blood request (removal)
# Key Skills Demonstrated
- Object-Oriented Programming (Classes, Encapsulation)
- JDBC – PreparedStatement, ResultSet, Transactions
- Layered Architecture (Entity → DAO → Application)
- Exception handling (SQLException, InputMismatchException)
- Input validation in console application
- Clean code practices & meaningful naming
# Future Scope/Improvement
- Add user authentication (Admin/Staff login)
- Enforce 90-day gap between donations
- Search donors by city / name / availability
- GUI version using JavaFX or Swing
- Export donor list / stock report to PDF/Excel
- Email notifications for low stock
# Description
Blood Bank Management System
Built a full-featured console-based blood bank application using Core Java, JDBC, and MySQL.
Implemented complete CRUD operations for donors, blood stock management with availability checks, and proper exception handling.
Demonstrates strong understanding of OOP, database connectivity, layered architecture, and clean code practices.
