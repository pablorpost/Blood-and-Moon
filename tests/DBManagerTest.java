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
        DBManager db = new DBManager();
        String nick = "e";
        String nickAd = "ed";
        String pass = "eqeqeqeq";
        String name = "paco";
        assertEquals(DataBaseResult.notFound,db.inDataBase(nick,pass));
        assertEquals(DataBaseResult.notFound,db.inDataBase(nickAd,pass));
        db.addUser(nick,name,pass);
        db.addAdmin(nickAd,name,pass);
        assertEquals(DataBaseResult.user,db.inDataBase(nick,pass));
        assertEquals(DataBaseResult.admin,db.inDataBase(nickAd,pass));
        assertEquals(DataBaseResult.wrongPassword,db.inDataBase(nick,pass+" "));
        assertEquals(DataBaseResult.wrongPassword,db.inDataBase(nickAd,pass+"1"));
        User u = db.getUser(nick,pass);
        db.deletePerson(u);
        assertEquals(DataBaseResult.notFound,db.inDataBase(nick,pass));
        Admin v = db.getAdmin(nickAd,pass);
        db.deletePerson(v);
        assertEquals(DataBaseResult.notFound,db.inDataBase(nickAd,pass));
    }

    @Test
    void existingUser() {
        DBManager db = new DBManager();
        String nick = "e";
        String pass = "eqeqeqeq";
        String name = "paco";
        assertEquals(DataBaseResult.notFound,db.existingUser(nick));
        db.addUser(nick,name,pass);
        assertEquals(DataBaseResult.user,db.existingUser(nick));
        User u = db.getUser(nick,pass);
        db.deletePerson(u);
        assertEquals(DataBaseResult.notFound,db.existingUser(nick));
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
        assertFalse(db.adminPasswordCheck(""));
        assertFalse(db.adminPasswordCheck("wrteyhdjhfsgdafaghjdgnxbzvfhsjgnbzv<sfagshjxnbzvfGAHSFNXVBZV<FGAhsfxnbzvg\nregfsf"));
    }

    @Test
    void addUser() {
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
    void addAdmin() {
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
    void save() {
        //explicar en memoria
        //esta funcion no se puecer una prueba de ella porque el lector dle binario es una funcion privado, y no genero un binario de una manera que sea comprobable
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