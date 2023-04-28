import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class User extends Person{

    private Character character;
    private List<String> weapons;
    private String armor;
    private boolean ban;
    private List<String> pendingRequest;
    private List<Battle> battles;
    private int gold;

    //Constructor
    public User(String nick, String name, int password){
        super(nick,name,password,DataBaseResult.user);
        this.weapons = new ArrayList<>();
        this.pendingRequest = null;
        this.battles = new ArrayList<>();
        character = null;
        gold = 500;
    }

    //Devolverá el personaje del user
    public Character getCharacter() {
        return character;
    }

    //Modificará el personaje del user
    public void setCharacter(Character character) {
        this.character = character;
    }

    //Devolverá la lista de armas activas
    public List<String> getWeapons() {
        return weapons;
    }

    //Devolverá el armadura activa
    public String getArmor() {
        return armor;
    }

    //Asignará el armadura activa
    public void setArmor(String armor) {
        this.armor = armor;
    }

    //Dirá si el user está o no baneado
    public boolean isBan() {
        return ban;
    }

    //Asignará el ban del usuario
    public void setBan(boolean ban) {
        this.ban = ban;
    }

    //Devolverá la lista de solicitudes pendientes
    public List<String> getPendingRequest() {
        return pendingRequest;
    }

    //Asignará la lista de solicitudes pendientes
    public void setPendingRequest(List<String> pendingRequest) {
        this.pendingRequest = pendingRequest;
    }

    //Devolverá la lista de las batallas
    @Override
    public List<Battle> getBattles() {
        return battles;
    }

    //Añadirá una batalla a la lista
    public void addBattle(Battle battle) {
        this.battles.add(battle);
    }

    //Añadirá un arma a la lista
    public void addWeapon(String weapon) {
        this.weapons.add(weapon);
    }

    //Devolverá el oro
    public int getGold() {
        return gold;
    }

    //Asignará el valor de oro
    public void setGold(int gold) {
        this.gold = gold;
    }

}
