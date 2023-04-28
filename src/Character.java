import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Character implements Serializable {
    private String name;
    private int life;
    private String description;
    private List<String> weapons;
    private List<String> armors;
    private String skill;
    private List<String> minions;
    private List<String> modifiers;

    private int power;
    // Constructor de personaje
    public Character (){
        this.weapons = new ArrayList<>();
        this.armors = new ArrayList<>();
        this.minions = new ArrayList<>();
        this.modifiers = new ArrayList<>();
    }
    // Cargar la info del personaje del .txt
    public  void loadCharacter(String directorio, String character) throws FileNotFoundException {
            String ruta = directorio + File.separatorChar + "characters" + File.separatorChar + character+ ".txt";
            File fichero = new File(ruta);
            Scanner scanner = new Scanner(fichero);
            while (scanner.hasNextLine()) { //mientras existan lineas
                String linea = scanner.nextLine();
                String[] var = linea.split(":");
                readLine(var);
            }

    }
    //  Leer linea (se implementa en sus hijos)
    public void readLine(String[] var) {
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLife(int life) {
        this.life = life;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setWeapons(List<String> weapons) {
        this.weapons = weapons;
    }

    public void setArmors(List<String> armors) {
        this.armors = armors;
    }

    public void setMinions(List<String> minions) {
        this.minions = minions;
    }

    public List<String> getWeapons() {
        return this.weapons;
    }

    public List<String> getArmors() {return this.armors;}

    public List<String> getMinions() {
        return this.minions;
    }

    public void addWeapon(String weapon) {
        this.weapons.add(weapon);
    }

    public void addArmor(String armor) {
        this.armors.add(armor);
    }

    public void addModifier(String modifier) {
        this.modifiers.add(modifier);
    }

    public void addMinion(String minion) {
        this.minions.add(minion);
    }

    private void setSkillDefault() {}

    public String getName() { return this.name;}

    public int getLife() {return this.life;}

    public String getDescription() { return this.description;}

    public String getSkill() { return this.skill;}

    public List<String> getModifiers() { return this.modifiers;}

    public int getPowerAtribute() { return 0;}

    public int getPower() { return this.power;}

    public void setPower(int pow){this.power = pow;}
}
