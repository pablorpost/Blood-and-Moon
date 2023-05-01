import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InRegMenuScreenTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void showOptions() {
        try {
            String input = "1\nDavidPruebas2\ndavid1\n12345678";
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            Game game = new Game();
            game.run();
        }
        catch(Exception e) {
            e.getStackTrace();
        }
    }
}