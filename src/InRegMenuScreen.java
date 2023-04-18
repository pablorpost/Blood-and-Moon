import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InRegMenuScreen extends Screen{

    private Map<String, List<String>> options;
    private Map<String, List<String>> formVersions;

    public InRegMenuScreen(DBManager dataBase, Store store) {
        super.setStore(store);
        super.setDataBase(dataBase);
        super.setTitle("");

    }



    public int showOptions(String option){
        List<String> thisOptions = options.get(option);
        for (int i = 0; i < options.size(); i++) {
            System.out.println(Integer.toString(i) + ".    " + thisOptions.get(i));
        }
        String election;
        Scanner teclado = new Scanner(System.in);
        election = teclado.nextLine();
        return Integer.valueOf(election);
    }

    public Map<String, String> showForm(boolean isRegist, boolean isAdmin){
        Map<String, String> formulario = new HashMap<String, String>();
        if (isAdmin){
            System.out.println("Admin Login...");
        } else {
            System.out.println("User Login...");
        }
        System.out.println("User/Nick:");
        String election;
        Scanner teclado = new Scanner(System.in);
        election = teclado.nextLine();
        formulario.put("nick", election);

        if (isRegist){
            System.out.println("Name:");
            teclado = new Scanner(System.in);
            election = teclado.nextLine();
            formulario.put("name", election);
        }

        System.out.println("Password:");
        teclado = new Scanner(System.in);
        election = teclado.nextLine();
        formulario.put("pas", election);

        if (isRegist){
            this.addPerson(formulario.get("nick"), formulario.get("name"), formulario.get("pas"), isAdmin);
        } else {
            DataBaseResult resulta = this.checkPerson(formulario.get("nick"), formulario.get("pas"));
            if (resulta == DataBaseResult.user || resulta == DataBaseResult.admin){
                Person thisPerson = this.getPersonAndSet(formulario.get("nick"), formulario.get("pas"));

                // por terminar------------------------------------

            }
        }
        return null;
    }

    public DataBaseResult checkPerson(String nick, String pass){
        return null;
    }

    public Person addPerson(String nick, String name, String pass, boolean isAdmin){
        return null;
    }

    public void showError(){

    }

    public boolean checkAdmin(int pas){
        return false;
    }

    public Person getPersonAndSet(String name, String pass){
        return null;
    }

    public Map<String, List<String>> getOptions() {
        return options;
    }

    public void setOptions(Map<String, List<String>> options) {
        this.options = options;
    }

    public Map<String, List<String>> getFormVersions() {
        return formVersions;
    }

    public void setFormVersions(Map<String, List<String>> formVersions) {
        this.formVersions = formVersions;
    }
}
