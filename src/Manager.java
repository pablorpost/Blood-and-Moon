import java.io.IOException;

public class Manager {
    private DBManager database;
    private Store store;

    public Manager(Store store){
        this.store = store;
        this.database = new DBManager();
    }

    public void showScreen(Screen screen){

    }

    private void loadUsers(){

    }

}
