import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private final String directorio;
    private final List<Character> chracters;
    private List<Weapon> weapons;
    private List<Armor> armors;
    private final List<Minion> minions;
    private List<Modifier> modifiers;
    private List<Skill> skills;

    //Constructor
    public Store(){
        this.minions = new ArrayList<>();
        this.chracters = new ArrayList<>();
        this.armors = new ArrayList<>();
        this.weapons = new ArrayList<>();
        this.skills = new ArrayList<>();
        this.modifiers = new ArrayList<>();

        //out/atrifacts/Blood-and-moon.jar
        //this.directorio = "../../../storeFiles";

        //src/
        this.directorio = "storeFiles";

        loadStore(this.directorio);

    }

    public Store(String directorio){
        this.minions = new ArrayList<>();
        this.chracters = new ArrayList<>();
        this.armors = new ArrayList<>();
        this.weapons = new ArrayList<>();
        this.skills = new ArrayList<>();
        this.modifiers = new ArrayList<>();

        //src/directorio
        this.directorio = directorio;

        loadStore(this.directorio);

    }

    //Cargará toda la información de la store, leyendo los diferentes archivos
    public void loadStore(String directorio){
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

        Modifier modifier = new Modifier();
        try {
            this.modifiers = modifier.loadModifiers(directorio);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadCharacters();
        loadMinions();



    }

    //Cargará los esbirros
    private void loadMinions() {
        Human human = new Human(this.directorio);
        this.minions.add(human);
        Ghoul ghoul = new Ghoul(this.directorio);
        this.minions.add(ghoul);
        Demon demon = new Demon(this.directorio);
        this.minions.add(demon);

    }

    //Cargará los personajes
    public void loadCharacters() {
        Vampire vampire = new Vampire(this.directorio);
        this.chracters.add(vampire);
        Hunter hunter = new Hunter(this.directorio);
        this.chracters.add(hunter);
        Lycanthrope lycanthrope = new Lycanthrope(this.directorio);
        this.chracters.add(lycanthrope);
    }

    //Cargará las armas
    public Weapon getInfoWeapon(String name){
        for(Weapon weapon : this.weapons){
            if(weapon.getName().equals(name)){
                return weapon;
            }
        }
        return null;
    }

    //Cargará las armaduras
    public Armor getInfoArmor(String name){
        for(Armor armor : this.armors){
            if(armor.getName().equals(name)){
                return armor;
            }
        }
        return null;
    }

    //Devolverá el esbirro que tenga el nombre indicado
    public Minion getInfoMinion(String name){
        for(Minion minion : this.minions){
            if(minion.getName().equals(name)){
                return minion;
            }
        }
        return null;

    }

    //Devolverá la skill que tenga el nombre indicado
    public Skill getInfoSkill(String name){
        for(Skill skill : this.skills){
            if(skill.getName().equals(name)){
                return skill;
            }
        }
        return null;
    }

    //Devolverá la lista de personajes
    public List<Character> getChracters() {
        return chracters;
    }

    //Devolverá la lista de armas
    public List<String> getWeapons() {
        List<String> weapons = new ArrayList<>();
        for (Weapon weapon : this.weapons) {
            weapons.add(weapon.getName());
        }
        return weapons;
    }

    //Devolverá la lista de armaduras
    public List<String> getArmors() {
        List<String> armors = new ArrayList<>();
        for (Armor armor : this.armors) {
            armors.add(armor.getName());
        }
        return armors;
    }

    //Devolverá la lista de esbirros
    public List<String> getMinions() {
        List<String> minions = new ArrayList<>();
        for (Minion minion : this.minions) {
            minions.add(minion.getName());
        }
        return minions;
    }

    //Devolverá la lista de modificadores
    public List<Modifier> getModifiers() {
        return modifiers;
    }

    //Devolverá el directorio
    public String getDirectory() {
        return directorio;
    }

    //Modificará el fichero de texto del personaje indicado, con la información del mismo
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
            bw.close(); // Cerrar el objeto BufferedWriter
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al reescribir el archivo: " + e.getMessage());
        }
    }
}
