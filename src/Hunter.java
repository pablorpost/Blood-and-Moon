import java.io.*;

public class Hunter extends Character{
    private int willpower;

    public Hunter(String directorio){
        super();
        try {
            loadCharacter(directorio,"hunter");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Hunter(Hunter original) {
        super.setName(original.getName());
        super.setLife(original.getLife());
        super.setDescription(original.getDescription());
        super.setPower(original.getPower());
        super.setSkill(original.getSkill());
        this.willpower = original.getWillpower();
        for (String weapon : original.getWeapons()) {
            addWeapon(weapon);
        }
        for (String armor : original.getArmors()) {
            addArmor(armor);
        }
        for (String modifier : original.getModifiers()) {
            addModifier(modifier);
        }
        for (String minion : original.getMinions()) {
            addMinion(minion);
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
            case "modifier":
                addModifier(var[1]);
                break;
            case "skill":
                setSkill(var[1]);
                break;
            case "willpower":
                setWillpower(Integer.valueOf(var[1]));
                break;
            case "power":
                setPower(Integer.valueOf(var[1]));
                break;
            default:
                break;
        }
    }

    public int getWillpower() {
        return willpower;
    }

    public void setWillpower(int willpower) {
        this.willpower = willpower;
    }
    @Override
    public int getPowerAtribute(){return this.willpower;}

    public void lessWillpower(int i) {
        if (this.willpower > 0){
            this.willpower-=i;
        }
    }
}
