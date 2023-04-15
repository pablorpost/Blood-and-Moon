import java.io.Serializable;
import java.util.List;

public class Person implements Serializable {

    private String name;
    private String nick;
    private String password;
    private DataBaseResult type;
    private List<Battle> battles;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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
