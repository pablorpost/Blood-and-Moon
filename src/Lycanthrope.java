import java.io.FileNotFoundException;

public class Lycanthrope extends Character{
    private int anger;

    //Constructor para cargar archivo
    public Lycanthrope(String directorio){
        super();
        try {
            loadCharacter(directorio,"lycanthrope");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //constructor copia
    public Lycanthrope(Lycanthrope original) {
        super.setName(original.getName());
        super.setLife(original.getLife());
        super.setDescription(original.getDescription());
        super.setPower(original.getPower());
        super.setSkill(original.getSkill());
        this.anger = original.getAnger();
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

    //Modificará el valor del personaje en función de la clave
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
            case "anger":
                setAnger(Integer.valueOf(var[1]));
                break;
            case "power":
                setPower(Integer.valueOf(var[1]));
                break;
            default:
                break;

        }
    }

    //Devolverá el atributo anger
    public int getAnger() {
        return anger;
    }

    //Modificará el atributo anger
    public void setAnger(int anger) {
        this.anger = anger;
    }

    //Devolverá el atributo distintivo del personaje
    @Override
    public int getPowerAtribute(){return this.anger;}

    //Añadirá puntos de anger
    public void addAnger(int i) {
        if (this.anger<3){
            this.anger+=i;
        }
    }
}
