import java.io.Serializable;
import java.util.List;

public abstract class Person implements Serializable{

    private String name;
    private String nick;
    private int password;
    private DataBaseResult type;
    private List<Battle> battles;

    public Person(String nick, int password, DataBaseResult type){
        this.nick = nick;
        this.password = password;
        this.type = type;
    }
    public void addBattle(Battle battle){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public DataBaseResult getType() {
        return type;
    }

    public void setType(DataBaseResult type) {
        this.type = type;
    }

    public List<Battle> getBattles() {
        return battles;
    }

    public void setBattles(List<Battle> battles) {
        this.battles = battles;
    }



}
