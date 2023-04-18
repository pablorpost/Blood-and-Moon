import java.io.IOException;

public class Manager {
    private DBManager database;
    private Store store;

    public Manager(Store store) {
        this.store = store;
        this.database = new DBManager();
        InRegMenuScreen inicio = new InRegMenuScreen(database, store);
        showScreen(inicio);
    }


    public void showScreen(Screen screen) {
        ScreenResult sr = ScreenResult.stay;
        int i=0;
        while (sr.equals(ScreenResult.stay)) {
            clearConsole();
            System.out.println(Integer.toBinaryString(i++));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }

    private void loadUsers() {

    }

    public void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
