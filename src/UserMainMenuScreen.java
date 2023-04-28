import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class UserMainMenuScreen extends Screen{
    private Character character;
    private int gold;
    private Map<String, List<String>> options;
    private User user;

    //Constructor. Inicializará las diferentes opciones de este menú.
    public UserMainMenuScreen(DBManager dataBase, Store store, Manager manager, User user) {
        super.setTitle("Welcome " + user.getNick() + " you have " + user.getGold() + " coins.");
        super.setDescription("What would you like to do?");
        setDataBase(dataBase);
        setStore(store);
        super.setManager(manager);
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

    /*
    Mostrará las opciones pertinentes en cada caso. Si el usuario está baneado, lo especificará. Sino mostrará
    el menú. También distinguirá si el usuario tiene un personaje creado o no y mostrará el menú correspondiente.
     */
    @Override
    public ScreenResult showOptions() {
        if (user.getPendingRequest() != null){
            showPendingRequest();
            return ScreenResult.stay;
        }
        List<String> show;
        if (this.user.isBan()){
            System.out.println("This user is banned temporarily");
            System.out.println("(Press ENTER to go back to login page)");
            Scanner keyBoard = new Scanner(System.in);
            String nxtStr = keyBoard.nextLine();
            return ScreenResult.exit;
        }
        if (this.user.getCharacter() == null){
            show = options.get("1");
        } else {
            show = options.get("0");
        }
        for(int i = 0; i<show.size(); i ++){
            System.out.println(show.get(i));
        }
        Scanner sc = new Scanner(System.in);
        int optionSelected = sc.nextInt();
        if (this.user.getCharacter() != null) {
            return hasCharacterOptions(optionSelected);
        } else{
            return hasNoCharacterOptions(optionSelected);
        }
    }

    //Mostrará el menú correspondiente al caso en el que el usuario tiene personaje.
    private ScreenResult hasCharacterOptions(int optionSelected) {
        switch (optionSelected) {
            case 0:
                ChallengeRequestScreen screen = new ChallengeRequestScreen(this.getDataBase(), user);
                getManager().showScreen(screen);
                super.setTitle("Welcome " + user.getNick() + " you have " + user.getGold() + " coins.");
                return ScreenResult.stay;
            case 1:
                ConfigureEquipmentScreen configureScreen = new ConfigureEquipmentScreen(getManager(), this.user);
                getManager().showScreen(configureScreen);
                return ScreenResult.stay;
            case 2:
                BattleHistoryScreen bhs = new BattleHistoryScreen(getDataBase(), getStore(), user);
                getManager().showScreen(bhs);
                return ScreenResult.stay;
            case 3:
                RankingScreen rkSc = new RankingScreen(getDataBase(),getStore(),getManager());
                getManager().showScreen(rkSc);
                return ScreenResult.stay;
            case 4:
                PopUpScreen popUp = new PopUpScreen(super.getDataBase(), super.getManager(), user);
                ScreenResult result = popUp.showPopUp(1);
                if (result == ScreenResult.stay){
                    user.setCharacter(null);
                }
                //this.showOptions();
                break;
            case 5:
                return ScreenResult.exit;
        }
        return ScreenResult.stay;
    }

    //Mostrará el menú correspondiente al caso en el que el usuario no tiene personaje.
    private ScreenResult hasNoCharacterOptions(int optionSelected) {
        switch (optionSelected) {
            case 0:
                CreateCharacterScreen selectCharac = new CreateCharacterScreen(this.user, getManager());
                getManager().showScreen(selectCharac);
                return ScreenResult.stay;
            case 1:
                PopUpScreen popUp = new PopUpScreen(super.getDataBase(), super.getManager(), user);
                ScreenResult result = popUp.showPopUp(2);
                if (result == ScreenResult.stay){
                    System.out.println(user.getName());
                    super.getDataBase().deletePerson(user);
                    return result;
                } else {
                    return ScreenResult.stay;
                }
            case 2:
                BattleHistoryScreen bhs = new BattleHistoryScreen(getDataBase(), getStore(), user);
                getManager().showScreen(bhs);
                return ScreenResult.stay;
            case 3:
                RankingScreen rkSc = new RankingScreen(getDataBase(),getStore(),getManager());
                getManager().showScreen(rkSc);
                return ScreenResult.stay;
            case 4:
                return ScreenResult.exit;
        }
        return ScreenResult.stay;
    }

    /*
    Mostrará las opciones en caso de que el usuario quiera desafiar a otro.
    También gestionará los mensajes de victoria, derrota y empate, y modificará el valor del oro en cada caso.
     */
    private void showPendingRequest() {
        List<String> request = user.getPendingRequest();
        User origen = getDataBase().getUserByNick(request.get(0));
        User destino = getDataBase().getUserByNick(request.get(1));
        int goldBet = Integer.valueOf(request.get(2));
        user.setGold(max(0, user.getGold()-goldBet));
        PopUpScreen popUp = new PopUpScreen(super.getDataBase(), super.getManager(), origen);
        ScreenResult result = popUp.showPopUp(0);
        if (result == ScreenResult.stay){
            System.out.println("You have chosen to fight against " + origen.getNick() + ", then the fight will take place:\n");
            Battle thisBattle = new Battle(origen, destino, goldBet,getStore());
            origen.addBattle(thisBattle);
            destino.addBattle(thisBattle);
            getDataBase().addBattleToList(thisBattle);
            if (thisBattle.getWinner().equals(request.get(0))){
                origen.setGold(origen.getGold() + 2*goldBet);
                System.out.println("You have lost...\n");
            }else if (thisBattle.getWinner().equals(request.get(1))){
                destino.setGold(destino.getGold() + 2*goldBet);
                System.out.println("You have won!\n");
            } else {
                System.out.println("It's a tie.\n");
                origen.setGold(origen.getGold() + goldBet);
                destino.setGold(destino.getGold() + goldBet);
            }
        } else {
            origen.setGold(origen.getGold() + (int)(goldBet * 1.1));
            destino.setGold(max(destino.getGold() - (int)(goldBet * 0.1), 0));
            System.out.println("You have rejected the fight against " + origen.getNick() + ".\n10% Of the bet will be transferred to the challenger.\n");
        }
        user.setPendingRequest(null);
        super.setTitle("Welcome " + user.getNick() + " you have " + user.getGold() + " coins.");
        System.out.println("(Press ENTER to continue)");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        getDataBase().save();
        getManager().clearConsole();
    }

    //getter
    public Character getCharacter() {
        return character;
    }

    //setter
    public void setCharacter(Character character) {
        this.character = character;
    }

}
