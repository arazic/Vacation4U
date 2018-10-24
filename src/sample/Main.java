package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Model.Model;
import sample.View.View;

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
        Parent root = fxmlLoader.load(getClass().getResource("View/sample.fxml").openStream());
        Scene scene = new Scene(root, 600, 575);
        primaryStage.setScene(scene);

        View view = fxmlLoader.getController();
        view.setController(controller);
        controller.setView(view);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        System.out.println("chen anf");

    }
}
