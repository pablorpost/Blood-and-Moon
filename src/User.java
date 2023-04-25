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

    public User(String nick, String name, int password){
        super(nick,name,password,DataBaseResult.user);
        this.weapons = new ArrayList<>();
        this.pendingRequest = new ArrayList<>();
        character = null;
    }
    public void uploadBattles(Battle value){
        this.battles.add(value);
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public List<String> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<String> weapons) {
        this.weapons = weapons;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    public List<String> getPendingRequest() {
        return pendingRequest;
    }

    public void setPendingRequest(List<String> pendingRequest) {
        this.pendingRequest = pendingRequest;
    }

    @Override
    public List<Battle> getBattles() {
        return battles;
    }

    @Override
    public void setBattles(List<Battle> battles) {
        this.battles = battles;
    }

    public void addWeapon(String weapon) {
        this.weapons.add(weapon);
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int compare(User o2) {
        return this.getGold() - o2.getGold();
    }
}
