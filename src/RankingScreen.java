import java.util.List;
import java.util.Scanner;

public class RankingScreen extends Screen{
    public RankingScreen(DBManager dataBase, Store store, Manager manager) {
        super.setTitle("Player ranking");
        super.setManager(manager);
        super.setStore(store);
        super.setDataBase(dataBase);
    }

    public ScreenResult showOptions(){
        Manager m = super.getManager();

        DBManager db = getDataBase();
        List<User> list = db.top10();
        for(int j = 0; j< list.size(); j++){
            System.out.println((j+1) + ". " + list.get(j).getNick() +" --> "+ list.get(j).getGold() + " gold");
        }
        System.out.println("(Press ENTER to exit)");
        Scanner sc = new Scanner(System.in);
        String optionSelected = sc.nextLine();
        return ScreenResult.exit;
    }
}
