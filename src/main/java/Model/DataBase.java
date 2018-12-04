package Model;

import View.User;
import View.Vacation;
import View.userMessage;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class DataBase {
    //    Connection conn;
    static String name;


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
            String url = "jdbc:sqlite:src\\SQL\\" + name + ".db";
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
        String url = "jdbc:sqlite:src\\SQL\\" + name + ".db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createNewDatabase() {

        String url = "jdbc:sqlite:src\\SQL\\" + name + ".db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
//                ResultSet rs = conn.getMetaData().getCatalogs();
//                while (rs.next()){
//                    String catalogs = rs.getString(1);
//                    if (name.equals(catalogs)){
//                        System.out.println("the database "+name+" exists");
//                    }
//                }
//                System.out.println("The driver name is " + meta.getDriverName());
//                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void createUserNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:src\\SQL\\" + name + ".db";

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
//        System.out.println("The new table created");
    }

    public void insertUser(User user) throws Exception {
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
            String sql = "UPDATE Users SET " + field + " = ? "
                    + "WHERE UserName = ?";
//            System.out.println(sql);
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

                User user = new User(UserName, Password, BirthDate, FirstName, LastName, City, false);
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
    public  void createVacationNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:src\\SQL\\" + name + ".db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE `Vacations` (\n" +
                "\t`FlightNum`\tTEXT NOT NULL,\n" +
                "\t`FromPlace`\tTEXT NOT NULL,\n" +
                "\t`ToPlace`\tTEXT,\n" +
                "\t`Airline`\tTEXT,\n" +
                "\t`FromDate`\tDATE,\n" +
                "\t`ToDate`\tDATE,\n" +
                "\t`TicketNum`\tINTEGER,\n" +
                "\t`baggage`\tTEXT,\n" +
                "\t`baggageWeight`\tINTEGER,\n" +
                "\t`Back`\tTEXT,\n" +
                "\t`BackDate`\tDATE,\n" +
                "\t`Kind`\tTEXT,\n" +
                "\t`Hotel`\tTEXT,\n" +
                "\t`salerName`\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(`FlightNum`)\n" +
                ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            if (!e.getMessage().contains("already exists"))
                System.out.println(e.getMessage());
        }
//        System.out.println("The new table created");

    }

    public List<Vacation> searchVacation(Vacation vacationTerms) {

        List<Vacation> foundVacation= new ArrayList <Vacation> ();

        String sql = "SELECT FlightNum, FromPlace, ToPlace, Airline, FromDate, ToDate, TicketNum, baggage, " +
                "baggageWeight, Back, BackDate, Kind, Hotel, salerName " +
                "FROM Vacations WHERE (FromPlace =? ) AND (ToPlace =? ) ";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vacationTerms.getFromPlace());
            pstmt.setString(2, vacationTerms.getToPlace());
           // pstmt.setString(3, String.valueOf(vacationTerms.getFromDate()));
           // pstmt.setString(4, String.valueOf(vacationTerms.getToDate()));
          //  pstmt.setInt(5, vacationTerms.getTicketNum());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String FlightNum= rs.getString("FlightNum");
                String FromPlace = rs.getString("FromPlace");
                String ToPlace = rs.getString("ToPlace");
                String Airline= rs.getString("Airline");
                String FromDate = rs.getString("FromDate");
                String ToDate = rs.getString("ToDate");
                Integer TicketNum = rs.getInt("TicketNum");
                String baggage= rs.getString("baggage");
                Integer baggageWeight = rs.getInt("baggageWeight");
                String Back= rs.getString("Back");
                String BackDate = rs.getString("BackDate");
                String Kind= rs.getString("Kind");
                String Hotel= rs.getString("Hotel");
                String salerName= rs.getString("salerName");

                Date backDate=null;
                Date fromDate=null;
                Date toDate=null;
                DateFormat dateFormatbackDate =  new SimpleDateFormat("yyyy-MM-dd");
                DateFormat dateFormatfromDate =  new SimpleDateFormat("yyyy-MM-dd");
                DateFormat dateFormattoDate =  new SimpleDateFormat("yyyy-MM-dd");
                try {
                    fromDate =  (Date)dateFormatfromDate.parse(FromDate);
                    toDate =  (Date)dateFormattoDate.parse(ToDate);
                    if(backDate!=null)
                       backDate =  (Date)dateFormatbackDate.parse(BackDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Vacation vacation= new Vacation(FlightNum,FromPlace,ToPlace,Airline,
                        fromDate,toDate, TicketNum,baggage,baggageWeight,
                        Back,backDate,Kind,Hotel,salerName);
                foundVacation.add(vacation);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return foundVacation;
    }

    public void createMessagesNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:src\\SQL\\" + name + ".db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE `Messages` (\n" +
                "\t`FlightNum`\tTEXT NOT NULL,\n" +
                "\t`UserNameFrom`\tTEXT NOT NULL,\n" +
                "\t`UserNameTo`\tTEXT NOT NULL,\n" +
                "\t`Status`\tTEXT,\n" +
                "\tPRIMARY KEY(`FlightNum`,`UserNameFrom`,`UserNameTo`)\n" +
                ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            if (!e.getMessage().contains("already exists"))
                System.out.println(e.getMessage());
        }
        //System.out.println("The new table created");
    }


    public void insertMessage(User FromUser, String vacationToBuy, String ToUser) throws Exception {
        String sql = "INSERT INTO Messages(FlightNum,UserNameFrom,UserNameTo,Status) VALUES(?,?,?,?)";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vacationToBuy);
            pstmt.setString(2, FromUser.getUserName());
            pstmt.setString(3, ToUser);
            pstmt.setString(4, "waiting");
