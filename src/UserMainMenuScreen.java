import java.util.*;

public class UserMainMenuScreen extends Screen{
    private Character character;
    private int gold;
    private Map<String, List<String>> options;
    private User request;
    private User user;


    public UserMainMenuScreen(DBManager dataBase, Store store, User user) {
        super.setTitle("What would you like to do?");
        this.setDescription(null);
        setDataBase(dataBase);
        setStore(store);
        this.user = user;
        this.options = new HashMap<>();
        List<String> auxList = new ArrayList<>();
        auxList.add("0. Challenge user");
        auxList.add("1. Configure my equipment");
        auxList.add("2. Battle history");
        auxList.add("3. Ranking");
        auxList.add("4. Delete character");
        auxList.add("5. Log out");
        this.options.put("0", auxList);

        List<String> auxList2 = new ArrayList<>();
        auxList2.add("0. Create character");
        auxList2.add("1. Delete user");
        auxList2.add("2. Battle history");
        auxList2.add("3. Ranking");
        auxList2.add("4. Log out");
        this.options.put("1", auxList2);

    }
    public ScreenResult showOptions() {
        List<String> show;
        if (this.user.getCharacter() == null){
            show = options.get("1");
        } else{
            show = options.get("0");
        }

        for(int i = 0; i<show.size(); i ++){
            System.out.println(show.get(i));
        }

        Scanner sc = new Scanner(System.in);
        int optionSelected = sc.nextInt();
        return ScreenResult.exit;
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
