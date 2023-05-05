import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class MinionTest {

    @BeforeEach
    void setUp() {
    }


    @Test
    void laodMinionGhoulTest() {
        Ghoul ghoul = new Ghoul();
        ghoul.setDependency(3);
        ghoul.setName("Ghoul");
        ghoul.setLife(3);

        Ghoul ghoulAux = new Ghoul();
        try {
            ghoulAux.laodMinion("tests/storeFilesTests", "Ghoul");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        assertEquals(ghoul.getDependency(),ghoulAux.getDependency());
        assertEquals(ghoul.getLife(),ghoulAux.getLife());
        assertEquals(ghoul.getName(),ghoulAux.getName());

        Ghoul ghoulAuxNotFound = new Ghoul();
        try {
            ghoulAuxNotFound.laodMinion("tests", "Ghoul");
        } catch (FileNotFoundException e) {
        }
        assertEquals(0,ghoulAuxNotFound.getDependency());
        assertEquals(0,ghoulAuxNotFound.getLife());
        assertNull(ghoulAuxNotFound.getName());
    }

    @Test
    void laodDemonTest() {
            Demon demon = new Demon();
            demon.setName("Demon");
            demon.setOath("Oath");
            demon.setLife(3);

            Demon demonAux = new Demon();
            try {
                demonAux.laodMinion("tests/storeFilesTests", "Demon");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            assertEquals(demon.getOath(),demonAux.getOath());
            assertEquals(demon.getLife(),demonAux.getLife());
            assertEquals(demon.getName(),demonAux.getName());

            Demon demonAuxNotFound = new Demon();
            try {
                demonAuxNotFound.laodMinion("tests", "Demon");
            } catch (FileNotFoundException e) {
            }
            assertEquals(0,demonAuxNotFound.getLife());
            assertNull(demonAuxNotFound.getName());
            assertNull(demonAuxNotFound.getOath());

    }

    @Test
    void laodMinionHumanTest() {
        Human human = new Human();
        human.setName("Human");
        human.setLife(3);
        human.setLoyalty("ALTA");

        Human humanAux = new Human();
        try {
            humanAux.laodMinion("tests/storeFilesTests", "Human");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        assertEquals(human.getLoyalty(),humanAux.getLoyalty());
        assertEquals(human.getLife(),humanAux.getLife());
        assertEquals(human.getName(),humanAux.getName());

        Human humanAuxNotFound = new Human();
        try {
            humanAuxNotFound.laodMinion("tests", "Human");
        } catch (FileNotFoundException e) {
        }
        assertEquals(0,humanAuxNotFound.getLife());
        assertNull(humanAuxNotFound.getName());
        assertNull(humanAuxNotFound.getLoyalty());
    }
}