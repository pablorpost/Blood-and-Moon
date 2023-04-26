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

        System.out.println("Choose a weapon that needs 2 hands, or two that need 1 each");
        for(int i = 0; i<charWeap.size(); i ++){
            String weaponName = charWeap.get(i);
            Weapon wea = getStore().getInfoWeapon(weaponName);
            if (!(wea == null)){
                System.out.println(i + ". " + weaponName +" (needs " + wea.getHands()+")");
            }
        }
        Scanner sc = new Scanner(System.in);
        int numHands = 2;
        while (numHands>0){
            int nu = -1;
            int a = charWeap.size();
            while (nu < 0 || nu >= a) {
                nu = sc.nextInt();
            }
            int hands = getStore().getInfoWeapon(charWeap.get(nu)).getHands();
            if (numHands-hands >= 0){
                ((User) getPerson()).addWeapon(charWeap.get(nu));
                numHands-= hands;
            }

        }

    }

    private void chooseArmor(){
        List<String> charArmors = ((User) getPerson()).getCharacter().getArmors();

        System.out.println("Choose an armor");
        for(int i = 0; i<charArmors.size(); i ++){
            String armorName = charArmors.get(i);
            System.out.println(i + ". " + armorName);
        }
        int mu = -1;
        Scanner sc = new Scanner(System.in);
        while (mu < 0 || mu >= charArmors.size()) {
            mu = sc.nextInt();
        }
        ((User) getPerson()).setArmor(charArmors.get(mu));
    }

}
