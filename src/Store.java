import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        this.directorio = "../../../storeFiles";

        //src/
        //this.directorio = "storeFiles";

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

    public List<String> getWeapons() {
        List<String> weapons = new ArrayList<>();
        for (Weapon weapon : this.weapons) {
            weapons.add(weapon.getName());
        }
        return weapons;
    }

    public List<String> getArmors() {
        List<String> armors = new ArrayList<>();
        for (Armor armor : this.armors) {
            armors.add(armor.getName());
        }
        return armors;
    }

    public List<String> getMinions() {
        List<String> minions = new ArrayList<>();
        for (Minion minion : this.minions) {
            minions.add(minion.getName());
        }
        return minions;
    }

    public List<Modifier> getModifiers() {
        return modifiers;
    }

    public String getDirectory() {
        return directorio;
    }

    public void saveCharacter(int charac){
        Character character = getChracters().get(charac);
        try {
            FileWriter fw = new FileWriter(getDirectory() + File.separatorChar + "characters" + File.separatorChar+ character.getName().toLowerCase()+".txt");
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("name:" + character.getName() + "\n");
            bw.write("life:" + character.getLife() + "\n");
            bw.write("description:" + character.getDescription() + "\n");

            for (String weapon : character.getWeapons()) {
                bw.write("weapon:" + weapon + "\n");
            }

            for (String armor : character.getArmors()) {
                bw.write("armor:" + armor + "\n");
            }

            bw.write("skill:" + character.getSkill() + "\n");

            bw.write("power:" + character.getPower() + "\n");

            if (character.getName().toLowerCase().equals("vampire")){
                Vampire vamp = (Vampire)character;
                bw.write("age:" + vamp.getAge() + "\n");
                bw.write("blood:" + vamp.getBlood() + "\n");

            } else if (character.getName().toLowerCase().equals("hunter")) {
                Hunter hunt = (Hunter) character;
                bw.write("willpower:" + hunt.getWillpower() + "\n");

            } else if (character.getName().toLowerCase().equals("lycanthrope")) {
                Lycanthrope lycanth = (Lycanthrope) character;
                bw.write("anger:" + lycanth.getAnger() + "\n");

            }


            for (String minion : character.getMinions()) {
                bw.write("minion:" + minion + "\n");
            }
            for (String modifier : character.getModifiers()) {
                bw.write("modifier:" + modifier + "\n");
            }

            // Cerrar el objeto BufferedWriter
            bw.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al reescribir el archivo: " + e.getMessage());
        }
    }
}
