package Controller;

import Model.Model;
import View.View;
import View.User;
import View.Vacation;
import View.userMessage;

import java.sql.SQLException;
import java.util.List;

public class Controller  {
    private View view;
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void setView(View view){
        this.view = view;
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

    public List<userMessage> searchReqFromPurchaser(User registeredUser) {
        return model.searchReqFromPurchaser(registeredUser);

    }

    public List<userMessage> searchAnsFromSalers(User registeredUser) {
        return model.searchAnsFromSalers(registeredUser);
        }

    public Vacation searchVacationByFlightNum(Vacation vacation) {
        return model.searchVacationByFlightNum(vacation);
    }
}



