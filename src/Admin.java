public class Admin extends Person{
    // Constructior del Admin
    public Admin(String nick, String name, int password){
        super(nick, name, password,DataBaseResult.admin);
    }
}
