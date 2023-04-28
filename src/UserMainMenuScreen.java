import java.util.*;

import static java.lang.Math.max;

public class UserMainMenuScreen extends Screen{
    private Character character;
    private int gold;
    private Map<String, List<String>> options;
    private User user;

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
                    this.showOptions();
                    break;
                case 5:
                    return ScreenResult.exit;
            }
        } else{
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
                    } else {
                        return ScreenResult.stay;
                    }
                    break;
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
        }
        return ScreenResult.exit;
    }

    private void showPendingRequest() {
        List<String> request = user.getPendingRequest();
        User origen = getDataBase().getUserByNick(request.get(0));
        User destino = getDataBase().getUserByNick(request.get(1));
        int goldBet = Integer.valueOf(request.get(2));
        PopUpScreen popUp = new PopUpScreen(super.getDataBase(), super.getManager(), origen);
        ScreenResult result = popUp.showPopUp(0);
        if (result == ScreenResult.stay){
            System.out.println("You have chosen to fight against " + origen.getNick() + ", then the fight will take place:\n");
            Battle thisBattle = new Battle(origen, destino, goldBet, getStore());
            origen.addBattle(thisBattle);
            destino.addBattle(thisBattle);
            getDataBase().addBattleToList(thisBattle);
            if (thisBattle.getWinner().equals(request.get(0))){
                origen.setGold(origen.getGold() + 2*goldBet);
                System.out.println("You have lost...\n");
            }else if (thisBattle.getWinner().equals(request.get(1))){
                destino.setGold(destino.getGold() + 2*goldBet);
                System.out.println("You have won!\n");
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
}
