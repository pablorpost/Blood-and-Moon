import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArmorTest {

    private ArrayList<Armor> armorsOriginal;

    @BeforeEach
    void setUp() {
        armorsOriginal = new ArrayList<>();

    }

    @Test
    void loadArmorsCorrectTest() {

        for(int i = 0; i<=2; i++){
            String linea = "Armor"+ i + " " + i + " "+ i ;
            String [] var = linea.split(" ");
            Armor armor = new Armor(var);
            armorsOriginal.add(armor);
        }

        Armor armor1 = new Armor();
        ArrayList<Armor> armorsComplete = new ArrayList<>();
        try {
            armorsComplete = (ArrayList<Armor>) armor1.loadArmors("tests/storeFilesTests");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertEquals(armorsOriginal.size(), armorsComplete.size());
        ArrayList<Armor> armorsEmpty = new ArrayList<>();
        try {
            armorsEmpty = (ArrayList<Armor>) armor1.loadArmors("Files");
        } catch (FileNotFoundException e) {
        }
        assertEquals(0, armorsEmpty.size());
    }

    @Test
    void loadArmorsNoFileTest() {
        Armor armor1 = new Armor();
        ArrayList<Armor> armorsEmpty = new ArrayList<>();
        try {
            armorsEmpty = (ArrayList<Armor>) armor1.loadArmors("Files");
        } catch (FileNotFoundException e) {
        }
        assertEquals(0, armorsEmpty.size());
    }
}


