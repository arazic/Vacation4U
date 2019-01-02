package Controller;

import Model.*;
import View.View;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    public static DataBase dataBase;
    private View view;

    public Controller(DataBase dataBase) {
        this.dataBase = dataBase;
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

    public boolean sendBuyingRequestMessage(User registeredUser, String flightNum, String sellerUser) {
        return registeredUser.sendBuyingRequestMessage(flightNum, sellerUser);
    }


    public boolean sendTradingRequestMessage(User fromUser, String offerVacationNum, String requestFlightNum, String toUser) {
        return fromUser.sendTradingRequestMessage(offerVacationNum, requestFlightNum, toUser);
    }

    public boolean createUser(String userName, String password, String birthDate, String firstName, String lastName, String city, boolean b) {
        try {
            dataBase.insertUser(userName, password, birthDate, firstName, lastName, city, b);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public void setUserVacations(User user) {
        user.setMyVacations(dataBase.getUserVacations(user.getUserName()));
    }

    public boolean addVacationToSell(String flightNum, String from, String to, String airlineCompany, LocalDate fromDate, LocalDate toDate, String ticketType, String baggageWeight, String kind, String lodging, User registeredUser) {
        return registeredUser.addVacationToSell(flightNum, from, to, airlineCompany, fromDate, toDate, ticketType, baggageWeight, kind, lodging);
    }

    public void setView(View view) {
        this.view = view;
    }
}



