import java.util.List;
import java.util.Map;

public class InRegMenuScreen extends Screen{

    private Map<String, List<String>> options;
    private Map<String, List<String>> formVersions;
    public Map<String, List<String>> getOptions() {
        return options;
    }


    public InRegMenuScreen(DBManager dataBase, Store store) {
        setStore(store);
        setDataBase(dataBase);
    }


    public int showOptions(String option){
        return 0; //Vacío
    }

    public Map<String, String> showForm(boolean isRegist, boolean isAdmin){
        return null;
    }

    public DataBaseResult checkPerson(String name, String pass){
        return null;
    }

    public Person addPerson(String name, String pass, boolean isAdmin){
        return null;
    }

    public void showError(){

    }

    public boolean checkAdmin(int pas){
        return false; //Vacío
    }

    public Person getPersonAndSet(String name, String pass){
        return null;
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
