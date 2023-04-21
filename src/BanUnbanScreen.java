import java.util.*;

public class BanUnbanScreen extends Screen{
    private boolean ban;
    private Map<String, List<String>> options;
    public BanUnbanScreen(DBManager dataBase, Manager manager) {
        super.setManager(manager);
        super.setDataBase(dataBase);
        this.setDescription(null);
        options = new HashMap<>();
        List<String> auxList = new ArrayList<>();
        auxList.add("0. Ban user");
        auxList.add("1. Unban user");
        auxList.add("2. Exit");
        options.put("0", auxList);
    }

    public ScreenResult showOptions(){
        Manager m =  super.getManager();
        m.clearConsole();
        List<String> show = options.get("0");
        for(int i = 0; i<show.size(); i ++){
            System.out.println(show.get(i));
        }

        Scanner sc = new Scanner(System.in);
        int optionSelected = sc.nextInt();

        switch (optionSelected){
            case 0:
                m.clearConsole();
                System.out.println("Write the user's nick:");
                Scanner bannedUser = new Scanner(System.in);
                String election = bannedUser.nextLine();
                DBManager db = super.getDataBase();
                User userToBan = db.getUserToBan(election);
                if (userToBan != null){
                    ban(userToBan);
                } else {
                    System.out.println("User not found");
                }
                break;

            case 1:
                break;

            case 2:
                return ScreenResult.exit;
        }
        return ScreenResult.stay;
    }

    public void ban(User user){
        user.setBan(true);
    }
    public void desBan(User user){

    }
}
