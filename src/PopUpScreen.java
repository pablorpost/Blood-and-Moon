import java.util.*;

public class PopUpScreen extends Screen{
    private Map<String, List<String>> options = new HashMap<>();

    //inicia las pantallas con las posibilidades para cada confirmacion
    public PopUpScreen(DBManager dataBase, Manager manager, Person request) {
        super.setTitle("ATENTION!!!");
        super.setDataBase(dataBase);
        this.setDescription(null);
        super.setManager(manager);

        List<String> auxList = new ArrayList<>();
        auxList.add("You have an incoming challenge from " + request.getNick() + ".\n");
        auxList.add("0. Accept");
        auxList.add("1. Decline");
        options.put("0", auxList);

        auxList = new ArrayList<>();
        auxList.add("Are you sure you want to delete your character?\n");
        auxList.add("0. Continue");
        auxList.add("1. Cancel");
        options.put("1", auxList);

        auxList = new ArrayList<>();
        auxList.add("Are you sure you want to delete your user?\nYou can not undo this action.\n");
        auxList.add("0. Continue");
        auxList.add("1. Cancel");
        options.put("2", auxList);

        auxList = new ArrayList<>();
        auxList.add("Are you sure you want to delete your admin account?\nYou can not undo this action.\n");
        auxList.add("0. Continue");
        auxList.add("1. Cancel");
        options.put("3", auxList);
    }

    //muestra la confirmación dada una opcion, dictada por un entero en la pagina madre
    public ScreenResult showPopUp(int option){
        super.getManager().clearConsole();
        List<String> show = options.get(String.valueOf(option));
        for(int i = 0; i<show.size(); i ++){
            System.out.println(show.get(i));
        }

        Scanner sc = new Scanner(System.in);
        int optionSelected = sc.nextInt();
        if (optionSelected == 0){
            return ScreenResult.stay;
        }else{
            return ScreenResult.exit;
        }
    }
}
