import java.io.Console;
import java.io.IOException;
import java.util.*;

public class InRegMenuScreen extends Screen{

    private Map<String, List<String>> options;
    private Map<String, List<String>> formVersions;

    private String option;
    public InRegMenuScreen(DBManager dataBase, Store store,Manager manager) {
        super.setStore(store);
        super.setDataBase(dataBase);
        super.setManager(manager);
        super.setTitle("What would you like to do?");
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


    @Override
    public ScreenResult showOptions(){
        int election = -1;
        List<String> thisOptions = options.get(option);
        for (int i = 0; i < thisOptions.size(); i++) {
            System.out.println(i+ ".    " + thisOptions.get(i));
        }
        Scanner teclado = new Scanner(System.in);
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
                System.out.println("██████╗ ██╗      ██████╗  ██████╗ ██████╗    ██╗   ███╗   ███╗ ██████╗  ██████╗ ███╗   ██╗\n██╔══██╗██║     ██╔═══██╗██╔═══██╗██╔══██╗   ██║   ████╗ ████║██╔═══██╗██╔═══██╗████╗  ██║\n██████╔╝██║     ██║   ██║██║   ██║██║  ██║████████╗██╔████╔██║██║   ██║██║   ██║██╔██╗ ██║\n██╔══██╗██║     ██║   ██║██║   ██║██║  ██║██╔═██╔═╝██║╚██╔╝██║██║   ██║██║   ██║██║╚██╗██║\n██████╔╝███████╗╚██████╔╝╚██████╔╝██████╔╝██████║  ██║ ╚═╝ ██║╚██████╔╝╚██████╔╝██║ ╚████║\n╚═════╝ ╚══════╝ ╚═════╝  ╚═════╝ ╚═════╝ ╚═════╝  ╚═╝     ╚═╝ ╚═════╝  ╚═════╝ ╚═╝  ╚═══╝");
                showForm((election==1),(option.equals("admin")));
                option = "user";
            }
        }
        return ScreenResult.stay;
    }

    public Map<String, String> showForm(boolean isRegist, boolean isAdmin){
        Console console = System.console();
        Scanner teclado = new Scanner(System.in);
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
            while (super.getDataBase().existingUser(election) != DataBaseResult.notFound) {
                System.out.println("This user already exists. Please enter another nick name.");
                System.out.print("User/Nick:  ");
                election = teclado.nextLine();
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
        }

        formulario.put("pas", password);


        if (isRegist){
            this.addPerson(formulario.get("nick"), formulario.get("name"), formulario.get("pas"), isAdmin);
            this.getDataBase().save();
        }
        DataBaseResult resulta = this.checkPerson(formulario.get("nick"), formulario.get("pas"));
        if (resulta == DataBaseResult.user && !isAdmin){
            User user = getDataBase().getUser(formulario.get("nick"), formulario.get("pas"));
            UserMainMenuScreen pantalla = new UserMainMenuScreen(getDataBase(),getStore(),getManager(), user);
            getManager().showScreen(pantalla);
            return null;
        } else if (resulta==DataBaseResult.admin && isAdmin){
            Admin admin = getDataBase().getAdmin(formulario.get("nick"), formulario.get("pas"));
            AdminMainMenuScreen pantalla = new AdminMainMenuScreen(getDataBase(),getStore(),getManager(),admin);
            getManager().showScreen(pantalla);
            return null;
        }
        System.out.println("Incorrect user or password");
        System.out.println("(Press ENTER to continue)");
        teclado = new Scanner(System.in);
        election = teclado.nextLine();
        return null;
    }

    public DataBaseResult checkPerson(String nick, String pass){
        return getDataBase().inDataBase(nick,pass);
    }

    public Person addPerson(String nick, String name, String pass, boolean isAdmin){
        DBManager d = getDataBase();
        if (isAdmin){
            d.addAdmin(nick, name, pass);
            return d.getAdmin(nick,pass);
        }
        d.addUser(nick, name, pass);
        return d.getUser(nick, pass);
    }

    public void showError(){

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
