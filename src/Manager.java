import java.io.IOException;

public class Manager {
    private DBManager database;
    private Store store;

    public Manager(Store store) {
        this.store = store;
        this.database = new DBManager();
        name();
        InRegMenuScreen inicio = new InRegMenuScreen(database, store,this);
        showScreen(inicio);
    }


    public void showScreen(Screen screen) {
        ScreenResult sr = ScreenResult.stay;
        String title;
        String desc;
        while (sr.equals(ScreenResult.stay)) {
            clearConsole();
            title = screen.getTitle();
            desc = screen.getDescription();
            if (title!=null){
                System.out.println(screen.getTitle());
            }
            if (desc!=null){
                System.out.println(screen.getDescription());
            }
            System.out.println();
            sr = screen.showOptions();
            database.save();
        }

    }

    private void loadUsers() {

    }

    public void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void name(){
        for (int i=0;i<101;i=i+2) {
            try {
                clearConsole();
                System.out.println("██████╗ ██╗      ██████╗  ██████╗ ██████╗    ██╗   ███╗   ███╗ ██████╗  ██████╗ ███╗   ██╗\n██╔══██╗██║     ██╔═══██╗██╔═══██╗██╔══██╗   ██║   ████╗ ████║██╔═══██╗██╔═══██╗████╗  ██║\n██████╔╝██║     ██║   ██║██║   ██║██║  ██║████████╗██╔████╔██║██║   ██║██║   ██║██╔██╗ ██║\n██╔══██╗██║     ██║   ██║██║   ██║██║  ██║██╔═██╔═╝██║╚██╔╝██║██║   ██║██║   ██║██║╚██╗██║\n██████╔╝███████╗╚██████╔╝╚██████╔╝██████╔╝██████║  ██║ ╚═╝ ██║╚██████╔╝╚██████╔╝██║ ╚████║\n╚═════╝ ╚══════╝ ╚═════╝  ╚═════╝ ╚═════╝ ╚═════╝  ╚═╝     ╚═╝ ╚═════╝  ╚═════╝ ╚═╝  ╚═══╝");
                System.out.println("\n\n\n");
                System.out.println("                                         "+i+"%");
                for (int j=0;j<i;j++) {
                    System.out.print(".-");
                }
                Thread.sleep(25);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
