import java.io.Console;
import java.io.IOException;
import java.util.*;

public class InRegMenuScreen extends Screen{

    private Map<String, List<String>> options;
    private Map<String, List<String>> formVersions;
    private String option;
    private Scanner teclado;

    //Constructor
    public InRegMenuScreen(DBManager dataBase, Store store,Manager manager, Scanner teclado) {
        super.setStore(store);
        super.setDataBase(dataBase);
        super.setManager(manager);
        super.setTitle("What would you like to do?");
        this.teclado = teclado;
        this.options = new HashMap<String, List<String>>();
        this.formVersions = new HashMap<String, List<String>>();
        option = "user";
        List<String> optionsUser = new ArrayList<String>();
        optionsUser.add("Log in");
        optionsUser.add("Sign up");
        optionsUser.add("Log as admin");
        options.put("user",optionsUser);
        List<String> optionsAdmin = new ArrayList<String>();
        optionsAdmin.add("Log in as Admin");
        optionsAdmin.add("Create new Admin");
        optionsAdmin.add("Exit");
        options.put("admin",optionsAdmin);

    }

    //Mostrará al usuario las opciones de esta pantalla.
    @Override
    public ScreenResult showOptions(){
        int election = -1;
        List<String> thisOptions = options.get(option);
        for (int i = 0; i < thisOptions.size(); i++) {
            System.out.println(i+ ".    " + thisOptions.get(i));
        }
        try{
            election = Integer.parseInt(teclado.nextLine());
        }
        catch (NumberFormatException e){}
        if (election>=0 && election<thisOptions.size()){
            if(election==2){
                if (option.equals("user")) {
                    option = "admin";
                }else {
                    option = "user";
                }
            }
            if (election==0 || election==1){
                this.getManager().clearConsole();
                showForm((election==1),(option.equals("admin")), teclado);
                option = "user";
            }
        }
        return ScreenResult.stay;
    }

    //Muestra los distintos campos que se solicitará al usuario (los campos dependen del tipo de usuario)
    public Map<String, String> showForm(boolean isRegist, boolean isAdmin, Scanner teclado){
        Console console = System.console();
        String password="";
        Map<String, String> formulario = new HashMap<String, String>();
        if (isAdmin){
            System.out.print("Admin ");
        } else {
            System.out.print("User ");
        }
        if (isRegist){
            System.out.println("Register...");
        }
        else{
            System.out.println("Login...");
        }
        if (isAdmin && isRegist){
            System.out.println("Introduce la contraseña de administrador");
            if (console == null) {
                password = teclado.nextLine();
            }
            else{
                char[] passwordArray = console.readPassword("Enter the password: ");
                password = new String(passwordArray);
            }
            if (!getDataBase().adminPasswordCheck(password)){
                System.out.println("Incorrect admin password");
                System.out.println("(Press ENTER to continue)");
                return null;
            }
            System.out.println();
        }
        System.out.print("User/Nick:  ");
        String election = teclado.nextLine();
        if (isRegist) {
            while (super.getDataBase().existingUser(election) != DataBaseResult.notFound || election.contains(" ") || election.equals("")) {
                if (super.getDataBase().existingUser(election) != DataBaseResult.notFound) {
                    System.out.println("This user already exists. Please enter another nick name.");
                    System.out.print("User/Nick:  ");
                    election = teclado.nextLine();
                } else {
                    System.out.println("Your nick can't contain a space.");
                    System.out.print("User/Nick:  ");
                    election = teclado.nextLine();
                }
            }
        }
        formulario.put("nick", election);
        if (isRegist){
            System.out.print("Name:  ");
            election = teclado.nextLine();
            formulario.put("name", election);
        }
        System.out.print("Password:  ");
        if (console == null) {
            password = teclado.nextLine();
        }
        else{
            char[] passwordArray = console.readPassword();
            password = new String(passwordArray);
            if (isRegist){
                while(password.length()<8 || password.length()>12){
                    System.out.println("Your password must be in a length between 8 and 12 characters");
                    System.out.print("Password:  ");
                    passwordArray = console.readPassword();
                    password = new String(passwordArray);
                }
            }
        }
        formulario.put("pas", password);
        if (isRegist){
            if (formulario.get("pas").length() >= 8 && formulario.get("pas").length() <= 12) {
                this.addPerson(formulario.get("nick"), formulario.get("name"), formulario.get("pas"), isAdmin);
                this.getDataBase().save();
            } else {
                System.out.println("Invalid password");
                System.out.println("(Press ENTER to continue)");
                election = teclado.nextLine();
                return null;
            }
        }
        DataBaseResult resulta = this.checkPerson(formulario.get("nick"), formulario.get("pas"));
        if (resulta == DataBaseResult.user && !isAdmin){
            User user = getDataBase().getUser(formulario.get("nick"), formulario.get("pas"));
            UserMainMenuScreen pantalla = new UserMainMenuScreen(getDataBase(),getStore(),getManager(), user, teclado);
            getManager().showScreen(pantalla);
            return null;
        } else if (resulta==DataBaseResult.admin && isAdmin){
            Admin admin = getDataBase().getAdmin(formulario.get("nick"), formulario.get("pas"));
            AdminMainMenuScreen pantalla = new AdminMainMenuScreen(getDataBase(),getStore(),getManager(),admin, teclado);
            getManager().showScreen(pantalla);
            return null;
        }
        System.out.println("Incorrect user or password");
        System.out.println("(Press ENTER to continue)");
        election = teclado.nextLine();
        return null;
    }

    //Devolverá el resultado de buscar una persona en la base de datos
    public DataBaseResult checkPerson(String nick, String pass){
        return getDataBase().inDataBase(nick,pass);
    }

    //Añadirá una persona a la base de datos
    public Person addPerson(String nick, String name, String pass, boolean isAdmin){
        DBManager d = getDataBase();
        if (isAdmin){
            d.addAdmin(nick, name, pass);
            return d.getAdmin(nick,pass);
        }
        d.addUser(nick, name, pass);
        return d.getUser(nick, pass);
    }

}
