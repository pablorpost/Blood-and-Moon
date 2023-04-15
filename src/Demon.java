import java.util.List;

public class Demon extends Minion{
    private List<String> minions;
    private String oath;

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
}
