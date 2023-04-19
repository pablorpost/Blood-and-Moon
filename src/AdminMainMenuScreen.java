import java.util.*;

public class AdminMainMenuScreen extends Screen{

    private Admin admin;
    private Map<String, List<String>> options;

    public ScreenResult showOptions() {
        List<String> show = options.get("0");
        for(int i = 0; i<show.size(); i ++){
            System.out.println(show.get(i));
        }

        Scanner sc = new Scanner(System.in);
        int optionSelected = sc.nextInt();
        return ScreenResult.exit;
    }

    public void validatRequests(){

    }

    public AdminMainMenuScreen(DBManager dataBase, Store store, Admin admin) {
        super.setTitle("What would you like to do?");
        this.setDescription(null);
        setDataBase(dataBase);
        setStore(store);
        this.admin = admin;
        options = new HashMap<>();
        List<String> auxList = new ArrayList<>();
        auxList.add("0. Edit a character");
        auxList.add("1. Modify bans");
        auxList.add("2. Validate challenges");
        auxList.add("3. Delete admin account");
        auxList.add("4. Log out");
        options.put("0", auxList);
    }

    public Admin getAdmin() {
        return admin;
    }

    public Map<String, List<String>> getOptions() {
        return options;
    }
}
