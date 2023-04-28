import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBManager{
    private DataBase dataBase;
    private int adminPassword;


    /*Este método comprobará si el usuario retador no había desafiado a el usuario retado en las ultimas 24 horas,
      si lo había hecho, se baneará al usuario retador.
     */
    public boolean lastRequestMoreThanADayAgo(String challenger, String challenged){
        String par = challenger+" "+challenged;
        if (dataBase.getLastRequest().containsKey(par)){
            LocalDateTime n = LocalDateTime.now();
            LocalDateTime last = dataBase.getLastRequest().get(par);
            if (last.plusDays(1).isBefore(n)){
                return true;
            }
            return false;
        }
        return true;
    }

    //Asigna a
    public void addLastRequestDate(String challenger, String challenged) {
        String par = challenger+" "+challenged;
        dataBase.getLastRequest().put(par,LocalDateTime.now());
    }
    //getter
    public User getUser(String nick, String password){
        return dataBase.getUser(nick, password.hashCode());
    }

    //getter
    public Admin getAdmin(String nick, String password){
        return dataBase.getAdmin(nick, password.hashCode());
    }

    //LLamará al método inDataBase de la clase DataBase.
    public DataBaseResult inDataBase(String nick, String password){
        return dataBase.inDataBase(nick,password.hashCode());
    }

    //Llamará al método existingUser de DataBase, que comprobará si el nick corresponde a un usuario existente.
    public DataBaseResult existingUser(String nick){
        return dataBase.existingUser(nick);
    }

    //Constructor
    public DBManager(){

        this.dataBase = loadDataBase();
        System.out.println(dataBase.getUsers());
        System.out.println(dataBase.getAdmins());
        this.adminPassword=53195061;
    }

    /*Cargará la base de datos. Leerá el fichero de la base de datos en caso de que existe,
    en caso contrario, creará una nueva base de datos
     */

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

    //Comprobará si el usuario ha introducido correctamente la contraseña de administrador.
    public boolean adminPasswordCheck(String value){
        return value.hashCode()==adminPassword;
    }

    /*Añadirá un usuario a la base de datos con las credenciales introducidas por parámetro,
    llamando a la funcion addUser de la clase DataBase
     */
    public void addUser(String nick, String name, String password){
        dataBase.addUser(nick, name, password.hashCode());
    }

    /*Añadirá un administrador a la base de datos con las credenciales introducidas por parámetro,
    llamando a la funcion addAdmin de la clase DataBase
     */
    public void addAdmin(String nick, String name, String password){
        dataBase.addAdmin(nick, name, password.hashCode());
    }

    //Este método serializará el objeto.
    public void save(){
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("dataBase.binaryDB"));
            salida.writeObject(this.dataBase);
            salida.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Comprobará si el usuario tiene un personaje creado.
    public boolean hasCharacter(String nick){
        return getUserByNick(nick).getCharacter()==null;
    }

    //Borrará a una persona de la base de datos
    public void deletePerson(Person person){
        dataBase.deletePerson(person);
    }

    //Añadirá una a la base de datos un desafío (request) de un usuario a otro.
    public void addRequest(String challenger, String challenged, int gold){
        String[] request = {challenger,challenged,Integer.toString(gold)};
        dataBase.getRequests().add(List.of(request));


    }

    //Lamará al método getTop10 de la clase DataBase, y devolverá la lista resultante.
    public List<User> top10() {
        return dataBase.getTop10();
    }

    //Devolverá al usuario que tenga el nick introducido, en caso de que exista.
    public User getUserByNick(String nick){
        Map<String, User> users =  dataBase.getUsers();
        if (users.containsKey(nick)){
            return users.get(nick);
        }else{
            return null;
        }
    }


    //Devolverá la lista de usuarios baneados.
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

    //Llamará al método getRequest de la clase DataBase y devolverá la lista.
    public List<List<String>> getRequests(){
        return dataBase.getRequests();
    }

    //Llamará al método addBattleToList de DataBase.
    public void addBattleToList(Battle battle){
        dataBase.addBattleToList(battle);
    }
}








