package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import Controller.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class View {

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
    public javafx.scene.control.TextField txtfld_userDetails;
    public javafx.scene.control.Button btn_backToMenu;
    public javafx.scene.control.TextField txtfld_userNameToDelete;
    public javafx.scene.control.Button btn_delete;
    public javafx.scene.control.Button btn_SignIn;
    public javafx.scene.control.Button btn_back;
    public javafx.scene.control.Button btn_LogIn;
    public javafx.scene.control.Button btn_forgotUser;
    public javafx.scene.control.TextField txtfld_userNameToLogIn;
    public javafx.scene.control.TextField txtfld_passwordToLogIn;
    public javafx.scene.control.Button btn_LogInside;
    public javafx.scene.control.Button btn_LogOut;
    public javafx.scene.control.Label txt_Welcome;
    public javafx.scene.control.DatePicker dp_departure;
    public javafx.scene.control.DatePicker dp_return;
    public javafx.scene.control.Spinner sp_adults;
    public javafx.scene.control.Spinner sp_children;
    public javafx.scene.control.TextField txtfld_FROM;
    public javafx.scene.control.TextField txtfld_TO;
    public javafx.scene.control.TextField txtflf_found1;
    public javafx.scene.control.TextField txtflf_found2;
    public javafx.scene.control.TextField txtflf_found3;
    public javafx.scene.control.Button bnt_foundDetails1;
    public javafx.scene.control.Button bnt_foundDetails2;
    public javafx.scene.control.Button bnt_foundDetails3;
    public javafx.scene.control.Button btn_toBuy;
    public javafx.scene.control.Button btn_GoSearchVacation;
    public javafx.scene.control.Button btn_PerArea;
    public javafx.scene.control.Label lab_looking;
    public javafx.scene.control.TextArea txt_textArea;
    public javafx.scene.control.TabPane TabP_Waiting;
    public javafx.scene.control.Button btn_patience;
    public javafx.scene.control.Label txt_loading;
    public javafx.scene.control.TableView TV_Message;
    public javafx.scene.control.CheckBox ChBox_1;
    public javafx.scene.control.CheckBox ChBox_2;
    public javafx.scene.control.CheckBox ChBox_3;
    public javafx.scene.control.Button btn_Reject;
    public javafx.scene.control.Button btn_Confirm;
    public javafx.scene.control.TextArea txt_ansMessage;


    public static List<Vacation> foundVacation;
    public static Vacation vacationToBuy;
    public static User registeredUser;
    public static List<String> pagesApp = new ArrayList<>();
    private static Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void goToCreatePage(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("createUser.fxml").openStream());
        Stage stage = (Stage) btn_create.getScene().getWindow();
        stage.setTitle("Create user");
        Scene scene2 = new Scene(root, 300, 300);
        scene2.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
        stage.setScene(scene2);
        stage.show();
    }

    public void goToEditPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("editUser.fxml").openStream());
        Stage stage = (Stage) btn_edit.getScene().getWindow();
        stage.setTitle("Edit user");
        Scene scene2 = new Scene(root, 700, 500);
        scene2.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
        stage.setScene(scene2);
        View view = fxmlLoader.getController();
        stage.show();
    }

    public void goToSearchPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("searchUser.fxml").openStream());
        Stage stage = (Stage) btn_search.getScene().getWindow();
        stage.setTitle("Search user");
        Scene scene2 = new Scene(root, 850, 230);
        scene2.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
        stage.setScene(scene2);
        View view = fxmlLoader.getController();
        stage.show();
    }

    public void goToDeletePage(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("deleteUser.fxml").openStream());
        Stage stage = (Stage) btn_search.getScene().getWindow();
        stage.setTitle("Delete user");
        Scene scene2 = new Scene(root, 400, 250);
        scene2.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
        stage.setScene(scene2);
        View view = fxmlLoader.getController();
        stage.show();
    }

    public void searchUser(ActionEvent actionEvent) throws IOException {
        if (checkOneValuesIsLegal(txtfld_userNameToSearch.getText())) {

            User UserDetails = controller.seacrhUser(txtfld_userNameToSearch.getText());

            if (UserDetails != null) {
                txtfld_userDetails.setText(UserDetails.toString());
            } else {
                ThereIsNoUser();
            }
        }

    }

    private void ThereIsNoUser() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Be Attention");
        alert.setHeaderText("There is no such a User ");
        alert.showAndWait();
    }

    public void deleteUser(ActionEvent actionEvent) throws IOException, SQLException {
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
                Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("ManagerMenu.fxml").openStream());
                Scene scene = new Scene(root, 300, 300);
                Stage stage = (Stage) txtfld_userNameToDelete.getScene().getWindow();
                stage.setTitle("Vacation4U");
                scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                stage.setScene(scene);
                View view = fxmlLoader.getController();
                stage.show();
            } else {
                // ... user chose CANCEL or closed the dialog
                alert.close();
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("ManagerMenu.fxml").openStream());
                Scene scene = new Scene(root, 300, 300);
                scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                Stage stage = (Stage) txtfld_userNameToDelete.getScene().getWindow();
                stage.setTitle("Vacation4U");
                stage.setScene(scene);
                View view = fxmlLoader.getController();
                stage.show();
            }
        }
    }


    public void editUser(ActionEvent actionEvent) throws IOException {
        if (checkOneValuesIsLegal(txtfld_userNameToedit.getText()) && checkOneValuesIsLegal(txtfld_newValue.getText())
                && checkChbxValuesIsLegal(chbx_optionToChange.getValue())) {
            if (controller.editUser(txtfld_userNameToedit.getText(), chbx_optionToChange.getValue(), txtfld_newValue.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Congratulations");
                alert.setHeaderText("Changes have been successfully recorded\n" + "The " + chbx_optionToChange.getValue() + " was changed");
                alert.showAndWait();
            } else {
                ThereIsNoUser();
            }
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("ManagerMenu.fxml").openStream());
            Scene scene = new Scene(root, 300, 300);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
            Stage stage = (Stage) txtfld_userNameToedit.getScene().getWindow();
            stage.setTitle("Vacation4U");
            stage.setScene(scene);
            View view = fxmlLoader.getController();
            stage.show();
        }
    }

    private boolean checkChbxValuesIsLegal(String value) {
        if (value == null) {
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
        if (field.isEmpty()) {
            EmptyFieldAlert();
            return false;
        }
        return true;
    }

    public void createNewUser(ActionEvent actionEvent) throws IOException {

        //  System.out.println(" " + txtfld_firstName.getText() + " " + txtfld_lastName.getText() + " " + txtfld_birthDate.getValue().toString());
        if (checkAllValuesIsLegal(txtfld_userName.getText(), txtfld_password.getText(), txtfld_birthDate.getValue(),
                txtfld_firstName.getText(), txtfld_lastName.getText(), txtfld_city.getText())) {
            User user = new User(txtfld_userName.getText(), txtfld_password.getText(), txtfld_birthDate.getValue().toString(),
                    txtfld_firstName.getText(), txtfld_lastName.getText(), txtfld_city.getText(), false);
//            System.out.println(user.toString());

            if (controller.createUser(user)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Congratulations");
                alert.setHeaderText("A new user has been created named " + txtfld_userName.getText() + " !!!");
                alert.showAndWait();

                Scene scene = getScene();
                scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                Stage stage = (Stage) txtfld_userName.getScene().getWindow();
                stage.setTitle("Vacation4U");
                stage.setScene(scene);
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Be Attention");
                alert.setHeaderText("This User already exist ");
                alert.showAndWait();
            }
        }

    }


    private boolean checkAllValuesIsLegal(String userName, String password, LocalDate birthDate,
                                          String firstName, String lastName, String city) {
        String error = "";
        if (userName.isEmpty() || password.isEmpty() || birthDate == null || firstName.isEmpty() || lastName.isEmpty() || city.isEmpty()) {
            if (userName.isEmpty())
                error = "You have to choose User name!";
            else if (password.isEmpty())
                error = "You have to choose Password!";
            else if (birthDate == null || birthDate.toString().isEmpty())
                error = "You have to choose Birth Date!";
            else if (firstName.isEmpty())
                error = "You have to choose First Name!";
            else if (lastName.isEmpty())
                error = "You have to choose Last Name!";
            else if (city.isEmpty())
                error = "You have to choose City!";

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Details are invalid\n");
            alert.setHeaderText(error);
            alert.setContentText("Try again");

            alert.showAndWait();
            return false;
        }
        return true;
    }

    public void goToSignInPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("CreateUser.fxml").openStream());
        Scene scene = new Scene(root, 700, 500);
        Stage stage = (Stage) btn_SignIn.getScene().getWindow();
        scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
        stage.setTitle("Sign in");
        stage.setScene(scene);
        stage.show();
    }


    public void backToMenu(ActionEvent actionEvent) throws IOException {
        Scene scene = getScene();
        Stage stage = (Stage) btn_back.getScene().getWindow();
        showStage(scene, stage);
    }

    private void showStage(Scene scene, Stage stage) {
        scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
        stage.setTitle("Vacation4U");
        stage.setScene(scene);
        stage.show();
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Scene scene = getScene();
        registeredUser.setLogIn(true);
        Stage stage = (Stage) btn_LogOut.getScene().getWindow();
        showStage(scene, stage);
    }

    private Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
        return new Scene(root, 700, 500);
    }

    public void goToLogInPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("logIn.fxml").openStream());
        pagesApp.add("logIn");
        Scene scene = new Scene(root, 700, 500);
        Stage stage = (Stage) btn_LogIn.getScene().getWindow();
        scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
        stage.setTitle("Log in");
        stage.setScene(scene);
        stage.show();

    }

    public void goToForgotUser(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("forgotUser.fxml").openStream());
        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
        Stage stage = (Stage) btn_forgotUser.getScene().getWindow();
        stage.setTitle("Update User");
        stage.setScene(scene);
        stage.show();

    }

    public void logIn(ActionEvent actionEvent) throws IOException {
        if (checkOneValuesIsLegal(txtfld_userNameToLogIn.getText())) {

            User UserDetails = controller.seacrhUser(txtfld_userNameToLogIn.getText());
            if (UserDetails != null) {
                if (UserDetails.getPassword().equals(txtfld_passwordToLogIn.getText())) {

                    registeredUser = UserDetails;
                    registeredUser.setLogIn(true);
                    if (pagesApp.contains("foundVacations")) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("foundVacations.fxml").openStream());
                        pagesApp.add("homeMenu");
                        Scene scene = new Scene(root, 700, 500);
                        scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                        Stage stage = (Stage) btn_LogInside.getScene().getWindow();
                        String title = "Welcome " + registeredUser.getFirstName() + " " + registeredUser.getLastName();
                        stage.setTitle(title);
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
                        pagesApp.add("homeMenu");
                        Scene scene = new Scene(root, 700, 500);
                        scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                        Stage stage = (Stage) btn_LogInside.getScene().getWindow();
                        String title = "Welcome " + registeredUser.getFirstName() + " " + registeredUser.getLastName();
                        stage.setTitle(title);
                        stage.setScene(scene);
                        stage.show();
                        btn_LogIn= (Button) scene.lookup("#btn_LogIn");
                        btn_LogIn.setText("Log Out");
                        btn_SignIn= (Button) scene.lookup("#btn_SignIn");
                        btn_SignIn.setVisible(false);
                        btn_SignIn= (Button) scene.lookup("#btn_SignIn");
                        btn_SignIn.setVisible(false);
                        btn_PerArea= (Button) scene.lookup("#btn_PerArea");
                        if(registeredUser.getMessageNum()==0){
                            btn_PerArea.setText(" There are no messages");
                        }else{
                            btn_PerArea.setText(registeredUser.getMessageNum()+ " message are waiting for you");
                        }
                        btn_PerArea.setVisible(true);

                    }

                } else {
                    incorrectPassword();
                }
            } else {
                ThereIsNoUser();
            }
        }

    }


    private void incorrectPassword() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Incorrect Password");
        alert.setHeaderText("Try again or restore password");
        alert.showAndWait();
    }

    public void goToSearchVacation(ActionEvent actionEvent) throws IOException, ParseException {

        if (datesLegal(dp_departure, dp_return))
            if (checkOneValuesIsLegal(sp_adults.getValue().toString()) && checkOneValuesIsLegal(sp_children.getValue().toString()) &&
                    checkOneValuesIsLegal(txtfld_FROM.getText()) && checkOneValuesIsLegal(txtfld_TO.getText())) {
                int TicketNum = Integer.parseInt(sp_adults.getValue().toString()) + Integer.parseInt(sp_children.getValue().toString());
                Date dp_departureDate = new Date(dp_departure.getValue().getYear(), dp_departure.getValue().getMonthValue(), dp_departure.getValue().getDayOfMonth());
                Date dp_returnDate = new Date(dp_return.getValue().getYear(), dp_return.getValue().getMonthValue(), dp_return.getValue().getDayOfMonth());

                Vacation vacationTerms = new Vacation(null, txtfld_FROM.getText(), txtfld_TO.getText(), null,
                        dp_departureDate, dp_returnDate, TicketNum, null, 0, "", null, null, null, null);

                foundVacation = controller.searchVacation(vacationTerms);


                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("foundVacations.fxml"));
                pagesApp.add("foundVacations");
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 700, 500);
                scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                Stage stage = (Stage) btn_GoSearchVacation.getScene().getWindow();
                String title = "available vacations";
                stage.setTitle(title);
                stage.setScene(scene);
                stage.show();

             //   bnt_showMeVac= (Button) scene.lookup("bnt_showMeVac");
                lab_looking= (Label) scene.lookup("#lab_looking");
                txtflf_found1= (TextField) scene.lookup("#txtflf_found1");
                bnt_foundDetails1= (Button) scene.lookup("#bnt_foundDetails1");
                txtflf_found2= (TextField) scene.lookup("#txtflf_found2");
                bnt_foundDetails2= (Button) scene.lookup("#bnt_foundDetails2");
                txtflf_found3= (TextField) scene.lookup("#txtflf_found3");
                bnt_foundDetails3= (Button) scene.lookup("#bnt_foundDetails3");


                    lab_looking.setText("The vacations we found for you..");
                    for (int i = 0; i < foundVacation.size(); i += 2) {
                        txtflf_found1.setVisible(true);
                        txtflf_found1.setText(foundVacation.get(i).shortToString());
                        bnt_foundDetails1.setVisible(true);
                        if ((i + 1) < foundVacation.size()) {
                            txtflf_found2.setVisible(true);
                            txtflf_found2.setText(foundVacation.get(i + 1).shortToString());
                            bnt_foundDetails2.setVisible(true);
                            if ((i + 2) < foundVacation.size()) {
                                txtflf_found3.setVisible(true);
                                txtflf_found3.setText(foundVacation.get(i + 2).shortToString());
                                bnt_foundDetails3.setVisible(true);

                            }

                    }
                    // vacationToBuy=foundVacation.get(i);

                  //  bnt_showMeVac.setVisible(false);
                }


            }
    }
    //  txtflf_found1.setText("From "+ Vacation1.getFromPlace()+" To"+Vacation1.getToPlace());


    private boolean datesLegal(DatePicker dp_departure, DatePicker dp_return) {
        if (dp_departure.getValue() == null || dp_return.getValue() == null) {
            EmptyFieldAlert();
            return false;
        } else {
            Date currentDate = null, departureDate = null, returnDate = null;
            LocalDate localDate = LocalDate.now();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                currentDate = sdf.parse(localDate.toString());
                departureDate = sdf.parse(dp_departure.getValue().toString());
                returnDate = sdf.parse(dp_return.getValue().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (currentDate.compareTo(departureDate) > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Details are invalid\n");
                alert.setHeaderText("You must choose Future dates\n");
                alert.setContentText("Try again");

                alert.showAndWait();
                return false;
            }
            if (returnDate.compareTo(departureDate) > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Details are invalid\n");
                alert.setHeaderText(" return date can not be before departure\n");
                alert.setContentText("Try again");

                alert.showAndWait();
                return false;
            }

            return true;

        }
    }

    private void EmptyFieldAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Details are invalid\n");
        alert.setHeaderText("You must fill out all fields\n");
        alert.setContentText("Try again");

        alert.showAndWait();
    }


    public void goToBuyPage(ActionEvent actionEvent) throws IOException {

        if (registeredUser == null || (!registeredUser.isLogIn())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Be Attention");
            alert.setHeaderText("You need to be logged in to buy a vacation\n");
            alert.setContentText("Do you want to log in?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... user chose OK
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("logIn.fxml").openStream());
                pagesApp.add("logIn");
                Scene scene = new Scene(root, 700, 500);
                Stage stage = (Stage) btn_toBuy.getScene().getWindow();
                scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                stage.setTitle("Log in");
                stage.setScene(scene);
                stage.show();
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
                pagesApp.add("homeMenu");
                Scene scene = new Scene(root, 700, 500);
                Stage stage = (Stage) btn_toBuy.getScene().getWindow();
                scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                stage.setTitle("Vacation 4U");
                stage.setScene(scene);
                stage.show();
            }
        } else {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Request sent");
            alert2.setHeaderText("Do You want to send the request to the seller " + vacationToBuy.getSalerName() + " ?");
            alert2.setContentText("When you receive a reply it will appear in your message box");
            Optional<ButtonType> result2 = alert2.showAndWait();
            if (result2.get() == ButtonType.OK) {
                // ... user chose OK
                try {
                    User salerUser = controller.seacrhUser(vacationToBuy.getSalerName());
                    userMessage Message = new userMessage(vacationToBuy.getFlightNum(), registeredUser, salerUser, "waiting");
                    if (controller.insertMessage(Message)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Good news");
                        alert.setHeaderText("The message has been sent ");
                        alert.showAndWait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
            pagesApp.add("homeMenu");
            Scene scene = new Scene(root, 700, 500);
            Stage stage = (Stage) btn_toBuy.getScene().getWindow();
            scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
            stage.setTitle("Vacation 4U");
            stage.setScene(scene);
            stage.show();

        }
    }


    public void goToDetailPage(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource().equals(bnt_foundDetails1)) {
            vacationToBuy = foundVacation.get(0);
        } else if (actionEvent.getSource().equals(bnt_foundDetails2)) {
            vacationToBuy = foundVacation.get(1);
        } else if (actionEvent.getSource().equals(bnt_foundDetails3)) {
            vacationToBuy = foundVacation.get(2);
        }

        // txtflf_found1.setText(vacationToBuy.toString());
        lab_looking.setText("The best vacation for you..");
        txt_textArea.setText(vacationToBuy.toString());
        txt_textArea.setVisible(true);
        txtflf_found1.setVisible(false);
        txtflf_found2.setVisible(false);
        txtflf_found3.setVisible(false);
        bnt_foundDetails1.setVisible(false);
        bnt_foundDetails2.setVisible(false);
        bnt_foundDetails3.setVisible(false);
        btn_toBuy.setVisible(true);
    }

    public void updateVacations(ActionEvent actionEvent) throws IOException {
     /*   if (bnt_showMeVac.getText().equals("Show me vacations!")) {
            lab_looking.setText("The vacations we found for you..");
            String allVacations = "";
            for (int i = 0; i < foundVacation.size(); i += 2) {
                txtflf_found1.setVisible(true);
                txtflf_found1.setText(foundVacation.get(i).shortToString());
                bnt_foundDetails1.setVisible(true);
                if ((i + 1) < foundVacation.size()) {
                    txtflf_found2.setVisible(true);
                    txtflf_found2.setText(foundVacation.get(i + 1).shortToString());
                    bnt_foundDetails2.setVisible(true);
                    if ((i + 2) < foundVacation.size()) {
                        txtflf_found3.setVisible(true);
                        txtflf_found3.setText(foundVacation.get(i + 2).shortToString());
                        bnt_foundDetails3.setVisible(true);

                    }
                }
            }
            // vacationToBuy=foundVacation.get(i);

            bnt_showMeVac.setVisible(false);
        }*/

    }

    public void goToPersonal(ActionEvent actionEvent) throws IOException {
        if (registeredUser == null || (!registeredUser.isLogIn())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Be Attention");
            alert.setHeaderText("You need to be logged in to buy a vacation\n");
            alert.setContentText("Do you want to log in?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... user chose OK
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("logIn.fxml").openStream());
                pagesApp.add("logIn");
                Scene scene = new Scene(root, 700, 500);
                Stage stage = (Stage) btn_PerArea.getScene().getWindow();
                scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                stage.setTitle("Log in");
                stage.setScene(scene);
                stage.show();
            }

        } else {

            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("Messages.fxml").openStream());
            pagesApp.add("logIn");
            Scene scene = new Scene(root, 700, 500);
            Stage stage = (Stage) btn_PerArea.getScene().getWindow();
            scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
            stage.setTitle("your messages");
            stage.setScene(scene);
            stage.show();


        }


    }

    public void goToMessages(ActionEvent actionEvent) {
        List<userMessage> ReqFromPurchasers = controller.searchReqFromPurchaser(registeredUser);
        TabP_Waiting.setVisible(true);
        txt_loading.setVisible(false);
        btn_patience.setVisible(false);

        ChBox_1.setText("The user " + ReqFromPurchasers.get(0).getFromUser().getUserName() +
                " want to buy the vacation " + ReqFromPurchasers.get(0).getVacationToBuy() + " from you");
        ChBox_1.setVisible(true);

        if (ChBox_1.isSelected())
            System.out.println(ReqFromPurchasers.toString());


        List<userMessage> AnsFromSalersMessages = controller.searchAnsFromSalers(registeredUser);
        TabP_Waiting.setVisible(true);
        txt_loading.setVisible(false);
        btn_patience.setVisible(false);

        txt_ansMessage.setText("The Message status to " + AnsFromSalersMessages.get(0).getFromUser().getUserName()
              + " Regarding the " + AnsFromSalersMessages.get(0).getVacationToBuy() + " vacations, is: "+
                AnsFromSalersMessages.get(0).getStatus());
        txt_ansMessage.setVisible(true);



    }

    public void handleWithMessage(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(btn_Reject)) {
            System.out.println("delete");
        } else if (actionEvent.getSource().equals(btn_Confirm)) {
            System.out.println("not delete");

        }
    }
}

/*  TableView table = new TableView();
             ObservableList<userMessage> data;

             TableColumn flightNameCol = new TableColumn("FlightNumber");
              TableColumn purchaserNameCol = new TableColumn("PurchaserName");
             TableColumn actionCol = new TableColumn("Reply");

            table.getColumns().addAll(flightNameCol, purchaserNameCol, actionCol);

            data = FXCollections.observableArrayList(
                    new userMessage("Dd234234","chen")
            );

            flightNameCol.setCellValueFactory(
                    new PropertyValueFactory<userMessage, String>("FlightNumber")
            );

            purchaserNameCol.setCellValueFactory(
                    new PropertyValueFactory<userMessage, String>("purchaserNameCol")
            );

            actionCol.setCellValueFactory(
                    new PropertyValueFactory<userMessage, String>("checkbox")
            );

            table.setItems(data);
           // table.setEditable(true);*/
