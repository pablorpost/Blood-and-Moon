import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Battle implements Serializable {

    private String winner;
    private String looser;
    private int rounds;
    private LocalDateTime date;
    private int gold;
    private String challenger;
    private String challenged;
    // Constructor de la batalla
    public Battle(User challenger, User challenged, int goldBet, Store store){
        this.challenger = challenger.getName();
        this.challenged = challenged.getName();
        this.gold = goldBet;
        Random rand = new Random();
        int index = rand.nextInt(store.getModifiers().size());
        Modifier modifier =  store.getModifiers().get(index);
        boolean more = modifier.getType()==1;
        this.date = LocalDateTime.now();

        Character char0 = challenger.getCharacter();
        Character char1 = challenged.getCharacter();

        rounds = battleExecute(challenger, challenged, store, modifier, more, char0, char1);
        //dejará los personajes como estaban
        store.loadCharacters();
        setRounds(rounds);
    }
    // Ejecutar la batalla entro los dos contrincantes
    private int battleExecute(User challenger, User challenged, Store store, Modifier modifier, boolean more, Character char0, Character char1) {
        int rounds = 0;
        int char0Life = calculateLife(char0, store); //la vida de cada personaje será la suya predeterminada, sumando la de sus esbirros
        int char1Life = calculateLife(char1, store);
        printStats(challenger,challenged,char0Life,char1Life);
        while (char0Life > 0 && char1Life > 0) {
            rounds += 1;
            int firstAttack = getPowerOfAtack(char0, challenger, store, more, modifier);
            int secondAttack = getPowerOfAtack(char1, challenged, store, more, modifier);
            int firstDefense = getPowerOfDefense(char1, challenged, store, more);
            int secondDefense = getPowerOfDefense(char0, challenger, store, more);
            int succesAttack1 = calculateSucces(firstAttack);
            int succesAttack2 = calculateSucces(secondAttack);
            int succesDefense1 = calculateSucces(firstDefense);
            int succesDefense2 = calculateSucces(secondDefense);
            Character winner = null;
            Character looser = null;
            if (succesAttack1>=succesDefense1){
                char1Life -=1;
                winner = char0;
                looser = char1;
                printPhrase(winner,looser,challenger.getNick(),challenged.getNick());
                update(winner,looser);
            }
            if ((succesAttack2>=succesDefense2)) {
                char0Life -=1;
                winner = char1;
                looser = char0;
                printPhrase(winner,looser,challenged.getNick(),challenger.getNick());
                update(winner,looser);
            }
            if (char1Life <= 0 && char0Life <= 0) {
                this.winner = "BOTH";
            } else if (char1Life <= 0) {
                this.winner = challenger.getNick();
                this.looser = challenged.getNick();
            } else {
                this.winner = challenged.getNick();
                this.looser = challenger.getNick();
            }
            printCalcs(rounds,challenger,challenged,firstAttack,firstDefense,secondDefense,secondAttack);
            printStats(challenger,challenged,char0Life,char1Life);
        }
        return rounds;
    }
    // Mostrar las acciones de los personajes de la batalla por el momento
    private void printPhrase(Character winner, Character looser,String wnick, String lnick){
        if (winner instanceof Vampire){
            System.out.print(wnick+"'s vampire violently bites");
        }
        if (winner instanceof Lycanthrope){
            System.out.print(wnick+"'s lycanthrope gives a serious slash");
        }
        if (winner instanceof Hunter){
            System.out.print(wnick+"'s hunter repeatedly shots in the chest");
        }
        if (looser instanceof Vampire){
            System.out.println(" to "+lnick+"'s vampire\n");
        }
        if (looser instanceof Lycanthrope){
            System.out.println(" to "+lnick+"'s lycanthrope\n");
        }
        if (looser instanceof Hunter){
            System.out.println(" to "+lnick+"'s hunter\n");
        }
    }
    // Mostrar el estado de los contrincantes en la batalla
    private void printStats(User challenger, User challenged,int challengerChar, int challengedChar){
        System.out.println(challenger.getNick()+"'s character has "+challengerChar+" life points");
        System.out.println(challenged.getNick()+"'s character has "+challengedChar+" life points\n");

        System.out.println("(Press ENTER to continue)");
        Scanner sc = new Scanner(System.in);
        String nReq = sc.nextLine();

        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("██████╗ ██╗      ██████╗  ██████╗ ██████╗    ██╗   ███╗   ███╗ ██████╗  ██████╗ ███╗   ██╗\n██╔══██╗██║     ██╔═══██╗██╔═══██╗██╔══██╗   ██║   ████╗ ████║██╔═══██╗██╔═══██╗████╗  ██║\n██████╔╝██║     ██║   ██║██║   ██║██║  ██║████████╗██╔████╔██║██║   ██║██║   ██║██╔██╗ ██║\n██╔══██╗██║     ██║   ██║██║   ██║██║  ██║██╔═██╔═╝██║╚██╔╝██║██║   ██║██║   ██║██║╚██╗██║\n██████╔╝███████╗╚██████╔╝╚██████╔╝██████╔╝██████║  ██║ ╚═╝ ██║╚██████╔╝╚██████╔╝██║ ╚████║\n╚═════╝ ╚══════╝ ╚═════╝  ╚═════╝ ╚═════╝ ╚═════╝  ╚═╝     ╚═╝ ╚═════╝  ╚═════╝ ╚═╝  ╚═══╝");


    }
    // Muestra los calculos de la batalla por ronda
    private void printCalcs(int Round,User challenger, User challenged,int at_r, int at_d, int def_r, int def_d ){
        System.out.println("Round: "+Round);
        System.out.println(challenger.getNick()+" attacks with "+at_r+ " attack points");
        System.out.println(challenged.getNick()+" defends itslef with "+def_d+ " defense points");
        System.out.println(challenged.getNick()+" attacks with "+at_d+ " attack points");
        System.out.println(challenger.getNick()+" defends itslef with "+def_r+ " defense points\n");
    }
    // Calcula la vida de un personaje
    private int calculateLife(Character chara, Store store) {
        int lifeAux = chara.getLife();
        for (String minion : chara.getMinions()) {
            lifeAux+= store.getInfoMinion(minion).getLife();
        }

        return lifeAux;
    }
    // Actualiza los valores internos de los personajes een funcion de si ganan o pierden
    private void update(Character winner, Character looser){
        if (winner instanceof Vampire){
            ((Vampire) winner).addBlood((5));

        }
        if (looser instanceof Lycanthrope){
            ((Lycanthrope) looser).addAnger((1));

        }
        if (looser instanceof Hunter){
            ((Hunter) looser).lessWillpower((1));

        }
    }
    // Calcula la propabilidad de tener un ataque efectivo
    private int calculateSucces(int atribute) {
        Random random = new Random();
        int acum = 0;
        for (int i=0;i<atribute;i++){
            int ran = random.nextInt(6) + 1;
            if (ran==5 || ran==6){
                acum +=1;
            }
        }
        return acum;
    }
    // Obtiene el poder del ataque de un personaje
    private int getPowerOfAtack(Character charac, User user, Store store, boolean more, Modifier modifier){
        int power = charac.getPower(); //poder
        int powerAtribute = charac.getPowerAtribute(); //poder según personaje
        List<String> weapons = user.getWeapons();
        String armor = user.getArmor();
        String skill = charac.getSkill();
        int skillPoints = store.getInfoSkill(skill).getAttackPoints();
        int equipmentPoints = 0;
        for (String weapon:weapons){
            equipmentPoints += store.getInfoWeapon(weapon).getAttack();
        }
        equipmentPoints += store.getInfoArmor(armor).getAtack();
        int acum = power + skillPoints + equipmentPoints;
        if (charac instanceof Vampire){
            if (powerAtribute >= 5){
                acum += 2;
                Random random = new Random();
                int cost = random.nextInt(3) + 1; //pagar el coste de su disciplina
                ((Vampire)charac).setBlood(((Vampire)charac).getBlood() - cost);
            }
        } else if (charac instanceof Lycanthrope){
            if (powerAtribute >= 1){
                acum += powerAtribute; //si la rabia no llega al mínimo, no usará su don
            }
        } else {
            acum += powerAtribute;
        }
        boolean containModifier = false;
        for (String modifierS:charac.getModifiers()) {
            containModifier = modifierS.equals(modifier);
        }
        if (more && containModifier){
            return acum+modifier.getPower();
        } else if (!(more) && containModifier) {
            return acum-modifier.getPower();
        }else{
            return acum;
        }
    }
    // Obtiene el poder de la defensa de un personaje
    private int getPowerOfDefense(Character charac, User user, Store store, boolean more){
        int power = charac.getPower();
        int powerAtribute = charac.getPowerAtribute();
        List<String> weapons = user.getWeapons();
        String armor = user.getArmor();
        String skill = charac.getSkill();
        int skillPoints = store.getInfoSkill(skill).getDefensePoints();
        int equipmentPoints = 0;
        for (String weapon:weapons){
            equipmentPoints += store.getInfoWeapon(weapon).getDefense();
        }
        equipmentPoints += store.getInfoArmor(armor).getAtack();
        int acum = power + skillPoints + equipmentPoints;
        if (charac instanceof Vampire){
            if (powerAtribute >= 5){
                acum += 2;
                Random random = new Random();
                //el coste de la Disciplina será un valor entre 1 y 3
                int cost = random.nextInt(3) + 1;
                ((Vampire)charac).setBlood(((Vampire)charac).getBlood() - cost);
            }
        } else if (charac instanceof Lycanthrope){
            if (! (powerAtribute < 1)){
                acum += powerAtribute; //si la rabia no llega al mínimo, no usará su don
            }
        } else {
            acum += powerAtribute;
        }
        return acum;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getLooser() {
        return looser;
    }

    public void setLooser(String looser) {
        this.looser = looser;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getChallenger() {
        return challenger;
    }

    public void setChallenger(String challenger) {
        this.challenger = challenger;
    }

    public String getChallenged() {
        return challenged;
    }

    public void setChallenged(String challenged) {
        this.challenged = challenged;
    }
}
