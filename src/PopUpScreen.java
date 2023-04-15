import java.util.List;
import java.util.Map;

public class PopUpScreen extends Screen{
    private Map<String, List<String>> options;

    public PopUpScreen(int option, DBManager dataBase, User request) {
        setDataBase(dataBase);
    }

    public Battle startBattle(User request){
        return null;
    }
}
