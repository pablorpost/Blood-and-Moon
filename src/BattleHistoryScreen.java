import java.util.List;

public class BattleHistoryScreen extends Screen{
    public BattleHistoryScreen(DBManager dataBase, Store store) {
        setStore(store);
        setDataBase(dataBase);
    }

    public void generateInfo(List<Battle> battles){

    }
}
