package Model;

import Controller.User;

import java.sql.*;

public class DataBase {
    //    Connection conn;
    static String name;
//    public DataBase(String name) {
//        this.name = name;
//    }

    public DataBase(String dbName) {
        name = dbName;
    }

    /**
     * Connect to the database
     */
    public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:SQL\\" + name + ".db";
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

    private Connection getConnection() {
        String url = "jdbc:sqlite:SQL\\" + name + ".db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createNewDatabase() {

        String url = "jdbc:sqlite:SQL\\" + name + ".db";

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
        String url = "jdbc:sqlite:SQL\\" + name + ".db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE `Users` (\n" +
                "\t`UserName`\tTEXT NOT NULL,\n" +
                "\t`Password`\tTEXT NOT NULL,\n" +
                "\t`BirthDate`\tTEXT,\n" +
                "\t`FirstName`\tTEXT,\n" +
                "\t`LastName`\tTEXT,\n" +
                "\t`City`\tTEXT,\n" +
                "\tPRIMARY KEY(`UserName`)\n" +
                ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            if (!e.getMessage().contains("already exists"))
                System.out.println(e.getMessage());
        }
        System.out.println("The new table created");
    }

    public void insert(User user) throws Exception {
        String sql = "INSERT INTO Users(UserName,Password,BirthDate,FirstName,LastName,City) VALUES(?,?,?,?,?,?)";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getbirthDate());
            pstmt.setString(4, user.getFirstName());
            pstmt.setString(5, user.getLastName());
            pstmt.setString(6, user.getCity());
            pstmt.executeUpdate();
        }
    }

    public boolean updateUserData(String userNameToEdit, String field, String newValue) throws SQLException {
        User user= this.searchUser(userNameToEdit);
        if(user==null){
            return false;
        }
        else {
            String sql = "UPDATE Users SET " + field + "= ? "
                    + "WHERE UserName = ?";
            Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, newValue);
            pstmt.setString(2, userNameToEdit);
            //update
            pstmt.executeUpdate();
            return true;
        }
    }

    public User searchUser(String userNameToSearch) {
        String sql = "SELECT UserName, Password, BirthDate, FirstName, LastName, City " +
                "FROM Users WHERE UserName = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userNameToSearch);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String UserName = rs.getString("UserName");
                String Password = rs.getString("Password");
                String BirthDate = rs.getString("BirthDate");
                String FirstName = rs.getString("FirstName");
                String LastName = rs.getString("LastName");
                String City = rs.getString("City");
                User user = new User(UserName, Password, BirthDate, FirstName, LastName, City);
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    public int deleteUser(String userNameToDelete) throws SQLException {
        String sql = "DELETE FROM Users WHERE UserName = ?";
        Connection conn = this.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userNameToDelete);
        return pstmt.executeUpdate();
    }
}
