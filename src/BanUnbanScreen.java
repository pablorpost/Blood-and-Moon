import java.util.*;

public class BanUnbanScreen extends Screen{
    private boolean ban;
    private Map<String, List<String>> options;

    //Constructor
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
     //Mostrará al usuario las opciones de esta pantalla.
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

        ScreenResult exit = differentOptions(optionSelected);
        if (exit != null) return exit;
        return ScreenResult.stay;
    }

    //Este método mostrará el formulario que debe rellenar el admin para banear a un usuario, y una vez comprobado que el usuario existe, lo baneará.
    private ScreenResult differentOptions(int optionSelected) {
        switch (optionSelected){
            case 0:
                getManager().clearConsole();
                System.out.println("Write the user's nick:");
                Scanner bannedUser = new Scanner(System.in);
                String election = bannedUser.nextLine();

                User userToBan = getDataBase().getUserByNick(election);
                if (userToBan != null){
                    ban(userToBan);
                } else {
                    System.out.println("User not found");
                }
                break;

            case 1:
                ScreenResult exit = unBanUserM();
                if (exit != null) return exit;
                break;
            case 2:
                return ScreenResult.exit;
        }
        return null;
    }

    //Este método mostrará la lista de usuarios baneados y desbaneará el usuario que decida el administrador.
    private ScreenResult unBanUserM() {
        getManager().clearConsole();
        List<User> bannedUsers = getDataBase().getBannedUsers();
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
        return null;
    }

    //Baneará al usuario introducido en el parámetro
    public void ban(User user){
        user.setBan(true);
    }
    //Baneará al usuario introducido en el parámetro
    public void desBan(User user){
        user.setBan(false);
    }
}
