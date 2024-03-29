import java.util.*;

import static java.lang.Math.min;

public class AdminMainMenuScreen extends Screen{

    private Admin admin;
    private Map<String, List<String>> options;
    private Scanner teclado;

    // Constructor de Admin
    public AdminMainMenuScreen(DBManager dataBase, Store store, Manager manager, Admin admin, Scanner teclado) {
        super.setTitle("What would you like to do?");
        this.setDescription(null);
        setDataBase(dataBase);
        setStore(store);
        this.teclado = teclado;
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
    // Mostrar las opciones de administrador
    public ScreenResult showOptions() {
        List<String> show = options.get("0");
        for(int i = 0; i<show.size(); i ++){
            System.out.println(show.get(i));
        }
        int optionSelected = teclado.nextInt();
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
                displayScreenRequestAcceptance();
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
    // Mostrar la pantalla de validar request
    private void displayScreenRequestAcceptance() {
        getManager().clearConsole();
        System.out.println("There are "+getDataBase().getRequests().size()+" request prending of validation");
        if (getDataBase().getRequests().size() > 0) {
            System.out.println("How many requests do you want to validate?");
            int nReq = min(teclado.nextInt(), getDataBase().getRequests().size());
            for (int i = 0; i < nReq; i++) {
                getManager().clearConsole();
                List<String> request = getDataBase().getRequests().get(0);
                System.out.println("Do you want to validate this request? (Y/N)\n");
                System.out.println("Challenger: " + request.get(0));
                System.out.println("Challenged: " + request.get(1));
                System.out.println("Gold betted: " + request.get(2));
                if (getDataBase().getUserByNick(request.get(1)).getPendingRequest()!=null || getDataBase().existingUser(request.get(1))!=DataBaseResult.user || getDataBase().getUserByNick(request.get(1)).getCharacter()==null){
                    failedValidation(request);
                }
                else{
                    String response = teclado.nextLine(); 
                    getDataBase().getRequests().remove(0);
                    if (response.equals("Y") || response.equals("y")) {
                        User challengedUser = getDataBase().getUserByNick(request.get(1));
                        challengedUser.setPendingRequest(request);
                    }else{
                        int oldGold = getDataBase().getUserByNick(request.get(0)).getGold();
                        getDataBase().getUserByNick(request.get(0)).setGold(Integer.valueOf(request.get(2))+oldGold);
                    }

                }

            }
        }else{
            System.out.println("(Press ENTER to continue)");
            String nReq = teclado.nextLine();
        }
        getDataBase().save();
    }
    // Completar validacion si no se puede realizar
    private void failedValidation(List<String> request){
        if(!(getDataBase().existingUser(request.get(1))==DataBaseResult.user)){
            System.out.println("\n" + request.get(1) + " has deleted their account");
            getDataBase().getRequests().remove(0);
            int oldGold = getDataBase().getUserByNick(request.get(0)).getGold();
            getDataBase().getUserByNick(request.get(0)).setGold(Integer.valueOf(request.get(2))+oldGold);
            System.out.println("(Press ENTER to continue)");
            String nReq = teclado.nextLine();
        }
        else {
            if(getDataBase().getUserByNick(request.get(1)).getPendingRequest()!=null){
                System.out.println("\nA challenge for " + request.get(1) + " has already been set");
            }
            else{
                System.out.println("\n" + request.get(1) + " has deleted their character");
            }
            System.out.println("\nA challenge for " + request.get(1) + " has already been set");
            System.out.println("Do you want to delete it (Y) or keep it for future validation (N)?");
            String response = teclado.nextLine();
            if (response.equals("Y") || response.equals("y")) {
                getDataBase().getRequests().remove(0);
                int oldGold = getDataBase().getUserByNick(request.get(0)).getGold();
                getDataBase().getUserByNick(request.get(0)).setGold(Integer.valueOf(request.get(2))+oldGold);
            }
        }
    }

    public Admin getAdmin() {
        return admin;
    }

    public Map<String, List<String>> getOptions() {
        return options;
    }
}
