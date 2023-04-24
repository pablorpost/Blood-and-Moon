import java.util.Scanner;

public class ChallengeRequestScreen extends Screen{
    String challenger;
    public ChallengeRequestScreen(DBManager dataBase, String usuario) {
        setDataBase(dataBase);
        challenger=usuario;
    }

    @Override
    public ScreenResult showOptions() {
        System.out.println("What user would you like to challenge?");
        Scanner teclado = new Scanner(System.in);
        String challenged = teclado.nextLine();
        if(lookForNick(challenged)){
            System.out.println("How much gold would you like to bet?");
            Integer gold = teclado.nextInt();
            getDataBase().addRequest(challenger, challenged, gold);
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
