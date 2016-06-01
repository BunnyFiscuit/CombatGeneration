import java.util.Random;

public class Monster implements IStats{

    private int str, def, hp;
    private String name;
    private String[] names = {"Deep Closure", "Fallen Priest", "OP Thief"};

    public Monster(int difficulty){
        // Gets a random number based on the amount of names in names.
        Random rn = new Random();
        int max = names.length;
        int nameSelect = rn.nextInt(max);
        setName(names[nameSelect]);
        // Sets stats based on difficulty int.
        switch(difficulty) {
            case 1:
                setStrength(60);
                setDefence(30);
                setHitpoints(100);
                break;
            case 2:
                setStrength(80);
                setDefence(40);
                setHitpoints(200);
                break;
        }
    }

    @Override
    public void setHitpoints(int hp){
        this.hp = hp;
    }

    @Override
    public void setStrength(int str) {
        this.str = str;
    }

    @Override
    public void setDefence(int def) {
        this.def = def;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStrength() {
        return str;
    }

    @Override
    public int getDefence() {
        return def;
    }

    @Override
    public int getHitpoints(){
        return hp;
    }
}
