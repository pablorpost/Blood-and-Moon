import java.util.List;
import java.util.Scanner;

public class ConfigureEquipmentScreen extends Screen{
    public ConfigureEquipmentScreen(Manager manager, User user) {
        setStore(manager.getStore());
        setManager(manager);
        setPerson(user);
        this.setDescription(null);
        this.setTitle(null);
    }


    public ScreenResult showOptions(){
        Manager m =  super.getManager();
        DBManager db = super.getDataBase();
        m.clearConsole();
        chooseWeapons();
        m.clearConsole();
        chooseArmor();
        return ScreenResult.exit;
    }

    private void chooseWeapons(){
        List<String> charWeap = ((User) getPerson()).getCharacter().getWeapons();
        int numHands = 2;
        while (numHands > 0){
            System.out.println("\nChoose a weapon, you have " + numHands+ " free hands.");
            for(int i = 0; i<charWeap.size(); i ++){
                String weaponName = charWeap.get(i);
                Weapon wea = getStore().getInfoWeapon(weaponName);
                if (wea != null && wea.getHands() <= numHands){
                    System.out.println(i + ". " + weaponName +" (defense: " + wea.getDefense() +
                            ", attack: "+ wea.getAttack()+")-"+ "(needs " + wea.getHands()+" hands)");
                }
            }
            Scanner sc = new Scanner(System.in);
            int nu = sc.nextInt();
            int a = charWeap.size();
            if (!(nu < 0 || nu >= a)) {
                int hands = getStore().getInfoWeapon(charWeap.get(nu)).getHands();
                ((User) getPerson()).addWeapon(charWeap.get(nu));
                numHands -= hands;
            } else {
                System.out.println("Thats not a weapon. Please enter a valid number.");
            }

        }

    }

    private void chooseArmor(){
        List<String> charArmors = ((User) getPerson()).getCharacter().getArmors();

        System.out.println("Choose an armor");
        for(int i = 0; i<charArmors.size(); i ++){
            String armorName = charArmors.get(i);
            Armor arm = getStore().getInfoArmor(armorName);
            System.out.println(i + ". " + armorName + "(defense: "+ arm.getDefense()+", attack: "+ arm.getAtack()+")");
        }
        int mu = -1;
        Scanner sc = new Scanner(System.in);
        while (mu < 0 || mu >= charArmors.size()) {
            mu = sc.nextInt();
        }
        ((User) getPerson()).setArmor(charArmors.get(mu));
    }

}
