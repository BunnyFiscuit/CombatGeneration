
public class Character implements IStats {

    private int str,def,hp, xp, xpMax;
    private String name;
    private String profession;

    public Character(String name, int str, int def, int hp){
        this.name = name;
        this.str = str;
        this.def = def;
        this.hp = hp;
    }

    public void setProfession(String profession){
        this.profession = profession;
    }

    public String getProfession(){
        return profession;
    }

    public void setXP(int xp){
        this.xp = xp;
    }

    public int getXP(){
        return this.xp;
    }

    public void setXPMax(int xpMax){
        this.xpMax = xpMax;
    }

    public int getXPMAx(){
        return this.xpMax;
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
