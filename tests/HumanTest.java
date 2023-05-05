import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {

    @Test
    void readLineTest() {
        Human human = new Human();

        String line = "name:Human";
        String[] var = line.split(":");
        human.readLine(var);
        assertEquals("Human", human.getName());

        line = "life:3";
        var = line.split(":");
        human.readLine(var);
        assertEquals(3, human.getLife());

        line = "loyalty:ALTA";
        var = line.split(":");
        human.readLine(var);
        assertEquals("ALTA", human.getLoyalty());

    }

}