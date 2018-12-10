package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Controller.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class View {
//change
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
    public javafx.scene.control.TextArea TA_details;
    public javafx.scene.control.Accordion acc_Vacations;
    public javafx.scene.control.Accordion acc_reqMessage;
    public javafx.scene.control.Accordion acc_ansMessage;
    public javafx.scene.control.Label txt_messageLabel;
    public javafx.scene.control.Button btn_backFromMes;
    public javafx.scene.control.Button btn_SellVacation;


    public static List<Vacation> foundVacation;
    public static Vacation vacationToBuy;
    public static User registeredUser;
    public static List<String> pagesApp = new ArrayList<>();
    private static Controller controller;
    public static userMessage currentMessage;

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


    private Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
        return new Scene(root, 700, 500);
    }

    public void goToLogInPage(ActionEvent actionEvent) throws IOException {

        if (((Button) actionEvent.getSource()).getText().equals("Log Out")) {
            Scene scene = getScene();
            registeredUser.setLogIn(false);
            Stage stage = (Stage) btn_LogIn.getScene().getWindow();
            showStage(scene, stage);
            btn_PerArea = (Button) scene.lookup("#btn_PerArea");
            btn_PerArea.setVisible(false);
            txt_Welcome = (Label) scene.lookup("#txt_Welcome");
            txt_Welcome.setVisible(false);
        } else {
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
                    if (pagesApp.size() >= 2 && pagesApp.get(pagesApp.size() - 2).equals("vacationDetails")) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("vacationDetails.fxml"));
                        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("vacationDetails.fxml").openStream());
                        pagesApp.add("vacationDetails");
                        Scene scene = new Scene(root, 700, 500);
                        scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                        Stage stage = (Stage) btn_LogInside.getScene().getWindow();
                        String title = "available vacations";
                        stage.setTitle(title);
                        stage.setScene(scene);
                        stage.show();
                        TA_details = (TextArea) scene.lookup("#TA_details");
                        TA_details.setText(vacationToBuy.toString());
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
                        personalHome(scene);


                    }

                } else {
                    incorrectPassword();
                }
            } else {
                ThereIsNoUser();
            }
        }


    }

    private void personalHome(Scene scene) {
        btn_LogIn = (Button) scene.lookup("#btn_LogIn");
        btn_LogIn.setText("Log Out");
        btn_SignIn = (Button) scene.lookup("#btn_SignIn");
        btn_SignIn.setVisible(false);
        btn_SignIn = (Button) scene.lookup("#btn_SignIn");
        btn_SignIn.setVisible(false);
        btn_PerArea = (Button) scene.lookup("#btn_PerArea");
        txt_Welcome = (Label) scene.lookup("#txt_Welcome");
        registeredUser.setIncomingReqMessages(controller.searchReqMessages(registeredUser));
        registeredUser.setIncomingAnsMessages(controller.searchAnsMessages(registeredUser));
        if (registeredUser.getMessageNum() == 0) {
            btn_PerArea.setText(" no messages");
        } else if (registeredUser.getMessageNum() == 1) {
            btn_PerArea.setText(registeredUser.getMessageNum() + " message");
        } else {
            btn_PerArea.setText(registeredUser.getMessageNum() + " messages");
        }
        btn_PerArea.setVisible(true);
        txt_Welcome.setText("welcome " + registeredUser.userName +" !");
        txt_Welcome.setVisible(true);
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
                lab_looking = (Label) scene.lookup("#lab_looking");

                TitledPane[] tps = new TitledPane[foundVacation.size()];
                for (int i = 0; i < foundVacation.size(); i++) {
                    acc_Vacations = (Accordion) scene.lookup("#acc_Vacations");
                    TextArea TA = new TextArea(foundVacation.get(i).toString());
                    Button Bt = new Button(foundVacation.get(i).getFlightNum());
                    Bt.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("vacationDetails.fxml"));
                            pagesApp.add("vacationDetails");
                            Parent root = null;
                            try {
                                root = fxmlLoader.load();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            Scene scene = new Scene(root, 700, 500);
                            scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                            Stage stage = (Stage) Bt.getScene().getWindow();
                            String title = "available vacations";
                            stage.setTitle(title);
                            stage.setScene(scene);
                            stage.show();
                            vacationToBuy = (Vacation) controller.searchVacationByFlightNum(Bt.getText());
                            TA_details = (TextArea) scene.lookup("#TA_details");
                            TA_details.setText(vacationToBuy.toString());
                        }
                    });
                    GridPane GP = new GridPane();
                    GP.add(TA, 0, 0);
                    GP.add(Bt, 1, 0);
                    tps[i] = new TitledPane(foundVacation.get(i).getFlightNum(), GP);

                }
                acc_Vacations.getPanes().addAll(tps);
                acc_Vacations.setExpandedPane(tps[0]);
                root = scene.getRoot();
                stage.setScene(scene);
                stage.show();

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
            askToLogIn();
        } else {

            if (pagesApp.get(pagesApp.size() - 1) == "vacationDetailsAfterOk") {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("secure payment ");
                alert.setHeaderText("This step will go beyond filling out details for a secure payment");
                alert.setContentText("We assume that your secure payment has been successful");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Congratulations");
                    alert.setHeaderText("You bought your vacation to " + vacationToBuy.getToPlace() + " !!!");
                    alert.setContentText(vacationToBuy.getToPlace() + " feels closer than ever...!");
                    alert.showAndWait();

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
                        salerUser.addIncomingReqMessages(Message);
                        registeredUser.addIncomingReqMessages(Message);
                        registeredUser.addOutgoingMessages(Message);
                        if (controller.insertMessage(Message)) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Good news");
                            alert.setHeaderText("The message has been sent ");
                            alert.showAndWait();
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                FXMLLoader fxmlLoader = new FXMLLoader();
                                Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
                                pagesApp.add("homeMenu");
                                Scene scene = new Scene(root, 700, 500);
                                Stage stage = (Stage) btn_toBuy.getScene().getWindow();
                                scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                                stage.setTitle("Vacation 4U");
                                stage.setScene(scene);
                                stage.show();
                                personalHome(scene);

                            } else {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("foundVacations.fxml"));
                                pagesApp.add("foundVacations");
                                Parent root = fxmlLoader.load();
                                Scene scene = new Scene(root, 700, 500);
                                scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                                Stage stage = (Stage) btn_toBuy.getScene().getWindow();
                                String title = "available vacations";
                                stage.setTitle(title);
                                stage.setScene(scene);
                                stage.show();
                            }


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void askToLogIn() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Be Attention");
        alert.setHeaderText("You need to be logged in to sell your vacation\n");
        alert.setContentText("Do you want to log in?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("logIn.fxml").openStream());
            pagesApp.add("logIn");
            Scene scene = new Scene(root, 700, 500);
            Stage stage = (Stage) btn_SellVacation.getScene().getWindow();
            scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
            stage.setTitle("Log in");
            stage.setScene(scene);
            stage.show();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
            pagesApp.add("homeMenu");
            Scene scene = new Scene(root, 700, 500);
            Stage stage = (Stage) btn_SellVacation.getScene().getWindow();
            scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
            stage.setTitle("Vacation 4U");
            stage.setScene(scene);
            stage.show();
        }
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
            pagesApp.add("Messages");
            Scene scene = new Scene(root, 700, 500);
            Stage stage = (Stage) btn_PerArea.getScene().getWindow();
            scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
            stage.setTitle("your messages");
            stage.setScene(scene);
            stage.show();

            TitledPane[] tpsReq = new TitledPane[registeredUser.getIncomingReqMessages().size()];
            for (int i = 0; i < registeredUser.getIncomingReqMessages().size(); i++) {
                acc_reqMessage = (Accordion) scene.lookup("#acc_reqMessage");
                currentMessage = registeredUser.getIncomingReqMessages().get(i);
                TextArea TA = new TextArea(registeredUser.getIncomingReqMessages().get(i).getFromUser().getUserName() + " want to buy from" +
                        " you flight num: " + registeredUser.getIncomingReqMessages().get(i).getVacationToBuy());
                Button Confirm = new Button("confirm " + registeredUser.getIncomingReqMessages().get(i).toString());
                Button Reject = new Button("Reject " + registeredUser.getIncomingReqMessages().get(i).toString());
                Confirm.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        currentMessage.setStatus("confirm");
                        if (controller.updateMessage(currentMessage, "confirm")) {
                            registeredUser.removeIncomingReqMessages(currentMessage);
                            System.out.println("conf");
                        }
                    }
                });
                Reject.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        currentMessage.setStatus("reject");
                        if (controller.updateMessage(currentMessage, "reject")) {
                            registeredUser.removeIncomingReqMessages(currentMessage);
                            System.out.println("reject");
                        }
                    }
                });
                GridPane GP = new GridPane();
                GP.add(TA, 0, 0);

                GP.add(Confirm, 1, 0);
                GP.add(Reject, 2, 0);
                tpsReq[i] = new TitledPane("request number " + (i + 1), GP);
            }

            if (registeredUser.getIncomingReqMessages().size() != 0) {
                acc_reqMessage.getPanes().addAll(tpsReq);
                acc_reqMessage.setExpandedPane(tpsReq[0]);
            }

            acc_ansMessage = (Accordion) scene.lookup("#acc_ansMessage");

            TitledPane[] tpsAns = new TitledPane[registeredUser.getIncomingAnsMessages().size()];
            for (int i = 0; i < registeredUser.getIncomingAnsMessages().size(); i++) {
//                acc_ansMessage= (Accordion) scene.lookup("#acc_ansMessage");
                currentMessage = registeredUser.getIncomingAnsMessages().get(i);
                TextArea TA = new TextArea(registeredUser.getIncomingAnsMessages().get(i).getToUser().getUserName() + " answer to your request  " +
                        " regarding flight num: " + registeredUser.getIncomingAnsMessages().get(i).getVacationToBuy() + ". The answer is: "
                        + registeredUser.getIncomingAnsMessages().get(i).getStatus());
                Button toBuy = new Button("Buy " + currentMessage.getVacationToBuy());
                toBuy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        registeredUser.removeIncomingAnsMessages(currentMessage);
                        vacationToBuy = controller.searchVacationByFlightNum(currentMessage.getVacationToBuy());

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("vacationDetails.fxml"));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load(getClass().getClassLoader().getResource("vacationDetails.fxml").openStream());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        pagesApp.add("vacationDetailsAfterOk");
                        Scene scene = new Scene(root, 700, 500);
                        scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                        Stage stage = (Stage) toBuy.getScene().getWindow();
                        String title = "buy";
                        stage.setTitle(title);
                        stage.setScene(scene);
                        stage.show();
                        TA_details = (TextArea) scene.lookup("#TA_details");
                        TA_details.setText(vacationToBuy.toString());
                        if (controller.removeMessage(currentMessage)) {
                            System.out.println("Message removed");
                        }
                    }
                });

                GridPane GP = new GridPane();
                GP.add(TA, 0, 0);
                GP.add(toBuy, 1, 0);
                tpsAns[i] = new TitledPane("answer number " + (i + 1), GP);

            }
            if (registeredUser.getIncomingAnsMessages().size() != 0) {
                acc_ansMessage.getPanes().addAll(tpsAns);
                acc_ansMessage.setExpandedPane(tpsAns[0]);
            }
            root = scene.getRoot();
            stage.setScene(scene);
            stage.show();

        }


    }

    public void handleWithMessage(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(btn_Reject)) {
            System.out.println("delete");
        } else if (actionEvent.getSource().equals(btn_Confirm)) {
            System.out.println("not delete");

        }
    }

    public void backFromPersonal(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
        pagesApp.add("homeMenu");
        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
        Stage stage = (Stage) txt_messageLabel.getScene().getWindow();
        String title = "Welcome " + registeredUser.getFirstName() + " " + registeredUser.getLastName();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        personalHome(scene);

    }

    public void goToSellVacation(ActionEvent actionEvent) throws IOException {
        if (registeredUser == null || (!registeredUser.isLogIn())) {
            try {
                askToLogIn();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("addVacation.fxml").openStream());
            pagesApp.add("sellVacation");
            Scene scene = new Scene(root, 700, 500);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
            Stage stage = (Stage) btn_SellVacation.getScene().getWindow();
            String title = "Welcome " + registeredUser.getFirstName() + " " + registeredUser.getLastName();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
            personalHome(scene);
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
