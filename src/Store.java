import java.io.FileNotFoundException;
import java.util.List;

public class Store {
    private String directorio;
    private List<Character> chracters;
    private List<Weapon> weapons;
    private List<Armor> armors;
    private List<Minion> minions;
    private List<Modifier> modifiers;
    private List<Skill> skills;

    public Store(){
        this.directorio = "storeFiles";
        loadStore(this.directorio);


    }

    public void uploadInfo(){

    }

    private void loadStore(String directorio){
        Armor armor= new Armor();
        try {
            this.armors = armor.loadArmors(directorio);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Weapon weapon= new Weapon();
        try {
            this.weapons = weapon.loadWeapon(directorio);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Skill skill = new Skill();
        try {
            this.skills = skill.loadSkills(directorio);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public Weapon getInfoWeapon(String name){
        return null;
    }

    public Armor getInfoArmor(String name){
        return null;
    }

    public Modifier getInfoModifier(String name){
        return null;
    }

    public Minion getInfoMinion(String name){
        return null;
    }

    public Skill getInfoSkill(String name){
        return null;
    }

    public List<Character> getChracters() {
        return chracters;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public List<Armor> getArmors() {
        return armors;
    }

    public List<Minion> getMinions() {
        return minions;
    }

    public List<Modifier> getModifiers() {
        return modifiers;
    }
}
