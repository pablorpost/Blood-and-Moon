import java.util.*;

import static java.lang.Math.min;

public class AdminMainMenuScreen extends Screen{

    private Admin admin;
    private Map<String, List<String>> options;

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
    public ScreenResult showOptions() {
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
                displayScreenRequestAcceptance(sc);
                return ScreenResult.stay;

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

    private void displayScreenRequestAcceptance(Scanner sc) {
        getManager().clearConsole();
        System.out.println("There are "+getDataBase().getRequests().size()+" request prending of validation");
        if (getDataBase().getRequests().size() > 0) {
            System.out.println("How many requests do you want to validate?");
            int nReq = min(sc.nextInt(), getDataBase().getRequests().size());
            for (int i = 0; i <= nReq; i++) {
                getManager().clearConsole();
                List<String> request = getDataBase().getRequests().get(0);
                System.out.println("Do you want to validate this request? (Y/N)");
                System.out.println();
                System.out.println("Challenger: " + request.get(0));
                System.out.println("Challenged: " + request.get(1));
                System.out.println("Gold betted: " + request.get(2));
                sc = new Scanner(System.in);
                String response = sc.nextLine();
                getDataBase().getRequests().remove(0);
                if (response.equals("Y") || response.equals("y")) {
                    getDataBase().getRequests().add(request);
                    User challengedUser = getDataBase().getUserByNick(request.get(1));
                    challengedUser.setPendingRequest(request);
                }
            }
        }else{
            System.out.println("(Press ENTER to continue)");
            sc = new Scanner(System.in);
            String nReq = sc.nextLine();
        }
    }

    public void validatRequests(){

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
