# User Access Management System 📜

## 📝 Project Overview
The User Access Management System is a web-based application designed to manage user access to various software applications within an organization. It provides functionalities like user registration, login, access request submission, and approval or rejection of access requests by managers. Admins have the additional ability to create new software applications within the system.

## 📷 Screen Shots
![image](https://github.com/user-attachments/assets/38fb8fe2-8674-4116-b823-ad0f22bad7a7)
![image](https://github.com/user-attachments/assets/cd5b0aec-7f1b-4beb-a30c-acc910be6799)
![image](https://github.com/user-attachments/assets/d9374016-17da-4a07-b968-21d79a2b0aec)
![image](https://github.com/user-attachments/assets/95105e2e-d8d1-4a22-b654-5b98fd3030e5)
![image](https://github.com/user-attachments/assets/736c862a-206a-479b-a56f-eb29eb565763)

## 🚀 Features
- **User Registration (Sign-Up):** Allows new users to register with a default role of "Employee."
- **User Authentication (Login):** Registered users can log in to the system based on their roles (Employee, Manager, Admin).
- **Software Management (Admin Only):** Admins can create and manage software applications.
- **Access Request Submission (Employee):** Employees can request access to software applications and specify the access level.
- **Access Request Approval (Manager):** Managers can approve or reject access requests.

## 🔧 Technologies Used
- **Backend:** Java Servlets, Java Server Pages, Spring Boot (for project setup)
- **Frontend:** JavaServer Pages (JSP), HTML, CSS
- **Database:** PostgreSQL
- **Build Tool:** Maven

## 🧑‍💻 Installation & Setup

### Step 1: Clone the Repository
To get started with the project, you can clone the repository to your local machine using the following command: 

-- git clone https://github.com/Ragulakarthik/UAMSystem.git

## Step 2: Configure Database

1. **Install PostgreSQL**: If you haven't already, install PostgreSQL. You can download it from [here](https://www.postgresql.org/download/).


2. **Create the Database**: Create a database named `uams` in PostgreSQL.

3. **Run the Provided Database Script**: Use the following SQL script to create the necessary tables for the application (`users`, `software`, and `requests`).

   ```sql
   CREATE TABLE users (
     id SERIAL PRIMARY KEY,
     username TEXT UNIQUE NOT NULL,
     password TEXT NOT NULL,
     role TEXT NOT NULL CHECK (role IN ('Employee', 'Manager', 'Admin'))
   );

   CREATE TABLE software (
     id SERIAL PRIMARY KEY,
     name TEXT NOT NULL,
     description TEXT,
     access_levels TEXT[]
   );

   CREATE TABLE requests (
     id SERIAL PRIMARY KEY,
     user_id INTEGER REFERENCES users(id),
     software_id INTEGER REFERENCES software(id),
     access_type TEXT CHECK (access_type IN ('Read', 'Write', 'Admin')),
     reason TEXT,
     status TEXT CHECK (status IN ('Pending', 'Approved', 'Rejected'))
   );


## Step 3: Configure Application Properties

In the project, you need to configure the database connection in the `application.properties` file located in the `src/main/resources` directory.

Add the following configuration for your PostgreSQL database connection:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/uams
spring.datasource.username=your-db-username
spring.datasource.password=your-db-password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

This section provides the necessary details to configure the database connection for the application.


## Step 3: Build and Run the Project

1. **Open the project in your preferred IDE** (e.g., Spring boot, IntelliJ IDEA, Eclipse).

2. **Build the project using Maven:**

   ```bash
   mvn clean install

# User Roles and Permissions

The system supports three main user roles:

## 👨‍💻 Employee
- Can sign up, log in, and request access to software applications.
- ❌ Cannot approve or reject access requests.
- ❌ Cannot create new software applications.

## 🧑‍💼 Manager
- ✅ Can approve or reject access requests.
- ❌ Cannot request access to software applications.
- ❌ Cannot create new software applications.

## 🛠️ Admin
- ✅ Can create new software applications.
- 🔓 Has full access to all functionalities, including employee and manager roles.

## 📚 How to Use

### Employee (Role: "Employee")
- **Sign-Up**: Go to [http://localhost:8080/signup.jsp](http://localhost:8080/signup.jsp) and create a new account.
- **Login**: After signing up, you can log in from `login.jsp`.
- **Request Access**: Once logged in, you will be redirected to the Access Request page where you can request access to available software applications.

### Manager (Role: "Manager")
- **Login**: Create one Manager before only. After logging in, you will be directed to the Pending Requests page, where you can approve or reject access requests made by employees.

### Admin (Role: "Admin")
- **Login**: Create one Admin before only. After logging in, you will have the ability to create new software applications from the `createSoftware.jsp` page.

## 🧑‍💻 Source Code and Contributions

Feel free to contribute to the project! Fork the repository, create a branch for your changes, and submit a pull request. Please ensure that you follow best practices and keep your code clean.


## 🧑‍💻 Contact

For any issues or suggestions, feel free to open an issue on GitHub.

Happy coding! 😃


