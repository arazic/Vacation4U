package sample.View;


import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class View extends java.util.Observable {

    @FXML
    public javafx.scene.control.Button btn_create;
    public javafx.scene.control.TextField txtfld_userName;
    public javafx.scene.control.TextField txtfld_password;
    public javafx.scene.control.DatePicker txtfld_birthDate;
    public javafx.scene.control.TextField txtfld_firstName;
    public javafx.scene.control.TextField txtfld_lastName;
    public javafx.scene.control.TextField txtfld_city;
    public boolean createUser = false;


    public void goToCreatePage(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("createPage.fxml"));
        Stage stage = (Stage) btn_create.getScene().getWindow();
        stage.setTitle("Create user");
        Scene scene2= new Scene(root,300,300);
        stage.setScene(scene2);
        stage.show();
    }


    public void createNewUser(ActionEvent actionEvent) {
        System.out.println(" " + txtfld_firstName.getText() + " " + txtfld_lastName.getText() + " " + txtfld_birthDate.getValue());
        if (checkValuesIsLegal()){
            createUser = true;
        }

    }

    private boolean checkValuesIsLegal() {
        return true;
    }
}
