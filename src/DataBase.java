import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class DataBase implements Serializable{
    private Map<String, User> users;

    private Map<String, Admin> admins;
    private List<Battle> battles;
    private List<List<String>> requests;
    private Map<String, LocalDateTime> lastRequest;

    //inicializa la base de datos y sus atributos
    public DataBase(){
        users = new HashMap<String, User>();
        admins = new HashMap<String,Admin>();
        this.requests = new ArrayList<>();
        battles = new ArrayList<>();
        lastRequest = new HashMap<>();
    }
    //devuelve el usuario correspondiente a un nick y una contraseña
    public User getUser(String nick, int password){
        if (inDataBase(nick,password)==DataBaseResult.user){
            return users.get(nick);
        }
        return null;
    }
    //devuelve el mapa que une pares de usuarios con la fecha del ultimo desafio
    public Map<String, LocalDateTime> getLastRequest(){
        return lastRequest;
    }

    //devuelve el admin correspondiente a un nick y una contraseña
    public Admin getAdmin(String nick, int password){
        if (inDataBase(nick,password)==DataBaseResult.admin){
            return admins.get(nick);
        }
        return null;
    }

    //borra una persona de la base de datos
    public void deletePerson(Person person){

        if (person instanceof User){
            users.remove(person.getNick());
            System.out.println("User " + person.getName() + " has been deleted.");
        } else {
            admins.remove(person.getNick());
            System.out.println("Admin " + person.getName() + " has been deleted.");
        }
    }

    //verifica si un usario existe o no en la base de datos
    public DataBaseResult existingUser(String nick){
        if (users.containsKey(nick)){
                return DataBaseResult.user;
            }
        return DataBaseResult.notFound;
    }
    //verifica si una persona existe o no en la base de datos y si su contraseña es correcta
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

    //añade un usuario a la base de datos
    public void addUser(String nick, String name, int password){
        if (inDataBase(nick,password)==DataBaseResult.notFound){
            users.put(nick, new User(nick, name, password));
        }
    }

    //añade un admin a la base de datos
    public void addAdmin(String nick,String name, int password){
        if (inDataBase(nick,password)==DataBaseResult.notFound){
            admins.put(nick, new Admin(nick,name,password));
        }
    }

    //devuelve una lista con los 10 usuarios con más oro
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

    //devuelve las peticiones de batalla pendientes de validacion
    public List<List<String>> getRequests() {
        return requests;
    }

    //debuelve el mapa de usuarios
    public Map<String, User> getUsers(){
        return users;
    }
    //debuelve el mapa de admins
    public Map<String, Admin> getAdmins(){
        return admins;
    }

    //añade una batalla a la lista de batallas
    public void addBattleToList(Battle battle){
        this.battles.add(battle);
    }
}
