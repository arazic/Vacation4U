import Controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Model.Model;
import View.View;
import javafx.util.Duration;

import java.util.List;

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

        View view = fxmlLoader.getController();
        view.setController(controller);
        controller.setView(view);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
