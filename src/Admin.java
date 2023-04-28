public class Admin extends Person{
    public Admin(String nick, String name, int password){
        super(nick, name, password,DataBaseResult.admin);
    }
}
