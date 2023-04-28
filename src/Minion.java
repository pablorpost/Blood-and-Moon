import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Minion{
    private int life;
    private String name;

    //Constructor
    public Minion (){
    }

    //Cargará la información del minion que hay en el fichero
    public  void laodMinion(String directorio,String minion) throws FileNotFoundException {
        String ruta = directorio + File.separatorChar + "minions" + File.separatorChar + minion+ ".txt";
        File fichero = new File(ruta);
        Scanner scanner = new Scanner(fichero);
        while (scanner.hasNextLine()) { //mientras existan lineas
            String linea = scanner.nextLine();
            String[] var = linea.split(":");
            readLine(var);
        }

    }


    public void readLine(String[] var) {
    }


    //Modificará el nombre
    public void setName (String name){this.name = name;}
    //Modificará la vida
    public void setLife(int life){this.life = life;}
    //Devolverá el nombre
    public String getName (){return this.name;}
    //Devolverá la vida
    public int getLife(){return this.life;}




}
