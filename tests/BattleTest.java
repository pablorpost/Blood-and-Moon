import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {

    @Test
    void battleExecute() {
    }

    @Test
    void printPhrase() {
    }

    @Test
    void printStats() {
    }

    @Test
    void printCalcs() {
    }

    @Test
    void calculateLifeTest() {
        Character charac = new Lycanthrope("tests/storeFilesTests");
        Store store = new Store();
        Battle battle = new Battle();
        charac.addMinion(store.getMinions().get(0));
        assertEquals(17,battle.calculateLife(charac,store));
    }


    @Test
    void getPowerOfAtackTest(){
        RandomGenerator.initRandomGenerator();
        RandomGenerator.setSeed(1234);
        Character charac = new Lycanthrope("tests/storeFilesTests");
        User user1 = new User("test","test",1234);
        user1.setCharacter(charac);
        Store store = new Store();
        user1.setArmor(store.getArmors().get(0));
        String weapon = store.getWeapons().get(0);
        user1.addWeapon(weapon);
        store.loadStore("tests/storeFilesTests");
        Battle battle = new Battle();
        Modifier modifier = store.getModifiers().get(0);
        assertEquals(18,battle.getPowerOfAtack(charac,user1,store,true, modifier));
    }

    @Test
    void getPowerOfDefenseTest() {
        RandomGenerator.initRandomGenerator();
        RandomGenerator.setSeed(1234);
        Character charac = new Lycanthrope("tests/storeFilesTests");
        User user1 = new User("test","test",1234);
        user1.setCharacter(charac);
        Store store = new Store();
        user1.setArmor(store.getArmors().get(0));
        String weapon = store.getWeapons().get(0);
        user1.addWeapon(weapon);
        store.loadStore("tests/storeFilesTests");
        Battle battle = new Battle();
        Modifier modifier = store.getModifiers().get(0);
        assertEquals(10,battle.getPowerOfDefense(charac,user1,store,true));
    }
}