import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Ghoul extends Minion {
    private int dependency;

    public Ghoul(){
        super();
        try {
            laodMinion("ghoul");
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
            case "dependency":
                setDependency(Integer.valueOf(var[1]));
                break;
            default:
                break;

        }
    }
    public int getDependency() {
        return dependency;
    }

    public void setDependency(int dependency) {
        this.dependency = dependency;
    }
}
