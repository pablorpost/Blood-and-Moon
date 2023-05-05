import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinionTest {

    @BeforeEach
    void setUp() {
    }





    @Test
    void laodMinionVampireTest() {
        Vampire vampire = new Vampire();
        vampire.setName("Vampire");
        vampire.setLife(4);
        vampire.setBlood(10);
        vampire.addMinion("Demon");
        vampire.addMinion("Ghoul");
        vampire.setDescription("Le encanta beber sangre");
        vampire.addWeapon("Pistol1");
        vampire.addWeapon("Pistol2");
        vampire.addArmor("Armor1");
        vampire.addArmor("Armor2");
        vampire.setSkill("Disciplines");
        vampire.setPower(4);
        vampire.setAge(25);
        vampire.addModifier("SangreCerca");

        //Vampire vampire;
    }

    @Test
    void laodMinionHunterTest() {
    }

    @Test
    void laodMinionLycanthtopeTest() {
    }
}