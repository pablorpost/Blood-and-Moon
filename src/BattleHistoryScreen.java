import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.min;

public class BattleHistoryScreen extends Screen{

    private User user;
    public BattleHistoryScreen(DBManager dataBase, Store store, User user) {
        setStore(store);
        setDataBase(dataBase);
        this.user = user;
    }

    public ScreenResult showOptions(){
        List<Battle> battles = user.getBattles();
        for (int i = 0; i < min(battles.size(), 20); i++) {
            Battle bat = battles.get(battles.size() - 1 - i);
            String monStr = "lost, losing ";
            if (bat.getWinner().equals(user.getNick())){
                monStr = "won, winning ";
            }
            String againstNick = bat.getChallenged();
            if (againstNick.equals(user.getNick())){
                againstNick = bat.getChallenger();
            }
            System.out.println((i + 1) + ". Battle against " + againstNick + " on " + bat.getDate().getMonth() + "/" +
                    bat.getDate().getDayOfMonth() + "/" + bat.getDate().getYear() + " at " +
                    bat.getDate().getHour() + ":" + bat.getDate().getMinute() + " that you have " + monStr +
                    bat.getGold() + " coins in total.");
        }
        System.out.println("(Press ENTER to continue)");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        return ScreenResult.exit;
    }
}
