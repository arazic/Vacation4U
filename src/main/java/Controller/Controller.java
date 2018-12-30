package Controller;

import Model.*;
import View.System;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private System system;
    private Vacation4UManager vacation4UManager;


    public Controller(Vacation4UManager vacation4UManager) {
        this.vacation4UManager = vacation4UManager;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public boolean createUser(User user) {
        return vacation4UManager.createUser(user);
    }

    public boolean editUser(String userNameToedit, String optionToChange, String newValue) {
        try {
            return Vacation4UManager.dataBase.updateUserData(userNameToedit, optionToChange, newValue);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User seacrhUser(String userNameToSearch) {
        return Vacation4UManager.dataBase.searchUser(userNameToSearch);
    }

    public boolean deleteUser(String userNameToDelete) throws SQLException {
        if (Vacation4UManager.dataBase.deleteUser(userNameToDelete) == 1)
            return true;
        return false;
    }

    public boolean insertTradingMessage(userMessage message) {
        try {
            return vacation4UManager.insertTradingMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<userMessage> searchAnsMessages(User registeredUser) {
        return vacation4UManager.searchAnsMessages(registeredUser);

    }

    public List<userMessage> searchReqMessages(User registeredUser) {
        return vacation4UManager.searchReqMessages(registeredUser);
    }

    public Vacation searchVacationFlightNumBySeller(String flightNum, String seller) {
        return vacation4UManager.searchVacationFlightNumBySeller(flightNum, seller);
    }

    public boolean updateMessage(userMessage currentMessage, String newStatus) {
        return vacation4UManager.updateMessage(currentMessage, newStatus);
    }


    public boolean removeMessage(userMessage currentMessage) {
        return vacation4UManager.removeMessage(currentMessage);

    }

    public boolean addVacationToSell(Vacation vacation) {
        return vacation4UManager.addVacationToSell(vacation);
    }

    public ArrayList<Vacation> searchVacation(String fromPlace, String toPlace, LocalDate dp_departureDate, LocalDate dp_returnDate, String ticketType) {
        return vacation4UManager.searchVacation(fromPlace, toPlace, dp_departureDate, dp_returnDate, ticketType);
    }

    public boolean deleteVacation(Vacation vacationToBuy) {
        return vacation4UManager.deleteVacation(vacationToBuy);

    }

    public boolean updateVacationSell(Vacation vacationToBuy, String buyer) {
        return vacation4UManager.updateVacationSell(vacationToBuy, buyer);

    }

    public ArrayList<Vacation> getUserVacations(String userName) {
        return vacation4UManager.getUserVacations(userName);
    }

    public ArrayList<Vacation> searchVacationTrading(String fromPlace, String toPlace, LocalDate dp_departureDate, LocalDate dp_returnDate, String ticketType) {
        return vacation4UManager.searchVacationTrading(fromPlace, toPlace, dp_departureDate, dp_returnDate, ticketType);
    }

    public boolean updateTradingMessage(userMessage currentMessage, String newStatus) {
        return vacation4UManager.updateTradingMessage(currentMessage, newStatus);

    }

    public List<userMessage> searchTraidReqMessages(User User) {
        return vacation4UManager.searchTraidReqMessages(User);
    }

    public List<userMessage> searchTraidAnsMessages(User User) {
        return vacation4UManager.searchTraidAnsMessages(User);
    }

    public boolean removeTraidMessage(userMessage currentMessage) {
        return vacation4UManager.removeTraidMessage(currentMessage);
    }


    public Vacation searchVacationFlightNumByBuyer(String vacation, String userName) {
        return vacation4UManager.searchVacationFlightNumByBuyer(vacation, userName);
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

//            if (controller.sendTradingRequestMessage(registeredUser, vacationToOffer.getFlightNum(), vacationToBuy.getFlightNum(), vacationToBuy.getBuyer())){

    public boolean sendTradingRequestMessage(User fromUser, String offerVacationNum, String requestFlightNum, String toUser) {
        return fromUser.sendTradingRequestMessage(offerVacationNum, requestFlightNum, toUser);
//        User BuyerUser = controller.seacrhUser(vacationToBuy.getBuyer());
//        if (!registeredUser.getUserName().equals(BuyerUser.getUserName())) {
//            User buyer= controller.seacrhUser(vacationToBuy.getBuyer());
//            userMessage message= new userMessage(vacationToOffer.getFlightNum(), registeredUser,vacationToBuy.getFlightNum(),buyer,"waiting");
//            registeredUser.addIncomingTradingReqMessages(message);
        // sellerUser.addIncomingTradingReqMessages(Message);
//        try {
////                    if (controller.insertTradingMessage(Message)) {
//            if (controller.insertTradingMessage(message)) {
    }


//                if (controller.createUser(txtfld_userName.getText(), txtfld_password.getText(), txtfld_birthDate.getValue().toString(),
//                    txtfld_firstName.getText(), txtfld_lastName.getText(), txtfld_city.getText(), false)) {

    public boolean createUser(String userName, String password, String birthDate, String firstName, String lastName, String city, boolean b) {
        try {
            Vacation4UManager.dataBase.insertUser(userName, password, birthDate, firstName, lastName, city, b);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}



