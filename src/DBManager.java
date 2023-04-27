import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBManager{
    private DataBase dataBase;
    private int adminPassword;


    public User getUser(String nick, String password){
        return dataBase.getUser(nick, password.hashCode());
    }

    public Admin getAdmin(String nick, String password){
        return dataBase.getAdmin(nick, password.hashCode());
    }

    public DataBaseResult inDataBase(String nick, String password){
        return dataBase.inDataBase(nick,password.hashCode());
    }

    public DataBaseResult existingUser(String nick){
        return dataBase.existingUser(nick);
    }

    public DBManager(){

        this.dataBase = loadDataBase();
        System.out.println(dataBase.getUsers());
        System.out.println(dataBase.getAdmins());
        this.adminPassword=53195061;
    }
    public DataBase loadDataBase(){
        DataBase dataBaseCop = null;
        if (new File("dataBase.binaryDB").exists()) {
            try {
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("dataBase.binaryDB"));
                dataBaseCop = (DataBase) entrada.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            dataBaseCop = new DataBase();
            this.dataBase = dataBaseCop;
            save();
        }
        return dataBaseCop;
    }

    public boolean adminPasswordCheck(String value){
        return value.hashCode()==adminPassword;
    }

    public void addUser(String nick, String name, String password){
        dataBase.addUser(nick, name, password.hashCode());
    }

    public void addAdmin(String nick, String name, String password){
        dataBase.addAdmin(nick, name, password.hashCode());
    }

    public void save(){
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("dataBase.binaryDB"));
            salida.writeObject(this.dataBase);
            salida.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hasCharacter(String nick){
        return getUserByNick(nick).getCharacter()==null;
    }
    public void deletePerson(Person person){
        dataBase.deletePerson(person);
    }

    public String getRequestUser(String nick){
        String hasUser = null;
        for (int i = 0; i < dataBase.getRequests().size(); i++) {
            if (dataBase.getRequests().get(i).get(1).equals(nick)){
                hasUser = dataBase.getRequests().get(i).get(0);
            }
        }
        return hasUser;
    }

    public void addRequest(String challenger, String challenged, int gold){
        String[] request = {challenger,challenged,Integer.toString(gold)};
        dataBase.getRequests().add(List.of(request));


    }

    public List<User> top10() {
        return dataBase.getTop10();
    }

    public User getUserByNick(String nick){
        Map<String, User> users =  dataBase.getUsers();
        if (users.containsKey(nick)){
            return users.get(nick);
        }else{
            return null;
        }
    }

    public List<User> getBannedUsers(){
        Map<String, User> users = dataBase.getUsers();
        List<User> values = new ArrayList<User>(users.values());
        List<User> bannedUsers = new ArrayList<>();
        for(int i = 0; i<values.size(); i++){
            if (values.get(i).isBan()){
                bannedUsers.add(values.get(i));
            }
        }
        return bannedUsers;
    }

    public List<List<String>> getRequests(){
        return dataBase.getRequests();
    }

    public void addBattleToList(Battle battle){
        dataBase.addBattleToList(battle);
    }
}








