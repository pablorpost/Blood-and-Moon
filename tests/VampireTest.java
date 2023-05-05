import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VampireTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void readLine() {
        Vampire vampire = new Vampire();
        String line = "name:Vampire";
        String[] var = line.split(":");
        vampire.readLine(var);
        assertEquals("Vampire", vampire.getName());

        line = "description:Le encanta beber sangre";
        var = line.split(":");
        vampire.readLine(var);
        assertEquals("Le encanta beber sangre", vampire.getDescription());

        line = "age:25";
        var = line.split(":");
        vampire.readLine(var);
        assertEquals(25, vampire.getAge());

        line = "power:4";
        var = line.split(":");
        vampire.readLine(var);
        assertEquals(4, vampire.getPower());

        line = "blood:10";
        var = line.split(":");
        vampire.readLine(var);
        assertEquals(10, vampire.getBlood());

        line = "weapon:Pistol1";
        var = line.split(":");
        vampire.readLine(var);
        assertEquals("Pistol1", vampire.getWeapons().get(vampire.getWeapons().size()-1));

        line = "weapon:Pistol2";
        var = line.split(":");
        vampire.readLine(var);
        assertEquals("Pistol2", vampire.getWeapons().get(vampire.getWeapons().size()-1));

        line = "armor:Armor1";
        var = line.split(":");
        vampire.readLine(var);
        assertEquals("Armor1", vampire.getArmors().get(vampire.getArmors().size()-1));

        line = "armor:Armor2";
        var = line.split(":");
        vampire.readLine(var);
        assertEquals("Armor2", vampire.getArmors().get(vampire.getArmors().size()-1));

        line = "skill:Disciplines";
        var = line.split(":");
        vampire.readLine(var);
        assertEquals("Disciplines", vampire.getSkill());

        line = "minion:Demon";
        var = line.split(":");
        vampire.readLine(var);
        assertEquals("Demon", vampire.getMinions().get(vampire.getMinions().size()-1));

        line = "modifier:SangreCerca";
        var = line.split(":");
        vampire.readLine(var);
        assertEquals("SangreCerca", vampire.getModifiers().get(vampire.getModifiers().size()-1));

    }

    @Test
    void addMinion() {
        Vampire vampire = new Vampire();
        vampire.addMinion("Demon");
        assertEquals(1, vampire.getMinions().size());
        //Comprueba que un Vampiro no pueda tener a un humano de esbirro
        vampire.addMinion("Human");
        assertEquals(1, vampire.getMinions().size());
        vampire.addMinion("Ghoul");
        assertEquals(2, vampire.getMinions().size());
        vampire.addMinion("Ghoul");
        assertEquals(3, vampire.getMinions().size());


    }

    @Test
    void addBlood() {
        Vampire vampire = new Vampire();
        vampire.addBlood(0);
        assertEquals(0, vampire.getBlood());

        vampire.addBlood(3);
        assertEquals(3, vampire.getBlood());

        //valor menor que 4
        vampire.addBlood(1);
        assertEquals(3, vampire.getBlood());
    }
}