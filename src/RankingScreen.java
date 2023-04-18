import java.util.List;
import java.util.Scanner;

public class RankingScreen extends Screen{
    public RankingScreen(DBManager dataBase, Store store) {
        setStore(store);
        setDataBase(dataBase);
    }

    public void generateRanking(List<String> top10){
        System.out.println("Player ranking");
        List<User> users = dataBase.getTop10;
        for(int i = 0; i<users.size(); i++){
            String name = users.get(i).getName();
            int gold = users.get(i).getGold();
            System.out.println(name + ": " + gold +" gold.");
        }
        System.out.println("Press any key to exit");
        Scanner sc = new Scanner(System.in);
    }
}
