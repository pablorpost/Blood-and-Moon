import java.util.List;

public class DBManager {
    private DataBase dataBase;
    private int adminPassword;
    private List<User> requests;

    public User getUser(String nick, String password){
        return null;
    }

    public Admin getAdmin(String nick, String password){
        return null;
    }

    public DataBaseResult inDataBase(String nick, String password){
        return null;
    }

    public void loadDataBase(){

    }

    public boolean adminPasswordCheck(int value){
        return false;
    }

    public void addUser(String nick, String password){

    }

    public void addAdmin(String nick, String password){

    }

    public void save(){

    }

    public List<User> getRequests() {
        return requests;
    }

    public void setRequests(List<User> requests) {
        this.requests = requests;
    }
}
