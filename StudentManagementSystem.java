import java.sql.*;
import java.util.Scanner;

public class StudentManagementSystem {

    //  CREATE: Dynamic database creation
    public static void createDatabase(Connection con, Scanner sc) {
        System.out.println("Enter the name for your Database:");
        String dbname = sc.next();
        String query = "CREATE DATABASE " + dbname;

        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("✅ Database '" + dbname + "' created successfully!");
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  INSERT: Static DB (testdb), table students
    public static void insert(Connection con, Scanner sc) {
        String query = "INSERT INTO students (name, age) VALUES (?, ?)";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            while (true) {
                System.out.print("Enter Name: ");
                String name = sc.next();

                System.out.print("Enter Age: ");
                int age = sc.nextInt();
                sc.nextLine();

                pr.setString(1, name);
                pr.setInt(2, age);
                pr.executeUpdate();

                System.out.println("✅ Record inserted successfully!");

                System.out.print("Do you want to add another Student? (yes/no): ");
                String choice = sc.next();
                if (choice.equalsIgnoreCase("no")) break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  READ: Display all records
    public static void read(Connection con) {
        String query = "SELECT * FROM students";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            System.out.println("\n Students Table:");
            System.out.println("-----------------------------");
            System.out.printf("%-5s %-15s %-5s%n", "ID", "Name", "Age");
            System.out.println("-----------------------------");

            while (rs.next()) {
                System.out.printf("%-5d %-15s %-5d%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"));
            }
            System.out.println("-----------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  UPDATE: Modify student data
    public static void update(Connection con, Scanner sc) {
        try {
            System.out.print("Enter ID of student to update: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter new name: ");
            String name = sc.nextLine();

            System.out.print("Enter new age: ");
            int age = sc.nextInt();

            String query = "UPDATE students SET name=?, age=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("✅ Student record updated successfully!");
            else
                System.out.println("⚠️ No student found with ID " + id);

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE: Remove student by ID
    public static void delete(Connection con, Scanner sc) {
        try {
            System.out.print("Enter ID of student to delete: ");
            int id = sc.nextInt();

            String query = "DELETE FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println(" Record deleted successfully!");
            else
                System.out.println(" No record found for ID " + id);

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // READ : List all databases
    public static void showDatabases(Connection con) {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SHOW DATABASES;")) {

            System.out.println("\n---- Available Databases ----");
            while (rs.next()) {
                System.out.println("- " + rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // MAIN MENU
    public static void main(String[] args) {
        String rootUrl = "jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC";
        String dbUrl = "jdbc:mysql://localhost:3306/testdb?useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "dead1234";

        //Handle Exceptions
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n==== SQL Operations Menu ====");
                System.out.println("1 -> Create Database (Dynamic)");
                System.out.println("2 -> Insert Data (testdb)");
                System.out.println("3 -> Read Data (testdb)");
                System.out.println("4 -> Update Data (testdb)");
                System.out.println("5 -> Delete Data (testdb)");
                System.out.println("6 -> Show All Databases");
                System.out.println("0 -> Exit");
                System.out.print("Choose option: ");

                int x = sc.nextInt();
                sc.nextLine();

                switch (x) {
                    case 1 -> {
                        Connection con = DriverManager.getConnection(rootUrl, username, password);
                        createDatabase(con, sc);
                        con.close();
                    }
                    case 2 -> {
                        Connection con = DriverManager.getConnection(dbUrl, username, password);
                        insert(con, sc);
                        con.close();
                    }
                    case 3 -> {
                        Connection con = DriverManager.getConnection(dbUrl, username, password);
                        read(con);
                        con.close();
                    }
                    case 4 -> {
                        Connection con = DriverManager.getConnection(dbUrl, username, password);
                        update(con, sc);
                        con.close();
                    }
                    case 5 -> {
                        Connection con = DriverManager.getConnection(dbUrl, username, password);
                        delete(con, sc);
                        con.close();
                    }
                    case 6 -> {
                        Connection con = DriverManager.getConnection(rootUrl, username, password);
                        showDatabases(con);
                        con.close();
                    }
                    case 0 -> {
                        System.out.println(" Exiting program...");
                        sc.close();
                        return;
                    }
                    default -> System.out.println(" Invalid option! Try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
