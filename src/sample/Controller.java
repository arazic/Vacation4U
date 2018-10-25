package sample;

import sample.Model.Model;
import sample.View.View;

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

    public boolean deleteUser(String userNameToDelete) {
        return model.deleteUser(userNameToDelete);
    }
}



