import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lycanthrope extends Character{
    private int anger;

    public Lycanthrope(String directorio){
        super();
        try {
            laodCharacter(directorio,"lycanthrope");
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
            case "description":
                setDescription(var[1]);
                break;
            case "weapon":
                addWeapon(var[1]);
                break;
            case "armor":
                addArmor(var[1]);
                break;
            case "minion":
                addMinion(var[1]);
                break;
            case "skill":
                setSkill(var[1]);
                break;
            case "anger":
                setAnger(Integer.valueOf(var[1]));
                break;
            default:
                break;

        }
    }
    public int getAnger() {
        return anger;
    }

    public void setAnger(int anger) {
        this.anger = anger;
    }
}
