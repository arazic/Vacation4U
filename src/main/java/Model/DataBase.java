package Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    //    Connection conn;
    static String name;


    public DataBase(String dbName, User administrator) {
        name = dbName;
        createNewDatabase();
        connect();
        createUserNewTable();
        createVacationNewTable();
        createMessagesNewTable();
        try {
            insertUser(administrator);
            java.lang.System.out.println("The admin username of the system is: " + administrator.getUserName());
            java.lang.System.out.println("The admin default password is: 12345678");
        } catch (Exception e) {
            java.lang.System.out.println("The admin username of the system is: " + administrator.getUserName());
        }
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
//                        View.out.println("the database "+name+" exists");
//                    }
//                }
//                View.out.println("The driver name is " + meta.getDriverName());
//                View.out.println("A new database has been created.");
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
                "\t`PhoneNumber`\tTEXT,\n" +
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
//        View.out.println("The new table created");


    }


    public static void createVacationsOfUsersNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:src\\SQL\\" + name + ".db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE `VacationsOfUsers` (\n" +
                "\t`UserName`\tTEXT NOT NULL,\n" +
                "\t`FlightNum`\tTEXT NOT NULL,\n" +
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
//        View.out.println("The new table created");
    }

    public void insertUser(User user) throws Exception {
        String sql = "INSERT INTO Users(UserName,Password,BirthDate,FirstName,LastName,City,PhoneNumber) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getbirthDate());
            pstmt.setString(4, user.getFirstName());
            pstmt.setString(5, user.getLastName());
            pstmt.setString(6, user.getCity());
            pstmt.setString(7, user.getPhoneNumber());
            pstmt.executeUpdate();
        }
    }

    public boolean updateUserData(String userNameToEdit, String field, String newValue) throws SQLException {
        User user = this.searchUser(userNameToEdit);
        if (user == null) {
            return false;
        } else {
            String sql = "UPDATE Users SET " + field + " = ? "
                    + "WHERE UserName = ?";
//            View.out.println(sql);
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
        String sql = "SELECT UserName, Password, BirthDate, FirstName, LastName, City, PhoneNumber " +
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
                String PhoneNumber = rs.getString("PhoneNumber");

                User user = new User(UserName, Password, BirthDate, FirstName, LastName, City, PhoneNumber, false);
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

    public int deleteUser(String buyer, Vacation vacationToBuy) throws SQLException {
        String sql = "DELETE FROM VacationsOfUsers WHERE (UserName = ?) AND (FlightNum = ?)";
        Connection conn = this.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, buyer);
        pstmt.setString(2, vacationToBuy.getFlightNum());
        return pstmt.executeUpdate();

    }

    public void createVacationNewTable() {
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

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            if (!e.getMessage().contains("already exists"))
                System.out.println(e.getMessage());
        }
    }

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


    public Vacation searchVacationFlightNumBySeller(String vacationFlightNum, String seller) {
        Vacation foundVacation = null;

        String sql = "SELECT FlightNum, FromPlace, ToPlace, AirlineCompany, FromDate, ToDate, TicketType, Baggage, TripType, Lodging, SalerName, BuyerName "
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
                String FlightNum = rs.getString("FlightNum");
                String FromPlace = rs.getString("FromPlace");
                String ToPlace = rs.getString("ToPlace");
                String Airline = rs.getString("AirlineCompany");
                String TicketType = rs.getString("TicketType");
                String baggage = rs.getString("Baggage");
                String salerName = rs.getString("SalerName");
                String tripType = rs.getString("TripType");
                String lodging = rs.getString("Lodging");
                LocalDate FromDate = LocalDate.parse(rs.getString("FromDate"));
                LocalDate ToDate = LocalDate.parse(rs.getString("ToDate"));
                String BuyerName = rs.getString("BuyerName");

                foundVacation = new Vacation(FlightNum, FromPlace, ToPlace, Airline, FromDate, ToDate, TicketType, baggage, tripType, lodging, salerName, BuyerName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return foundVacation;

    }

    public Vacation searchVacationFlightNumByBuyer(String vacationFlightNum, String buyer) {
        Vacation foundVacation = null;

        String sql = "SELECT FlightNum, FromPlace, ToPlace, AirlineCompany, FromDate, ToDate, TicketType, Baggage, TripType, Lodging, SalerName, BuyerName "
                + "FROM Vacations "
                + "WHERE (FlightNum = ?) AND (BuyerName = ?)";  //AND ((FromDate BETWEEN ? AND ?) OR (ToDate BETWEEN ? AND ?))

        try (
                Connection conn = this.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, vacationFlightNum);
            pstmt.setString(2, buyer);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String FlightNum = rs.getString("FlightNum");
                String FromPlace = rs.getString("FromPlace");
                String ToPlace = rs.getString("ToPlace");
                String Airline = rs.getString("AirlineCompany");
                String TicketType = rs.getString("TicketType");
                String baggage = rs.getString("Baggage");
                String salerName = rs.getString("SalerName");
                String tripType = rs.getString("TripType");
                String lodging = rs.getString("Lodging");
                LocalDate FromDate = LocalDate.parse(rs.getString("FromDate"));
                LocalDate ToDate = LocalDate.parse(rs.getString("ToDate"));
                String BuyerName = rs.getString("BuyerName");

                foundVacation = new Vacation(FlightNum, FromPlace, ToPlace, Airline, FromDate, ToDate, TicketType, baggage, tripType, lodging, salerName, BuyerName);
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
        //     public UserMessage(String vacationToBuy, User fromUser, String vacationOffering, User toUser, String status) {
        //View.out.println("The new table created");
        sql = "CREATE TABLE `TradingMessages` (\n" +
                "\t`FlightNumOffering`\tTEXT NOT NULL,\n" +
                "\t`UserNameOffering`\tTEXT NOT NULL,\n" +
                "\t`FlightNumToGet`\tTEXT NOT NULL,\n" +
                "\t`ToUser`\tTEXT,\n" +
                "\t`Status`\tTEXT,\n" +
                "\tPRIMARY KEY(`FlightNumToGet`,`ToUser`,`FlightNumOffering`,`UserNameOffering`)\n" +
                ");";
//        public void insertTradingMessage(String vacationOffering, String userNameOffering, String vacationToGet, String fromUser, String status) {

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            if (!e.getMessage().contains("already exists"))
                System.out.println(e.getMessage());
        }
    }


    public boolean insertMessage(User FromUser, String vacationToBuy, String ToUser) throws Exception {
        String sql = "INSERT INTO Messages(FlightNum,UserNameFrom,UserNameTo,Status) VALUES(?,?,?,?)";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vacationToBuy);
            pstmt.setString(2, FromUser.getUserName());
            pstmt.setString(3, ToUser);
            pstmt.setString(4, "waiting");
