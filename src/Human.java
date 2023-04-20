import java.io.FileNotFoundException;

public class Human extends Minion{
    private String loyalty;

    public Human(){
        super();
        try {
            laodMinion("human");
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
            case "loyalty":
                setLoyalty(var[1]);
                break;
            default:
                break;

        }
    }

    public String getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(String loyalty) {
        this.loyalty = loyalty;
    }
}
