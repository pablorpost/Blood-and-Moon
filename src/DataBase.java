import java.io.Serializable;
import java.util.*;

public class DataBase implements Serializable{
    private Map<String, User> users;

    private Map<String, Admin> admins;
    private List<Battle> battles;
    private List<List<String>> requests;

    public DataBase(){
        users = new HashMap<String, User>();
        admins = new HashMap<String,Admin>();
        this.requests = new ArrayList<>();
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

        if (person instanceof User){
            users.remove(person.getNick());
            System.out.println("User " + person.getName() + " has been deleted.");
        } else {
            admins.remove(person.getNick());
            System.out.println("Admin " + person.getName() + " has been deleted.");
        }
    }

    public DataBaseResult existingUser(String nick){
        if (users.containsKey(nick)){
                return DataBaseResult.user;
            }
        return DataBaseResult.notFound;
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

    public void addUser(String nick, String name, int password){
        if (inDataBase(nick,password)==DataBaseResult.notFound){
            users.put(nick, new User(nick, name, password));
        }
    }

    public void addAdmin(String nick,String name, int password){
        if (inDataBase(nick,password)==DataBaseResult.notFound){
            admins.put(nick, new Admin(nick,name,password));
        }
    }

    public void saveData(){

    }

    public List<User> getTop10(){
        UserComparator uc = new UserComparator();
        List<User> aux = new ArrayList<User>(users.values());
        Collections.sort(aux,uc);
        Collections.reverse(aux);
        List<User> aux2 = new ArrayList<>();
        for (int j = 0; j<aux.size() && j<=10;j++){
            aux2.add(aux.get(j));
        }
        return aux2;
    }


    public List<List<String>> getRequests() {
        return requests;
    }

    public void setRequests(List<List<String>> requests) {
        this.requests = requests;
    }

    //para borrar
    public Map<String, User> getUsers(){
        return users;
    }
    public Map<String, Admin> getAdmins(){
        return admins;
    }

    public void addBattleToList(Battle battle){
        this.battles.add(battle);
    }
}
