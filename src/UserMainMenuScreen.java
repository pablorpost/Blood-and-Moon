import java.util.*;

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
        Map <String, List<String>> auxOptions = new HashMap<>();
        List<String> auxList = new ArrayList<>();
        auxList.add("0. Challenge user");
        auxList.add("1. Configure my equipment");
        auxList.add("2. Battle history");
        auxList.add("3. Ranking");
        auxList.add("4. Delete character");
        auxList.add("5. Log out");
        auxOptions.put("0", auxList);
        setOptions(auxOptions);

    }

    public int showOptions(String option) {
        Map <String, List<String>> auxOptions = getOptions();
        List<String> show = auxOptions.get(option);
        for(int i = 0; i<show.size(); i ++){
            System.out.println(show.get(i));
        }

        Scanner sc = new Scanner(System.in);
        int optionSelected = sc.nextInt();
        return optionSelected;
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
