package sample;

import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.View.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Model model = new Model();
        FXMLLoader fxmlLoader = new FXMLLoader();
        View view = (View) fxmlLoader.getController();
        Controller controller = new Controller(model, view);
        Parent root = FXMLLoader.load(getClass().getResource("View/sample.fxml"));
        primaryStage.setTitle("Vacation4U System");
        primaryStage.setScene(new Scene(root, 600, 575));
        primaryStage.show();
        //view.addObserver(controller);
        //model.addObserver(controller);


    }




    public static void main(String[] args) {
        launch(args);

    }
}
