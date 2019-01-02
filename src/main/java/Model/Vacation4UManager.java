package Model;

import Controller.Controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Vacation4UManager {
    private static Controller controller;
    private static DataBase dataBase;
    private static List<Vacation> foundVacation;
    private static Vacation vacationToBuy;
    private static Vacation vacationToOffer;
    private static User registeredUser;
    private static List<String> pagesApp = new ArrayList<>();
    private static UserMessage currentMessage;



    public static DataBase getDataBase() {
        return dataBase;
    }

    public static void setDataBase(DataBase dataBase) {
        Vacation4UManager.dataBase = dataBase;
    }
    public Vacation4UManager(DataBase dataBase) {
        this.dataBase= dataBase;
    }

    public static List<Vacation> getFoundVacation() {
        return foundVacation;
    }

    public static void setFoundVacation(List<Vacation> foundVacation) {
        Vacation4UManager.foundVacation = foundVacation;
    }

    public static Vacation getVacationToBuy() {
        return vacationToBuy;
    }

    public static void setVacationToBuy(Vacation vacationToBuy) {
        Vacation4UManager.vacationToBuy = vacationToBuy;
    }

    public static Vacation getVacationToOffer() {
        return vacationToOffer;
    }

    public static void setVacationToOffer(Vacation vacationToOffer) {
        Vacation4UManager.vacationToOffer = vacationToOffer;
    }

    public static User getRegisteredUser() {
        return registeredUser;
    }

    public static void setRegisteredUser(User registeredUser) {
        Vacation4UManager.registeredUser = registeredUser;
    }

    public static List<String> getPagesApp() {
        return pagesApp;
    }

    public static void setPagesApp(List<String> pagesApp) {
        Vacation4UManager.pagesApp = pagesApp;
    }

    public static UserMessage getCurrentMessage() {
        return currentMessage;
    }

    public static void setCurrentMessage(UserMessage currentMessage) {
        Vacation4UManager.currentMessage = currentMessage;
    }

    public void setController(Controller controller) {
        this.controller=controller;
    }

    public boolean editUser(String userNameToedit, String optionToChange, String newValue) {
        try {
            return dataBase.updateUserData(userNameToedit, optionToChange, newValue);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User searchUser(String userNameToSearch) {
        return dataBase.searchUser(userNameToSearch);
    }

    public boolean deleteUser(String userNameToDelete) throws SQLException {
        if (dataBase.deleteUser(userNameToDelete) == 1)
            return true;
        return false;
    }

    public List<UserMessage> searchAnsMessages(User registeredUser) {
        return dataBase.searchAnsMessages(registeredUser);
    }

    public List<UserMessage> searchReqMessages(User registeredUser) {
        return dataBase.searchReqMessages(registeredUser);
    }

    public Vacation searchVacationFlightNumBySeller(String flightNum, String seller) {
        return dataBase.searchVacationFlightNumBySeller(flightNum, seller);
    }

    public boolean updateMessage(UserMessage currentMessage, String newStatus) {
        try {
            return dataBase.updateMessage(currentMessage,newStatus);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean removeMessage(UserMessage currentMessage) {
        return dataBase.removeMessage(currentMessage) == 1;
    }


    public ArrayList<Vacation> searchVacation(String fromPlace, String toPlace, LocalDate dp_departureDate, LocalDate dp_returnDate, String ticketType) {
        return dataBase.searchVacation(fromPlace, toPlace, dp_departureDate, dp_returnDate, ticketType);
    }

    public boolean deleteVacation(Vacation vacationToDelete) {
        try {
            if(dataBase.deleteVacation(vacationToDelete)==1){
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

    public boolean updateTradingMessage(UserTradingMessage currentMessage, String newStatus) {
        try {
            return dataBase.updateTradingMessage(currentMessage,newStatus);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<UserTradingMessage> searchTradeReqMessages(User user) {
        return dataBase.searchTradeReqMessages(user);
    }

    public List<UserTradingMessage> searchTradeAnsMessages(User user) {
        return dataBase.searchTradeAnsMessages(user);
    }

    public boolean removeTradeMessage(UserTradingMessage currentMessage) {
        return dataBase.removeTradeMessage(currentMessage) == 1;
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


    public boolean createUser(String userName, String password, String birthDate, String firstName, String lastName, String city, boolean b) {
        try {
            dataBase.insertUser(userName, password, birthDate, firstName, lastName, city, b);
            return true;
        }catch (Exception e){
            return false;
        }

    }







}
