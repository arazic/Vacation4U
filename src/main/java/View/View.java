package View;

import Model.*;
import javafx.collections.FXCollections;
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
import java.time.Period;
import java.util.*;

public class View {

    public static List<String> pagesApp = new ArrayList<>();
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
    public javafx.scene.control.Button btn_Mess;
    public javafx.scene.control.Button btn_PersonalArea;
    public javafx.scene.control.Label lab_looking;
    public javafx.scene.control.TabPane TabP_Waiting;
    public TextField txtfld_phone;
    private javafx.scene.control.Button btn_Reject;
    private javafx.scene.control.Button btn_Confirm;
    public javafx.scene.control.TextArea TA_details;
    public javafx.scene.control.Accordion acc_Vacations;
    public javafx.scene.control.Accordion acc_reqMessage;
    public javafx.scene.control.Accordion acc_ansMessage;
    public javafx.scene.control.Accordion acc_TradeReqMessage;
    public javafx.scene.control.Accordion acc_TradeAnsMessage;
    public javafx.scene.control.Label txt_messageLabel;
    public javafx.scene.control.Button btn_backFromMes;
    public javafx.scene.control.Button btn_SellVacation;
    // addVacationProperties
    public javafx.scene.control.Button btn_sell;
    public javafx.scene.control.Button btn_allVacations;
    public javafx.scene.control.Button btn_MyVacation;
    public javafx.scene.control.TextField tf_airlineName;
    public javafx.scene.control.TextField tf_flightNumber;
    public javafx.scene.control.ChoiceBox<String> cb_rate;
    public javafx.scene.control.ChoiceBox<String> cb_accommondation;
    public javafx.scene.control.ChoiceBox<String> cb_kind;
    public CheckBox checkbx_accommodation;
    public CheckBox checkbx_flightBackIndlude;
    public ChoiceBox cb_baggage;
    public ChoiceBox cb_ticketType;
    public DatePicker dp_departureDate;
    public DatePicker dp_returnDate;
    public TextField tf_to;
    public TextField tf_from;


