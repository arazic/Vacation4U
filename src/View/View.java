package View;

import Controller.Controller;
import Controller.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class View  {

    @FXML
    public javafx.scene.control.Button btn_create;
    public javafx.scene.control.TextField txtfld_userName;
    public javafx.scene.control.PasswordField txtfld_password;
    public javafx.scene.control.DatePicker txtfld_birthDate;
    public javafx.scene.control.TextField txtfld_firstName;
    public javafx.scene.control.TextField txtfld_lastName;
    public javafx.scene.control.TextField txtfld_city;
    public javafx.scene.control.Button btn_edit;
    public javafx.scene.control.TextField txtfld_userNameToedit;
    public javafx.scene.control.TextField txtfld_newValue;
    public javafx.scene.control.ChoiceBox<String> chbx_optionToChange;
    public javafx.scene.control.Button btn_search;
    public javafx.scene.control.TextField txtfld_userNameToSearch;
    public javafx.scene.control.TextField  txtfld_userDetails;
    public javafx.scene.control.Button btn_backToMenu;
    public javafx.scene.control.TextField  txtfld_userNameToDelete;
    public javafx.scene.control.Button btn_delete;

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
        scene2.getStylesheets().add(getClass().getResource("MenuStyle.css").toExternalForm());
        stage.setScene(scene2);
        View view = fxmlLoader.getController();
        stage.show();
    }

    public void goToEditPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("editUser.fxml").openStream());
        Stage stage = (Stage) btn_edit.getScene().getWindow();
        stage.setTitle("Edit user");
        Scene scene2= new Scene(root,400,350);
        scene2.getStylesheets().add(getClass().getResource("MenuStyle.css").toExternalForm());
        stage.setScene(scene2);
        View view = fxmlLoader.getController();
        stage.show();
    }

    public void goToSearchPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("searchUser.fxml").openStream());
        Stage stage = (Stage) btn_search.getScene().getWindow();
        stage.setTitle("Search user");
        Scene scene2= new Scene(root,850,230);
        scene2.getStylesheets().add(getClass().getResource("MenuStyle.css").toExternalForm());
        stage.setScene(scene2);
        View view = fxmlLoader.getController();
        stage.show();
    }

    public void goToDeletePage(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("deleteUser.fxml").openStream());
        Stage stage = (Stage) btn_search.getScene().getWindow();
        stage.setTitle("Delete user");
        Scene scene2= new Scene(root,400,250);
        scene2.getStylesheets().add(getClass().getResource("MenuStyle.css").toExternalForm());
        stage.setScene(scene2);
        View view = fxmlLoader.getController();
        stage.show();
    }

    public void searchUser(ActionEvent actionEvent) throws IOException {
        if (checkOneValuesIsLegal(txtfld_userNameToSearch.getText())) {

            User UserDetails= controller.seacrhUser(txtfld_userNameToSearch.getText());

            if(UserDetails!=null) {
                txtfld_userDetails.setText(UserDetails.toString());
            }
            else{
                ThereIsNoUser();
            }
        }
    }

    private void ThereIsNoUser() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Be Attention");
        alert.setHeaderText("There is no such a User " );
        alert.showAndWait();
    }

    public void deleteUser(ActionEvent actionEvent) throws IOException {
        if (checkOneValuesIsLegal(txtfld_userNameToDelete.getText())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Be Attention");
            alert.setHeaderText("Delete user\n");
            alert.setContentText("Are you sure you want to delete " + txtfld_userNameToDelete.getText() + " ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... user chose OK
                if (controller.deleteUser(txtfld_userNameToDelete.getText())) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("The deletion was successful\n");
                    alert2.setHeaderText(txtfld_userNameToDelete.getText() + " Deleted");
                    alert2.showAndWait();
                } else {
                    ThereIsNoUser();
                }

                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root = fxmlLoader.load(getClass().getResource("Menu.fxml").openStream());
                Scene scene = new Scene(root, 300, 300);
                Stage stage = (Stage) txtfld_userNameToDelete.getScene().getWindow();
                stage.setTitle("Vacation4U");
                scene.getStylesheets().add(getClass().getResource("MenuStyle.css").toExternalForm());
                stage.setScene(scene);
                View view = fxmlLoader.getController();
                stage.show();
            } else {
                // ... user chose CANCEL or closed the dialog
                alert.close();
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root = fxmlLoader.load(getClass().getResource("Menu.fxml").openStream());
                Scene scene = new Scene(root, 300, 300);
                scene.getStylesheets().add(getClass().getResource("MenuStyle.css").toExternalForm());
                Stage stage = (Stage) txtfld_userNameToDelete.getScene().getWindow();
                stage.setTitle("Vacation4U");
                stage.setScene(scene);
                View view = fxmlLoader.getController();
                stage.show();
            }
        }
    }


    public void editUser(ActionEvent actionEvent) throws IOException {
        if (checkOneValuesIsLegal(txtfld_userNameToedit.getText())&& checkOneValuesIsLegal(txtfld_newValue.getText())
                && checkChbxValuesIsLegal(chbx_optionToChange.getValue())) {
            if(controller.editUser(txtfld_userNameToedit.getText(),chbx_optionToChange.getValue(),txtfld_newValue.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Congratulations");
                alert.setHeaderText("Changes have been successfully recorded\n"+"The " + chbx_optionToChange.getValue() + " was changed");
                alert.showAndWait();
            }
            else{
                ThereIsNoUser();
            }
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("Menu.fxml").openStream());
            Scene scene = new Scene(root, 300, 300);
            scene.getStylesheets().add(getClass().getResource("MenuStyle.css").toExternalForm());
            Stage stage = (Stage) txtfld_userNameToedit.getScene().getWindow();
            stage.setTitle("Vacation4U");
            stage.setScene(scene);
            View view = fxmlLoader.getController();
            stage.show();
        }
    }

    private boolean checkChbxValuesIsLegal(String value) {
        if(value==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Details are invalid\n");
            alert.setHeaderText("You must choose the type of value \n");
            alert.setContentText("Try again");

            alert.showAndWait();
            return false;
        }
        return true;
    }

    private boolean checkOneValuesIsLegal(String field) {
        if (field.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Details are invalid\n");
            alert.setHeaderText("You must fill out all fields\n");
            alert.setContentText("Try again");

            alert.showAndWait();
            return false;
        }
        return true;
    }

    public void createNewUser(ActionEvent actionEvent) throws IOException {

        //  System.out.println(" " + txtfld_firstName.getText() + " " + txtfld_lastName.getText() + " " + txtfld_birthDate.getValue().toString());
        if (checkAllValuesIsLegal(txtfld_userName.getText(),txtfld_password.getText(),txtfld_birthDate.getValue(),
                txtfld_firstName.getText(), txtfld_lastName.getText(), txtfld_city.getText())) {
            User user= new User(txtfld_userName.getText(),txtfld_password.getText(),txtfld_birthDate.getValue().toString(),
                    txtfld_firstName.getText(), txtfld_lastName.getText(), txtfld_city.getText() ) ;
            System.out.println(user.toString());

            if(controller.createUser(user)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Congratulations");
                alert.setHeaderText("A new user has been created named "+txtfld_userName.getText()+ " !!!");
                alert.showAndWait();

                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root = fxmlLoader.load(getClass().getResource("Menu.fxml").openStream());
                Scene scene = new Scene(root, 300, 300);
                scene.getStylesheets().add(getClass().getResource("MenuStyle.css").toExternalForm());
                Stage stage = (Stage) txtfld_userName.getScene().getWindow();
                stage.setTitle("Vacation4U");
                stage.setScene(scene);
                View view = fxmlLoader.getController();
                stage.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Be Attention");
                alert.setHeaderText("This User already exist " );
                alert.showAndWait();
            }
        }

    }


    private boolean checkAllValuesIsLegal(String userName, String password, LocalDate birthDate,
                                          String firstName, String lastName, String city) {
        String error="";
        if(userName.isEmpty() || password.isEmpty()  ||birthDate==null  ||firstName.isEmpty()  || lastName.isEmpty()  ||city.isEmpty()){
            if(userName.isEmpty() )
                error="You have to choose UserName!";
            else if(password.isEmpty() )
                error="You have to choose Password!";
            else if(birthDate==null || birthDate.toString().isEmpty() )
                error="You have to choose BirthDate!";
            else if(firstName.isEmpty() )
                error="You have to choose FirstName!";
            else if(lastName.isEmpty() )
                error="You have to choose LastName!";
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


    public void BackFromSearch(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("Menu.fxml").openStream());
        Scene scene = new Scene(root, 300, 300);
        Stage stage = (Stage) btn_backToMenu.getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("MenuStyle.css").toExternalForm());
        stage.setTitle("Vacation4U");
        stage.setScene(scene);
        View view = fxmlLoader.getController();
        stage.show();
    }
}