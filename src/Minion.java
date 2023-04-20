import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Minion{
    private int life;
    private String name;

    public Minion (){
    }

    public  void laodMinion(String minion) throws FileNotFoundException {
        String ruta = "storeFiles" + File.separatorChar + "minions" + File.separatorChar + minion+ ".txt";
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
    public void loadMinion(){

    }

    public void setName (String name){this.name = name;}
    public void setLife(int life){this.life = life;}
    public String getName (){return this.name;}
    public int getLife(){return this.life;}




}
