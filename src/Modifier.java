import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Modifier {
    private int type;
    private int power;
    private String name;

    //Constructor que leerá una línea de información
    public Modifier(String [] line){
        this.name = line[0];
        this.type = Integer.valueOf(line[1]);
        this.power = Integer.valueOf(line[2]);

    }
    //Constructor
    public Modifier(){
    }

    //Cargará la información de los modificadores que hay en el fichero
    public List<Modifier> loadModifiers(String directorio) throws FileNotFoundException {
        ArrayList<Modifier> modifiers = new ArrayList<>();
        String ruta = directorio + File.separatorChar + "modifiers" + File.separatorChar + "modifiers.txt";
        File fichero = new File(ruta);
        Scanner scanner = new Scanner(fichero);
        while (scanner.hasNextLine()){ //mientras existan lineas
            String linea = scanner.nextLine();
            String [] var = linea.split(" ");
            Modifier modifier = new Modifier(var);
            modifiers.add(modifier);
        }
        return modifiers;

    }

    //Devolverá el tipo
    public int getType() {
        return type;
    }

    //Devolverá el poder
    public int getPower() {
        return power;
    }

    //Modificará el poder
    public void setPower(int power) {
        this.power = power;
    }

    //Devolverá el nombre
    public String getName() {
        return name;
    }

    //Modificará el nombre
    public void setName(String name) {
        this.name = name;
    }
}
