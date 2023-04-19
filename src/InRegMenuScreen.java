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
            if (option.equals("user")){
                if(election==2){
                    option = "admin";
                }
            }
            else{
                if (option.equals("admin")) {
                    if (election == 2) {
                        option = "user";
                    }
                }
            }
            if (election==0 || election==1){
                this.getManager().clearConsole();
                showForm((election==1),(option.equals("admin")));
            }
        }
        return ScreenResult.stay;
    }

    public Map<String, String> showForm(boolean isRegist, boolean isAdmin){
        Map<String, String> formulario = new HashMap<String, String>();
        if (isAdmin){
            System.out.println("Admin Login...");
        } else {
            System.out.println("User Login...");
        }
        System.out.print("User/Nick:  ");
        String election;
        Scanner teclado = new Scanner(System.in);
        election = teclado.nextLine();
        formulario.put("nick", election);

        if (isRegist){
            System.out.print("Name:  ");
            teclado = new Scanner(System.in);
            election = teclado.nextLine();
            formulario.put("name", election);
        }

        System.out.print("Password:  ");
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
        return getDataBase().inDataBase(nick,pass);
    }

    public Person addPerson(String nick, String name, String pass, boolean isAdmin){
        DBManager d = getDataBase();
        if (isAdmin){
            d.addAdmin(nick,pass);
            return d.getAdmin(nick,pass);
        }
        d.addUser(nick,pass);
        return d.getUser(nick,pass);
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
