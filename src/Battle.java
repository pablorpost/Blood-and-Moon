import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Battle implements Serializable {

    private String winner;
    private String looser;
    private int rounds;
    private LocalDateTime date;
    private int gold;
    private String challenger;
    private String challenged;

    public Battle(User challenger, User challenged, int goldBet, Store store){
        this.challenger = challenger.getName();
        this.challenged = challenged.getName();
        this.gold = goldBet;
        Random rand = new Random();
        int index = rand.nextInt(store.getModifiers().size());
        Modifier modifier =  store.getModifiers().get(index);
        boolean more = modifier.getType()==1;
        this.date = LocalDateTime.now();

        //ELIMINARR-----VVVVV
        /*
        this.rounds = new Random().nextInt(6);
        if(new Random().nextInt(2) == 1){
            this.winner = this.challenger;
            this.looser = this.challenged;
        }else{
            this.winner = this.challenged;
            this.looser = this.challenger;
        }
        **/

        Character char0 = challenger.getCharacter();
        Character char1 = challenged.getCharacter();

        int rounds = 0;
        //la vida de cada personaje será la suya predeterminada, sumando la de sus esbirros
        int char0Life = calculateLife(char0, store);
        int char1Life = calculateLife(char1, store);

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

            Character winner;
            Character looser;

            if (succesAttack1>=succesDefense1){
                char1Life-=1;
                winner = char0;
                looser = char1;
                update(winner,looser);
            }
            if ((succesAttack2>=succesDefense2)) {
                char0Life-=1;
                winner = char1;
                looser = char0;
                update(winner,looser);
            }

            if (char1Life <= 0 && char0Life <= 0) {
                this.winner = "BOTH";
            } else if (char1Life <= 0) {
                this.winner = char0.getName();
                this.looser = char1.getName();
            } else {
                this.winner = char1.getName();
                this.looser = char0.getName();
            }
        }
        //dejará los personajes como estaban
        store.loadCharacters();
        setRounds(rounds);

    }


    private int calculateLife(Character chara, Store store) {
        int lifeAux = chara.getLife();
        for (String minion : chara.getMinions()) {
            lifeAux+= store.getInfoMinion(minion).getLife();
        }

        return lifeAux;
    }

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

    private int getPowerOfAtack(Character charac, User user, Store store, boolean more, Modifier modifier){
        //poder
        int power = charac.getPower();
        //poder según personaje
        int powerAtribute = charac.getPowerAtribute();
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
                //pagar el coste de su disciplina
                int cost = random.nextInt(3) + 1;
                ((Vampire)charac).setBlood(((Vampire)charac).getBlood() - cost);
            }

        } else if (charac instanceof Lycanthrope){
            //si la rabia no llega al mínimo, no usará su don
            if (! (powerAtribute < 1)){
                acum += powerAtribute;
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
            //si la rabia no llega al mínimo, no usará su don
            if (! (powerAtribute < 1)){
                acum += powerAtribute;
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