    private static Controller controller;
    public Accordion my_Vacations;
    public Accordion acc_Vacations1;
    public Button btn_toTrade;
    public TextArea TA_vacationOfferDetails;
    public TextArea TA_vacationToGetDetails;


    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void goToCreatePage(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("createUser.fxml").openStream());
        Stage stage = (Stage) btn_create.getScene().getWindow();
        stage.setTitle("Create user");
        Scene scene2 = new Scene(root, 700, 500);
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
        Scene scene2 = new Scene(root, 850, 400);
        scene2.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
        stage.setScene(scene2);
        View view = fxmlLoader.getController();
        stage.show();
    }

    public void goToDeletePage(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("deleteUser.fxml").openStream());
        Stage stage = (Stage) btn_delete.getScene().getWindow();
        stage.setTitle("Delete user");
        Scene scene2 = new Scene(root, 700, 500);
        scene2.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
        stage.setScene(scene2);
        View view = fxmlLoader.getController();
        stage.show();
    }

    public void searchUser(ActionEvent actionEvent) throws IOException {
        if (checkOneValuesIsLegal(txtfld_userNameToSearch.getText())) {

            User UserDetails = controller.searchUser(txtfld_userNameToSearch.getText());

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
            if (!txtfld_userNameToDelete.getText().equals(controller.getVacation4UManager().getRegisteredUser().getUserName()) && !controller.getVacation4UManager().getRegisteredUser().getUserName().equals("admin")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Illegall action");
                alert.setHeaderText("You can delete only your account");
                alert.showAndWait();
                return;
            } else {

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
                    Scene scene = new Scene(root, 700, 500);
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
                    Scene scene = new Scene(root, 700, 500);
                    scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                    Stage stage = (Stage) txtfld_userNameToDelete.getScene().getWindow();
                    stage.setTitle("Vacation4U");
                    stage.setScene(scene);
                    View view = fxmlLoader.getController();
                    stage.show();
                }
            }
        }
    }


    public void editUser(ActionEvent actionEvent) throws IOException {
        if (!txtfld_userNameToedit.equals(controller.getVacation4UManager().getRegisteredUser().getUserName()) && !controller.getVacation4UManager().getRegisteredUser().getUserName().equals("admin")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Illegall action");
            alert.setHeaderText("You can edit only your account");
            alert.showAndWait();
            return;
        }

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
            Scene scene = new Scene(root, 700, 500);
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

        if (checkAllValuesIsLegal(txtfld_userName.getText(), txtfld_password.getText(), txtfld_birthDate.getValue(),
                txtfld_firstName.getText(), txtfld_lastName.getText(), txtfld_city.getText(), txtfld_phone.getText())) {
            if (controller.createUser(txtfld_userName.getText(), txtfld_password.getText(), txtfld_birthDate.getValue().toString(),
                    txtfld_firstName.getText(), txtfld_lastName.getText(), txtfld_city.getText(), txtfld_phone.getText(), false)) {
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
                                          String firstName, String lastName, String city, String phone) {
        String error = "";
        if (userName.isEmpty() || password.length()<8 || birthDate == null || calculateAge(birthDate)<18|| firstName.isEmpty() || lastName.isEmpty() || city.isEmpty() || phone.isEmpty()) {
            if (userName.isEmpty())
                error = "You have to choose User name!";
            else if (password.isEmpty())
                error = "You have to choose Password!";
            else if (password.length()<8)
                error = "Password must contain at least 8 characters!";
            else if (birthDate == null || birthDate.toString().isEmpty())
                error = "You have to choose Birth Date!";
            else if ( calculateAge(birthDate)<18 )
                error = "To register you need to be at least 18 years old !";
            else if (firstName.isEmpty())
                error = "You have to choose First Name!";
            else if (lastName.isEmpty())
                error = "You have to choose Last Name!";
            else if (city.isEmpty())
                error = "You have to choose City!";
            else if (phone.isEmpty())
                error = "You have to insert Phone Number!";

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Details are invalid\n");
            alert.setHeaderText(error);
            alert.setContentText("Try again");

            alert.showAndWait();
            return false;
        }
        return true;
    }

    private int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
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
            controller.getVacation4UManager().getRegisteredUser().setLogIn(false);
            Stage stage = (Stage) btn_LogIn.getScene().getWindow();
            showStage(scene, stage);
            btn_Mess = (Button) scene.lookup("#btn_Mess");
            btn_Mess.setVisible(false);
            btn_PersonalArea= (Button) scene.lookup("#btn_PersonalArea");
            btn_PersonalArea.setVisible(false);
            txt_Welcome = (Label) scene.lookup("#txt_Welcome");
            txt_Welcome.setVisible(false);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("logIn.fxml").openStream());
            controller.getVacation4UManager().getPagesApp().add("logIn");
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

            User UserDetails = controller.searchUser(txtfld_userNameToLogIn.getText());
            if (UserDetails != null) {
                if (UserDetails.getPassword().equals(txtfld_passwordToLogIn.getText())) {

                    controller.getVacation4UManager().setRegisteredUser(UserDetails);
                    controller.getVacation4UManager().getRegisteredUser().setLogIn(true);
                    if ( controller.getVacation4UManager().getPagesApp().size() >= 2 && controller.getVacation4UManager().getPagesApp().get(controller.getVacation4UManager().getPagesApp().size() - 2).equals("vacationDetails")) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("vacationDetails.fxml"));
                        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("vacationDetails.fxml").openStream());
                        controller.getVacation4UManager().getPagesApp().add("vacationDetails");
                        Scene scene = new Scene(root, 700, 500);
                        scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                        Stage stage = (Stage) btn_LogInside.getScene().getWindow();
                        String title = "available vacations";
                        stage.setTitle(title);
                        stage.setScene(scene);
                        stage.show();
                        TA_details = (TextArea) scene.lookup("#TA_details");
                        TA_details.setText(controller.getVacation4UManager().getVacationToBuy().toString());
                    } else {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
                        controller.getVacation4UManager().getPagesApp().add("homeMenu");
                        Scene scene = new Scene(root, 700, 500);
                        scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                        Stage stage = (Stage) btn_LogInside.getScene().getWindow();
                        String title = "Welcome " + controller.getVacation4UManager().getRegisteredUser().getFirstName() + " " + controller.getVacation4UManager().getRegisteredUser().getLastName();
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
        btn_Mess = (Button) scene.lookup("#btn_Mess");
        btn_PersonalArea = (Button) scene.lookup("#btn_PersonalArea");
        if (btn_PersonalArea != null)
            btn_PersonalArea.setVisible(true);
        txt_Welcome = (Label) scene.lookup("#txt_Welcome");
        btn_MyVacation= (Button) scene.lookup("#btn_MyVacation");
        if(btn_MyVacation!=null){
        btn_MyVacation.setVisible(true);}
        controller.getVacation4UManager().getRegisteredUser().setIncomingReqMessages(controller.searchReqMessages(controller.getVacation4UManager().getRegisteredUser()));
        controller.getVacation4UManager().getRegisteredUser().setIncomingAnsMessages(controller.searchAnsMessages(controller.getVacation4UManager().getRegisteredUser()));
        controller.getVacation4UManager().getRegisteredUser().setIncomingTradingReqMessages(controller.searchTradeReqMessages(controller.getVacation4UManager().getRegisteredUser()));
        controller.getVacation4UManager().getRegisteredUser().setIncomingTradingAnsMessages(controller.searchTradeAnsMessages(controller.getVacation4UManager().getRegisteredUser()));
        if (controller.getVacation4UManager().getRegisteredUser().getMessageNum() == 0) {
            btn_Mess.setText(" no messages");
        } else if (controller.getVacation4UManager().getRegisteredUser().getConfirmedMessageNum() == 1) {
            btn_Mess.setText(controller.getVacation4UManager().getRegisteredUser().getConfirmedMessageNum() + " message");
        } else {
            btn_Mess.setText(controller.getVacation4UManager().getRegisteredUser().getConfirmedMessageNum() + " messages");
        }
        btn_Mess.setVisible(true);
        txt_Welcome.setText("welcome " + controller.getVacation4UManager().getRegisteredUser().getUserName()+ " !");
        txt_Welcome.setVisible(true);
    }


    private void incorrectPassword() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Incorrect Password");
        alert.setHeaderText("Try again or restore password");
        alert.showAndWait();
    }

    public void goToSearchVacation(ActionEvent actionEvent) throws IOException, ParseException {
        if (datesLegal(dp_return, dp_departure))
            if (checkOneValuesIsLegal(sp_adults.getValue().toString()) && checkOneValuesIsLegal(sp_children.getValue().toString()) &&
                    checkOneValuesIsLegal(txtfld_FROM.getText()) && checkOneValuesIsLegal(txtfld_TO.getText())) {
                String ticketType = "adult"; // need to change
                LocalDate dp_returnDate=null;
                if(dp_departure.getValue()!=null) {
                    dp_returnDate = LocalDate.of(dp_departure.getValue().getYear(), dp_departure.getValue().getMonthValue(), dp_departure.getValue().getDayOfMonth());
                }
                LocalDate dp_departureDate = LocalDate.of(dp_return.getValue().getYear(), dp_return.getValue().getMonthValue(), dp_return.getValue().getDayOfMonth());
                String fromPlace = txtfld_FROM.getText();
                String toPlace = txtfld_TO.getText();

                // Return the available vacations that nobody buy yet.
                controller.getVacation4UManager().setFoundVacation(controller.searchVacation(fromPlace, toPlace, dp_departureDate, dp_returnDate, ticketType));
                // Return the vacations that is optional to trade (someone bought the vacation already).
                ArrayList<Vacation>  foundVacationForTrading = controller.searchVacationTrading(fromPlace, toPlace, dp_departureDate, dp_returnDate, ticketType);


                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("foundVacations.fxml"));
                controller.getVacation4UManager().getPagesApp().add("foundVacations");
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 700, 500);
                scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                Stage stage = (Stage) btn_GoSearchVacation.getScene().getWindow();
                String title = "Available Vacations";
                stage.setTitle(title);
                stage.setScene(scene);
                stage.show();

                //   bnt_showMeVac= (Button) scene.lookup("bnt_showMeVac");
                lab_looking = (Label) scene.lookup("#lab_looking");

                TitledPane[] tps = new TitledPane[controller.getVacation4UManager().getFoundVacation().size()]; // TitledPane of the available vacations that nobody buy yet.
                for (int i = 0; i < controller.getVacation4UManager().getFoundVacation().size(); i++) {
                    acc_Vacations = (Accordion) scene.lookup("#acc_Vacations");
                    TextArea TA = new TextArea(controller.getVacation4UManager().getFoundVacation().get(i).toString());
                    Button Bt = new Button(controller.getVacation4UManager().getFoundVacation().get(i).getFlightNum());
                    String flightNumOfVacation = controller.getVacation4UManager().getFoundVacation().get(i).getFlightNum();
                    String seller = controller.getVacation4UManager().getFoundVacation().get(i).getSaler();
                    Bt.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            Bt.getText();
                            controller.getVacation4UManager().setVacationToBuy(controller.searchVacationFlightNumBySeller(flightNumOfVacation, seller));
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("vacationDetails.fxml"));
                            controller.getVacation4UManager().getPagesApp().add("vacationDetails");
                            Parent root = null;
                            try {
                                root = fxmlLoader.load();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            Scene scene = new Scene(root, 700, 500);
                            scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                            Stage stage = (Stage) Bt.getScene().getWindow();
                            String title = "Available Vacations";
                            stage.setTitle(title);
                            stage.setScene(scene);
                            stage.show();
                            //vacationToBuy = (Vacation) controller.searchVacationByFlightNum(Bt.getText());
                            TA_details = (TextArea) scene.lookup("#TA_details");
                            TA_details.setText(controller.getVacation4UManager().getVacationToBuy().toString());
                        }
                    });

                    GridPane GP = new GridPane();
                    GP.add(TA, 0, 0);
                    GP.add(Bt, 1, 0);
                    tps[i] = new TitledPane(controller.getVacation4UManager().getFoundVacation().get(i).getFlightNum(), GP);

                }
                if (tps.length > 0) {
                    acc_Vacations.getPanes().addAll(tps);
                    acc_Vacations.setExpandedPane(tps[0]);
                }

                if(foundVacationForTrading!=null) {
                    TitledPane[] tps1 = new TitledPane[foundVacationForTrading.size()];
                    for (int i = 0; i < foundVacationForTrading.size(); i++) {
                        ChoiceBox cb = null;
                        if (controller.getVacation4UManager().getRegisteredUser()!= null) {
                            ArrayList<Vacation> userVactions = controller.getUserVacations(controller.getVacation4UManager().getRegisteredUser().getUserName());
                            cb = new ChoiceBox(FXCollections.observableArrayList(
                                    userVactions)
                            );
                        }
                        acc_Vacations1 = (Accordion) scene.lookup("#acc_Vacations1");
                        TextArea TA = new TextArea(foundVacationForTrading.get(i).toString());
                        Button Bt = new Button(foundVacationForTrading.get(i).getFlightNum());
                        String flightNumOfVacationToGet = foundVacationForTrading.get(i).getFlightNum();
                        String seller = foundVacationForTrading.get(i).getSaler();
                        Label lb = new Label("Vacation to offer");
                        ChoiceBox finalCb = cb;
                        Bt.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent e) {
                                // User must choose some of his vacation to trade offer
                                if (finalCb.getValue() == null) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Details are invalid\n");
                                    alert.setContentText("You have to choose vacation to offer");
                                    alert.showAndWait();
                                    return;
                                } else {
                                    controller.getVacation4UManager().setVacationToOffer((Vacation) finalCb.getValue());
                                    controller.getVacation4UManager().setVacationToBuy(controller.searchVacationFlightNumBySeller(flightNumOfVacationToGet, seller));
                                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("vacationTrading.fxml"));
                                    controller.getVacation4UManager().getPagesApp().add("vacationTrading");

                                    Parent root = null;
                                    try {
                                        root = fxmlLoader.load();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                    Scene scene = new Scene(root, 700, 500);
                                    scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                                    Stage stage = (Stage) Bt.getScene().getWindow();
                                    String title = "Vacation Trading";
                                    stage.setTitle(title);
                                    stage.setScene(scene);
                                    stage.show();
                                    //vacationToBuy = (Vacation) controller.searchVacationByFlightNum(Bt.getText());
                                    TA_vacationOfferDetails = (TextArea) scene.lookup("#TA_vacationOfferDetails");
                                    TA_vacationToGetDetails = (TextArea) scene.lookup("#TA_vacationToGetDetails");
                                    TA_vacationOfferDetails.setText(controller.getVacation4UManager().getVacationToOffer().toString());
                                    TA_vacationToGetDetails.setText(controller.getVacation4UManager().getVacationToBuy().toString());
                                }
                            }
                        });

                        GridPane GP1 = new GridPane();
                        GP1.add(TA, 0, 0);
                        GP1.add(Bt, 1, 0);
                        if (controller.getVacation4UManager().getRegisteredUser()!= null) {
                            GP1.add(lb, 2, 0);
                            GP1.add(cb, 2, 1);
                        }
                        tps1[i] = new TitledPane(foundVacationForTrading.get(i).getFlightNum(), GP1);

                    }
                    if (tps1.length > 0) {
                        acc_Vacations1.getPanes().addAll(tps1);
                        acc_Vacations1.setExpandedPane(tps1[0]);
                    }
                }
                root = scene.getRoot();
                stage.setScene(scene);
                stage.show();

            }


    }
    //  txtflf_found1.setText("From "+ Vacation1.getFromPlace()+" To"+Vacation1.getToPlace());


    private boolean datesLegal(DatePicker dp_departure, DatePicker dp_return) {
        if (dp_departure.getValue() == null ) {
            EmptyFieldAlert();
            return false;
        } else {
            Date currentDate = null, departureDate = null, returnDate = null;
            LocalDate localDate = LocalDate.now();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                currentDate = sdf.parse(localDate.toString());
                departureDate = sdf.parse(dp_departure.getValue().toString());
                if(dp_return.getValue() !=null){
                returnDate = sdf.parse(dp_return.getValue().toString());}
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
            if(dp_return.getValue() !=null){
             if (departureDate.compareTo(returnDate) > 0) {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Details are invalid\n");
                 alert.setHeaderText(" return date can not be before departure\n");
                 alert.setContentText("Try again");

                 alert.showAndWait();
                 return false;
             }
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
        if (controller.getVacation4UManager().getRegisteredUser() == null || (!controller.getVacation4UManager().getRegisteredUser().isLogIn())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Be Attention");
            alert.setHeaderText("You need to be logged in to sell your vacation\n");
            alert.setContentText("Do you want to log in?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... user chose OK
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("logIn.fxml").openStream());
                controller.getVacation4UManager().getPagesApp().add("logIn");
                Scene scene = new Scene(root, 700, 500);
                Stage stage = (Stage) btn_toBuy.getScene().getWindow();
                scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                stage.setTitle("Log in");
                stage.setScene(scene);
                stage.show();
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
                controller.getVacation4UManager().getPagesApp().add("homeMenu");
                Scene scene = new Scene(root, 700, 500);
                Stage stage = (Stage) btn_toBuy.getScene().getWindow();
                scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                stage.setTitle("Vacation 4U");
                stage.setScene(scene);
                stage.show();
            }
        } else {

            if (controller.getVacation4UManager().getPagesApp().get(controller.getVacation4UManager().getPagesApp().size() - 1) == "vacationDetailsAfterOk") {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("secure payment ");
                alert.setHeaderText("This step will go beyond filling out details for a secure payment");
                alert.setContentText("We assume that your secure payment has been successful");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    controller.getVacation4UManager().getRegisteredUser().buyVacation(controller.getVacation4UManager().getVacationToBuy());
                    controller.getVacation4UManager().getVacationToBuy().setBuyer(controller.getVacation4UManager().getRegisteredUser().getUserName());
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Congratulations");
                    alert.setHeaderText("You bought your vacation to " + controller.getVacation4UManager().getVacationToBuy().getToPlace() + " !!!");
                    alert.setContentText(controller.getVacation4UManager().getVacationToBuy().getToPlace() + " feels closer than ever...!");
                    alert.showAndWait();

                    if (controller.updateVacationSell(controller.getVacation4UManager().getVacationToBuy(), controller.getVacation4UManager().getRegisteredUser().getUserName())) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
                        controller.getVacation4UManager().getPagesApp().add("homeMenu");
                        Scene scene = new Scene(root, 700, 500);
                        Stage stage = (Stage) btn_toBuy.getScene().getWindow();
                        scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                        stage.setTitle("Vacation 4U");
                        stage.setScene(scene);
                        stage.show();
                        java.lang.System.out.println("deleted");
                        btn_Mess = (Button) scene.lookup("#btn_Mess");
                        btn_Mess.setVisible(false);
                        btn_PersonalArea=(Button) scene.lookup("#btn_PersonalArea");
                        btn_PersonalArea.setVisible(false);
                        txt_Welcome = (Label) scene.lookup("#txt_Welcome");
                        txt_Welcome.setVisible(false);

                    }


                }
            } else {
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Request sent");
                alert2.setHeaderText("Do You want to send the request to the seller " +controller.getVacation4UManager().getVacationToBuy().getSaler() + " ?");
                alert2.setContentText("When you receive a reply it will appear in your message box");
                Optional<ButtonType> result2 = alert2.showAndWait();
                if (result2.get() == ButtonType.OK) {
                    if (controller.sendBuyingRequestMessage(controller.getVacation4UManager().getRegisteredUser(), controller.getVacation4UManager().getVacationToBuy().getFlightNum(), controller.getVacation4UManager().getVacationToBuy().getSaler())){
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Good news");
                                alert.setHeaderText("The message has been sent ");
                                alert.showAndWait();
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) {
                                    FXMLLoader fxmlLoader = new FXMLLoader();
                                    Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
                                    controller.getVacation4UManager().getPagesApp().add("homeMenu");
                                    Scene scene = new Scene(root, 700, 500);
                                    Stage stage = (Stage) btn_toBuy.getScene().getWindow();
                                    scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                                    stage.setTitle("Vacation 4U");
                                    stage.setScene(scene);
                                    stage.show();
                                    personalHome(scene);

                                } else {
                                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("foundVacations.fxml"));
                                    controller.getVacation4UManager().getPagesApp().add("foundVacations");
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
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Be Attention");
                        alert.setHeaderText("You can not but vacation from yourself!\n");
                        alert.showAndWait();
                        FXMLLoader fxmlLoader = new FXMLLoader();

                        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
                    controller.getVacation4UManager().getPagesApp().add("homeMenu");
                        Scene scene = new Scene(root, 700, 500);
                        Stage stage = (Stage) btn_toBuy.getScene().getWindow();
                        scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                        stage.setTitle("Vacation 4U");
                        stage.setScene(scene);
                        stage.show();
                        personalHome(scene);
                    }

                }
            }
        }



    public void goToPersonalMessage(ActionEvent actionEvent) throws IOException {
        if (controller.getVacation4UManager().getRegisteredUser() == null || (!controller.getVacation4UManager().getRegisteredUser().isLogIn())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Be Attention");
            alert.setHeaderText("You need to be logged in to buy a vacation\n");
            alert.setContentText("Do you want to log in?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... user chose OK
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("logIn.fxml").openStream());
                controller.getVacation4UManager().getPagesApp().add("logIn");
                Scene scene = new Scene(root, 700, 500);
                Stage stage = (Stage) btn_Mess.getScene().getWindow();
                scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                stage.setTitle("Log in");
                stage.setScene(scene);
                stage.show();
            }

        } else {

            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("Messages.fxml").openStream());
            controller.getVacation4UManager().getPagesApp().add("Messages");
            Scene scene = new Scene(root, 700, 500);
            Stage stage = (Stage) btn_Mess.getScene().getWindow();
            scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
            stage.setTitle("your messages");
            stage.setScene(scene);
            stage.show();

            TitledPane[] tpsReq = new TitledPane[controller.getVacation4UManager().getRegisteredUser().getIncomingReqMessages().size()];
            for (int i = 0; i < controller.getVacation4UManager().getRegisteredUser().getIncomingReqMessages().size(); i++) {
                acc_reqMessage = (Accordion) scene.lookup("#acc_reqMessage");
                controller.getVacation4UManager().setCurrentMessage(controller.getVacation4UManager().getRegisteredUser().getIncomingReqMessages().get(i));
                TextArea TA = new TextArea(controller.getVacation4UManager().getRegisteredUser().getIncomingReqMessages().get(i).getFromUser().getUserName() + " want to buy from" +
                        " you flight num: " + controller.getVacation4UManager().getRegisteredUser().getIncomingReqMessages().get(i).getVacationToGet());
                Button confirm;
                Button reject = null;
                if (controller.getVacation4UManager().getRegisteredUser().getIncomingReqMessages().get(i).getStatus().equals("confirm")){
                    confirm = new Button("I got the money from " + controller.getVacation4UManager().getRegisteredUser().getIncomingReqMessages().get(i).getFromUser().getUserName());
                    int finalI = i;
                    confirm.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        controller.updateVacationSell(controller.getVacation4UManager().getVacationToBuy(),controller.getVacation4UManager().getCurrentMessage().getFromUser().getUserName());
                        controller.getVacation4UManager().getRegisteredUser().getIncomingReqMessages().get(finalI).getFromUser().buyVacation(controller.getVacation4UManager().getVacationToBuy());
                        controller.getVacation4UManager().getRegisteredUser().removeIncomingAnsMessages(controller.getVacation4UManager().getCurrentMessage());
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Congratulation");
                        alert.setHeaderText("Thanks for your update, your vacation removed from the vacation for sell list");
                        alert.showAndWait();
                        if (controller.removeMessage(controller.getVacation4UManager().getCurrentMessage())) {
                            java.lang.System.out.println("Message removed");
                        }
                        confirm.setDisable(true);
                    }

                });


                }
                else{
                    confirm = new Button("confirm " + controller.getVacation4UManager().getRegisteredUser().getIncomingReqMessages().get(i).getFromUser().getUserName());
                    reject =  new Button("Reject " + controller.getVacation4UManager().getRegisteredUser().getIncomingReqMessages().get(i).getFromUser().getUserName());
                    Button finalReject2 = reject;
                    confirm.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            controller.getVacation4UManager().getCurrentMessage().setStatus("confirm");
                            if (controller.updateMessage(controller.getVacation4UManager().getCurrentMessage(), "confirm")) {
                                controller.getVacation4UManager().getRegisteredUser().removeIncomingReqMessages(controller.getVacation4UManager().getCurrentMessage());
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Congratulation");
                                alert.setHeaderText("Your message has been sent! \n Your contact information been attached to message in order to make an appointment to transfer the money.");
                                alert.showAndWait();
                                confirm.setText("I got the money");
                                finalReject2.setDisable(true);
                                //Confirm.setDisable(true);
                            }
                        }
                    });
                    reject.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            controller.getVacation4UManager().getCurrentMessage().setStatus("reject");
                            if (controller.updateMessage(controller.getVacation4UManager().getCurrentMessage(), "reject")) {
                                controller.getVacation4UManager().getRegisteredUser().removeIncomingReqMessages(controller.getVacation4UManager().getCurrentMessage());
                                confirm.setDisable(true);
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Information");
                                alert.setHeaderText("Your reject message has been sent \n");
                                alert.showAndWait();
                            }
                        }
                    });
                }
