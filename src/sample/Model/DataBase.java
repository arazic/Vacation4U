package sample.Model;

import sample.User;

import java.sql.*;

public class DataBase {
//    Connection conn;
    static String name;
//    public DataBase(String name) {
//        this.name = name;
//    }

    public DataBase(String dbName) {
        name=dbName;
    }

    /**
     * Connect to a sample database
     */
    public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/Users/nadavbar/IdeaProjects/Vacation4UYalle/sql/" + name + ".db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private Connection getConnection(){
        String url = "jdbc:sqlite:C:/Users/nadavbar/IdeaProjects/Vacation4UYalle/sql/" + name + ".db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createNewDatabase() {

        String url = "jdbc:sqlite:C:/Users/nadavbar/IdeaProjects/Vacation4UYalle/sql" + name;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/nadavbar/IdeaProjects/Vacation4UYalle/sql/" + name + ".db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE `Users` (\n" +
                "\t`Username`\tTEXT NOT NULL,\n" +
                "\t`Password`\tTEXT NOT NULL,\n" +
                "\t`Birthdate`\tTEXT,\n" +
                "\t`FirstName`\tTEXT,\n" +
                "\t`LastName`\tTEXT,\n" +
                "\t`City`\tTEXT,\n" +
                "\tPRIMARY KEY(`Username`)\n" +
                ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("The new table created");
    }

    public void insert(User user){


        String sql = "INSERT INTO Users(Username,Password,Birthdate,FirstName,LastName,City) VALUES(?,?,?,?,?,?)";
        try (Connection conn = this.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getBirthDate());
            pstmt.setString(4, user.getFirstName());
            pstmt.setString(5, user.getLastName());
            pstmt.setString(6, user.getCity());
            pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());

        }
    }


}
