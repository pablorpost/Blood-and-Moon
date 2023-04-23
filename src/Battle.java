import java.io.Serializable;
import java.util.Date;

public class Battle implements Serializable {

    private String winner;
    private String looser;
    private int rounds;
    private Date date;
    private int gold;
    private String challenger;
    private String challenged;

    public Battle(User challenger, User challenged, int goldBet){
        Character char0 = challenger.getCharacter();
        Character char1 = challenged.getCharacter();
        int vida0 = 0;
        int vida1 = 0;
        while (vida0 > 0 && vida1 > 0){

        }
    }

    private int getPowerOfAtack(Character char0){
        if (char0 instanceof Vampire){
            int calc = 0;
            //calc += poder??
            //calc += valor de ataque de disciplina
            //calc += valor de ataque de equipo activo
            if (((Vampire)char0).getBlood() >= 5){
                calc += 2;
                ((Vampire)char0).setBlood(((Vampire)char0).getBlood() - 5);
            }


        } else if (char0 instanceof Lycanthrope){

        } else {

        }
        return 0;
    }

    private int getPowerOfDeffense(Character char0){
        return 0;
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
