import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager implements Serializable{
    private DataBase dataBase;
    private int adminPassword;
    private List<List<String>> requests;
    private List<Integer> requestsMoney;

    public User getUser(String nick, String password){
        return dataBase.getUser(nick, password.hashCode());
    }

    public Admin getAdmin(String nick, String password){
        return dataBase.getAdmin(nick, password.hashCode());
    }

    public DataBaseResult inDataBase(String nick, String password){
        return dataBase.inDataBase(nick,password.hashCode());
    }

    public DBManager(){
        this.requests = new ArrayList<>();
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

    public void deletePerson(Person person){
        dataBase.deletePerson(person);
    }

    public String getRequestUser(String nick){
        String hasUser = null;
        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i).get(0).equals(nick)){
                hasUser = requests.get(i).get(1);
            }
        }
        return hasUser;
    }

    public int getRequestMoney(String nick){
        int moneyBet = 0;
        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i).get(0).equals(nick)){
                moneyBet = requestsMoney.get(i);
            }
        }
        return moneyBet;
    }

    public List<List<String>> getRequests() {
        return requests;
    }

    public void setRequests(List<List<String>> requests) {
        this.requests = requests;
    }
}








