import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HunterTest {
    @BeforeEach
    void setUp() {
    }

    @Test
    void readLineTest() {
        Hunter hunter = new Hunter();
        String line = "name:Hunter";
        String[] var = line.split(":");
        hunter.readLine(var);
        assertEquals("Hunter", hunter.getName());

        line = "life:5";
        var = line.split(":");
        hunter.readLine(var);
        assertEquals(5, hunter.getLife());

        line = "description:Le encanta asesinar";
        var = line.split(":");
        hunter.readLine(var);
        assertEquals("Le encanta asesinar", hunter.getDescription());

        line = "power:5";
        var = line.split(":");
        hunter.readLine(var);
        assertEquals(5, hunter.getPower());

        line = "willpower:3";
        var = line.split(":");
        hunter.readLine(var);
        assertEquals(3, hunter.getWillpower());

        line = "weapon:Pistol1";
        var = line.split(":");
        hunter.readLine(var);
        assertEquals("Pistol1", hunter.getWeapons().get(hunter.getWeapons().size()-1));

        line = "weapon:Shotgun1";
        var = line.split(":");
        hunter.readLine(var);
        assertEquals("Shotgun1", hunter.getWeapons().get(hunter.getWeapons().size()-1));

        line = "weapon:Shotgun2";
        var = line.split(":");
        hunter.readLine(var);
        assertEquals("Shotgun2", hunter.getWeapons().get(hunter.getWeapons().size()-1));

        line = "weapon:Shotgun3";
        var = line.split(":");
        hunter.readLine(var);
        assertEquals("Shotgun3", hunter.getWeapons().get(hunter.getWeapons().size()-1));

        line = "armor:Armor1";
        var = line.split(":");
        hunter.readLine(var);
        assertEquals("Armor1", hunter.getArmors().get(hunter.getArmors().size()-1));

        line = "armor:Armor2";
        var = line.split(":");
        hunter.readLine(var);
        assertEquals("Armor2", hunter.getArmors().get(hunter.getArmors().size()-1));

        line = "armor:Armor3";
        var = line.split(":");
        hunter.readLine(var);
        assertEquals("Armor3", hunter.getArmors().get(hunter.getArmors().size()-1));

        line = "armor:Armor4";
        var = line.split(":");
        hunter.readLine(var);
        assertEquals("Armor4", hunter.getArmors().get(hunter.getArmors().size()-1));

        line = "skill:Talent";
        var = line.split(":");
        hunter.readLine(var);
        assertEquals("Talent", hunter.getSkill());

        line = "minion:Demon";
        var = line.split(":");
        hunter.readLine(var);
        assertEquals("Demon", hunter.getMinions().get(hunter.getMinions().size()-1));

        line = "minion:Ghoul";
        var = line.split(":");
        hunter.readLine(var);
        assertEquals("Ghoul", hunter.getMinions().get(hunter.getMinions().size()-1));

        line = "minion:Human";
        var = line.split(":");
        hunter.readLine(var);
        assertEquals("Human", hunter.getMinions().get(hunter.getMinions().size()-1));

        line = "modifier:Licantropos";
        var = line.split(":");
        hunter.readLine(var);
        assertEquals("Licantropos", hunter.getModifiers().get(hunter.getModifiers().size()-1));

        line = "modifier:MalaVision";
        var = line.split(":");
        hunter.readLine(var);
        assertEquals("MalaVision", hunter.getModifiers().get(hunter.getModifiers().size()-1));

    }

    @Test
    void addMinionTest() {
        Hunter hunter = new Hunter();
        hunter.addMinion("Demon");
        assertEquals(1, hunter.getMinions().size());
        hunter.addMinion("Human");
        assertEquals(2, hunter.getMinions().size());
        hunter.addMinion("Ghoul");
        assertEquals(3, hunter.getMinions().size());
        hunter.addMinion("Ghoul");
        assertEquals(4, hunter.getMinions().size());
    }

    @Test
    void lessWillpowerTest() {
        Hunter hunter = new Hunter();
        hunter.setWillpower(3);
        assertEquals(3, hunter.getWillpower());

        hunter.lessWillpower(3);
        assertEquals(0, hunter.getWillpower());

        //valor menor que 4
        hunter.lessWillpower(5);
        assertEquals(0, hunter.getWillpower());
    }
}