import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Skill {
    private String name;
    private int attackPoints;
    private int defensePoints;

    public Skill(String [] line){
        this.name = line[0];
        this.attackPoints = Integer.valueOf(line[1]);
        this.defensePoints = Integer.valueOf(line[2]);

    }
    public Skill(){
    }

    public List<Skill> loadSkills(String directorio) throws FileNotFoundException {
        ArrayList<Skill> skills = new ArrayList<>();
        String ruta = directorio + File.separatorChar + "skills";
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }
}
