import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ArmorTest {

    private ArrayList<Armor> armors1;
    private ArrayList<Armor> armors2;
    private ArrayList<Armor> armors3;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Armor armor1= new Armor();
        armors1 = new ArrayList<>();
        try {
            armors1 = (ArrayList<Armor>) armor1.loadArmors("storeFilesTests");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Armor armor2= new Armor();
        armors2 = new ArrayList<>();
        try {
            armors2 = (ArrayList<Armor>) armor2.loadArmors("storeFilesTestsNoText");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Armor armor3= new Armor();
        armors3 = new ArrayList<>();
        try {
            armors2 = (ArrayList<Armor>) armor3.loadArmors("storeFilesTestsEmpty");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void loadArmorsTest() {
        ArrayList<Armor> armors1HaveTo = new ArrayList<>();
        for(int i = 0; i==2; i++){
            String linea = "Armor"+ "i " +"i " + "i";
            String [] var = linea.split(" ");
            Armor armor = new Armor(var);
            armors1HaveTo.add(armor);
        }
        assertEquals(armors1HaveTo.size(), armors1.size());

        ArrayList<Armor> armors2HaveTo = new ArrayList<>();
        assertEquals(armors2HaveTo.size(), armors2.size());

        ArrayList<Armor> armors3HaveTo = new ArrayList<>();
        assertEquals(armors3HaveTo.size(), armors3.size());

        }

    }


