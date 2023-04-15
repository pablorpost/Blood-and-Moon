import java.util.List;
import java.util.Map;

public class AdminMainMenuScreen extends Screen{

    private Admin admin;
    private Map<String, List<String>> options;

    public int showOptions(String options){
        return 0;
    }

    public void validatRequests(){

    }

    public void AdminMainMenuScreen(DBManager dataBase, Store store){

    }

    public Admin getAdmin() {
        return admin;
    }

    public Map<String, List<String>> getOptions() {
        return options;
    }
}
