import java.io.IOException;

public class Game {
    public void run(){
        Store store = new Store();
        Manager manager = new Manager(store);
    }
}