//            pstmt.setString(4, "waiting");
            pstmt.executeUpdate();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }


    public boolean insertTradingMessage(String flightNumOffering, String userNameOffering, String flightNumToGet, String ToUser, String status) {
        String sql = "INSERT INTO TradingMessages(FlightNumOffering,UserNameOffering,FlightNumToGet,ToUser,Status) VALUES(?,?,?,?,?)";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, flightNumOffering);
            pstmt.setString(2, userNameOffering);
            pstmt.setString(3, flightNumToGet);
            pstmt.setString(4, ToUser);
            pstmt.setString(5, "waiting");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<UserMessage> searchReqMessages(User user) {

        List<UserMessage> foundMessages = new ArrayList<UserMessage>();

        String sql = "SELECT FlightNum, UserNameFrom, UserNameTo, Status " +
                "FROM Messages WHERE (UserNameTo =? ) ";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserName());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String FlightNum = rs.getString("FlightNum");
                String UserNameFrom = rs.getString("UserNameFrom");
                String UserNameTo = rs.getString("UserNameTo");
                String Status = rs.getString("Status");

                User userFrom = searchUser(UserNameFrom);
                UserMessage inboxMessage = new UserMessage(FlightNum, userFrom, user, Status);
                foundMessages.add(inboxMessage);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return foundMessages;

    }

    public List<UserTradingMessage> searchTradeReqMessages(User user) {

        List<UserTradingMessage> foundMessages = new ArrayList<>();
//TradingMessages(FlightNumOffering,UserNameOffering,FlightNumToGet,ToUser,Status)
        String sql = "SELECT FlightNumOffering, UserNameOffering, FlightNumToGet, ToUser, Status " +
                "FROM TradingMessages WHERE (ToUser =? ) ";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserName());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String flightNumOffering = rs.getString("FlightNumOffering");
                String userNameOffering = rs.getString("UserNameOffering");
                String flightNumToGet = rs.getString("FlightNumToGet");
                String toUser = rs.getString("ToUser");
                String Status = rs.getString("Status");

                User userNameOffer = searchUser(userNameOffering);
                UserTradingMessage inboxMessage = new UserTradingMessage(flightNumOffering, userNameOffer, flightNumToGet, user, Status);
                foundMessages.add(inboxMessage);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return foundMessages;

    }

    public List<UserMessage> searchAnsMessages(User user) {

        List<UserMessage> foundMessages = new ArrayList<UserMessage>();

        String sql = "SELECT FlightNum, UserNameFrom, UserNameTo, Status " +
                "FROM Messages WHERE (UserNameFrom =? ) ";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserName());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String FlightNum = rs.getString("FlightNum");
                String UserNameFrom = rs.getString("UserNameFrom");
                String UserNameTo = rs.getString("UserNameTo");
                String Status = rs.getString("Status");

                User userFrom = searchUser(UserNameFrom);
                User userTo = searchUser(UserNameTo);
                UserMessage inboxMessage = new UserMessage(FlightNum, userFrom, userTo, Status);
                foundMessages.add(inboxMessage);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return foundMessages;
    }

    public List<UserTradingMessage> searchTradeAnsMessages(User user) {

        List<UserTradingMessage> foundMessages = new ArrayList<>();

        String sql = "SELECT FlightNumOffering, UserNameOffering, FlightNumToGet, ToUser, Status " +
                "FROM TradingMessages WHERE (UserNameOffering =? ) ";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserName());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String flightNumOffering = rs.getString("FlightNumOffering");
                String userNameOffering = rs.getString("UserNameOffering");
                String flightNumToGet = rs.getString("FlightNumToGet");
                String toUser = rs.getString("ToUser");
                String status = rs.getString("Status");


                User userFrom = searchUser(userNameOffering);
                User userTo = searchUser(toUser);
                UserTradingMessage inboxMessage = new UserTradingMessage(flightNumOffering, userFrom, flightNumToGet, userTo, status);
                foundMessages.add(inboxMessage);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return foundMessages;
    }


    public boolean updateMessage(UserMessage currentMessage, String newStatus) throws SQLException {
        if (currentMessage == null || newStatus == null) {
            return false;
        } else {
            String sql = "UPDATE Messages SET Status " + " = ? "
                    + "WHERE (FlightNum = ?) AND (UserNameFrom = ?) AND (UserNameTo = ?)";
//            View.out.println(sql);
            Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, newStatus);
            pstmt.setString(2, currentMessage.getVacationToGet());
            pstmt.setString(3, currentMessage.getFromUser().getUserName());
            pstmt.setString(4, currentMessage.getToUser().getUserName());
            //update
            pstmt.executeUpdate();
            return true;
        }
    }


    public int removeMessage(UserMessage currentMessage) {
        String sql = "DELETE FROM Messages WHERE (FlightNum = ?) AND (UserNameFrom = ?) AND (UserNameTo = ?)";
        Connection conn = this.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, currentMessage.getVacationToGet());
            pstmt.setString(2, currentMessage.getFromUser().getUserName());
            pstmt.setString(3, currentMessage.getToUser().getUserName());
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }


    public int removeTradeMessage(UserTradingMessage currentMessage) {
        String sql = "DELETE FROM TradingMessages WHERE (FlightNumOffering = ?) AND (UserNameOffering = ?) AND (FlightNumToGet = ?) AND (ToUser = ?)";
        Connection conn = this.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, currentMessage.getVacationOffer());
            pstmt.setString(2, currentMessage.getFromUser().getUserName());
            pstmt.setString(3, currentMessage.getVacationToGet());
            pstmt.setString(4, currentMessage.getToUser().getUserName());
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public boolean addVacation(Vacation vacation) {
        String sql = "INSERT INTO Vacations(FlightNum, FromPlace, ToPlace, AirlineCompany, FromDate, ToDate, TicketType, Baggage, TripType, Lodging, SalerName, BuyerName) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        //java.sql.Time fromDate = new java.sql.Time(vacation.getFromDate().);

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vacation.getFlightNum());
            pstmt.setString(2, vacation.getFromPlace());
            pstmt.setString(3, vacation.getToPlace());
            pstmt.setString(4, vacation.getAirlineCompany());
            pstmt.setObject(5, vacation.getFromDate());
            pstmt.setObject(6, vacation.getToDate());
            pstmt.setString(7, vacation.getTicketType());
            pstmt.setString(8, vacation.getBaggage());
            pstmt.setString(9, vacation.getTripType());
            pstmt.setString(10, vacation.getLodging());
            pstmt.setString(11, vacation.getSaler());
            pstmt.setString(12, "NULL");

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public ArrayList<Vacation> searchVacation(String fromPlace, String toPlace, LocalDate dp_departureDate, LocalDate dp_returnDate, String ticketType) {
        ArrayList<Vacation> foundVacation = new ArrayList<Vacation>();
        String returnMinusDays = "NULL";
        String returnPlusDays = "NULL";
        if (dp_returnDate != null) {
            returnMinusDays = dp_returnDate.minusDays(3).toString();
            returnPlusDays = dp_returnDate.plusDays(3).toString();


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
                pstmt.setString(5, returnMinusDays);
                pstmt.setString(6, returnPlusDays);
                pstmt.setString(7, "NULL");

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String FlightNum = rs.getString("FlightNum");
                    String FromPlace = rs.getString("FromPlace");
                    String ToPlace = rs.getString("ToPlace");
                    String Airline = rs.getString("AirlineCompany");
                    String TicketType = rs.getString("TicketType");
                    String baggage = rs.getString("Baggage");
                    String salerName = rs.getString("SalerName");
                    String tripType = rs.getString("TripType");
                    String lodging = rs.getString("Lodging");
                    LocalDate FromDate = LocalDate.parse(rs.getString("FromDate"));
                    LocalDate ToDate = LocalDate.parse(rs.getString("ToDate"));

                    Vacation vacation = new Vacation(FlightNum, FromPlace, ToPlace, Airline, FromDate, ToDate, TicketType, baggage, tripType, lodging, salerName);
                    foundVacation.add(vacation);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return null;
            }
            return foundVacation;
        } else {
            String sql = "SELECT FlightNum, FromPlace, ToPlace, AirlineCompany, FromDate, ToDate, TicketType, Baggage, TripType, Lodging, SalerName, BuyerName "
                    + "FROM Vacations "
                    + "WHERE (FromPlace = ?) AND (ToPlace = ?) AND(FromDate BETWEEN ? AND ?) AND(BuyerName = ?);";  //AND ((FromDate BETWEEN ? AND ?) OR (ToDate BETWEEN ? AND ?))

            try (
                    Connection conn = this.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)
            ) {
                pstmt.setString(1, fromPlace);
                pstmt.setString(2, toPlace);
                pstmt.setString(3, dp_departureDate.minusDays(3).toString());
                pstmt.setString(4, dp_departureDate.plusDays(3).toString());
                pstmt.setString(5, "NULL");

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String FlightNum = rs.getString("FlightNum");
                    String FromPlace = rs.getString("FromPlace");
                    String ToPlace = rs.getString("ToPlace");
                    String Airline = rs.getString("AirlineCompany");
                    String TicketType = rs.getString("TicketType");
                    String baggage = rs.getString("Baggage");
                    String salerName = rs.getString("SalerName");
                    String tripType = rs.getString("TripType");
                    String lodging = rs.getString("Lodging");
                    LocalDate FromDate = LocalDate.parse(rs.getString("FromDate"));
                    LocalDate ToDate = null;

                    Vacation vacation = new Vacation(FlightNum, FromPlace, ToPlace, Airline, FromDate, ToDate, TicketType, baggage, tripType, lodging, salerName);
                    foundVacation.add(vacation);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return null;
            }
            return foundVacation;
        }
    }

    public ArrayList<Vacation> searchVacationTrading(String fromPlace, String toPlace, LocalDate dp_departureDate, LocalDate dp_returnDate, String ticketType) {
        ArrayList<Vacation> foundVacation = new ArrayList<Vacation>();
        String depMinusDays = dp_departureDate.minusDays(3).toString();
        String depPlusDays = dp_departureDate.plusDays(3).toString();

        String returnMinusDays = "NULL";
        String returnPlusDays = "NULL";
        if (dp_returnDate != null) {
            returnMinusDays = dp_returnDate.minusDays(3).toString();
            returnPlusDays = dp_returnDate.plusDays(3).toString();


            String sql = "SELECT FlightNum, FromPlace, ToPlace, AirlineCompany, FromDate, ToDate, TicketType, Baggage, TripType, Lodging, SalerName, BuyerName "
                    + "FROM Vacations "
                    + "WHERE (FromPlace = ?) AND (ToPlace = ?) AND(FromDate BETWEEN ? AND ?) AND(ToDate BETWEEN ? AND ?) AND(BuyerName != ?);";  //AND ((FromDate BETWEEN ? AND ?) OR (ToDate BETWEEN ? AND ?))

            try (
                    Connection conn = this.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)
            ) {
                pstmt.setString(1, fromPlace);
                pstmt.setString(2, toPlace);
                pstmt.setString(3, depMinusDays);
                pstmt.setString(4, depPlusDays);
                pstmt.setString(5, returnMinusDays);
                pstmt.setString(6, returnPlusDays);
                pstmt.setString(7, "NULL");

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String FlightNum = rs.getString("FlightNum");
                    String FromPlace = rs.getString("FromPlace");
                    String ToPlace = rs.getString("ToPlace");
                    String Airline = rs.getString("AirlineCompany");
                    String TicketType = rs.getString("TicketType");
                    String baggage = rs.getString("Baggage");
                    String salerName = rs.getString("SalerName");
                    String tripType = rs.getString("TripType");
                    String lodging = rs.getString("Lodging");
                    LocalDate FromDate = LocalDate.parse(rs.getString("FromDate"));
                    LocalDate ToDate = LocalDate.parse(rs.getString("ToDate"));

                    Vacation vacation = new Vacation(FlightNum, FromPlace, ToPlace, Airline, FromDate, ToDate, TicketType, baggage, tripType, lodging, salerName);
                    foundVacation.add(vacation);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return null;
            }
            return foundVacation;
        } else {
            String sql = "SELECT FlightNum, FromPlace, ToPlace, AirlineCompany, FromDate, ToDate, TicketType, Baggage, TripType, Lodging, SalerName, BuyerName "
                    + "FROM Vacations "
                    + "WHERE (FromPlace = ?) AND (ToPlace = ?) AND(FromDate BETWEEN ? AND ?) AND ?) AND(BuyerName != ?);";  //AND ((FromDate BETWEEN ? AND ?) OR (ToDate BETWEEN ? AND ?))

            try (
                    Connection conn = this.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)
            ) {
                pstmt.setString(1, fromPlace);
                pstmt.setString(2, toPlace);
                pstmt.setString(3, dp_departureDate.minusDays(3).toString());
                pstmt.setString(4, dp_departureDate.plusDays(3).toString());
                pstmt.setString(5, "NULL");

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String FlightNum = rs.getString("FlightNum");
                    String FromPlace = rs.getString("FromPlace");
                    String ToPlace = rs.getString("ToPlace");
                    String Airline = rs.getString("AirlineCompany");
                    String TicketType = rs.getString("TicketType");
                    String baggage = rs.getString("Baggage");
                    String salerName = rs.getString("SalerName");
                    String tripType = rs.getString("TripType");
                    String lodging = rs.getString("Lodging");
                    LocalDate FromDate = LocalDate.parse(rs.getString("FromDate"));
                    LocalDate ToDate = null;

                    Vacation vacation = new Vacation(FlightNum, FromPlace, ToPlace, Airline, FromDate, ToDate, TicketType, baggage, tripType, lodging, salerName);
                    foundVacation.add(vacation);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return null;
            }
            return foundVacation;
        }
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

        return pstmt.executeUpdate();