//                Button finalReject1 = reject;
//                if (reject != null) {
//                    Button finalReject = reject;
//                    reject.setOnAction(new EventHandler<ActionEvent>() {
//                        @Override
//                        public void handle(ActionEvent e) {
//                            controller.getVacation4UManager().getCurrentMessage().setStatus("reject");
//                            if (controller.updateMessage(controller.getVacation4UManager().getCurrentMessage(), "reject")) {
//                                controller.getVacation4UManager().getRegisteredUser().removeIncomingReqMessages(controller.getVacation4UManager().getCurrentMessage());
//                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                                alert.setTitle("Congratulation");
//                                alert.setHeaderText("Your message has been sent \n");
//                                alert.showAndWait();
//                                if (finalReject != null)
//                                    finalReject.setDisable(true);
//                            }
//                        }
//                    });
//                }
                GridPane GP = new GridPane();
                GP.add(TA, 0, 0);

                GP.add(confirm, 1, 0);
                if (reject != null)
                    GP.add(reject, 2, 0);
                tpsReq[i] = new TitledPane("request number " + (i + 1), GP);
            }

            if (controller.getVacation4UManager().getRegisteredUser().getIncomingReqMessages().size() != 0) {
                acc_reqMessage.getPanes().addAll(tpsReq);
                acc_reqMessage.setExpandedPane(tpsReq[0]);
            }

            acc_ansMessage = (Accordion) scene.lookup("#acc_ansMessage");

            TitledPane[] tpsAns = new TitledPane[controller.getVacation4UManager().getRegisteredUser().getIncomingAnsMessages().size()];
            for (int i = 0; i < controller.getVacation4UManager().getRegisteredUser().getIncomingAnsMessages().size(); i++) {
                Button deleteMsg = null;
//                acc_ansMessage= (Accordion) scene.lookup("#acc_ansMessage");
                controller.getVacation4UManager().setCurrentMessage(controller.getVacation4UManager().getRegisteredUser().getIncomingAnsMessages().get(i));
                TextArea TA = new TextArea(controller.getVacation4UManager().getRegisteredUser().getIncomingAnsMessages().get(i).getToUser().getUserName() + " answer to your request" +
                        " regarding flight num: " + controller.getVacation4UManager().getRegisteredUser().getIncomingAnsMessages().get(i).getVacationToGet() + ". The answer is: "
                        + controller.getVacation4UManager().getRegisteredUser().getIncomingAnsMessages().get(i).getStatus() + "\n");
                if (controller.getVacation4UManager().getRegisteredUser().getIncomingAnsMessages().get(i).getStatus().equals("confirm")){
//                    btn_toBuy.setVisible(false);
                    TA.appendText("Please contact the seller by the next phone number \n in order to make an appointment to transfer the money:\n");
                    TA.appendText(controller.getUserContactInformation(controller.getVacation4UManager().getRegisteredUser().getIncomingAnsMessages().get(i).getToUser()));
                }
                else if (controller.getVacation4UManager().getRegisteredUser().getIncomingAnsMessages().get(i).getStatus().equals("reject")){
                    if (controller.getVacation4UManager().getRegisteredUser().getIncomingAnsMessages().get(i).getFromUser().getUserName().equals(controller.getVacation4UManager().getRegisteredUser().getUserName())){
                        deleteMsg = new Button("Delete");
                        int finalI = i;
                        deleteMsg.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent e) {
                                controller.getVacation4UManager().getRegisteredUser().removeIncomingAnsMessages(controller.getVacation4UManager().getCurrentMessage());
                                if (controller.removeMessage(controller.getVacation4UManager().getCurrentMessage())) {
                                    java.lang.System.out.println("Message removed");
                                }
                            }
                        });

