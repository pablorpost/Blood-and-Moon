import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {

    @Test
    void battleExecuteTest() {
        RandomGenerator.initRandomGenerator();
        RandomGenerator.setSeed(1234);
        Store store = new Store();
        store.loadStore("tests/storeFilesTests");
        Character charac = new Lycanthrope("tests/storeFilesTests");
        User user1 = new User("test","test",1234);
        user1.setCharacter(charac);
        user1.setArmor(store.getArmors().get(0));
        String weapon = store.getWeapons().get(0);
        user1.addWeapon(weapon);

        Character charac2 = new Lycanthrope("tests/storeFilesTests");
        User user2 = new User("test2","test2",1234);
        user2.setCharacter(charac2);
        user2.setArmor(store.getArmors().get(1));
        String weapon2 = store.getWeapons().get(1);
        user2.addWeapon(weapon2);

        Battle battle = new Battle();
        Modifier modifier = store.getModifiers().get(0);


        assertEquals(18,battle.battleExecute(user1,user2,store,modifier,true,charac,charac2,false));
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