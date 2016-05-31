import java.util.Random;

public class Simulation implements Runnable {

    private Character hero;
    private Monster monster;
    private boolean heroDead, monsterDead;
    private Random rnd;
    private Thread t;
    private int simulations = 1;
    private String threadName = "Simulation " + simulations;


    public Simulation(Character hero, Monster monster){
        System.out.println("Creating " + threadName);
        this.hero = hero; this.monster = monster;
        simulate();

    }

    private boolean simulate(){
        heroDead = false; monsterDead = false;

            try {
                while(!heroDead && !monsterDead) {
                    rnd = new Random();
                    Thread.sleep(1000);
                    System.out.println(rnd.nextInt(100));
                }
            } catch (InterruptedException ex) {
                System.out.println("Thread " + threadName + " interrupted.");
            }
        System.out.println("Thread " + threadName + " exiting.");
        if (heroDead){
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void run() {
        System.out.println("Running " + threadName);
        simulate();
        simulations++;
    }

    public void start ()
    {
        System.out.println("Starting " +  threadName );
        if (t == null)
        {
            t = new Thread (this, threadName);
            t.start ();
        }
    }

    public void stop(){
        Thread.currentThread().interrupt();
    }
}