//                        + controller.getVacation4UManager().getRegisteredUser().getIncomingTradingReqMessages().get(i).toString()
                    }
                }


                GridPane GP = new GridPane();
                GP.add(TA, 0, 0);
                if (deleteMsg != null)
                    GP.add(deleteMsg, 1, 0);
                tpsAns[i] = new TitledPane("answer number " + (i + 1), GP);

            }
            if (controller.getVacation4UManager().getRegisteredUser().getIncomingAnsMessages().size() != 0) {
                acc_ansMessage.getPanes().addAll(tpsAns);
                acc_ansMessage.setExpandedPane(tpsAns[0]);
            }

            acc_TradeReqMessage = (Accordion) scene.lookup("#acc_TradeReqMessage");

            TitledPane[] TradeTpsReq = new TitledPane[controller.getVacation4UManager().getRegisteredUser().getIncomingTradingReqMessages().size()];
            for (int i = 0; i < controller.getVacation4UManager().getRegisteredUser().getIncomingTradingReqMessages().size(); i++) {
                controller.getVacation4UManager().setCurrentMessage(controller.getVacation4UManager().getRegisteredUser().getIncomingTradingReqMessages().get(i));
                TextArea TA = new TextArea(controller.getVacation4UManager().getRegisteredUser().getIncomingTradingReqMessages().get(i).getFromUser().getUserName() + " want to so some trading. He wants " +
                        "flight num: " + controller.getVacation4UManager().getRegisteredUser().getIncomingTradingReqMessages().get(i).getVacationToGet()+
                ". He offer flight num: "+ controller.getVacation4UManager().getRegisteredUser().getIncomingTradingReqMessages().get(i).getVacationOffer());
                Button Confirm = new Button("confirm " + controller.getVacation4UManager().getRegisteredUser().getIncomingTradingReqMessages().get(i).getFromUser().getUserName().toString());
                Button Reject = new Button("Reject " + controller.getVacation4UManager().getRegisteredUser().getIncomingTradingReqMessages().get(i).getFromUser().getUserName().toString());
                Confirm.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        controller.getVacation4UManager().getCurrentMessage().setStatus("confirm");
                        if (controller.updateTradingMessage((UserTradingMessage)controller.getVacation4UManager().getCurrentMessage(), "confirm")) {
                            controller.getVacation4UManager().getRegisteredUser().removeIncomingTradingReqMessages(controller.getVacation4UManager().getCurrentMessage());
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Congratulation");
                            alert.setHeaderText("Your message has been sent \n");
                            alert.showAndWait();
                            Confirm.setDisable(true);
                        }
                    }
                });
                Reject.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        controller.getVacation4UManager().getCurrentMessage().setStatus("reject");
                        if (controller.updateTradingMessage((UserTradingMessage) controller.getVacation4UManager().getCurrentMessage(), "reject")) {
                            controller.getVacation4UManager().getRegisteredUser().removeIncomingTradingReqMessages(controller.getVacation4UManager().getCurrentMessage());
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Congratulation");
                            alert.setHeaderText("Your message has been sent \n");
                            alert.showAndWait();
                            Reject.setDisable(true);
                        }
                    }
                });
                GridPane GP = new GridPane();
                GP.add(TA, 0, 0);

                GP.add(Confirm, 1, 0);
                GP.add(Reject, 2, 0);
                TradeTpsReq[i] = new TitledPane("request number " + (i + 1), GP);
            }

            if (controller.getVacation4UManager().getRegisteredUser().getIncomingTradingReqMessages().size() != 0) {
                acc_TradeReqMessage.getPanes().addAll(TradeTpsReq);
                acc_TradeReqMessage.setExpandedPane(TradeTpsReq[0]);
            }

            acc_TradeAnsMessage = (Accordion) scene.lookup("#acc_TradeAnsMessage");

            TitledPane[] TradeTpsAns = new TitledPane[controller.getVacation4UManager().getRegisteredUser().getIncomingTradingAnsMessages().size()];
            for (int i = 0; i < controller.getVacation4UManager().getRegisteredUser().getIncomingTradingAnsMessages().size(); i++) {
                controller.getVacation4UManager().setCurrentMessage(controller.getVacation4UManager().getRegisteredUser().getIncomingTradingAnsMessages().get(i));
                TextArea TA = new TextArea(controller.getVacation4UManager().getRegisteredUser().getIncomingTradingAnsMessages().get(i).getToUser().getUserName() + " answer to your request  " +
                        " regarding flight num: " + controller.getVacation4UManager().getRegisteredUser().getIncomingTradingAnsMessages().get(i).getVacationToGet() + ".\nThe answer is: "
                        + controller.getVacation4UManager().getRegisteredUser().getIncomingTradingAnsMessages().get(i).getStatus());
                Button trading = new Button("trading " );
                Button btnOk= new Button("OK");
                btnOk.setVisible(false);
                if (!(controller.getVacation4UManager().getCurrentMessage().getStatus().equals("confirm"))) {
                    trading.setDisable(true);
                    btnOk.setVisible(true);
                }
                trading.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        controller.getVacation4UManager().getRegisteredUser().removeIncomingTradingAnsMessages(controller.getVacation4UManager().getCurrentMessage());
                        controller.getVacation4UManager().setVacationToOffer(controller.searchVacationFlightNumByBuyer(controller.getVacation4UManager().getCurrentMessage().getVacationOffer(), controller.getVacation4UManager().getCurrentMessage().getFromUser().getUserName()));
                        controller.getVacation4UManager().setVacationToBuy(controller.searchVacationFlightNumByBuyer(controller.getVacation4UManager().getCurrentMessage().getVacationToGet(), controller.getVacation4UManager().getCurrentMessage().getToUser().getUserName()));
                        controller.updateVacationSell(controller.getVacation4UManager().getVacationToOffer(), controller.getVacation4UManager().getVacationToBuy().getBuyer());
                        controller.updateVacationSell(controller.getVacation4UManager().getVacationToBuy(), controller.getVacation4UManager().getVacationToOffer().getBuyer());
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("vacationDetails.fxml"));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load(getClass().getClassLoader().getResource("vacationDetails.fxml").openStream());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Congratulation");
                        alert.setHeaderText("trading success!");
                        alert.setContentText("the flight to " + controller.getVacation4UManager().getVacationToBuy().getFromPlace()+" is closer then ever ! " );
                        alert.showAndWait();
                        if (controller.removeTradeMessage((UserTradingMessage) controller.getVacation4UManager().getCurrentMessage())) {
                            java.lang.System.out.println("Message removed");
                        }
                        Parent root2 = null;
                        FXMLLoader fxmlLoader2 = new FXMLLoader();
                        try {
                             root2 = fxmlLoader2.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        controller.getVacation4UManager().getPagesApp().add("homeMenu");
                        Scene scene2 = new Scene(root2, 700, 500);
                        scene2.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                        Stage stage2= (Stage) trading.getScene().getWindow();
                        String title = "Welcome " + controller.getVacation4UManager().getRegisteredUser().getFirstName() + " " +controller.getVacation4UManager().getRegisteredUser().getLastName();
                        stage2.setTitle(title);
                        stage2.setScene(scene2);
                        stage2.show();
                        personalHome(scene2);
                    }

                });

                btnOk.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        if (controller.removeTradeMessage((UserTradingMessage) controller.getVacation4UManager().getCurrentMessage())) {
                            java.lang.System.out.println("Message removed");
                        }

                        FXMLLoader fxmlLoader = new FXMLLoader();
                        Parent root = null;
                        try {
                            root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        controller.getVacation4UManager().getPagesApp().add("homeMenu");
                        Scene scene = new Scene(root, 700, 500);
                        scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                        Stage stage = (Stage) btnOk.getScene().getWindow();
                        String title = "Welcome " + controller.getVacation4UManager().getRegisteredUser().getFirstName() + " " + controller.getVacation4UManager().getRegisteredUser().getLastName();
                        stage.setTitle(title);
                        stage.setScene(scene);
                        stage.show();
                        personalHome(scene);
                    }

                });

                GridPane GP = new GridPane();
                GP.add(TA, 0, 0);
                GP.add(trading, 1, 0);
                GP.add(btnOk, 2, 0);
                TradeTpsAns[i] = new TitledPane("answer number " + (i + 1), GP);

            }
            if (controller.getVacation4UManager().getRegisteredUser().getIncomingTradingAnsMessages().size() != 0) {
                acc_TradeAnsMessage.getPanes().addAll(TradeTpsAns);
                acc_TradeAnsMessage.setExpandedPane(TradeTpsAns[0]);
            }


            root = scene.getRoot();
            stage.setScene(scene);
            stage.show();

        }
    }

    public void handleWithMessage(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(btn_Reject)) {
            java.lang.System.out.println("delete");
        } else if (actionEvent.getSource().equals(btn_Confirm)) {
            java.lang.System.out.println("not delete");
        }
    }

    public void backFromPersonal(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
        controller.getVacation4UManager().getPagesApp().add("homeMenu");
        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
        Stage stage = (Stage) txt_messageLabel.getScene().getWindow();
        String title = "Welcome " + controller.getVacation4UManager().getRegisteredUser().getFirstName() + " " + controller.getVacation4UManager().getRegisteredUser().getLastName();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        personalHome(scene);

    }

    public void goToSellVacation(ActionEvent actionEvent) throws IOException {
        if (controller.getVacation4UManager().getRegisteredUser() == null || (!controller.getVacation4UManager().getRegisteredUser().isLogIn())) {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Be Attention");
                alert.setHeaderText("You need to be logged in to sell your vacation\n");
                alert.setContentText("Do you want to log in?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    // ... user chose OK
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("logIn.fxml").openStream());
                    controller.getVacation4UManager().getPagesApp().add("logIn");
                    Scene scene = new Scene(root, 700, 500);
                    Stage stage = (Stage) btn_SellVacation.getScene().getWindow();
                    scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                    stage.setTitle("Log in");
                    stage.setScene(scene);
                    stage.show();
                } else {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
                    controller.getVacation4UManager().getPagesApp().add("homeMenu");
                    Scene scene = new Scene(root, 700, 500);
                    Stage stage = (Stage) btn_SellVacation.getScene().getWindow();
                    scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                    stage.setTitle("Vacation 4U");
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("addVacation.fxml").openStream());
            controller.getVacation4UManager().getPagesApp().add("sellVacation");
            Scene scene = new Scene(root, 700, 500);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
            Stage stage = (Stage) btn_SellVacation.getScene().getWindow();
            String title = "Welcome " + controller.getVacation4UManager().getRegisteredUser().getFirstName() + " " + controller.getVacation4UManager().getRegisteredUser().getLastName();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
            personalHome(scene);
        }
    }


    public void accommodationChanged(ActionEvent actionEvent) {
        cb_rate.setDisable(!cb_rate.isDisabled());
        cb_accommondation.setDisable(!cb_accommondation.isDisabled());
    }

    public void flightBackChanged(ActionEvent actionEvent) {
        dp_returnDate.setDisable(!dp_returnDate.isDisable());
    }


    public void addVacation(ActionEvent actionEvent) throws IOException {
        //    public Vacation(String FlightNum, String FromPlace, String ToPlace, String Airline, Date FromDate,
        //        Date ToDate, int TicketNum, String baggage, int baggageWeight,
        //String Back, Date BackDate, String Kind, String hotel, String salerName) {
        String flightNum = tf_flightNumber.getText();
        String from = tf_from.getText();
        String to = tf_to.getText();
        String airlineCompany = tf_airlineName.getText();
        String ticketType = (String) cb_ticketType.getValue();
        String baggageWeight = (String) cb_baggage.getValue();
        String kind = cb_kind.getValue();
        LocalDate toDate = null;
        LocalDate fromDate = LocalDate.of(dp_departureDate.getValue().getYear(), dp_departureDate.getValue().getMonthValue(), dp_departureDate.getValue().getDayOfMonth());
        String lodging = "";
        if (flightNum == null || from == null || to == null || airlineCompany == null || ticketType == null || baggageWeight == null || kind == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Details are invalid\n");
            alert.setHeaderText(" You must fill all details\n");
            alert.setContentText("Try again");
            alert.showAndWait();
            return;
        }


        if (!dp_returnDate.isDisable()) {
            if (!datesLegal(dp_departureDate, dp_returnDate))
                return;
            toDate = LocalDate.of(dp_returnDate.getValue().getYear(), dp_returnDate.getValue().getMonthValue(), dp_returnDate.getValue().getDayOfMonth());
        } else if (!datesLegal(dp_departureDate, dp_departureDate)) {
            return;
        }

        if (cb_accommondation.getValue() != null)
            lodging = cb_accommondation.getValue() + cb_rate.getValue();
        //public Vacation(String flightNum, String fromPlace, String toPlace, String airlineCompany, Date fromDate, Date toDate, String ticketType, String baggage, String tripType, String lodging, String saler) {

        if (controller.addVacationToSell(flightNum, from, to, airlineCompany, fromDate, toDate, ticketType, baggageWeight, kind, lodging, controller.getVacation4UManager().getRegisteredUser())) {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Good news\n");
            alert2.setHeaderText("Your vacation been added\n");
            alert2.showAndWait();

            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
            controller.getVacation4UManager().getPagesApp().add("homeMenu");
            Scene scene = new Scene(root, 700, 500);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
            Stage stage = (Stage) btn_sell.getScene().getWindow();
            String title = "Welcome " + controller.getVacation4UManager().getRegisteredUser().getFirstName() + " " + controller.getVacation4UManager().getRegisteredUser().getLastName();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
            personalHome(scene);


        }
    }

    public void goToMyVacation(ActionEvent actionEvent) throws IOException {
        if (controller.getVacation4UManager().getRegisteredUser() == null || (!controller.getVacation4UManager().getRegisteredUser().isLogIn())) {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Be Attention");
                alert.setHeaderText("You need to be logged in to see your vacations\n");
                alert.setContentText("Do you want to log in?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    // ... user chose OK
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("logIn.fxml").openStream());
                    controller.getVacation4UManager().getPagesApp().add("logIn");
                    Scene scene = new Scene(root, 700, 500);
                    Stage stage = (Stage) btn_SellVacation.getScene().getWindow();
                    scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                    stage.setTitle("Log in");
                    stage.setScene(scene);
                    stage.show();
                } else {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
                    controller.getVacation4UManager().getPagesApp().add("homeMenu");
                    Scene scene = new Scene(root, 700, 500);
                    Stage stage = (Stage) btn_SellVacation.getScene().getWindow();
                    scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                    stage.setTitle("Vacation 4U");
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("myVacations.fxml").openStream());
            controller.getVacation4UManager().getPagesApp().add("myVacations");
            Scene scene = new Scene(root, 700, 500);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
            Stage stage = (Stage) btn_SellVacation.getScene().getWindow();
            String title = controller.getVacation4UManager().getRegisteredUser().getFirstName() + " " + controller.getVacation4UManager().getRegisteredUser().getLastName() + " Vacations";
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
            //personalHome(scene);
            controller.setUserVacations(controller.getVacation4UManager().getRegisteredUser());
            TitledPane[] tps = new TitledPane[controller.getVacation4UManager().getRegisteredUser().getMyVacations().size()];
            for (int i = 0; i < controller.getVacation4UManager().getRegisteredUser().getMyVacations().size(); i++) {
                my_Vacations = (Accordion) scene.lookup("#user_Vacations");
                TextArea TA = new TextArea(controller.getVacation4UManager().getRegisteredUser().getMyVacations().get(i).toString());
                Button Bt = new Button(controller.getVacation4UManager().getRegisteredUser().getMyVacations().get(i).getFlightNum());
//                Bt.setOnAction(new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent e) {
//                        Bt.getText();
//                        //vacationToBuy= controller.searchVacationByFlightNum(Bt.getText());
//                        vacationToBuy= controller.searchVacationFlightNumBySeller(Bt.getText());
//                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("vacationDetails.fxml"));
//                       controller.getVacation4UManager().getPagesApp().add("vacationDetails");
//                        Parent root = null;
//                        try {
//                            root = fxmlLoader.load();
//                        } catch (IOException e1) {
//                            e1.printStackTrace();
//                        }
//                        Scene scene = new Scene(root, 700, 500);
//                        scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
//                        Stage stage = (Stage) Bt.getScene().getWindow();
//                        String title = "available vacations";
//                        stage.setTitle(title);
//                        stage.setScene(scene);
//                        stage.show();
//                        //vacationToBuy = (Vacation) controller.searchVacationByFlightNum(Bt.getText());
//                        TA_details = (TextArea) scene.lookup("#TA_details");
//                        TA_details.setText(vacationToBuy.toString());
//                    }
//                });

                GridPane GP = new GridPane();
                GP.add(TA, 0, 0);
                GP.add(Bt, 1, 0);
                tps[i] = new TitledPane(controller.getVacation4UManager().getRegisteredUser().getMyVacations().get(i).getFlightNum(), GP);
            }
            if (tps.length > 0) {
                my_Vacations.getPanes().addAll(tps);
                my_Vacations.setExpandedPane(tps[0]);
            }
            root = scene.getRoot();
            stage.setScene(scene);
            stage.show();
        }
    }


    public void sendTradeRequestMsg(ActionEvent actionEvent) throws IOException {
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Request sent");
        alert2.setHeaderText("Do You want to send the trade request to the buyer " + controller.getVacation4UManager().getVacationToBuy().getBuyer() + " ?");
        alert2.setContentText("When you receive a reply it will appear in your message box");
        Optional<ButtonType> result2 = alert2.showAndWait();
        if (result2.get() == ButtonType.OK) {
            // ... user chose OK
            if (controller.sendTradingRequestMessage(controller.getVacation4UManager().getRegisteredUser(), controller.getVacation4UManager().getVacationToOffer().getFlightNum(), controller.getVacation4UManager().getVacationToBuy().getFlightNum(), controller.getVacation4UManager().getVacationToBuy().getBuyer())){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Good news");
                        alert.setHeaderText("The message has been sent ");
                        alert.showAndWait();
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
                            controller.getVacation4UManager().getPagesApp().add("homeMenu");
                            Scene scene = new Scene(root, 700, 500);
                            Stage stage = (Stage) btn_toTrade.getScene().getWindow();
                            scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                            stage.setTitle("Vacation 4U");
                            stage.setScene(scene);
                            stage.show();
                            personalHome(scene);

                        } else {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("foundVacations.fxml"));
                            controller.getVacation4UManager().getPagesApp().add("foundVacations");
                            Parent root = fxmlLoader.load();
                            Scene scene = new Scene(root, 700, 500);
                            scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                            Stage stage = (Stage) btn_toTrade.getScene().getWindow();
                            String title = "available vacations";
                            stage.setTitle(title);
                            stage.setScene(scene);
                            stage.show();
                        }

                    }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Be Attention");
                alert.setHeaderText("You can not but vacation from yourself!\n");
                alert.showAndWait();
                FXMLLoader fxmlLoader = new FXMLLoader();

                Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
            controller.getVacation4UManager().getPagesApp().add("homeMenu");
                Scene scene = new Scene(root, 700, 500);
                Stage stage = (Stage) btn_toTrade.getScene().getWindow();
                scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                stage.setTitle("Vacation 4U");
                stage.setScene(scene);
                stage.show();
                personalHome(scene);
            }
        }


    public void allVacations(ActionEvent actionEvent) {
// Return the available vacations that nobody buy yet.
        controller.getVacation4UManager().setFoundVacation(controller.searchAllVacations());
        // Return the vacations that is optional to trade (someone bought the vacation already).
        List<Vacation> foundVacationForTrading = controller.searchAllTradingVacations();


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("foundVacations.fxml"));
        controller.getVacation4UManager().getPagesApp().add("foundVacations");
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
        Stage stage = (Stage) btn_allVacations.getScene().getWindow();
        String title = "Available Vacations";
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

        //   bnt_showMeVac= (Button) scene.lookup("bnt_showMeVac");
        lab_looking = (Label) scene.lookup("#lab_looking");

        TitledPane[] tps = new TitledPane[controller.getVacation4UManager().getFoundVacation().size()]; // TitledPane of the available vacations that nobody buy yet.
        for (int i = 0; i < controller.getVacation4UManager().getFoundVacation().size(); i++) {
            acc_Vacations = (Accordion) scene.lookup("#acc_Vacations");
            TextArea TA = new TextArea(controller.getVacation4UManager().getFoundVacation().get(i).toString());
            Button Bt = new Button(controller.getVacation4UManager().getFoundVacation().get(i).getFlightNum());
            String flightNumOfVacation = controller.getVacation4UManager().getFoundVacation().get(i).getFlightNum();
            String seller = controller.getVacation4UManager().getFoundVacation().get(i).getSaler();
            Bt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    Bt.getText();
                    controller.getVacation4UManager().setVacationToBuy(controller.searchVacationFlightNumBySeller(flightNumOfVacation, seller));
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("vacationDetails.fxml"));
                    controller.getVacation4UManager().getPagesApp().add("vacationDetails");
                    Parent root = null;
                    try {
                        root = fxmlLoader.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Scene scene = new Scene(root, 700, 500);
                    scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                    Stage stage = (Stage) Bt.getScene().getWindow();
                    String title = "Available Vacations";
                    stage.setTitle(title);
                    stage.setScene(scene);
                    stage.show();
                    //vacationToBuy = (Vacation) controller.searchVacationByFlightNum(Bt.getText());
                    TA_details = (TextArea) scene.lookup("#TA_details");
                    TA_details.setText(controller.getVacation4UManager().getVacationToBuy().toString());
                }
            });

            GridPane GP = new GridPane();
            GP.add(TA, 0, 0);
            GP.add(Bt, 1, 0);
            tps[i] = new TitledPane(controller.getVacation4UManager().getFoundVacation().get(i).getFlightNum(), GP);

        }
        if (tps.length > 0) {
            acc_Vacations.getPanes().addAll(tps);
            acc_Vacations.setExpandedPane(tps[0]);
        }


        TitledPane[] tps1 = new TitledPane[foundVacationForTrading.size()];
        for (int i = 0; i < foundVacationForTrading.size(); i++) {
            ChoiceBox cb = null;
            if (controller.getVacation4UManager().getRegisteredUser()!= null) {
                ArrayList<Vacation> userVactions = controller.getUserVacations(controller.getVacation4UManager().getRegisteredUser().getUserName());
                cb = new ChoiceBox(FXCollections.observableArrayList(
                        userVactions)
                );
            }
            acc_Vacations1 = (Accordion) scene.lookup("#acc_Vacations1");
            TextArea TA = new TextArea(foundVacationForTrading.get(i).toString());
            Button Bt = new Button(foundVacationForTrading.get(i).getFlightNum());
            String flightNumOfVacationToGet = foundVacationForTrading.get(i).getFlightNum();
            String seller = foundVacationForTrading.get(i).getSaler();
            Label lb = new Label("Vacation to offer");
            ChoiceBox finalCb = cb;
            Bt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    // User must choose some of his vacation to trade offer
                    if (finalCb.getValue() == null) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Details are invalid\n");
                        alert.setContentText("You have to choose vacation to offer");
                        alert.showAndWait();
                        return;
                    } else {
                        controller.getVacation4UManager().setVacationToOffer((Vacation) finalCb.getValue());
                        controller.getVacation4UManager().setVacationToBuy(controller.searchVacationFlightNumBySeller(flightNumOfVacationToGet, seller));
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("vacationTrading.fxml"));
                        controller.getVacation4UManager().getPagesApp().add("vacationTrading");

                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        Scene scene = new Scene(root, 700, 500);
                        scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
                        Stage stage = (Stage) Bt.getScene().getWindow();
                        String title = "Vacation Trading";
                        stage.setTitle(title);
                        stage.setScene(scene);
                        stage.show();
                        //vacationToBuy = (Vacation) controller.searchVacationByFlightNum(Bt.getText());
                        TA_vacationOfferDetails = (TextArea) scene.lookup("#TA_vacationOfferDetails");
                        TA_vacationToGetDetails = (TextArea) scene.lookup("#TA_vacationToGetDetails");
                        TA_vacationOfferDetails.setText(controller.getVacation4UManager().getVacationToOffer().toString());
                        TA_vacationToGetDetails.setText(controller.getVacation4UManager().getVacationToBuy().toString());
                    }
                }
            });

            GridPane GP1 = new GridPane();
            GP1.add(TA, 0, 0);
            GP1.add(Bt, 1, 0);
            if (controller.getVacation4UManager().getRegisteredUser() != null) {
                GP1.add(lb, 2, 0);
                GP1.add(cb, 2, 1);
            }
            tps1[i] = new TitledPane(foundVacationForTrading.get(i).getFlightNum(), GP1);

        }
        if (tps1.length > 0) {
            acc_Vacations1.getPanes().addAll(tps1);
            acc_Vacations1.setExpandedPane(tps1[0]);
        }

        root = scene.getRoot();
        stage.setScene(scene);
        stage.show();


    }

    public void goToPersonalArea(ActionEvent actionEvent) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("ManagerMenu.fxml"));
        controller.getVacation4UManager().getPagesApp().add("ManagerMenu");
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("MenuStyle.css").toExternalForm());
        Stage stage = (Stage) btn_PersonalArea.getScene().getWindow();
        String title = "Personal Area";
        stage.setTitle(title);
        stage.setScene(scene);
        btn_search = (Button) scene.lookup("#btn_search");
        btn_create = (Button) scene.lookup("#btn_create");
        if (isAdmin()){
            btn_search.setDisable(false);
            btn_create.setDisable(false);
        }

        stage.show();
    }

    public boolean isAdmin() {
        if (controller.getVacation4UManager().getRegisteredUser().getUserName().equals("admin"))
            return true;
        return false;
    }

    public void goToHomePage(ActionEvent actionEvent) {

    }
}

