package Model;

import Controller.Controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Vacation4UManager {

    private final User administrator;

    private static Controller controller;
    public static DataBase dataBase;
//    private Connection db_connection;

    public Vacation4UManager(User administrator) {
        this.administrator = administrator;
        dataBase = new DataBase("Vacation4u");
        dataBase.createNewDatabase();
        dataBase.connect();
        dataBase.createUserNewTable();
        dataBase.createVacationNewTable();
        dataBase.createMessagesNewTable();
        dataBase.createVacationsOfUsersNewTable();
        try {
            dataBase.insertUser(administrator);
        } catch (Exception e) {
            System.out.println("The admin username of the system is: " + administrator.getUserName()) ;
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public boolean createUser(User user) {
        try{
            dataBase.insertUser(user);
//            System.out.println("User should be on the DB:" + user);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public boolean editUser(String userNameToEdit, String optionToChange, String newValue) {
        try {
            return dataBase.updateUserData(userNameToEdit, optionToChange, newValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User searchUser(String userNameToSearch) {
        //return new User("DEMO123", "123456", "12.10.92", "FIRST DEMO", "LAST DEMO", "DEMO CITY");
        return dataBase.searchUser(userNameToSearch);
    }

    public boolean deleteUser(String userNameToDelete) throws SQLException {
        if(dataBase.deleteUser(userNameToDelete)==1){
            return true;
        }
        return false;
    }

  /*  public List<Vacation> searchVacation(Vacation vacationTerms) {
        return dataBase.searchVacation(vacationTerms);
    }*/

    public boolean insertMessage(userMessage Message) throws Exception {
        try{
            dataBase.insertMessage(Message.getFromUser(),Message.getVacationToBuy(), Message.getToUser().getUserName());
            Message.getToUser().setMessageNum(Message.getToUser().getMessageNum()+1);
//            System.out.println("User should be on the DB:" + user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

//        String sql = "INSERT INTO TradingMessages
// (FlightNumToGet,UserNameFrom,FlightNumOffering,UserNameOffering,Status) VALUES(?,?,?,?)";
    //    public userMessage
// (String vacationOffering, User fromUser,  String vacationToBuy, User toUser, String status) {
    public boolean insertTradingMessage(userMessage Message) throws  Exception{
        try{
            dataBase.insertTradingMessage(Message.getVacationOffer() ,Message.getFromUser().getUserName(),
                    Message.getVacationToBuy(), Message.getToUser().getUserName(), Message.getStatus());
            Message.getToUser().setMessageNum(Message.getToUser().getMessageNum()+1);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public List<userMessage> searchAnsMessages(User registeredUser) {
        return dataBase.searchAnsMessages(registeredUser);
    }

    public List<userMessage> searchReqMessages(User registeredUser) {
        return dataBase.searchReqMessages(registeredUser);
    }

    public Vacation searchVacationFlightNumBySeller(String flightNum, String seller) {
        return dataBase.searchVacationFlightNumBySeller(flightNum, seller);
    }

    public boolean updateMessage(userMessage currentMessage, String newStatus) {
        try {
            return dataBase.updateMessage(currentMessage,newStatus);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeMessage(userMessage currentMessage) {
        if(dataBase.removeMessage(currentMessage)==1)
            return true;
        return false;
    }

    public boolean addVacationToSell(Vacation vacation) {
        return dataBase.addVacation(vacation);
    }

    public ArrayList<Vacation> searchVacation(String fromPlace, String toPlace, LocalDate dp_departureDate, LocalDate dp_returnDate, String ticketType) {
            return dataBase.searchVacation(fromPlace, toPlace, dp_departureDate, dp_returnDate, ticketType);

    }


    public boolean deleteVacation(Vacation vacationToBuy) {
        try {
            if(dataBase.deleteVacation(vacationToBuy)==1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateVacationSell(Vacation vacationToBuy, String buyer) {
        try {
            if(dataBase.updateVacationSell(vacationToBuy, buyer)==1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Vacation> getUserVacations(String userName) {
        return dataBase.getUserVacations(userName);
    }

    public ArrayList<Vacation> searchVacationTrading(String fromPlace, String toPlace, LocalDate dp_departureDate, LocalDate dp_returnDate, String ticketType) {
        return dataBase.searchVacationTrading(fromPlace,toPlace,dp_departureDate,dp_returnDate,ticketType);
    }

    public boolean updateTradingMessage(userMessage currentMessage, String newStatus) {

        try {
            return dataBase.updateTradingMessage(currentMessage,newStatus);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<userMessage> searchTraidReqMessages(User user) {
        return dataBase.searchTraidReqMessages(user);
    }

    public List<userMessage> searchTraidAnsMessages(User user) {
        return dataBase.searchTraidAnsMessages(user);
    }

    public boolean removeTraidMessage(userMessage currentMessage) {
        if(dataBase.removeTraidMessage(currentMessage)==1)
            return true;
        return false;
    }


    public Vacation searchVacationFlightNumByBuyer(String vacation, String userName) {
        return dataBase.searchVacationFlightNumByBuyer(vacation,userName);
    }

    public List<Vacation> searchAllVacations() {
        return dataBase.searchAllVacations();
    }

    public List<Vacation> searchAllTradingVacations() {
        return dataBase.searchAllTradingVacations();
    }
}
