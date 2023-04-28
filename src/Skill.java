import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Skill {
    private String name;
    private int attackPoints;
    private int defensePoints;

    //Constructor que leerá una línea para crear la skill
    public Skill(String [] line){
        this.name = line[0];
        this.attackPoints = Integer.valueOf(line[1]);
        this.defensePoints = Integer.valueOf(line[2]);

    }
    //Constructor
    public Skill(){
    }

    //Cargará las skills del archivo
    public List<Skill> loadSkills(String directorio) throws FileNotFoundException {
        ArrayList<Skill> skills = new ArrayList<>();
        String ruta = directorio + File.separatorChar + "skills"  + File.separatorChar + "skills.txt";
        File fichero = new File(ruta);
        Scanner scanner = new Scanner(fichero);
        while (scanner.hasNextLine()){ //mientras existan lineas
            String linea = scanner.nextLine();
            String [] var = linea.split(" ");
            Skill skill = new Skill(var);
            skills.add(skill);
        }
        return skills;

    }

    //Devolverá el nombre
    public String getName() {
        return name;
    }

    //Modificará el nombre
    public void setName(String name) {
        this.name = name;
    }

    //Devolverá los puntos de ataque
    public int getAttackPoints() {
        return attackPoints;
    }

    //Devolverá los puntos de defensa
    public int getDefensePoints() {
        return defensePoints;
    }


}
