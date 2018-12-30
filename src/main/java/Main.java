import Controller.Controller;
import Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Model.Vacation4UManager;
import View.System;

public class Main extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Main.primaryStage = primaryStage;
        User administrator = new User("admin", "12345678",  "13/09/1990", "admin", "admin", "Beer Sheva", true);
        Vacation4UManager vacation4UManager = new Vacation4UManager(administrator);
        Controller controller = new Controller(vacation4UManager);
        vacation4UManager.setController(controller);

        primaryStage.setTitle("Vacation4U System");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getResource("MenuStyle.css").toExternalForm());
        primaryStage.setScene(scene);

        System system = fxmlLoader.getController();
        system.setController(controller);
        controller.setSystem(system);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
