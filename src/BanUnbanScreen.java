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
        DBManager db = super.getDataBase();
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

                User userToBan = db.getUserByNick(election);
                if (userToBan != null){
                    ban(userToBan);
                } else {
                    System.out.println("User not found");
                }
                break;

            case 1:
                m.clearConsole();
                List<User> bannedUsers = db.getBannedUsers();
                if(!bannedUsers.isEmpty()){
                    for(int j = 0;j<bannedUsers.size(); j++){
                        System.out.println(j + ". " + bannedUsers.get(j).getNick());
                    }
                    System.out.println("Insert number");
                    Scanner keyB = new Scanner(System.in);
                    int opt = keyB.nextInt();
                    if (opt >= bannedUsers.size()){
                        System.out.println("Insert a valid number");
                    } else {
                        desBan(bannedUsers.get(opt));
                    }

                } else {
                    System.out.println("There are no banned users");
                    System.out.println("Press ENTER to exit");
                    Scanner emptyListSc = new Scanner(System.in);
                    String nxtLine = emptyListSc.nextLine();
                    return ScreenResult.exit;
                }
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
        user.setBan(false);
    }
}