//            pstmt.setString(4, "waiting");
            pstmt.executeUpdate();
        }
    }

    public List<userMessage> searchReqFromPurchaser(User user) {

        List<userMessage> foundMessages= new ArrayList <userMessage> ();

        String sql = "SELECT FlightNum, UserNameFrom, UserNameTo, Status " +
                "FROM Messages WHERE (UserNameTo =? ) ";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserName());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String FlightNum= rs.getString("FlightNum");
                String UserNameFrom = rs.getString("UserNameFrom");
                String UserNameTo = rs.getString("UserNameTo");
                String Status= rs.getString("Status");

                User userFrom= searchUser(UserNameFrom);
                userMessage inboxMessage= new userMessage(FlightNum,userFrom, user, Status) ;
                foundMessages.add(inboxMessage);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return foundMessages;

    }

    public List<userMessage> searchAnsFromSalers(User user) {

        List<userMessage> foundMessages= new ArrayList <userMessage> ();

        String sql = "SELECT FlightNum, UserNameFrom, UserNameTo, Status " +
                "FROM Messages WHERE (UserNameFrom =? ) ";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserName());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String FlightNum= rs.getString("FlightNum");
                String UserNameFrom = rs.getString("UserNameFrom");
                String UserNameTo = rs.getString("UserNameTo");
                String Status= rs.getString("Status");

                User userFrom= searchUser(UserNameFrom);
                userMessage inboxMessage= new userMessage(FlightNum,userFrom, user, Status) ;
                foundMessages.add(inboxMessage);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return foundMessages;

    }

    public Vacation searchVacationByFlightNum(Vacation vacation) {
        Vacation returnVacations=null;
        String sql = "SELECT FlightNum, FromPlace, ToPlace, Airline, FromDate, ToDate, TicketNum, baggage, " +
                "baggageWeight, Back, BackDate, Kind, Hotel, salerName " +
                "FROM Vacations WHERE (FlightNum =? ) ";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vacation.getFlightNum());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String FlightNum= rs.getString("FlightNum");
                String FromPlace = rs.getString("FromPlace");
                String ToPlace = rs.getString("ToPlace");
                String Airline= rs.getString("Airline");
                String FromDate = rs.getString("FromDate");
                String ToDate = rs.getString("ToDate");
                Integer TicketNum = rs.getInt("TicketNum");
                String baggage= rs.getString("baggage");
                Integer baggageWeight = rs.getInt("baggageWeight");
                String Back= rs.getString("Back");
                String BackDate = rs.getString("BackDate");
                String Kind= rs.getString("Kind");
                String Hotel= rs.getString("Hotel");
                String salerName= rs.getString("salerName");

                Date backDate=null;
                Date fromDate=null;
                Date toDate=null;
                DateFormat dateFormatbackDate =  new SimpleDateFormat("yyyy-MM-dd");
                DateFormat dateFormatfromDate =  new SimpleDateFormat("yyyy-MM-dd");
                DateFormat dateFormattoDate =  new SimpleDateFormat("yyyy-MM-dd");
                try {
                    fromDate =  (Date)dateFormatfromDate.parse(FromDate);
                    toDate =  (Date)dateFormattoDate.parse(ToDate);
                    if(backDate!=null)
                        backDate =  (Date)dateFormatbackDate.parse(BackDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                 returnVacations= new Vacation(FlightNum,FromPlace,ToPlace,Airline,
                        fromDate,toDate, TicketNum,baggage,baggageWeight,
                        Back,backDate,Kind,Hotel,salerName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return returnVacations;
    }
}



/*    public  void insertFictiveVacations() throws Exception {
        String sql = "INSERT INTO Vacations(FlightNum,FromPlace,ToPlace,Airline,FromDate,ToDate,TicketNum," +
                "baggage,baggageWeight,Back,BackDate,Kind,Hotel,salerName) VALUES('LACI6','Israel','London','ELAL','2018-12-05','2018-12-14','1','yes','7','NO','-','Romantic','NO','chen')";
        try (Connection conn = this.getConnection();
            PreparedStatement pstmt2 = conn.prepareStatement(sql)) {
           pstmt2.executeUpdate();
        }
    }*/
   /* public  void insertFictiveVacations() throws Exception {
        String sql = "INSERT INTO Vacations(FlightNum,FromPlace,ToPlace,Airline,FromDate,ToDate,TicketNum," +
                "baggage,baggageWeight,Back,BackDate,Kind,Hotel) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "LACI6");
            pstmt.setString(2, "Israel");
            pstmt.setString(3, "London");
            pstmt.setString(4, "ELAL");
            pstmt.setString(5, "2018-12-05");
            pstmt.setString(6, "2018-12-14");
            pstmt.setString(7, "1");
            pstmt.setString(8, "YES");
            pstmt.setString(9, "7");
            pstmt.setString(10, "NO");
            pstmt.setString(11, "-");
            pstmt.setString(12, "Romantic");
            pstmt.setString(13, "NO");
            pstmt.setString(13, "salerName");
            pstmt.executeUpdate();
        }
    }*/




