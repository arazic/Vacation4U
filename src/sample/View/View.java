package sample.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controller;
import sample.User;

import java.io.IOException;

public class View  {

    @FXML
    public javafx.scene.control.Button btn_create;
    public javafx.scene.control.TextField txtfld_userName;
    public javafx.scene.control.PasswordField txtfld_password;
    public javafx.scene.control.DatePicker txtfld_birthDate;
    public javafx.scene.control.TextField txtfld_firstName;
    public javafx.scene.control.TextField txtfld_lastName;
    public javafx.scene.control.TextField txtfld_city;

    private static Controller controller;

    public void setController(Controller controller) {
        this.controller= controller;
    }

    public void goToCreatePage(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("createPage.fxml").openStream());
        Stage stage = (Stage) btn_create.getScene().getWindow();
        stage.setTitle("Create user");
        Scene scene2= new Scene(root,300,300);
        stage.setScene(scene2);
        View view = fxmlLoader.getController();
        stage.show();
    }


    public void createNewUser(ActionEvent actionEvent) throws IOException {

        System.out.println(" " + txtfld_firstName.getText() + " " + txtfld_lastName.getText() + " " + txtfld_birthDate.getValue().toString());
        if (checkValuesIsLegal()){
            //to add checks
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("sample.fxml").openStream());
            Scene scene = new Scene(root, 600, 575);
            Stage stage = (Stage) txtfld_userName.getScene().getWindow();
            stage.setScene(scene);
            View view = fxmlLoader.getController();
            stage.show();
            User user= new User(txtfld_firstName.getText(),txtfld_password.getText(),txtfld_birthDate.getValue().toString(),
                    txtfld_firstName.getText(), txtfld_lastName.getText(), txtfld_city.getText() ) ;
            System.out.println(user.toString())
            ;
            controller.createUser(user);
        }
    }

    private boolean checkValuesIsLegal() {
        return true;
    }

}
