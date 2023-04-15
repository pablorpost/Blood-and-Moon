import java.util.List;
import java.util.Map;

public class UserMainMenuScreen extends Screen{
    private Character character;
    private int gold;
    private Map<String, List<String>> options;
    private User request;
    private User user;


    public UserMainMenuScreen(DBManager dataBase, Store store, User user) {
        setDataBase(dataBase);
        setStore(store);
        this.user = user;
    }

    public int showOptions(String option) {
        return 0; //Vacío
    }


    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Map<String, List<String>> getOptions() {
        return options;
    }

    public void setOptions(Map<String, List<String>> options) {
        this.options = options;
    }

    public User getRequest() {
        return request;
    }

    public void setRequest(User request) {
        this.request = request;
    }
}
