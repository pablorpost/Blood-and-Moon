import java.util.Comparator;
import java.util.List;

public class User extends Person implements Comparator<User>{

    private Character character;
    private List<String> weapons;
    private String armor;
    private boolean ban;
    private List<String> pendingRequest;
    private List<Battle> battles;
    private int gold;

    public User(String nick, String name, int password){
        super(nick,name,password,DataBaseResult.user);
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


    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public int compare(User o1, User o2) {
        return o1.getGold() - o2.getGold();
    }
}
