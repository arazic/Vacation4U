import Controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Model.Model;
import View.System;

public class Main extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Main.primaryStage = primaryStage;
        Model model = new Model();
        Controller controller = new Controller(model);
        model.setController(controller);

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
