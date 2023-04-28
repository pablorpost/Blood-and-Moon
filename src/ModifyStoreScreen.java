import java.util.*;

public class ModifyStoreScreen extends Screen{

    private Map<String, List<String>> options;

    //Constructor
    public ModifyStoreScreen(Manager manager) {
        super.setManager(manager);
        super.setStore(manager.getStore());
        this.setDescription(null);
        options = new HashMap<>();
        List<String> characters = new ArrayList<>();
        characters.add("1. Modify Vampire");
        characters.add("2. Modify Hunter");
        characters.add("3. Modify Lycanthrope");
        characters.add("4. Exit");
        options.put("0", characters);
        List<String> vampire = new ArrayList<>();
        vampire.add("0. Modify life");
        vampire.add("1. Modify description");
        vampire.add("2. Add weapon ");
        vampire.add("3. Add armor");
        vampire.add("4. Add minion");
        vampire.add("5. Modify Power");
        vampire.add("6. Modify age");
        vampire.add("7. Modify blood points");
        vampire.add("8. Exit");
        options.put("1", vampire);
        List<String> hunter = new ArrayList<>();
        hunter.add("0. Modify life");
        hunter.add("1. Modify description");
        hunter.add("2. Add weapon ");
        hunter.add("3. Add armor");
        hunter.add("4. Add minion");
        hunter.add("5. Modify Power");
        hunter.add("6. Modify willpower");
        hunter.add("7. Exit");
        options.put("2", hunter);
        List<String> lycanthrope = new ArrayList<>();
        lycanthrope.add("0. Modify life");
        lycanthrope.add("1. Modify description");
        lycanthrope.add("2. Add weapon ");
        lycanthrope.add("3. Add armor");
        lycanthrope.add("4. Add minion");
        lycanthrope.add("5. Modify Power");
        lycanthrope.add("5. Modify anger");
        lycanthrope.add("6. Exit");
        options.put("3", lycanthrope);
    }

    //Se encargará de interactuar con el usuario pidiéndole la nueva información para hacer la modificación
    public ScreenResult changeStore(Store store, int character, int atribute){
        switch (atribute) {
            case 0:
                Scanner scanner = new Scanner(System.in);
                int numero = 0;
                while (numero < 1 || numero > 5) {
                    System.out.print("Choose a new life between 5 and 1: ");
                    numero = scanner.nextInt();
                }
                store.getChracters().get(character).setLife(numero);
                break;
            case 1:
                Scanner scanne = new Scanner(System.in);
                System.out.print("Choose a new description: ");
                String desc = scanne.nextLine();
                store.getChracters().get(character).setDescription(desc);
                break;
            case 2:
                int nu = -1;
                Scanner scann = new Scanner(System.in);
                System.out.print("Choose a new weapon: \n");
                List<String> totalWeap = store.getWeapons();
                List<String> charWeap = store.getChracters().get(character).getWeapons();
                List<String> available = new ArrayList<>(totalWeap);
                available.removeAll(charWeap);
                if (available.size()==0){
                    System.out.println("FULL EQUIPED ALREADY");
                }else{
                    for(int i = 0; i<available.size(); i ++){
                        System.out.println(i + ". " + available.get(i));
                    }
                    while (nu < 0 || nu >= available.size()) {
                        nu = scann.nextInt();
                    }
                    store.getChracters().get(character).addWeapon(available.get(nu));
                }
                break;
            case 3:
                int num = -1;
                Scanner scan = new Scanner(System.in);
                System.out.print("Choose a new armor: \n");
                List<String> totalArmor = store.getArmors();
                List<String> charArm = store.getChracters().get(character).getArmors();
                List<String> avail = new ArrayList<>(totalArmor);
                avail.removeAll(charArm);
                if (avail.size()==0){
                    System.out.println("FULL EQUIPED ALREADY");
                }else {
                    for (int i = 0; i < avail.size(); i++) {
                        System.out.println(i + ". " + avail.get(i));
                    }
                    while (num < 0 || num >= avail.size()) {
                        num = scan.nextInt();
                    }
                    store.getChracters().get(character).addArmor(avail.get(num));
                }
                break;
            case 4:
                int n = -1;
                Scanner sca = new Scanner(System.in);
                System.out.print("Choose a new minion: \n");
                List<String> minions = store.getMinions();
                for(int i = 0; i<minions.size(); i ++){
                    System.out.println(i + ". " + minions.get(i));
                }
                while (n < 0 || n >= minions.size()) {
                    n = sca.nextInt();
                }
                store.getChracters().get(character).addMinion(minions.get(n));
                break;
            case 5:
                Scanner ssca = new Scanner(System.in);
                int numbe = 0;
                while (numbe < 1 || numbe > 5) {
                    System.out.print("Choose a new power between 1 and 5: ");
                    numbe = ssca.nextInt();
                }
                store.getChracters().get(character).setPower(numbe);
                break;
            case 6:
                Scanner s = new Scanner(System.in);
                int numm = 0;
                if (character == 0) {
                    while (numm < 1 || numm > 1000) {
                        System.out.print("Choose a new age between 1 and 1000: ");
                        numm = s.nextInt();
                    }
                    Vampire vampire = (Vampire) store.getChracters().get(0);
                    vampire.setAge(numm);

                } else if (character == 1) {
                    while (numm < 0 || numm > 3) {
                        System.out.print("Choose a new willpower to start in combat between 0 and 3: ");
                        numm = s.nextInt();
                    }
                    Hunter hunter = (Hunter) store.getChracters().get(1);
                    hunter.setWillpower(numm);

                } else if (character == 2) {
                    while (numm < 0 || numm > 3) {
                        System.out.print("Choose a new anger to start between 0 and 3: ");
                        numm = s.nextInt();
                    }
                    Lycanthrope lycanthrope = (Lycanthrope) store.getChracters().get(2);
                    lycanthrope.setAnger(numm);

                }
                break;
            case 7:
                if (character == 0) {
                    Scanner sc = new Scanner(System.in);
                    int numb = 0;
                    while (numb < 1 || numb > 10) {
                        System.out.print("Choose a new blood points between 1 and 10: ");
                        numb = sc.nextInt();
                    }
                    Vampire vampire = (Vampire) store.getChracters().get(0);
                    vampire.setBlood(numb);
                }
                break;
        }
        saveChanges(super.getStore(),character);
        return ScreenResult.exit;
    }


    //Mostrará las distintas opciones a modificar
    @Override
    public ScreenResult showOptions(){
        Manager m =  super.getManager();
        m.clearConsole();
        List<String> show = options.get("0");
        for(int i = 0; i<show.size(); i ++){
            System.out.println(show.get(i));
        }
        show.clear();

        Scanner sc = new Scanner(System.in);
        int optionSelected = sc.nextInt();

        switch (optionSelected){
            case 1:
            case 2:
            case 3:
                m.clearConsole();
                show = options.get(Integer.toString(optionSelected));
                System.out.println("Choose what to modify:");
                for(int i = 0; i<show.size(); i ++){
                    System.out.println(show.get(i));
                }
                Scanner op = new Scanner(System.in);
                int toModify = op.nextInt();
                m.clearConsole();
                return changeStore(super.getStore(),optionSelected-1, toModify);
            case 4:
                return ScreenResult.exit;
        }
        return ScreenResult.stay;
    }


    //Guardará los nuevos cambios
    private void saveChanges(Store store, int character){
        store.saveCharacter(character);
    }
}
