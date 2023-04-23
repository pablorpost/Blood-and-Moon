import java.io.*;

public class Vampire extends Character {
    private int age;
    private int blood;

    public Vampire(String directorio) {
        super();
        try {
            loadCharacter(directorio, "vampire");
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
            case "modifier":
                addModifier(var[1]);
                break;
            case "minion":
                //Los vampiros NO pueden tener esbirros humanos
                if (!var[1].equalsIgnoreCase("human")) {
                    addMinion(var[1]);

                }
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

    @Override
    public void addMinion(String minion) {
        if (!minion.equalsIgnoreCase("Human")) {
            super.addMinion(minion);
        }

    }

}



