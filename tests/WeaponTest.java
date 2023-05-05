import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    private ArrayList<Weapon> weaponsOriginal;

    @BeforeEach
    void setUp() {
        weaponsOriginal = new ArrayList<>();

    }

    @Test
    void loadWeaponsCorrectTest() {
        String linea = "Pistol1 1 1 10";
        String[] var = linea.split(" ");
        Weapon weapon = new Weapon(var);
        weaponsOriginal.add(weapon);
        linea = "Pistol2 2 2 5";
        var = linea.split(" ");
        weapon = new Weapon(var);
        weaponsOriginal.add(weapon);
        linea = "Pistol3 3 2 5";
        var = linea.split(" ");
        weapon = new Weapon(var);
        weaponsOriginal.add(weapon);

        Weapon weapon1 = new Weapon();
        ArrayList<Weapon> weaponsComplete = new ArrayList<>();
        try {
        weaponsComplete = (ArrayList<Weapon>) weapon1.loadWeapon("tests/storeFilesTests");
        } catch(FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        assertEquals(weaponsOriginal.size(),weaponsComplete.size());
}


    @Test
    void loadWeaponNoFileTest() {
        Weapon weapon1 = new Weapon();
        ArrayList<Weapon> weaponsEmpty = new ArrayList<>();
        try {
            weaponsEmpty = (ArrayList<Weapon>) weapon1.loadWeapon("Files");
        } catch (FileNotFoundException e) {

        }
        assertEquals(0, weaponsEmpty.size());
    }


}


