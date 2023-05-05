import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LycanthropeTest {
    @BeforeEach
    void setUp() {
    }

    @Test
    void readLineTest() {
        Lycanthrope lycanthrope = new Lycanthrope();
        String line = "name:Lycanthrope";
        String[] var = line.split(":");
        lycanthrope.readLine(var);
        assertEquals("Lycanthrope", lycanthrope.getName());

        line = "life:5";
        var = line.split(":");
        lycanthrope.readLine(var);
        assertEquals(5, lycanthrope.getLife());

        line = "description:Le encanta la luna llena";
        var = line.split(":");
        lycanthrope.readLine(var);
        assertEquals("Le encanta la luna llena", lycanthrope.getDescription());

        line = "power:2";
        var = line.split(":");
        lycanthrope.readLine(var);
        assertEquals(2, lycanthrope.getPower());

        line = "anger:3";
        var = line.split(":");
        lycanthrope.readLine(var);
        assertEquals(3, lycanthrope.getAnger());

        line = "weapon:Pistol1";
        var = line.split(":");
        lycanthrope.readLine(var);
        assertEquals("Pistol1", lycanthrope.getWeapons().get(lycanthrope.getWeapons().size()-1));

        line = "weapon:Pistol2";
        var = line.split(":");
        lycanthrope.readLine(var);
        assertEquals("Pistol2", lycanthrope.getWeapons().get(lycanthrope.getWeapons().size()-1));

        line = "weapon:Shotgun1";
        var = line.split(":");
        lycanthrope.readLine(var);
        assertEquals("Shotgun1", lycanthrope.getWeapons().get(lycanthrope.getWeapons().size()-1));

        line = "weapon:Shotgun2";
        var = line.split(":");
        lycanthrope.readLine(var);
        assertEquals("Shotgun2", lycanthrope.getWeapons().get(lycanthrope.getWeapons().size()-1));

        line = "armor:Armor1";
        var = line.split(":");
        lycanthrope.readLine(var);
        assertEquals("Armor1", lycanthrope.getArmors().get(lycanthrope.getArmors().size()-1));

        line = "armor:Armor2";
        var = line.split(":");
        lycanthrope.readLine(var);
        assertEquals("Armor2", lycanthrope.getArmors().get(lycanthrope.getArmors().size()-1));

        line = "armor:Armor3";
        var = line.split(":");
        lycanthrope.readLine(var);
        assertEquals("Armor3", lycanthrope.getArmors().get(lycanthrope.getArmors().size()-1));

        line = "armor:Armor4";
        var = line.split(":");
        lycanthrope.readLine(var);
        assertEquals("Armor4", lycanthrope.getArmors().get(lycanthrope.getArmors().size()-1));

        line = "skill:Gift";
        var = line.split(":");
        lycanthrope.readLine(var);
        assertEquals("Gift", lycanthrope.getSkill());

        line = "minion:Demon";
        var = line.split(":");
        lycanthrope.readLine(var);
        assertEquals("Demon", lycanthrope.getMinions().get(lycanthrope.getMinions().size()-1));

        line = "minion:Ghoul";
        var = line.split(":");
        lycanthrope.readLine(var);
        assertEquals("Ghoul", lycanthrope.getMinions().get(lycanthrope.getMinions().size()-1));

        line = "minion:Human";
        var = line.split(":");
        lycanthrope.readLine(var);
        assertEquals("Human", lycanthrope.getMinions().get(lycanthrope.getMinions().size()-1));

        line = "modifier:LunaLlena";
        var = line.split(":");
        lycanthrope.readLine(var);
        assertEquals("LunaLlena", lycanthrope.getModifiers().get(lycanthrope.getModifiers().size()-1));

        line = "modifier:MetalCerca";
        var = line.split(":");
        lycanthrope.readLine(var);
        assertEquals("MetalCerca", lycanthrope.getModifiers().get(lycanthrope.getModifiers().size()-1));

    }

    @Test
    void addMinionTest() {
        Lycanthrope lycanthrope = new Lycanthrope();
        lycanthrope.addMinion("Demon");
        assertEquals(1, lycanthrope.getMinions().size());
        lycanthrope.addMinion("Human");
        assertEquals(2, lycanthrope.getMinions().size());
        lycanthrope.addMinion("Ghoul");
        assertEquals(3, lycanthrope.getMinions().size());
        lycanthrope.addMinion("Ghoul");
        assertEquals(4, lycanthrope.getMinions().size());
    }

    @Test
    void addAngerTest() {
        Lycanthrope lycanthrope = new Lycanthrope();
        lycanthrope.setAnger(0);
        assertEquals(0, lycanthrope.getAnger());

        lycanthrope.addAnger(3);
        assertEquals(3, lycanthrope.getAnger());

        //valor menor que 4
        lycanthrope.addAnger(5);
        assertEquals(3, lycanthrope.getAnger());
    }

}