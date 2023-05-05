import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArmorTest {

    private ArrayList<Armor> armorsOriginal;

    @BeforeEach
    void setUp() {
        Armor armor1 = new Armor();
        armorsOriginal = new ArrayList<>();
        try {
            armorsOriginal = (ArrayList<Armor>) armor1.loadArmors("storeFilesTests");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void loadArmorsCorrectTest() {
        Armor armor1 = new Armor();
        ArrayList<Armor> armorsComplete = new ArrayList<>();
        try {
            armorsComplete = (ArrayList<Armor>) armor1.loadArmors("storeFilesTests");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertEquals(armorsOriginal.size(), armorsComplete.size());
    }

    @Test
    void loadArmorsFilesEmptyTest() {
        Armor armor1 = new Armor();
        ArrayList<Armor> armorsComplete = new ArrayList<>();
        try {
            armorsComplete = (ArrayList<Armor>) armor1.loadArmors("storeFilesTestsNoText");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertEquals(0, armorsComplete.size());
    }

    @Test
    void loadArmorsNoFilesTest() {
        Armor armor1 = new Armor();
        ArrayList<Armor> armorsComplete = new ArrayList<>();
        try {
            armorsComplete = (ArrayList<Armor>) armor1.loadArmors("storeFilesNoFiles");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertEquals(0, armorsComplete.size());
    }
}


