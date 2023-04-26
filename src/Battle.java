import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Battle implements Serializable {

    private String winner;
    private String looser;
    private int rounds;
    private Date date;
    private int gold;
    private String challenger;
    private String challenged;
    private Store store;

    public Battle(User challenger, User challenged, int goldBet, Store store){
        this.store = store;
        this.challenger = challenger.getName();
        this.challenged = challenged.getName();
        this.gold = goldBet;

        Character char0 = challenger.getCharacter();
        Character char1 = challenged.getCharacter();

        int rounds = 0;
        //la vida de cada personaje será la suya predeterminada, sumando la de sus esbirros
        int char0Life = calculateLife(char0);
        int char1Life = calculateLife(char1);

        while (char0Life > 0 && char1Life > 0) {
            rounds += 1;
            int firstAttack = getPowerOfAtack(char0, challenger.getWeapons(), challenger.getArmor());
            int secondAttack = getPowerOfAtack(char1, challenged.getWeapons(), challenged.getArmor());

            int firstDefense = getPowerOfDefense(char1, challenged.getWeapons(), challenged.getArmor());
            int secondDefense = getPowerOfDefense(char0, challenger.getWeapons(), challenger.getArmor());

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
        this.store.loadCharacters();
        setRounds(rounds);
    }


    private int calculateLife(Character chara) {
        int lifeAux = chara.getLife();
        for (String minion : chara.getMinions()) {
            lifeAux+= this.store.getInfoMinion(minion).getLife();
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

    private int getPowerOfAtack(Character charac, List<String> weapons, String armor){
        //poder
        int power = charac.getPower();
        //poder según personaje
        int powerAtribute = charac.getPowerAtribute();

        String skill = charac.getSkill();
        int skillPoints = this.store.getInfoSkill(skill).getAttackPoints();
        int equipmentPoints = 0;
        for (String weapon:weapons){
            equipmentPoints += this.store.getInfoWeapon(weapon).getAttack();
        }
        equipmentPoints += this.store.getInfoArmor(armor).getAtack();
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
        return acum;
    }

    private int getPowerOfDefense(Character charac, List<String> weapons, String armor){
        int power = charac.getPower();
        int powerAtribute = charac.getPowerAtribute();
        String skill = charac.getSkill();
        int skillPoints = this.store.getInfoSkill(skill).getDefensePoints();
        int equipmentPoints = 0;
        for (String weapon:weapons){
            equipmentPoints += this.store.getInfoWeapon(weapon).getDefense();
        }
        equipmentPoints += this.store.getInfoArmor(armor).getAtack();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
