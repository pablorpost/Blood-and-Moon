import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PopUpScreenTest {



    @Test
    @DisplayName("")
    void test1(){
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("3\n4\n".getBytes()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);


        System.setIn(stdin);
        System.setOut(stdout);
        System.setIn(new ByteArrayInputStream("0".getBytes()));
        Manager man = new Manager(new Store());
        PopUpScreen popUp = new PopUpScreen(null, man, new User("","",0));
        ScreenResult r = popUp.showPopUp(0);
        assertEquals(ScreenResult.stay,r);
    }

}