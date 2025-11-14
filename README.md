\# Student Management System (Java + MySQL)



\## 📘 Project Overview

The \*\*Student Management System\*\* is a Java-based console application that demonstrates how to integrate Java with MySQL using \*\*JDBC (Java Database Connectivity)\*\*.



This project allows you to perform full \*\*CRUD operations\*\* (Create, Read, Update, Delete) on student data stored in a MySQL database.  

It’s built purely with \*\*core Java\*\* and \*\*JDBC classes\*\*, without any frameworks — to understand the fundamentals of how Java interacts with databases.



---



\## 🎯 Features

\- Create new databases dynamically from Java

\- Add student records (Insert)

\- View all student records (Read)

\- Update student details (Update)

\- Delete student records (Delete)

\- View all existing databases



---



\## ⚙️ Technologies Used

\- \*\*Java (JDK 17 or above)\*\*

\- \*\*MySQL\*\* (8.0+)

\- \*\*JDBC (Java Database Connectivity)\*\*

\- \*\*IntelliJ IDEA\*\* or any IDE with Java support



---



\## 🧠 Key Concepts Learned



\### JDBC

\*\*JDBC (Java Database Connectivity)\*\* is an API that allows Java applications to connect and interact with relational databases like MySQL.  

It provides the necessary interfaces and classes to establish a connection, execute SQL queries, and process results.



---



\### Connection Class

The `Connection` class is used to establish a link between the Java application and the database.  

It requires:

\- JDBC URL (like `jdbc:mysql://localhost:3306/testdb`)

\- Database username and password  











🖥️ How to Run

1\. Clone the Repository
git clone https://github.com/your-username/StudentManagementSystem.git
cd StudentManagementSystem

2\. Setup MySQL Database
Create a new database named testdb in MySQL.
Create a table:

CREATE TABLE students (

   id INT AUTO\_INCREMENT PRIMARY KEY,

   name VARCHAR(50),

   age INT

);



3\. Add MySQL JDBC Driver

* Download mysql-connector-j.jar
* Add it to your project’s classpath (Project Structure → Libraries → Add JAR)



4\. Update Credentials

In the Java file, update:

* String username = "root";
* String password = "your\_mysql\_password";



5\. Run the Program

Execute the main class:

==== SQL Operations Menu ====

1 -> Create Database (Dynamic)

2 -> Insert Data (testdb)

3 -> Read Data (testdb)

4 -> Update Data (testdb)

5 -> Delete Data (testdb)

6 -> Show All Databases

0 -> Exit



🧱 Example Output

==== SQL Operations Menu ====

1 -> Create Database (Dynamic)

2 -> Insert Data (testdb)

3 -> Read Data (testdb)

4 -> Update Data (testdb)

5 -> Delete Data (testdb)

6 -> Show All Databases

0 -> Exit



Enter Name: Loga

Enter Age: 21

✅ Record inserted successfully!



