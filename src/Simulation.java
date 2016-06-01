import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

import java.util.Random;

public class Simulation extends GameFieldController implements Runnable {

    private Character hero;
    private Monster monster;
    private boolean heroDead, monsterDead;
    private boolean isWinner = false;
    private Random rnd;
    private Thread t;
    private int simulations = 1;
    private String threadName = "Simulation " + simulations;
    private FXMLLoader loader;
    private GameFieldController con;


    public void setLoader(FXMLLoader loader){
        this.loader = loader;
        con = loader.getController();

    }

    public void setHero(Character hero){
        this.hero = hero;
    }

    public void setMonster(Monster monster){
        this.monster = monster;
    }

    public boolean isWinner(){
        return isWinner;
    }

    private void simulate(){
        heroDead = false; monsterDead = false;
        int damage; int block;
        rnd = new Random();
        Attack combatType;
            try {
                while(!heroDead && !monsterDead) {
                    Thread.sleep(1500);
                    if((rnd.nextInt(6)+1) > 3){
                        damage = rnd.nextInt(hero.getStrength()) + 1;
                        block = rnd.nextInt(monster.getDefence()) + 1;
                        if(damage > block){
                            damage = (damage - block);
                            monster.setHitpoints(monster.getHitpoints()-damage);
                            combatType = Attack.HERO_DAMAGE;
                        } else {
                            combatType = Attack.MONSTER_BLOCK;
                        }
                    } else {
                        damage = rnd.nextInt(monster.getStrength()) + 1;
                        block = rnd.nextInt(hero.getDefence()) + 1;
                        if (damage > block) {
                            damage = (damage - block);
                            hero.setHitpoints(hero.getHitpoints() - damage);
                            combatType = Attack.MONSTER_DAMAGE;
                        } else {
                            combatType = Attack.MONSTER_BLOCK;
                        }
                    }
                    con.combatEvent(combatType, damage);
                    if(hero.getHitpoints() <= 0){
                        heroDead = true;
                        con.combatEvent(Attack.COMBAT_END,0);
                        Platform.runLater(() -> {
                            con.cancelButton.setDisable(true);
                            con.giveXP(Winner.MONSTER);
                        });

                    } else if (monster.getHitpoints() <= 0){
                        monsterDead = true;
                        con.combatEvent(Attack.COMBAT_END,0);
                        Platform.runLater(() -> {
                            con.cancelButton.setDisable(true);
                            con.giveXP(Winner.HERO);
                        });
                    }
                }
            } catch (InterruptedException ex) {
                System.out.println("Thread " + threadName + " interrupted.");
                simulations++;
            }
        System.out.println(threadName + " exiting.");
        if (!heroDead){
            isWinner = true;
        }
    }


    @Override
    public void run() {
        System.out.println("Running " + threadName);
        simulate();
    }

    public void start()
    {
        System.out.println("Starting " +  threadName );
        if (t == null)
        {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}
