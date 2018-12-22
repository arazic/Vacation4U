package Model;

import View.User;
import View.Vacation;
import View.userMessage;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

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
                "\t`AirlineCompany`\tTEXT,\n" +
                "\t`FromDate`\tDATE,\n" +
                "\t`ToDate`\tDATE,\n" +
                "\t`TicketType`\tINTEGER,\n" +
                "\t`Baggage`\tTEXT,\n" +
                "\t`TripType`\tTEXT,\n" +
                "\t`Lodging`\tINTEGER,\n" +
                "\t`SalerName`\tTEXT NOT NULL,\n" +
                "\t`BuyerName`\tTEXT DEFAULT NULL,\n" +
                "\tPRIMARY KEY(`FlightNum`,`SalerName`)\n" +
                ");";

//        try (Connection conn = DriverManager.getConnection(url);
//             Statement stmt = conn.createStatement()) {
//            // create a new table
//            stmt.execute(sql);
//        } catch (SQLException e) {
//            if (!e.getMessage().contains("already exists"))
//                System.out.println(e.getMessage());
//        }
//
//        sql = "CREATE TABLE `VacationsOfUsers` (\n" +
//                "\t`UserName`\tTEXT NOT NULL,\n" +
//                "\t`FlightNum`\tINTEGER NOT NULL,\n" +
//                "\tPRIMARY KEY(`UserName`,`FlightNum`)\n" +
//                ");";
//
//        try (Connection conn = DriverManager.getConnection(url);
//             Statement stmt = conn.createStatement()) {
//            // create a new table
//            stmt.execute(sql);
//        } catch (SQLException e) {
//            if (!e.getMessage().contains("already exists"))
//                System.out.println(e.getMessage());
//        }
//        System.out.println("The new table created");

    }
