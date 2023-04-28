import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Person implements Serializable{

    private String name;
    private String nick;
    private int password;
    private DataBaseResult type;
    private List<Battle> battles;

    //Constructor
    public Person(String nick, String name, int password, DataBaseResult type){
        this.nick = nick;
        this.name = name;
        this.password = password;
        this.type = type;
        this.battles = new ArrayList<>();
    }

    //Agregará una batalla a la lista
    public void addBattle(Battle battle){

    }

    //Devolverá el nombre
    public String getName() {
        return name;
    }

    //Modificará el nombre
    public void setName(String name) {
        this.name = name;
    }

    //Devolverá el nick
    public String getNick() {
        return nick;
    }

    //Devolverá la contraseña
    public int getPassword() {
        return password;
    }

    //Devolverá la lista de batallas
    public List<Battle> getBattles() {
        return battles;
    }

}
