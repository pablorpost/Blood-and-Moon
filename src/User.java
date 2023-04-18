import java.util.List;

public class User extends Person{

    private Character character;
    private List<String> weapons;
    private String armor;
    private boolean ban;
    private List<String> pendingRequest;
    private List<Battle> battles;

    public User(String nick, int password){
        super(nick,password,DataBaseResult.user);
    }
    public void uploadBattles(Battle value){

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
}
