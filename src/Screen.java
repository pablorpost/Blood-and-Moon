public class Screen {
    private String title;
    private String description;
    private Person person;
    private DBManager dataBase;
    private Store store;
    private Manager manager;


    public ScreenResult showOptions(){
        return null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public DBManager getDataBase() {
        return dataBase;
    }

    public void setDataBase(DBManager dataBase) {
        this.dataBase = dataBase;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
