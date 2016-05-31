import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ClassSelectorController implements Initializable{

    @FXML AnchorPane classSelectorPane;
    @FXML Button classOkButton;
    @FXML TextField nameTextField;
    @FXML RadioButton soldierRB,warriorRB,knightRB;

    ToggleGroup group = new ToggleGroup();
    String heroClass;
    Stage stage;
    Stage primaryStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        soldierRB.setToggleGroup(group); soldierRB.setUserData("Soldier");
        warriorRB.setToggleGroup(group); warriorRB.setUserData("Warrior");
        knightRB.setToggleGroup(group); knightRB.setUserData("Knight");

        // Set default value
        heroClass = "Soldier";

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != oldValue) {
                heroClass = (String) newValue.getUserData();
            }
        });

        classOkButton.setOnAction((event -> {
            Character hero = null;
            String heroName = nameTextField.getText();
            if (heroName != null) {
                switch(heroClass) {
                    case "Soldier":
                        System.out.println(heroName + " picked " + heroClass);
                        hero = new Character(heroName,8,8,150);
                        hero.setProfession(heroClass);
                        break;
                    case "Warrior":
                        System.out.println(heroName + " picked " + heroClass);
                        hero = new Character(heroName,10,6,125);
                        hero.setProfession(heroClass);
                        break;
                    case "Knight":
                        System.out.println(heroName + " picked " + heroClass);
                        hero = new Character(heroName,6,10,175);
                        hero.setProfession(heroClass);
                        break;
                }
                runGameAs(hero);
                stage.hide();
            }
        }));
    }

    private void runGameAs(Character hero){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/GameField.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Combat Generation");
            newStage.show();
            GameFieldController controller = loader.<GameFieldController>getController();
            Thread t = new Thread(controller);
            controller.setHero(hero);
            controller.setStage(newStage);
            controller.runGame();
            primaryStage.hide();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    protected void setStage(Stage stage){
        this.stage = stage;
    }

    protected void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
