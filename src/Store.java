import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        this.minions = new ArrayList<>();
        this.chracters = new ArrayList<>();

        //out/atrifacts/Blood-and-moon.jar
       // this.directorio = "../../../storeFiles";

        //src/
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
        loadCharacters();
        loadMinions();


    }

    private void loadMinions() {
        Human human = new Human(this.directorio);
        this.minions.add(human);
        Ghoul ghoul = new Ghoul(this.directorio);
        this.minions.add(ghoul);
        Demon demon = new Demon(this.directorio);
        this.minions.add(demon);

    }

    private void loadCharacters() {
        Vampire vampire = new Vampire(this.directorio);
        this.chracters.add(vampire);
        Hunter hunter = new Hunter(this.directorio);
        this.chracters.add(hunter);
        Lycanthrope lycanthrope = new Lycanthrope(this.directorio);
        this.chracters.add(lycanthrope);
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
