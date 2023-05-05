import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void loadCharacters() {
        Store store = new Store();
        assertEquals(3,store.getChracters().size());
    }

    @Test
    void getInfoWeapon() {
        Store store = new Store("tests/storeFilesTests");

        Weapon weapon1 = new Weapon();
        weapon1 = store.getInfoWeapon("Pistol1");
        assertEquals("Pistol1",weapon1.getName());
        assertEquals(1,weapon1.getHands());
        assertEquals(10,weapon1.getAttack());
        assertEquals(1,weapon1.getDefense());

        weapon1 = store.getInfoWeapon("Pistol2");
        assertEquals("Pistol2",weapon1.getName());
        assertEquals(2,weapon1.getHands());
        assertEquals(5,weapon1.getAttack());
        assertEquals(2,weapon1.getDefense());

        //buscar elemento que no está en la lista
        assertNull(store.getInfoWeapon("Shotgun5"));
    }

    @Test
    void getInfoArmor() {
        Store store = new Store("tests/storeFilesTests");

        Armor armor1 = new Armor();
        armor1 = store.getInfoArmor("Armor1");
        assertEquals("Armor1",armor1.getName());
        assertEquals(1,armor1.getAtack());
        assertEquals(1,armor1.getDefense());

        armor1 = store.getInfoArmor("Armor3");
        assertEquals("Armor3",armor1.getName());
        assertEquals(3,armor1.getAtack());
        assertEquals(3,armor1.getDefense());

        //buscar elemento que no está en la lista
        assertNull(store.getInfoArmor("Armor6"));
    }

    @Test
    void getInfoMinion() {
        Store store = new Store("tests/storeFilesTests");

        Minion minion1 = new Minion();
        minion1 = store.getInfoMinion("Demon");
        assertEquals("Demon",minion1.getName());
        assertEquals(3,minion1.getLife());

        minion1 = store.getInfoMinion("Ghoul");
        assertEquals("Ghoul",minion1.getName());
        assertEquals(3,minion1.getLife());

        //buscar elemento que no está en la lista
        assertNull(store.getInfoMinion("Deemon"));
    }

    @Test
    void getInfoSkill() {
        Store store = new Store("tests/storeFilesTests");

        Skill skill1 = new Skill();
        skill1 = store.getInfoSkill("Gift");
        assertEquals("Gift",skill1.getName());
        assertEquals(2,skill1.getAttackPoints());
        assertEquals(3,skill1.getDefensePoints());

        skill1 = store.getInfoSkill("Talent");
        assertEquals("Talent",skill1.getName());
        assertEquals(2,skill1.getAttackPoints());
        assertEquals(2,skill1.getDefensePoints());

        //buscar elemento que no está en la lista
        assertNull(store.getInfoSkill("giift"));
    }

    @Test
    void loadMinions() {
        Store store = new Store();
        assertEquals(3,store.getChracters().size());
    }
}