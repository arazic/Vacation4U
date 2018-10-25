import Controller.Controller;
import Model.Model;
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
        this.primaryStage = primaryStage;
        Model model = new Model();
        Controller controller = new Controller(model);
        model.setController(controller);

        primaryStage.setTitle("Vacation4U System");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("View/Menu.fxml").openStream());
        Scene scene = new Scene(root, 300, 250);
        scene.getStylesheets().add(getClass().getResource("View/MenuStyle.css").toExternalForm());

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