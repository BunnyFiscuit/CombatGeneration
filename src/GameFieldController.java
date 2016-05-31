import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class GameFieldController extends Thread implements Initializable {

    @FXML AnchorPane gameFieldPane;
    @FXML Label mapNameLabel, heroNameLabel, heroClassLabel, healLabel;
    @FXML Label hpLabel, strengthLabel, defenceLabel, xpLabel, combatInfo1, combatInfo2, combatInfo3;
    @FXML Button fightButton, nextButton, cancelButton, healButton;
    @FXML ProgressBar xpBar;


    private Character hero;
    private Monster monster;
    private Stage primaryStage;
    private boolean heroDead, monsterDead;
    private double damageMultiplier = 1.25;
    private Simulation simulation;
    private Thread sim;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("game field hello!");
        xpLabel.setText("0 / 100");
        healLabel.setText("Heal for 20 XP");
        fightButton.setOnAction(event -> {
            if (monster == null){
                System.out.println("Player tried to fight unexisting monster. Please search for a new one");
            } else {
                cancelButton.setDisable(false);
                simulation = new Simulation(hero, monster);
                simulation.start();
            }
        });
        nextButton.setOnAction(event -> {
            monster = new Monster(1);
            System.out.println(monster.getName() + " selected.");
        });

        cancelButton.setOnAction(event -> {
            simulation.stop();
            cancelButton.setDisable(true);
        });

    }

    public void setMonster(Monster monster){
        this.monster = monster;
    }

    public void setHero(Character hero){
        this.hero = hero;
        heroNameLabel.setText(hero.getName());
        heroClassLabel.setText(hero.getProfession());
    }

    public void setStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void runGame(){
        System.out.println(hero.getName() + " says, 'Hello~!'");
        System.out.println("str: " + hero.getStrength() +
                ", def: " + hero.getDefence() +
                ", hp: " + hero.getHitpoints());
        hpLabel.setText(hero.getHitpoints() + "");
        strengthLabel.setText(hero.getStrength() + "");
        defenceLabel.setText(hero.getDefence() + "");

    }

    public static void main(String args[]){

    }

}
