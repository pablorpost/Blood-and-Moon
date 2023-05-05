import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemonTest {

    @Test
    void readLineTest() {
        Demon demon = new Demon();


        String line = "name:Demon";
        String[] var = line.split(":");
        demon.readLine(var);
        assertEquals("Demon", demon.getName());

        line = "life:3";
        var = line.split(":");
        demon.readLine(var);
        assertEquals(3, demon.getLife());

        line = "oath:Oath";
        var = line.split(":");
        demon.readLine(var);
        assertEquals("Oath", demon.getOath());

    }
}