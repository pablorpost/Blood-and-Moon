import java.io.IOException;

public class Game {
    //crea la tienda y manager que respectivamente cargaran los datos de, y gestionaran el juego
    public void run(){
        Store store = new Store();
        Manager manager = new Manager(store);
    }
}
