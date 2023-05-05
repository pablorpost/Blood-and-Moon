import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Armor {
    private String name;
    private int defense;
    private int atack;
    // Constructor de Armor
    public Armor(String [] line) {
        this.name = line[0];
        this.defense = Integer.valueOf(line[1]);
        this.atack = Integer.valueOf(line[2]);
    }
    // Constructor vacio de Armor, para no inicializar
    public Armor() {


    }
    // Cargar las armaduras de los .txt
    public List<Armor> loadArmors(String directorio) throws FileNotFoundException {
        ArrayList<Armor> armors = new ArrayList<>();
        String ruta = directorio + File.separatorChar + "armors" + File.separatorChar + "armors.txt";
        File fichero = new File(ruta);
        Scanner scanner = new Scanner(fichero);
        while (scanner.hasNextLine()){ //mientras existan lineas
            String linea = scanner.nextLine();
            String [] var = linea.split(" ");
            Armor armor = new Armor(var);
            armors.add(armor);
        }

        return armors;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAtack() {
        return atack;
    }

    public void setAtack(int atack) {
        this.atack = atack;
    }
}
