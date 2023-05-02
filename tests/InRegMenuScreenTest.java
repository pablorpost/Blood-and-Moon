import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;


class InRegMenuScreenTest {

    @BeforeEach
    void setUp() {
        File file = new File("dataBase.binaryDB");
        file.delete();
    }

    private void crearCuenta(String thisNick, String thisName, String thisPas, boolean isAdmin){
        try {
            // Entradas

            String input = "1\n";
            if (isAdmin){
                input = "2\n1\n80085\n";
            }

            input += thisNick + "\n" + thisName + "\n" + thisPas;
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            // Perder la consola
            OutputStream nullStream = new OutputStream() {public void write(int b) {}};
            PrintStream printStream = new PrintStream(nullStream);
            System.setOut(printStream);

            // Iniciar programa
            Game game = new Game();
            game.run();
        } catch (Exception e){
            // Fin de ejecucion
        }
    }

    private void iniciarSesionCuenta(String thisNick, String thisPas, boolean isAdmin) {
        try {
            // Entradas

            String input = "0\n";
            if (isAdmin) {
                input = "2\n0\n";
            }

            input += thisNick + "\n" + thisPas;
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            // Perder la consola
            OutputStream nullStream = new OutputStream() {
                public void write(int b) {
                }
            };
            PrintStream printStream = new PrintStream(nullStream);
            System.setOut(printStream);

            // Iniciar programa
            Game game = new Game();
            game.run();
        } catch (Exception e) {
            // Fin de ejecucion
        }
    }

    private boolean comprobarCuentas(List<String> nicks, List<String> passws, boolean isAdmin){
        DBManager database = new DBManager();
        for (int i = 0; i < nicks.size(); i++) {
            DataBaseResult res = database.inDataBase(nicks.get(i), passws.get(i));
            if ((res != DataBaseResult.user && !isAdmin) || (res != DataBaseResult.admin && isAdmin)){
                return false;
            }
        }
        return true;
    }

    private static int numeroAleatorioEnRango(int minimo, int maximo) {
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }

    private static String cadenaAleatoria(int minSize, int maxSize) {
        String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String cadena = "";
        int length = ThreadLocalRandom.current().nextInt(minSize, maxSize + 1);
        for (int x = 0; x < length; x++) {
            cadena += banco.charAt(numeroAleatorioEnRango(0, banco.length() - 1));
        }
        return cadena;
    }

    @Test
    void crearCuentasUsuario() {
        // Insertar 20 cuentas nuevas
        int numCuentas = 20;
        List<String> nicks = new ArrayList<>();
        List<String> passws = new ArrayList<>();
        for (int i = 0; i < numCuentas; i++) {
            String thisNick = "NickNamePruebasUser" + i;
            nicks.add(thisNick);
            String thisName = "NamePruebas" + i;
            String thisPas = "12345678";
            passws.add(thisPas);
            crearCuenta(thisNick, thisName, thisPas, false);
        }
        // Comprobar que se hayan creado con exito
        assertTrue(comprobarCuentas(nicks, passws, false));
    }

    @Test
    void crearCuentasAdmin() {
        // Insertar 20 cuentas nuevas
        int numCuentas = 20;
        List<String> nicks = new ArrayList<>();
        List<String> passws = new ArrayList<>();
        for (int i = 0; i < numCuentas; i++) {
            String thisNick = "NickNamePruebasAdmin" + i;
            nicks.add(thisNick);
            String thisName = "NamePruebas" + i;
            String thisPas = "12345678";
            passws.add(thisPas);
            crearCuenta(thisNick, thisName, thisPas, true);
        }
        // Comprobar que se hayan creado con exito
        assertTrue(comprobarCuentas(nicks, passws, true));
    }

