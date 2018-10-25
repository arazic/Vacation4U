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

    public void createUser(User user) {
            model.createUser(user);
        }

    public boolean editUser(String userNameToedit, String optionToChange, String newValue) {
        return model.editUser(userNameToedit, optionToChange,newValue);
    }
}



