import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DataBase implements Serializable{
    private Map<String, Person> users;

    private Map<String, Person> admins;
    private List<Battle> battles;

    private List<String> top10;

    public User getUser(String nick, String password){
        return null;
    }

    public Admin getAdmin(String nick, String password){
        return null;
    }

    public DataBaseResult inDataBase(String nick, String password){
        return null;
    }

    public void addUser(String nick, String password){

    }

    public void addAdmin(String nick, String password){

    }

    public void saveData(){

    }

    public void getTop10(){

    }

    public void uploadTop10(){

    }

    private void loadData(){

    }
}
