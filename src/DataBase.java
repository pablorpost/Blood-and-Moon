import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DataBase implements Serializable{
    private Map<String, User> users;

    private Map<String, Admin> admins;
    private List<Battle> battles;

    private List<String> top10;

    public User getUser(String nick, int password){
        if (inDataBase(nick,password)==DataBaseResult.user){
            return users.get(nick);
        }
        return null;
    }

    public Admin getAdmin(String nick, int password){
        if (inDataBase(nick,password)==DataBaseResult.admin){
            return admins.get(nick);
        }
        return null;
    }

    public DataBaseResult inDataBase(String nick, int password){
        if (users.containsKey(nick)){
            if (users.get(nick).getPassword()==password){
                return DataBaseResult.user;
            }
            return DataBaseResult.wrongPassword;
        }
        if (admins.containsKey(nick)){
            if (admins.get(nick).getPassword()==password){
                return DataBaseResult.admin;
            }
            return DataBaseResult.wrongPassword;
        }
        return DataBaseResult.notFound;
    }

    public void addUser(String nick, int password){
        if (inDataBase(nick,password)==DataBaseResult.notFound){
            users.put(nick, new User(nick,password));
        }
    }

    public void addAdmin(String nick, int password){
        if (inDataBase(nick,password)==DataBaseResult.notFound){
            admins.put(nick, new Admin(nick,password));
        }
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
