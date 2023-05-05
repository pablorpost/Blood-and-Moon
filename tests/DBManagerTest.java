import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBManagerTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void lastRequestMoreThanADayAgo() {

    }

    @Test
    void addLastRequestDate() {


    }

    @Test
    void getUser() {
        DBManager db = new DBManager();
        String nick = "e";
        String pass = "eqeqeqeq";
        String name = "paco";
        assertNull(db.getUser(nick, pass));
        db.addUser(nick,name,pass);
        User u = db.getUser(nick,pass);
        assertEquals(u.getName(),name);
        db.deletePerson(u);
        assertNull(db.getUser(nick, pass));
    }

    @Test
    void getAdmin() {
        DBManager db = new DBManager();
        String nick = "e";
        String pass = "eqeqeqeq";
        String name = "paco";
        assertNull(db.getAdmin(nick, pass));
        db.addAdmin(nick,name,pass);
        Admin u = db.getAdmin(nick,pass);
        assertEquals(u.getName(),name);
        db.deletePerson(u);
        assertNull(db.getAdmin(nick, pass));
    }

    @Test
    void inDataBase() {
    }

    @Test
    void existingUser() {
    }

    @Test
    void loadDataBase() {
    }

    @Test
    void adminPasswordCheck() {
        DBManager db = new DBManager();
        assertTrue(db.adminPasswordCheck("80085"));
        assertFalse(db.adminPasswordCheck("rjhgasbnbge"));
        assertFalse(db.adminPasswordCheck("80084"));
    }

    @Test
    void addUser() {
    }

    @Test
    void addAdmin() {
    }

    @Test
    void save() {
    }

    @Test
    void hasCharacter() {
    }

    @Test
    void deletePerson() {
    }

    @Test
    void addRequest() {
    }

    @Test
    void top10() {
    }

    @Test
    void getUserByNick() {
    }

    @Test
    void getBannedUsers() {
    }

    @Test
    void getRequests() {
    }

    @Test
    void addBattleToList() {
    }
}