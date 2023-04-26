import java.io.Serializable;
import java.time.Instant;
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

    public Battle(User challenger, User challenged, int goldBet, Store store){
        this.challenger = challenger.getNick();
        this.challenged = challenged.getNick();
        this.gold = goldBet;
        this.date = Date.from(Instant.now());


        //ELIMINARR-----VVVVV
        this.rounds = new Random().nextInt(6);
        if(new Random().nextInt(2) == 1){
            this.winner = this.challenger;
            this.looser = this.challenged;
        }else{
            this.winner = this.challenged;
            this.looser = this.challenger;
        }




        /*
        Character char0;
        Character char1;

        Character charac0 = challenger.getCharacter();
        Character charac1 = challenged.getCharacter();

        char0 = doCopy(charac0);
        char1 = doCopy(charac1);


        int char0Life = char0.getLife();
        int char1Life = char1.getLife();
        int rounds = 0;
        while (char0Life > 0 && char1Life > 0) {
            rounds += 1;
            int firstAttack = getPowerOfAtack(char0, challenger.getWeapons(), challenger.getArmor());
            int secondAttack = getPowerOfAtack(char1, challenged.getWeapons(), challenged.getArmor());

            int firstDefense = getPowerOfDefense(char1, challenger.getWeapons(), challenger.getArmor());
            int secondDefense = getPowerOfDefense(char0, challenged.getWeapons(), challenged.getArmor());

            int succesAttack1 = calculateSucces(firstAttack);
            int succesAttack2 = calculateSucces(secondAttack);
            int succesDefense1 = calculateSucces(firstDefense);
            int succesDefense2 = calculateSucces(secondDefense);

            Character winner = new Character();
            Character looser = new Character();

            if (succesAttack1>=succesDefense1){
                char1Life-=1;
                winner = char0;
                looser = char1;
            } else if ((succesAttack2>=succesDefense2)) {
                char0Life-=1;
                winner = char1;
                looser = char0;
            }

            if (winner instanceof Vampire){
                ((Vampire) char0).addBlood((5));

            }
            if (looser instanceof Lycanthrope){
                ((Lycanthrope) char1).addAnger((1));

            }
            if (looser instanceof Hunter){
                ((Hunter) char1).lessWillpower((1));

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

        setRounds(rounds);
        */
    }

    private Character doCopy(Character charac) {
        Character chara;
        if (charac instanceof Vampire){
            chara = new Vampire((Vampire) charac);
        }else if (charac instanceof Hunter){
            chara = new Hunter((Hunter) charac);
        }else{
            chara = new Lycanthrope((Lycanthrope) charac);
        }
        return chara;
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

    private int getPowerOfAtack(Character charac, List<String> weapons, String armor, Store store){
        int power = charac.getPower();
        int powerAtribute = charac.getPowerAtribute();
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

    private int getPowerOfDefense(Character charac, List<String> weapons, String armor, Store store){
        int power = charac.getPower();
        int powerAtribute = charac.getPowerAtribute();
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
