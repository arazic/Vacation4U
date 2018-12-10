package Controller;

import Model.Model;
import View.System;
import View.User;
import View.Vacation;
import View.userMessage;

import java.sql.SQLException;
import java.util.List;

public class Controller  {
    private System system;
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void setSystem(System system){
        this.system = system;
    }

    public boolean createUser(User user) {
        return model.createUser(user);
    }

    public boolean editUser(String userNameToedit, String optionToChange, String newValue) {
        return model.editUser(userNameToedit, optionToChange,newValue);
    }

    public User seacrhUser(String userNameToSearch) {
        return model.searchUser(userNameToSearch);
    }

    public boolean deleteUser(String userNameToDelete) throws SQLException {
        return model.deleteUser(userNameToDelete);
    }

    public List<Vacation> searchVacation(Vacation vacationTerms) {
        return model.searchVacation(vacationTerms);
    }

    public boolean insertMessage(userMessage Message) throws Exception {
        return model.insertMessage(Message);
    }

    public List<userMessage> searchAnsMessages(User registeredUser) {
        return model.searchAnsMessages(registeredUser);

    }

    public List<userMessage> searchReqMessages(User registeredUser) {
        return model.searchReqMessages(registeredUser);
        }

    public Vacation searchVacationByFlightNum(String vacation) {
        return model.searchVacationByFlightNum(vacation);
    }

    public boolean updateMessage(userMessage currentMessage, String newStatus) {
        return model.updateMessage(currentMessage,newStatus);
    }


    public boolean removeMessage(userMessage currentMessage) {
        return model.removeMessage(currentMessage);

    }
}



