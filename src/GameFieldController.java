import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    @FXML Label mapNameLabel, heroNameLabel, heroClassLabel, healLabel, levelLabel;
    @FXML Label heroHPLabel, heroStrengthLabel, heroDefenceLabel, xpLabel, combatInfo1, combatInfo2, combatInfo3;
    @FXML Button fightButton, nextButton, cancelButton, healButton;
    @FXML ProgressBar xpBar;


    private Character hero;
    private Monster monster;
    private Stage primaryStage;
    private boolean heroDead, monsterDead;
    private double damageMultiplier = 1.25;
    private Simulation simulation;
    private Thread sim;
    private String level;
    private GameFieldController controller;
    private FXMLLoader loader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

            loader = new FXMLLoader(
                    getClass().getResource("fxml/GameField.fxml")
            );
            loader.setController(this);

        System.out.println("game field hello!");
        xpLabel.setText("0 / 100");
        healLabel.setText("Heal for 20 XP");

        simulation = new Simulation();
        simulation.setLoader(loader);
        fightButton.setOnAction(event -> {
            if (monster == null){
                System.out.println("Player tried to fight un-existing monster. Please search for a new one");
            } else {
                cancelButton.setDisable(false);
                simulation.setHero(hero);
                simulation.setMonster(monster);
                sim = new Thread(simulation);
                sim.start();
                System.out.println("Simulation started");
            }
        });
        nextButton.setOnAction(event -> {
            monster = new Monster(1);
            System.out.println(monster.getName() + " selected.");
        });

        cancelButton.setOnAction(event -> {
            sim.interrupt();
            combatEvent(Attack.COMBAT_END,0);
            cancelButton.setDisable(true);
        });

    }

    public enum Attack {
        HERO_DAMAGE,
        HERO_BLOCK,
        MONSTER_DAMAGE,
        MONSTER_BLOCK,
        COMBAT_END
    }

    public enum Winner {
        HERO,
        MONSTER
    }

    public void updateCombatInfo(String info){
        System.out.println(info);
        System.out.println(monster.getHitpoints() + "");
        Platform.runLater(() -> {
            combatInfo3.setText(combatInfo2.getText());
            combatInfo2.setText(combatInfo1.getText());
            combatInfo1.setText(info);

            if(hero.getHitpoints() < 0){
                heroHPLabel.setText("0");
            } else {
                heroHPLabel.setText("" + hero.getHitpoints());
            }
        });
    }

    public void combatEvent(Attack type, int damage){
        String info;
        switch (type) {
            case HERO_DAMAGE:       info = hero.getName() + " did " + damage + " damage to " + monster.getName();
                break;
            case HERO_BLOCK:        info = hero.getName() + " blocked damage from " + monster.getName();
                break;
            case MONSTER_DAMAGE:    info = monster.getName() + " did " + damage + " damage to " + hero.getName();
                break;
            case MONSTER_BLOCK:     info = monster.getName() + " blocked damage from " + hero.getName();
                break;
            case COMBAT_END:        info = "Combat Ended.";
                break;
            default:                info = "KAAAW KAAW~";
                break;
        }
        updateCombatInfo(info);
    }

    public void giveXP(Winner winner){
        switch(winner){
            case HERO:      updateCombatInfo("You win!");
                            hero.setXP(hero.getXP() + 20);
                            if (hero.getXP() >= hero.getXPMax()){
                                hero.setLevel(hero.getLevel() + 1);
                                hero.setXPMax(hero.getXPMax() + 50);
                                xpBar.setProgress(hero.getXPMax() / hero.getXP());
                            }
                            break;

            case MONSTER:   updateCombatInfo(monster.getName() + " wins!");
                            break;
        }
    }

    public void setMonster(Monster monster){
        this.monster = monster;
    }

    public void setHero(Character hero){
        this.hero = hero;
        heroNameLabel.setText(hero.getName());
        heroClassLabel.setText(hero.getProfession());
        levelLabel.setText("" + hero.getLevel());
    }

    public void setStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void runGame(){
        System.out.println(hero.getName() + " says, 'Hello~!'");
        System.out.println("str: " + hero.getStrength() +
                ", def: " + hero.getDefence() +
                ", hp: " + hero.getHitpoints());
        heroHPLabel.setText(hero.getHitpoints() + "");
        heroStrengthLabel.setText(hero.getStrength() + "");
        heroDefenceLabel.setText(hero.getDefence() + "");

    }

}
