import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class WelcomeScreenController implements Initializable {

    @FXML Button startButton;
    @FXML AnchorPane mainAnchorPane;

    Stage primaryStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    @FXML //Shows a popup with classes to pick from.
    public void onStartButtonPressed(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/ClassSelector.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            ClassSelectorController contr = fxmlLoader.<ClassSelectorController>getController();
            contr.setStage(stage);
            contr.setPrimaryStage(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
