import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Weapon {
    private String name;
    private int hands;
    private int defense;
    private int attack;

    public Weapon(String [] line){
        this.name = line[0];
        this.hands = Integer.valueOf(line[1]);
        this.defense = Integer.valueOf(line[2]);
        this.attack = Integer.valueOf(line[3]);

    }

    public Weapon(){
    }

    public List<Weapon> loadWeapon(String directorio) throws FileNotFoundException {
        ArrayList<Weapon> weapons = new ArrayList<>();
        String ruta = directorio + File.separatorChar + "weapons";
        File fichero = new File(ruta);
        Scanner scanner = new Scanner(fichero);
        while (scanner.hasNextLine()){ //mientras existan lineas
            String linea = scanner.nextLine();
            String [] var = linea.split(" ");
            Weapon weapon = new Weapon(var);
            weapons.add(weapon);
        }
        return weapons;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHands() {
        return hands;
    }

    public void setHands(int hands) {
        this.hands = hands;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
