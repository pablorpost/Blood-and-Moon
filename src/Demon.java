import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Demon extends Minion{
    private List<String> minions;
    private String oath;

    public Demon(){
        super();
        this.minions = new ArrayList<>();
        try {
            laodMinion("demon");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readLine(String[] var) {
        if (null != var[0]) switch (var[0]) {
            case "name":
                setName(var[1]);
                break;
            case "life":
                setLife(Integer.valueOf(var[1]));
                break;
            case "oath":
                setOath(var[1]);
                break;
            case "minions":
                addMinion(var[1]);
                break;
            default:
                break;

        }
    }

    public List<String> getMinions() {
        return minions;
    }

    public void setMinions(List<String> minions) {
        this.minions = minions;
    }

    public String getOath() {
        return oath;
    }

    public void setOath(String oath) {
        this.oath = oath;
    }

    public void addMinion(String minion) {
        this.minions.add(minion);
    }
}
