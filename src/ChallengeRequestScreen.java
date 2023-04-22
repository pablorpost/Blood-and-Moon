import java.util.Scanner;

public class ChallengeRequestScreen extends Screen{
    public ChallengeRequestScreen(DBManager dataBase) {
        setDataBase(dataBase);
    }

    @Override
    public ScreenResult showOptions() {
        User challenged = null;
        System.out.println("What user would you like to challenge?");
        Scanner teclado = new Scanner(System.in);
        String election = teclado.nextLine();
        challenged = lookForNick(election);
        return super.showOptions();
    }

    public User lookForNick(String user){
        if (getDataBase().existingUser(user)==DataBaseResult.user){
            return null;
        }
        return null;
    }

    private void saveRequest(){

    }
}
