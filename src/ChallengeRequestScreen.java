import java.util.ArrayList;
import java.util.Objects;
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
        if (!lookForNick(challenged) || challenged.equals(challenger.getNick()) || getDataBase().getUserByNick(challenged).getPendingRequest()!=null || getDataBase().hasCharacter(challenged)) {
            if (lookForNick(challenged) && getDataBase().getUserByNick(challenged).getPendingRequest()!=null){
                System.out.println("This user currently has a pending challenge accepted by the admins.");
            }
            if (lookForNick(challenged) && getDataBase().hasCharacter(challenged)){
                System.out.println("This user does not have a character to battle");
            }
            System.out.println("Please enter a valid user nick name.");
            System.out.println("\n(Press ENTER to continue)");
            teclado.nextLine();
            return super.showOptions();
        }
        if (!getDataBase().lastRequestMoreThanADayAgo(challenger.getNick(),challenged)){
            System.out.println("You have challenged this user twice in less than 24 hours");
            System.out.println("You are now banned, to regain access to your account please contact an admin");
            System.out.println("\n(Press ENTER to continue)");
            challenger.setBan(true);
            teclado.nextLine();
            return ScreenResult.exit;
        }
        System.out.println("How much gold would you like to bet (you have " + challenger.getGold() + " coins)?");
        int gold = teclado.nextInt();
        while (gold > challenger.getGold()){
            System.out.println("You don't have that much money, please enter a valid amount.");
            gold = teclado.nextInt();
        }
        getDataBase().addRequest(challenger.getNick(), challenged, gold);
        challenger.setGold(challenger.getGold()-gold);
        getDataBase().addLastRequestDate(challenger.getNick(),challenged);
        getDataBase().save();
        return super.showOptions();
    }

    public boolean lookForNick(String user){
        if (getDataBase().existingUser(user)==DataBaseResult.user){
            return true;
        }
        return false;
    }
}