    @Test
    void crearCuentasUsuarioNombresRaros() {
        // Insertar 20 cuentas nuevas
        int numCuentas = 20;
        int longitudMin = 0;
        int longitudMax = 1000;
        List<String> nicks = new ArrayList<>();
        List<String> passws = new ArrayList<>();
        for (int i = 0; i < numCuentas; i++) {
            String thisNick = cadenaAleatoria(longitudMin, longitudMax);
            nicks.add(thisNick);
            String thisName = cadenaAleatoria(longitudMin, longitudMax);
            String thisPas = "12345678";
            passws.add(thisPas);
            crearCuenta(thisNick, thisName, thisPas, false);
        }
        // Comprobar que se hayan creado con exito
        assertTrue(comprobarCuentas(nicks, passws, false));
    }

    @Test
    void crearCuentasAdminNombresRaros() {
        // Insertar 20 cuentas nuevas
        int numCuentas = 20;
        int longitudMin = 0;
        int longitudMax = 1000;
        List<String> nicks = new ArrayList<>();
        List<String> passws = new ArrayList<>();
        for (int i = 0; i < numCuentas; i++) {
            String thisNick = cadenaAleatoria(longitudMin, longitudMax);
            nicks.add(thisNick);
            String thisName = cadenaAleatoria(longitudMin, longitudMax);
            String thisPas = "12345678";
            passws.add(thisPas);
            crearCuenta(thisNick, thisName, thisPas, true);
        }
        // Comprobar que se hayan creado con exito
        assertTrue(comprobarCuentas(nicks, passws, true));
    }

    @Test
    void crearCuentasUsuarioContrasenyasRaros() {
        // Insertar 20 cuentas nuevas
        int numCuentas = 20;
        int longitudMin = 8;
        int longitudMax = 12;
        List<String> nicks = new ArrayList<>();
        List<String> passws = new ArrayList<>();
        for (int i = 0; i < numCuentas; i++) {
            String thisNick = "NickNameUserPruebasContrasenyas" + i;
            nicks.add(thisNick);
            String thisName = "NamePruebas" + i;
            String thisPas = cadenaAleatoria(longitudMin, longitudMax);
            passws.add(thisPas);
            crearCuenta(thisNick, thisName, thisPas, false);
        }
        // Comprobar que se hayan creado con exito
        assertTrue(comprobarCuentas(nicks, passws, false));
    }

    @Test
    void crearCuentasAdminContrasenyasRaros() {
        // Insertar 20 cuentas nuevas
        int numCuentas = 20;
        int longitudMin = 8;
        int longitudMax = 12;
        List<String> nicks = new ArrayList<>();
        List<String> passws = new ArrayList<>();
        for (int i = 0; i < numCuentas; i++) {
            String thisNick = "NickNameAdminPruebasContrasenyas" + i;
            nicks.add(thisNick);
            String thisName = "NamePruebas" + i;
            String thisPas = cadenaAleatoria(longitudMin, longitudMax);
            passws.add(thisPas);
            crearCuenta(thisNick, thisName, thisPas, true);
        }
        // Comprobar que se hayan creado con exito
        assertTrue(comprobarCuentas(nicks, passws, true));
    }

    @Test
    void iniciarSesionCuentasUsuario() {
        crearCuentasUsuario();
        // Insertar 20 cuentas nuevas
        int numCuentas = 20;
        for (int i = 0; i < numCuentas; i++) {
            String thisNick = "NickNamePruebasUser" + i;
            String thisPas = "12345678";
            iniciarSesionCuenta(thisNick, thisPas, false);
            //assertEquals(endClassName, "UserMainMenuScreen");
        }
    }

    @Test
    void iniciarSesionCuentasAdmin() {
        crearCuentasAdmin();
        int numCuentas = 20;
        // Insertar 20 cuentas nuevas
        for (int i = 0; i < numCuentas; i++) {
            String thisNick = "NickNamePruebasAdmin" + i;
            String thisPas = "12345678";
            iniciarSesionCuenta(thisNick, thisPas, true);
            //assertEquals(endClassName, "AdminMainMenuScreen");
        }
    }
}