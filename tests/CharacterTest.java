import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void loadCharacterVampireTest() {
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

        Vampire vampireAux = new Vampire();
        try {
            vampireAux.loadCharacter("tests/storeFilesTests", "Vampire");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertEquals(vampire.getLife(),vampireAux.getLife());
        assertEquals(vampire.getPower(),vampireAux.getPower());
        assertEquals(vampire.getBlood(),vampireAux.getBlood());
        assertEquals(vampire.getAge(),vampireAux.getAge());
        assertEquals(vampire.getName(),vampireAux.getName());
        assertEquals(vampire.getDescription(),vampireAux.getDescription());
        assertEquals(vampire.getSkill(),vampireAux.getSkill());
        assertEquals(vampire.getArmors().size(),vampireAux.getArmors().size());
        assertEquals(vampire.getWeapons().size(),vampireAux.getWeapons().size());
        assertEquals(vampire.getMinions().size(),vampireAux.getMinions().size());
        assertEquals(vampire.getModifiers().size(),vampireAux.getModifiers().size());

        Vampire vampireAuxNotFound = new Vampire();
        try {
            vampireAuxNotFound.loadCharacter("tests", "Vampire");
        } catch (FileNotFoundException e) {
        }

        assertNull(vampireAuxNotFound.getName());
        assertNull(vampireAuxNotFound.getDescription());
        assertNull(vampireAuxNotFound.getSkill());

        assertEquals(0,vampireAuxNotFound.getLife());
        assertEquals(0,vampireAuxNotFound.getPower());
        assertEquals(0,vampireAuxNotFound.getBlood());
        assertEquals(0,vampireAuxNotFound.getAge());
        assertEquals(0,vampireAuxNotFound.getArmors().size());
        assertEquals(0,vampireAuxNotFound.getWeapons().size());
        assertEquals(0,vampireAuxNotFound.getMinions().size());
        assertEquals(0,vampireAuxNotFound.getModifiers().size());
    }



    @Test
    void loadCharacterHunterTest() {
        Hunter hunter = new Hunter();
        hunter.setName("Hunter");
        hunter.setLife(5);
        hunter.addMinion("Demon");
        hunter.addMinion("Ghoul");
        hunter.addMinion("Human");
        hunter.setDescription("Le encanta asesinar");
        hunter.addWeapon("Pistol1");
        hunter.addWeapon("Shotgun1");
        hunter.addWeapon("Shotgun2");
        hunter.addWeapon("Shotgun3");
        hunter.addArmor("Armor1");
        hunter.addArmor("Armor2");
        hunter.addArmor("Armor3");
        hunter.addArmor("Armor4");
        hunter.setSkill("Talent");
        hunter.setPower(5);
        hunter.setWillpower(3);
        hunter.addModifier("Licantropos");
        hunter.addModifier("MalaVision");

        Hunter hunterAux = new Hunter();
        try {
            hunterAux.loadCharacter("tests/storeFilesTests", "Hunter");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertEquals(hunter.getLife(),hunterAux.getLife());
        assertEquals(hunter.getPower(),hunterAux.getPower());
        assertEquals(hunter.getWillpower(),hunterAux.getWillpower());
        assertEquals(hunter.getName(),hunterAux.getName());
        assertEquals(hunter.getDescription(),hunterAux.getDescription());
        assertEquals(hunter.getSkill(),hunterAux.getSkill());
        assertEquals(hunter.getArmors().size(),hunterAux.getArmors().size());
        assertEquals(hunter.getWeapons().size(),hunterAux.getWeapons().size());
        assertEquals(hunter.getMinions().size(),hunterAux.getMinions().size());
        assertEquals(hunter.getModifiers().size(),hunterAux.getModifiers().size());

        Hunter hunterAuxNotFound = new Hunter();
        try {
            hunterAuxNotFound.loadCharacter("tests", "Hunter");
        } catch (FileNotFoundException e) {
        }

        assertNull(hunterAuxNotFound.getName());
        assertNull(hunterAuxNotFound.getDescription());
        assertNull(hunterAuxNotFound.getSkill());

        assertEquals(0,hunterAuxNotFound.getLife());
        assertEquals(0,hunterAuxNotFound.getPower());
        assertEquals(0,hunterAuxNotFound.getWillpower());
        assertEquals(0,hunterAuxNotFound.getArmors().size());
        assertEquals(0,hunterAuxNotFound.getWeapons().size());
        assertEquals(0,hunterAuxNotFound.getMinions().size());
        assertEquals(0,hunterAuxNotFound.getModifiers().size());
    }





    @Test
    void loadCharacterLycanthropeTest() {
        Lycanthrope licanthrope = new Lycanthrope();
        licanthrope.setName("Lycanthrope");
        licanthrope.setLife(5);
        licanthrope.addMinion("Demon");
        licanthrope.addMinion("Ghoul");
        licanthrope.addMinion("Human");
        licanthrope.setDescription("Le encanta la luna llena");
        licanthrope.addWeapon("Pistol1");
        licanthrope.addWeapon("Pistol2");
        licanthrope.addWeapon("Shotgun1");
        licanthrope.addWeapon("Shotgun2");
        licanthrope.addArmor("Armor1");
        licanthrope.addArmor("Armor2");
        licanthrope.addArmor("Armor3");
        licanthrope.addArmor("Armor4");
        licanthrope.setSkill("Gift");
        licanthrope.setPower(2);
        licanthrope.setAnger(3);
        licanthrope.addModifier("LunaLlena");
        licanthrope.addModifier("MetalCerca");

        Lycanthrope lycanthropeAux = new Lycanthrope();
        try {
            lycanthropeAux.loadCharacter("tests/storeFilesTests", "Lycanthrope");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertEquals(licanthrope.getLife(),lycanthropeAux.getLife());
        assertEquals(licanthrope.getPower(),lycanthropeAux.getPower());
        assertEquals(licanthrope.getAnger(),lycanthropeAux.getAnger());
        assertEquals(licanthrope.getName(),lycanthropeAux.getName());
        assertEquals(licanthrope.getDescription(),lycanthropeAux.getDescription());
        assertEquals(licanthrope.getSkill(),lycanthropeAux.getSkill());
        assertEquals(licanthrope.getArmors().size(),lycanthropeAux.getArmors().size());
        assertEquals(licanthrope.getWeapons().size(),lycanthropeAux.getWeapons().size());
        assertEquals(licanthrope.getMinions().size(),lycanthropeAux.getMinions().size());
        assertEquals(licanthrope.getModifiers().size(),lycanthropeAux.getModifiers().size());

        Lycanthrope licanthropeAuxNotFound = new Lycanthrope();
        try {
            licanthropeAuxNotFound.loadCharacter("tests", "Lycanthrope");
        } catch (FileNotFoundException e) {
        }

        assertNull(licanthropeAuxNotFound.getName());
        assertNull(licanthropeAuxNotFound.getDescription());
        assertNull(licanthropeAuxNotFound.getSkill());

        assertEquals(0,licanthropeAuxNotFound.getLife());
        assertEquals(0,licanthropeAuxNotFound.getPower());
        assertEquals(0,licanthropeAuxNotFound.getAnger());
        assertEquals(0,licanthropeAuxNotFound.getArmors().size());
        assertEquals(0,licanthropeAuxNotFound.getWeapons().size());
        assertEquals(0,licanthropeAuxNotFound.getMinions().size());
        assertEquals(0,licanthropeAuxNotFound.getModifiers().size());
    }
    @Test
    void addWeapon() {
        Character character = new Character();
        character.addWeapon("Pistola1");
        assertEquals(1,character.getWeapons().size());
        character.addWeapon("Pistola2");
        character.addWeapon("Pistola3");
        assertEquals(3,character.getWeapons().size());
    }

    @Test
    void addArmor() {
        Character character = new Character();
        character.addArmor("Armor1");
        assertEquals(1,character.getArmors().size());
        character.addArmor("Armor2");
        character.addArmor("Armor3");
        assertEquals(3,character.getArmors().size());
    }

    @Test
    void addModifier() {
        Character character = new Character();
        character.addModifier("Luna");
        assertEquals(1,character.getModifiers().size());
        character.addModifier("Luna menguante");
        character.addModifier("Sangre");
        assertEquals(3,character.getModifiers().size());
    }

    @Test
    void addMinion() {
        Character character = new Character();
        character.addMinion("Ghoul");
        assertEquals(1,character.getMinions().size());
        character.addMinion("Ghoul");
        character.addMinion("Demon");
        assertEquals(3,character.getMinions().size());
    }
}