//        sql = "INSERT INTO VacationsOfUsers(UserName,FlightNum) VALUES(?,?)";
//            PreparedStatement pstmt1 = conn.prepareStatement(sql);
//            pstmt1.setString(1, buyer);
//            pstmt1.setString(2, vacationToBuy.getFlightNum());
//            pstmt1.executeUpdate();
//
//        return pstmt.executeUpdate();
    }


    public ArrayList<Vacation> getUserVacations(String userName) {
        ArrayList<Vacation> userVacations = new ArrayList<Vacation>();

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
                String FlightNum = rs.getString("FlightNum");
                String FromPlace = rs.getString("FromPlace");
                String ToPlace = rs.getString("ToPlace");
                String Airline = rs.getString("AirlineCompany");
                String TicketType = rs.getString("TicketType");
                String baggage = rs.getString("Baggage");
                String salerName = rs.getString("SalerName");
                String tripType = rs.getString("TripType");
                String lodging = rs.getString("Lodging");
                LocalDate FromDate = LocalDate.parse(rs.getString("FromDate"));
                LocalDate ToDate = LocalDate.parse(rs.getString("ToDate"));

                Vacation vacation = new Vacation(FlightNum, FromPlace, ToPlace, Airline, FromDate, ToDate, TicketType, baggage, tripType, lodging, salerName);
                userVacations.add(vacation);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return userVacations;
    }


    public boolean updateTradingMessage(UserTradingMessage currentMessage, String newStatus) throws SQLException {
        if (currentMessage == null || newStatus == null) {
            return false;
        }
        //       (FlightNumOffering,UserNameOffering,FlightNumToGet,FromUser,Status) VALUES(?,?,?,?,?)";
        else {
            String sql = "UPDATE TradingMessages SET Status " + " = ? "
                    + "WHERE (FlightNumOffering = ?) AND (UserNameOffering = ?) AND (FlightNumToGet = ?) AND (ToUser = ?)";
//            View.out.println(sql);
            Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, newStatus);
            pstmt.setString(2, currentMessage.getVacationOffer());
            pstmt.setString(3, currentMessage.getFromUser().getUserName());
            pstmt.setString(4, currentMessage.getVacationToGet());
            pstmt.setString(5, currentMessage.getToUser().getUserName());
            //update
            pstmt.executeUpdate();
            return true;
        }
    }

    public List<Vacation> searchAllVacations() {
        List<Vacation> foundVacation = new ArrayList<>();
        Vacation CurrentVacation = null;

        String sql = "SELECT *"
                + "FROM Vacations "
                + "WHERE (BuyerName == ?);";  //AND ((FromDate BETWEEN ? AND ?) OR (ToDate BETWEEN ? AND ?))

        try (
                Connection conn = this.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, "NULL");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String FlightNum = rs.getString("FlightNum");
                String FromPlace = rs.getString("FromPlace");
                String ToPlace = rs.getString("ToPlace");
                String Airline = rs.getString("AirlineCompany");
                String TicketType = rs.getString("TicketType");
                String baggage = rs.getString("Baggage");
                String salerName = rs.getString("SalerName");
                String tripType = rs.getString("TripType");
                String lodging = rs.getString("Lodging");
                LocalDate FromDate = LocalDate.parse(rs.getString("FromDate"));
                LocalDate ToDate = LocalDate.parse(rs.getString("ToDate"));
                String BuyerName = rs.getString("BuyerName");

                CurrentVacation = new Vacation(FlightNum, FromPlace, ToPlace, Airline, FromDate, ToDate, TicketType, baggage, tripType, lodging, salerName, BuyerName);
                foundVacation.add(CurrentVacation);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return foundVacation;

    }

    public List<Vacation> searchAllTradingVacations() {
        List<Vacation> foundVacation = new ArrayList<>();
        Vacation CurrentVacation = null;

        String sql = "SELECT *"
                + "FROM Vacations "
                + "WHERE (BuyerName != ?);";  //AND ((FromDate BETWEEN ? AND ?) OR (ToDate BETWEEN ? AND ?))

        try (
                Connection conn = this.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, "NULL");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String FlightNum = rs.getString("FlightNum");
                String FromPlace = rs.getString("FromPlace");
                String ToPlace = rs.getString("ToPlace");
                String Airline = rs.getString("AirlineCompany");
                String TicketType = rs.getString("TicketType");
                String baggage = rs.getString("Baggage");
                String salerName = rs.getString("SalerName");
                String tripType = rs.getString("TripType");
                String lodging = rs.getString("Lodging");
                LocalDate FromDate = LocalDate.parse(rs.getString("FromDate"));
                LocalDate ToDate = LocalDate.parse(rs.getString("ToDate"));
                String BuyerName = rs.getString("BuyerName");

                CurrentVacation = new Vacation(FlightNum, FromPlace, ToPlace, Airline, FromDate, ToDate, TicketType, baggage, tripType, lodging, salerName, BuyerName);
                foundVacation.add(CurrentVacation);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return foundVacation;
    }

    public void insertUser(String userName, String password, String birthDate, String firstName, String lastName, String city, String phoneNumber, boolean b) throws SQLException {
        User user = new User(userName, password, birthDate, firstName, lastName, city, phoneNumber, b);
        String sql = "INSERT INTO Users(UserName,Password,BirthDate,FirstName,LastName,City,PhoneNumber) VALUES(?,?,?,?,?,?,?)";
        Connection conn = this.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getUserName());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getbirthDate());
        pstmt.setString(4, user.getFirstName());
        pstmt.setString(5, user.getLastName());
        pstmt.setString(6, user.getCity());
        pstmt.setString(7, user.getPhoneNumber());
        pstmt.executeUpdate();
    }

    public String getUserContactInformation(String userName) {
        String phoneNumber = "";
        String sql = "SELECT PhoneNumber"
                + "FROM Users "
                + "WHERE (UserName = ?);";

        try (
                Connection conn = this.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            phoneNumber = rs.getString("PhoneNumber");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phoneNumber;
    }
}