import Controller.Controller;
import Model.DataBase;
import Model.User;
import Model.Vacation4UManager;
import View.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Main.primaryStage = primaryStage;
        User administrator = new User("admin", "12345678",  "13/09/1990", "admin", "admin", "Beer Sheva", "0549808037", true);
        DataBase dataBase = new DataBase("Vacation4u", administrator);
        Vacation4UManager Vacation4UManager = new Vacation4UManager(dataBase);
        Controller controller = new Controller(Vacation4UManager);
        Vacation4UManager.setController(controller);

        primaryStage.setTitle("Vacation4U View");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("homeMenu.fxml").openStream());
        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getResource("MenuStyle.css").toExternalForm());
        primaryStage.setScene(scene);

        View view = fxmlLoader.getController();
        view.setController(controller);
        controller.setView(view);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
