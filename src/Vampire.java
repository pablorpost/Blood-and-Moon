import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Vampire extends Character{
    private int age;
    private int blood;

    public Vampire (){
        super();
        try {
            laodCharacter("vampire");
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
            case "age":
                setAge(Integer.valueOf(var[1]));
                break;
            case "blood":
                setBlood(Integer.valueOf(var[1]));
                break;
            case "skill":
                setSkill(var[1]);
                break;
            default:
                break;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }
}