/*
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
                Integer TicketNum = rs.getInt("TicketType");
                String baggage= rs.getString("baggage");
                Integer baggageWeight = rs.getInt("baggageWeight");
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
                } catch (ParseException e) {
                    e.printStackTrace();
                }
////
////                Vacation vacation= new Vacation(FlightNum,FromPlace,ToPlace,Airline,
////                        fromDate,toDate, TicketNum,baggage,baggageWeight,
////                        backDate,Kind,Hotel,salerName);
//                foundVacation.add(vacation);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return foundVacation;
    }*/
    public void updateVacationOwner(String vacationFlightNum, String seller, String buyer) {
        String sql = "UPDATE Vacations" +
                "SET BuyerName=?" +
                "WHERE  FlightNum=? AND SalerName=?;";
        //java.sql.Time fromDate = new java.sql.Time(vacation.getFromDate().);

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, buyer);
            pstmt.setString(2, vacationFlightNum);
            pstmt.setString(3, seller);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public Vacation searchVacationFlightNum(String vacationFlightNum, String seller) {

        Vacation foundVacation= null;

        String sql = "SELECT FlightNum, FromPlace, ToPlace, AirlineCompany, FromDate, ToDate, TicketType, Baggage, TripType, Lodging, SalerName "
                + "FROM Vacations "
                + "WHERE (FlightNum = ?) AND (SalerName = ?)";  //AND ((FromDate BETWEEN ? AND ?) OR (ToDate BETWEEN ? AND ?))

        try (
                Connection conn = this.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, vacationFlightNum);
            pstmt.setString(2, seller);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String FlightNum= rs.getString("FlightNum");
                String FromPlace = rs.getString("FromPlace");
                String ToPlace = rs.getString("ToPlace");
                String Airline= rs.getString("AirlineCompany");
                String TicketType = rs.getString("TicketType");
                String baggage= rs.getString("Baggage");
                String salerName= rs.getString("SalerName");
                String tripType = rs.getString("TripType");
                String lodging = rs.getString("Lodging");
                LocalDate FromDate = LocalDate.parse(rs.getString("FromDate"));
                LocalDate ToDate = LocalDate.parse(rs.getString("ToDate"));

                foundVacation= new Vacation(FlightNum,FromPlace,ToPlace,Airline,FromDate,ToDate,TicketType,baggage,tripType,lodging,salerName);
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

    public List<userMessage> searchReqMessages(User user) {

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

    public List<userMessage> searchAnsMessages(User user) {

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
                User userTo= searchUser(UserNameTo);
                userMessage inboxMessage= new userMessage(FlightNum,userFrom, userTo, Status) ;
                foundMessages.add(inboxMessage);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return foundMessages;
    }

   /* public Vacation searchVacationByFlightNum(String vacation) {
        Vacation returnVacations=null;
        String sql = "SELECT FlightNum, FromPlace, ToPlace, Airline, FromDate, ToDate, TicketNum, baggage, " +
                "baggageWeight, Kind, Hotel, salerName " +
                "FROM Vacations WHERE (FlightNum =? ) ";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vacation);
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
                String Kind= rs.getString("Kind");
                String Hotel= rs.getString("Hotel");
                String salerName= rs.getString("salerName");

                Date fromDate=null;
                Date toDate=null;
                DateFormat dateFormatbackDate =  new SimpleDateFormat("yyyy-MM-dd");
                DateFormat dateFormatfromDate =  new SimpleDateFormat("yyyy-MM-dd");
                DateFormat dateFormattoDate =  new SimpleDateFormat("yyyy-MM-dd");
                try {
                    fromDate =  (Date)dateFormatfromDate.parse(FromDate);
                    toDate =  (Date)dateFormattoDate.parse(ToDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
//
//                 returnVacations= new Vacation(FlightNum,FromPlace,ToPlace,Airline,
//                        fromDate,toDate, TicketNum,baggage,baggageWeight,
//                        Kind,Hotel,salerName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return returnVacations;
    }*/

    public boolean updateMessage(userMessage currentMessage, String newStatus) throws SQLException {
        if(currentMessage==null ||newStatus==null){
            return false;
        }
        else {
            String sql = "UPDATE Messages SET Status " + " = ? "
                    + "WHERE (FlightNum = ?) AND (UserNameFrom = ?) AND (UserNameTo = ?)";
//            System.out.println(sql);
            Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, newStatus);
            pstmt.setString(2, currentMessage.getVacationToBuy());
            pstmt.setString(3, currentMessage.getFromUser().getUserName());
            pstmt.setString(4, currentMessage.getToUser().getUserName());
            //update
            pstmt.executeUpdate();
            return true;
        }
    }


    public int removeMessage(userMessage currentMessage) {
        String sql = "DELETE FROM Messages WHERE (FlightNum = ?) AND (UserNameFrom = ?) AND (UserNameTo = ?)";
        Connection conn = this.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, currentMessage.getVacationToBuy());
            pstmt.setString(2, currentMessage.getFromUser().getUserName());
            pstmt.setString(3, currentMessage.getToUser().getUserName());
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public boolean addVacation(Vacation vacation) {
        String sql = "INSERT INTO Vacations(FlightNum, FromPlace, ToPlace, AirlineCompany, FromDate, ToDate, TicketType, Baggage, TripType, Lodging, SalerName) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        //java.sql.Time fromDate = new java.sql.Time(vacation.getFromDate().);

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vacation.getFlightNum());
            pstmt.setString(2, vacation.getFromPlace());
            pstmt.setString(3, vacation.getToPlace());
            pstmt.setString(4, vacation.getAirlineCompany());
            pstmt.setObject(5,  vacation.getFromDate());
            pstmt.setObject(6,  vacation.getToDate());
            pstmt.setString(7, vacation.getTicketType());
            pstmt.setString(8, vacation.getBaggage());
            pstmt.setString(9, vacation.getTripType());
            pstmt.setString(10, vacation.getLodging());
            pstmt.setString(11, vacation.getSaler());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public ArrayList<Vacation> searchVacation(String fromPlace, String toPlace, LocalDate dp_departureDate, LocalDate dp_returnDate, String ticketType) {
        ArrayList<Vacation> foundVacation= new ArrayList <Vacation> ();

        String sql = "SELECT FlightNum, FromPlace, ToPlace, AirlineCompany, FromDate, ToDate, TicketType, Baggage, TripType, Lodging, SalerName, BuyerName "
                + "FROM Vacations "
                + "WHERE (FromPlace = ?) AND (ToPlace = ?) AND(FromDate BETWEEN ? AND ?) AND(ToDate BETWEEN ? AND ?) AND(BuyerName = ?);";  //AND ((FromDate BETWEEN ? AND ?) OR (ToDate BETWEEN ? AND ?))

        try (
                Connection conn = this.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, fromPlace);
            pstmt.setString(2, toPlace);
            pstmt.setString(3, dp_departureDate.minusDays(3).toString());
            pstmt.setString(4, dp_departureDate.plusDays(3).toString());
            pstmt.setString(5, dp_returnDate.minusDays(3).toString());
            pstmt.setString(6, dp_returnDate.plusDays(3).toString());
            pstmt.setString(7, "NULL");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String FlightNum= rs.getString("FlightNum");
                String FromPlace = rs.getString("FromPlace");
                String ToPlace = rs.getString("ToPlace");
                String Airline= rs.getString("AirlineCompany");
                String TicketType = rs.getString("TicketType");
                String baggage= rs.getString("Baggage");
                String salerName= rs.getString("SalerName");
                String tripType = rs.getString("TripType");
                String lodging = rs.getString("Lodging");
                LocalDate FromDate = LocalDate.parse(rs.getString("FromDate"));
                LocalDate ToDate = LocalDate.parse(rs.getString("ToDate"));

                Vacation vacation= new Vacation(FlightNum,FromPlace,ToPlace,Airline,FromDate,ToDate,TicketType,baggage,tripType,lodging,salerName);
                foundVacation.add(vacation);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return foundVacation;

    }

    public ArrayList<Vacation> searchVacationTrading(String fromPlace, String toPlace, LocalDate dp_departureDate, LocalDate dp_returnDate, String ticketType) {
        ArrayList<Vacation> foundVacation= new ArrayList <Vacation> ();

        String sql = "SELECT FlightNum, FromPlace, ToPlace, AirlineCompany, FromDate, ToDate, TicketType, Baggage, TripType, Lodging, SalerName, BuyerName "
                + "FROM Vacations "
                + "WHERE (FromPlace = ?) AND (ToPlace = ?) AND(FromDate BETWEEN ? AND ?) AND(ToDate BETWEEN ? AND ?) AND(BuyerName != ?);";  //AND ((FromDate BETWEEN ? AND ?) OR (ToDate BETWEEN ? AND ?))

        try (
                Connection conn = this.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, fromPlace);
            pstmt.setString(2, toPlace);
            pstmt.setString(3, dp_departureDate.minusDays(3).toString());
            pstmt.setString(4, dp_departureDate.plusDays(3).toString());
            pstmt.setString(5, dp_returnDate.minusDays(3).toString());
            pstmt.setString(6, dp_returnDate.plusDays(3).toString());
            pstmt.setString(7, "NULL");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String FlightNum= rs.getString("FlightNum");
                String FromPlace = rs.getString("FromPlace");
                String ToPlace = rs.getString("ToPlace");
                String Airline= rs.getString("AirlineCompany");
                String TicketType = rs.getString("TicketType");
                String baggage= rs.getString("Baggage");
                String salerName= rs.getString("SalerName");
                String tripType = rs.getString("TripType");
                String lodging = rs.getString("Lodging");
                LocalDate FromDate = LocalDate.parse(rs.getString("FromDate"));
                LocalDate ToDate = LocalDate.parse(rs.getString("ToDate"));

                Vacation vacation= new Vacation(FlightNum,FromPlace,ToPlace,Airline,FromDate,ToDate,TicketType,baggage,tripType,lodging,salerName);
                foundVacation.add(vacation);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return foundVacation;

    }

    public int deleteVacation(Vacation vacationToBuy) throws SQLException {
        String sql = "DELETE FROM Vacations WHERE FlightNum = ?";
        Connection conn = this.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        try {
            pstmt.setString(1, vacationToBuy.getFlightNum());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt.executeUpdate();
    }

    public int updateVacationSell(Vacation vacationToBuy, String buyer) throws SQLException {
        String sql = "UPDATE Vacations " +
                "SET BuyerName=? " +
                "WHERE  FlightNum=? AND SalerName=?;";
        Connection conn = this.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //java.sql.Time fromDate = new java.sql.Time(vacation.getFromDate().);
            pstmt.setString(1, buyer);
            pstmt.setString(2, vacationToBuy.getFlightNum());
            pstmt.setString(3, vacationToBuy.getSaler());

            pstmt.executeUpdate();


        sql = "INSERT INTO VacationsOfUsers(UserName,FlightNum) VALUES(?,?)";
            PreparedStatement pstmt1 = conn.prepareStatement(sql);
            pstmt1.setString(1, buyer);
            pstmt1.setString(2, vacationToBuy.getFlightNum());
            pstmt1.executeUpdate();

        return pstmt.executeUpdate();
    }


    public ArrayList<Vacation> getUserVacations(String userName) {
        ArrayList<Vacation> userVacations = new ArrayList <Vacation> ();

        String sql = "SELECT FlightNum, FromPlace, ToPlace, AirlineCompany, FromDate, ToDate, TicketType, Baggage, TripType, Lodging, SalerName, BuyerName "
                + "FROM Vacations "
                + "WHERE BuyerName = ?;";  //AND ((FromDate BETWEEN ? AND ?) OR (ToDate BETWEEN ? AND ?))

        try (
                Connection conn = this.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, userName);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String FlightNum= rs.getString("FlightNum");
                String FromPlace = rs.getString("FromPlace");
                String ToPlace = rs.getString("ToPlace");
                String Airline= rs.getString("AirlineCompany");
                String TicketType = rs.getString("TicketType");
                String baggage= rs.getString("Baggage");
                String salerName= rs.getString("SalerName");
                String tripType = rs.getString("TripType");
                String lodging = rs.getString("Lodging");
                LocalDate FromDate = LocalDate.parse(rs.getString("FromDate"));
                LocalDate ToDate = LocalDate.parse(rs.getString("ToDate"));

                Vacation vacation= new Vacation(FlightNum,FromPlace,ToPlace,Airline,FromDate,ToDate,TicketType,baggage,tripType,lodging,salerName);
                userVacations.add(vacation);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return userVacations;
    }
}

