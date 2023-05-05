import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GhoulTest {


    @Test
    void readLineTest() {
        Ghoul ghoul = new Ghoul();


        String line = "name:Ghoul";
        String[] var = line.split(":");
        ghoul.readLine(var);
        assertEquals("Ghoul", ghoul.getName());

        line = "life:3";
        var = line.split(":");
        ghoul.readLine(var);
        assertEquals(3, ghoul.getLife());

        line = "dependency:3";
        var = line.split(":");
        ghoul.readLine(var);
        assertEquals(3, ghoul.getDependency());

    }


}