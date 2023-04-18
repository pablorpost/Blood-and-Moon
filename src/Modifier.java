import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Modifier {
    private int type;
    private int power;
    private String name;

    public Modifier(String [] line){
        this.name = line[0];
        this.type = Integer.valueOf(line[1]);
        this.power = Integer.valueOf(line[2]);

    }
    public Modifier(){
    }

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
