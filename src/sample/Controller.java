package sample;

import javafx.event.ActionEvent;
import sample.View.View;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {
    private View view;
    private Model model;

    public Controller(Model model, View view) {
        this.view = view;
        this.model = model;
        System.out.println("fada");
    }

    @Override
    public void update(Observable o, Object arg) {

        System.out.println("update method on controller b4 if");
        if (view.createUser){
            System.out.println("update method on con..");
            model.createUser(view.txtfld_userName.getText(), view.txtfld_password.getText() , view.txtfld_birthDate.getValue(),
                    view.txtfld_firstName.getText(), view.txtfld_lastName.getText(), view.txtfld_city.getText());
        }

    }


}
