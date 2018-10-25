package sample.View;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import sample.Controller;
import sample.User;

import java.io.IOException;
import java.time.LocalDate;

public class View  {

    @FXML
    public javafx.scene.control.Button btn_create;
    public javafx.scene.control.Button btn_edit;
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
        Parent root = fxmlLoader.load(getClass().getResource("createUser.fxml").openStream());
        Stage stage = (Stage) btn_create.getScene().getWindow();
        stage.setTitle("Create user");
        Scene scene2= new Scene(root,300,300);
        stage.setScene(scene2);
        View view = fxmlLoader.getController();
        stage.show();
    }

    public void goToEditPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("editUser.fxml").openStream());
        Stage stage = (Stage) btn_create.getScene().getWindow();
        stage.setTitle("Create user");
        Scene scene2= new Scene(root,400,350);
        stage.setScene(scene2);
        View view = fxmlLoader.getController();
        stage.show();
    }


    public void createNewUser(ActionEvent actionEvent) throws IOException {

      //  System.out.println(" " + txtfld_firstName.getText() + " " + txtfld_lastName.getText() + " " + txtfld_birthDate.getValue().toString());
        if (checkValuesIsLegal(txtfld_userName.getText(),txtfld_password.getText(),txtfld_birthDate.getValue(),
                txtfld_firstName.getText(), txtfld_lastName.getText(), txtfld_city.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Congratulations");
            alert.setHeaderText("A new user has been created named "+txtfld_userName.getText()+ " !!!");

            alert.showAndWait();
            User user= new User(txtfld_firstName.getText(),txtfld_password.getText(),txtfld_birthDate.getValue().toString(),
                    txtfld_firstName.getText(), txtfld_lastName.getText(), txtfld_city.getText() ) ;
            System.out.println(user.toString())
            ;
            controller.createUser(user);
        }
            //to add checks
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("Menu.fxml").openStream());
            Scene scene = new Scene(root, 300, 300);
            Stage stage = (Stage) txtfld_userName.getScene().getWindow();
            stage.setScene(scene);
            View view = fxmlLoader.getController();
            stage.show();
        }


    private boolean checkValuesIsLegal(String userName, String password, LocalDate birthDate,
                                       String firstName, String lastName, String city) {
        String error="";
        if(userName.isEmpty() || password.isEmpty()  ||birthDate==null  ||firstName.isEmpty()  || lastName.isEmpty()  ||city.isEmpty()){
            if(userName.isEmpty() )
                error="You have to choose User name!";
            else if(password.isEmpty() )
                error="You have to choose Password!";
            else if(birthDate==null || birthDate.toString().isEmpty() )
                error="You have to choose Birth Date!";
            else if(firstName.isEmpty() )
                error="You have to choose First Name!";
            else if(lastName.isEmpty() )
                error="You have to choose Last Name!";
            else if(city.isEmpty() )
                error="You have to choose City!";

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Details are invalid\n");
            alert.setHeaderText(error);
            alert.setContentText("Try again");

            alert.showAndWait();
            return false;

        }
        return true;

    }





}
