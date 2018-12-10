package Model;

import Controller.Controller;
import View.User;
import View.Vacation;
import View.userMessage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Model  {

    private static Controller controller;
    private DataBase dataBase;
//    private Connection db_connection;

    public Model() {
        dataBase = new DataBase("Vacation4u");
        dataBase.createNewDatabase();
        dataBase.connect();
        dataBase.createUserNewTable();
        dataBase.createVacationNewTable();
        dataBase.createMessagesNewTable();
    }

    public void setController(Controller controller) {
        this.controller= controller;
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

    public List<userMessage> searchAnsMessages(User registeredUser) {
        return dataBase.searchAnsMessages(registeredUser);
    }

    public List<userMessage> searchReqMessages(User registeredUser) {
        return dataBase.searchReqMessages(registeredUser);
    }

    public Vacation searchVacationFlightNum(String vacation) {
        return dataBase.searchVacationFlightNum(vacation);
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
}
