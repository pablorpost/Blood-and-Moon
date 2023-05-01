public class Manager {
    private DBManager database;
    private Store store;

    //inicializa manager y sus atributos
    public Manager(Store store) {
        this.store = store;
        this.database = new DBManager();
        //loadScreen(25);
        InRegMenuScreen inicio = new InRegMenuScreen(database, store,this);
        showScreen(inicio);
    }

    //muestra el titulo, la descripcion e inicia el funcionamiento de la pantalla
    public void showScreen(Screen screen) {
        ScreenResult sr = ScreenResult.stay;
        String title;
        String desc;
        while (sr==ScreenResult.stay) {
            //clearConsole();
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

    //limpia la consola y pone el nombre en ella
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
        System.out.println("██████╗ ██╗      ██████╗  ██████╗ ██████╗    ██╗   ███╗   ███╗ ██████╗  ██████╗ ███╗   ██╗\n██╔══██╗██║     ██╔═══██╗██╔═══██╗██╔══██╗   ██║   ████╗ ████║██╔═══██╗██╔═══██╗████╗  ██║\n██████╔╝██║     ██║   ██║██║   ██║██║  ██║████████╗██╔████╔██║██║   ██║██║   ██║██╔██╗ ██║\n██╔══██╗██║     ██║   ██║██║   ██║██║  ██║██╔═██╔═╝██║╚██╔╝██║██║   ██║██║   ██║██║╚██╗██║\n██████╔╝███████╗╚██████╔╝╚██████╔╝██████╔╝██████║  ██║ ╚═╝ ██║╚██████╔╝╚██████╔╝██║ ╚████║\n╚═════╝ ╚══════╝ ╚═════╝  ╚═════╝ ╚═════╝ ╚═════╝  ╚═╝     ╚═╝ ╚═════╝  ╚═════╝ ╚═╝  ╚═══╝");
    }

    //pantalla de carga falsa, para dar sensacion de calidad
    public void loadScreen(int x){
        for (int i=0;i<101;i=i+2) {
            try {
                clearConsole();
                System.out.println("\n\n\n");
                System.out.println("                                         "+i+"%");
                for (int j=0;j<i;j++) {
                    System.out.print(".-");
                }
                Thread.sleep(x);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //devuelve la store
    public Store getStore(){return this.store;}
    //devuelve el gestor de la base de datos
    public DBManager getDatabase(){return this.database;}
}
