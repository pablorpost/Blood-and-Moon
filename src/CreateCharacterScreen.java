import java.util.*;

public class CreateCharacterScreen extends Screen{
    private Map<String, List<String>> options;
    public CreateCharacterScreen(User user, Manager manager) {
        setStore(manager.getStore());
        setManager(manager);
        setPerson(user);
        this.setDescription(null);
        this.setTitle("Choose your character:");
        this.options = new HashMap<>();
        List<String> auxList = new ArrayList<>();
        auxList.add("0. Vampire");
        auxList.add("1. Lycanthrope");
        auxList.add("2. Hunter");
        this.options.put("0", auxList);
    }


    public ScreenResult showOptions(){
        Manager m =  super.getManager();
        DBManager db = super.getDataBase();
        m.clearConsole();
        List<String> show = options.get("0");
        for(int i = 0; i<show.size(); i ++){
            System.out.println(show.get(i));
        }

        Scanner sc = new Scanner(System.in);
        int optionSelected = sc.nextInt();
        ((User) getPerson()).setCharacter(getManager().getStore().getChracters().get(optionSelected));
        m.clearConsole();
        chooseWeapons(optionSelected);
        m.clearConsole();
        chooseArmor(optionSelected);
        return ScreenResult.exit;
    }

    private void chooseWeapons(int optionSelected){
        List<String> charWeap = getStore().getChracters().get(optionSelected).getWeapons();

        System.out.println("Choose a weapon that needs 2 hands, or two that need 1 each");
        for(int i = 0; i<charWeap.size(); i ++){
            String weaponName = charWeap.get(i);
            Weapon wea = getStore().getInfoWeapon(weaponName);
            if (!(wea == null)){
                System.out.println(i + ". " + weaponName +" (needs " + wea.getHands()+")");
            }
        }
        Scanner sc = new Scanner(System.in);
        int numHands = 0;
        while (numHands<2){
            int nu = -1;
            int a = charWeap.size();
            while (nu < 0 || nu >= charWeap.size()) {
                nu = sc.nextInt();
            }
            ((User) getPerson()).addWeapon(charWeap.get(nu));
            numHands+=getStore().getInfoWeapon(charWeap.get(nu)).getHands();
        }

    }

    private void chooseArmor(int optionSelected){
        List<String> charArmors = getStore().getChracters().get(optionSelected).getArmors();

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
