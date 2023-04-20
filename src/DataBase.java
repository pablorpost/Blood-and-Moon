import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase implements Serializable{
    private Map<String, User> users;

    private Map<String, Admin> admins;
    private List<Battle> battles;

    private List<String> top10;

    public DataBase(){
        users = new HashMap<String, User>();
        admins = new HashMap<String,Admin>();
    }

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

    public void deletePerson(Person person){
        // IMPLEMENTAR ----------------------------------------------------------------------------------------
        if (person instanceof User){
            System.out.println("User " + person.getName() + " has been deleted.");
        } else {
            System.out.println("Admin " + person.getName() + " has been deleted.");
        }
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

    public List<User> getTop10(){
        return null;
    }

    public void uploadTop10(){

    }

    private void loadData(){

    }












    //para borrar
    public Map<String, User> getUsers(){
        return users;
    }
    public Map<String, Admin> getAdmins(){
        return admins;
    }
}
