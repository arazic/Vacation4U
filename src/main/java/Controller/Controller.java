package Controller;

import Model.*;
import View.View;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static Vacation4UManager vacation4UManager;
    private static View view;

    public Controller( Vacation4UManager Vacation4UManager) {
        this.vacation4UManager = Vacation4UManager;
    }

    public Vacation4UManager getVacation4UManager() {
        return vacation4UManager;
    }

    public void setVacation4UManager(Vacation4UManager vacation4UManager) {
        this.vacation4UManager = vacation4UManager;
    }

    public boolean editUser(String userNameToedit, String optionToChange, String newValue) {
            return vacation4UManager.editUser(userNameToedit, optionToChange, newValue);
    }

    public User searchUser(String userNameToSearch) {
        return vacation4UManager.searchUser(userNameToSearch);
    }

    public boolean deleteUser(String userNameToDelete) throws SQLException {
        return vacation4UManager.deleteUser(userNameToDelete);

    }

    public List<UserMessage> searchAnsMessages(User registeredUser) {
        return vacation4UManager.searchAnsMessages(registeredUser);
    }

    public List<UserMessage> searchReqMessages(User registeredUser) {
        return vacation4UManager.searchReqMessages(registeredUser);
    }

    public Vacation searchVacationFlightNumBySeller(String flightNum, String seller) {
        return vacation4UManager.searchVacationFlightNumBySeller(flightNum, seller);
    }

    public boolean updateMessage(UserMessage currentMessage, String newStatus) {

        return vacation4UManager.updateMessage(currentMessage,newStatus);

    }


    public boolean removeMessage(UserMessage currentMessage) {
        return vacation4UManager.removeMessage(currentMessage);
    }


    public ArrayList<Vacation> searchVacation(String fromPlace, String toPlace, LocalDate dp_departureDate, LocalDate dp_returnDate, String ticketType) {
        return vacation4UManager.searchVacation(fromPlace, toPlace, dp_departureDate, dp_returnDate, ticketType);
    }

    public boolean deleteVacation(Vacation vacationToDelete) {
         return vacation4UManager.deleteVacation(vacationToDelete);

    }

    public boolean updateVacationSell(Vacation vacationToBuy, String buyer) {
       return  vacation4UManager.updateVacationSell(vacationToBuy, buyer);

    }

    public ArrayList<Vacation> getUserVacations(String userName) {
        return vacation4UManager.getUserVacations(userName);
    }

    public ArrayList<Vacation> searchVacationTrading(String fromPlace, String toPlace, LocalDate dp_departureDate, LocalDate dp_returnDate, String ticketType) {
        return vacation4UManager.searchVacationTrading(fromPlace,toPlace,dp_departureDate,dp_returnDate,ticketType);
    }

    public boolean updateTradingMessage(UserTradingMessage currentMessage, String newStatus) {
            return vacation4UManager.updateTradingMessage(currentMessage,newStatus);

    }

    public List<UserTradingMessage> searchTradeReqMessages(User user) {
        return vacation4UManager.searchTradeReqMessages(user);
    }

    public List<UserTradingMessage> searchTradeAnsMessages(User user) {
        return vacation4UManager.searchTradeAnsMessages(user);
    }

    public boolean removeTradeMessage(UserTradingMessage currentMessage) {
        return vacation4UManager.removeTradeMessage(currentMessage);
    }


    public Vacation searchVacationFlightNumByBuyer(String vacation, String userName) {
        return vacation4UManager.searchVacationFlightNumByBuyer(vacation,userName);
    }

    public List<Vacation> searchAllVacations() {
        return vacation4UManager.searchAllVacations();
    }

    public List<Vacation> searchAllTradingVacations() {
        return vacation4UManager.searchAllTradingVacations();
    }

    public boolean sendBuyingRequestMessage(User registeredUser, String flightNum, String sellerUser) {
        return registeredUser.sendBuyingRequestMessage(flightNum, sellerUser);
    }


    public boolean sendTradingRequestMessage(User fromUser, String offerVacationNum, String requestFlightNum, String toUser) {
        return fromUser.sendTradingRequestMessage(offerVacationNum, requestFlightNum, toUser);
    }

    public boolean createUser(String userName, String password, String birthDate, String firstName, String lastName, String city, boolean b) {
        try {
            vacation4UManager.createUser(userName, password, birthDate, firstName, lastName, city, b);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public void setUserVacations(User user) {
        user.setMyVacations(vacation4UManager.getUserVacations(user.getUserName()));
    }

    public boolean addVacationToSell(String flightNum, String from, String to, String airlineCompany, LocalDate fromDate, LocalDate toDate, String ticketType, String baggageWeight, String kind, String lodging, User registeredUser) {
        return registeredUser.addVacationToSell(flightNum, from, to, airlineCompany, fromDate, toDate, ticketType, baggageWeight, kind, lodging);
    }

    public void setView(View view) {
        this.view = view;
    }
}



