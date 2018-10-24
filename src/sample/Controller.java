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
        if (user.isLegal()){
            System.out.println("update method on con..");
            model.createUser(user);
        }

    }


}
