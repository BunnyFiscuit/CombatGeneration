import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    public static void main (String[] args){
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/WelcomeScreen.fxml"));
            Parent mainView = loader.load();
            Scene scene = new Scene(mainView);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Combat Generation");
            primaryStage.show();
            WelcomeScreenController controller = loader.<WelcomeScreenController>getController();
            controller.setPrimaryStage(primaryStage);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
