import java.util.Scanner;

public class ChallengeRequestScreen extends Screen{
    User challenger;
    public ChallengeRequestScreen(DBManager dataBase, User usuario) {
        setDataBase(dataBase);
        challenger=usuario;
    }

    @Override
    public ScreenResult showOptions() {
        System.out.println("What user would you like to challenge?");
        Scanner teclado = new Scanner(System.in);
        String challenged = teclado.nextLine();
        if(lookForNick(challenged)){
            System.out.println("How much gold would you like to bet (you have " + challenger.getGold() + " coins)?");
            int gold = teclado.nextInt();
            while (gold > challenger.getGold()){
                System.out.println("You don't have that much money, please enter a valid amount.");
                gold = teclado.nextInt();
            }
            getDataBase().addRequest(challenger.getNick(), challenged, gold);
        }
        return super.showOptions();
    }

    public boolean lookForNick(String user){
        if (getDataBase().existingUser(user)==DataBaseResult.user){
            return true;
        }
        return false;
    }
}
