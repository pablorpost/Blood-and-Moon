import java.util.*;

public class AdminMainMenuScreen extends Screen{

    private Admin admin;
    private Map<String, List<String>> options;

    public ScreenResult showOptions() {
        super.getManager().clearConsole();
        List<String> show = options.get("0");
        for(int i = 0; i<show.size(); i ++){
            System.out.println(show.get(i));
        }

        Scanner sc = new Scanner(System.in);
        int optionSelected = sc.nextInt();

        switch (optionSelected){
            case 0:
                ModifyStoreScreen modStoreSc = new ModifyStoreScreen(super.getManager());
                super.getManager().showScreen(modStoreSc);
                return ScreenResult.stay;

            case 1:
                BanUnbanScreen banSc = new BanUnbanScreen(super.getDataBase(), super.getManager());
                super.getManager().showScreen(banSc);
                return ScreenResult.stay;

            case 2:
                break;

            case 3:
                PopUpScreen popUp = new PopUpScreen(super.getDataBase(), super.getManager(), admin);
                ScreenResult result = popUp.showPopUp(3);
                if (result == ScreenResult.stay){
                    System.out.println(admin.getName());
                    super.getDataBase().deletePerson(admin);
                } else {
                    return ScreenResult.stay;
                }
                break;

            case 4:
                return ScreenResult.exit;
        }



        return ScreenResult.exit;
    }

    public void validatRequests(){

    }

    public AdminMainMenuScreen(DBManager dataBase, Store store, Manager manager, Admin admin) {
        super.setTitle("What would you like to do?");
        this.setDescription(null);
        setDataBase(dataBase);
        setStore(store);
        super.setManager(manager);
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

    public void ban(User user){

    }

    public Admin getAdmin() {
        return admin;
    }

    public Map<String, List<String>> getOptions() {
        return options;
    }
}
