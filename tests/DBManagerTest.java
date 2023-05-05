import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DBManagerTest {

    @BeforeEach
    void setUp() {
        RandomGenerator.initRandomGenerator();
        RandomGenerator.setSeed(1234);
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
        DBManager db = new DBManager();
        String nick = "e";
        String pass = "eqeqeqeq";
        String name = "paco";
        db.addUser(nick,name,pass);
        User u = db.getUser(nick,pass);
        assertTrue(db.hasCharacter(nick));
        Character c = new Character();
        u.setCharacter(c);
        assertFalse(db.hasCharacter(nick));
        u.setCharacter(null);
        assertTrue(db.hasCharacter(nick));
    }

    @Test
    void deletePerson() {
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
        db.addAdmin(nick,name,pass);
        Admin a = db.getAdmin(nick,pass);
        assertEquals(a.getName(),name);
        db.deletePerson(a);
        assertNull(db.getAdmin(nick, pass));
    }

    @Test
    void addRequest() {
    }

    @Test
    void top10() {
        DBManager db = new DBManager();
        ArrayList<ArrayList<String>> ab = new ArrayList<>();
        for(char i='a';i<='g';i++){
            db.addUser(String.valueOf(i),String.valueOf(i),"0000000"+String.valueOf(i));
            User u = db.getUser(String.valueOf(i),"0000000"+String.valueOf(i));
            int gold = RandomGenerator.RANDOM_OBJ.nextInt(2140000000);
            u.setGold(gold);
            ArrayList<String> a = new ArrayList<>();
            a.add(String.valueOf(i));
            a.add(String.valueOf(gold));
            ab.add(a);
        }
        Collections.sort(ab, (d,c) -> Integer.valueOf(c.get(1)) - Integer.valueOf(d.get(1)));
        int j = 0;
        for(User l:db.top10()){
            assertEquals(l.getNick(),ab.get(j++).get(0));
        }
        for(char i='h';i<='z';i++){
            db.addUser(String.valueOf(i),String.valueOf(i),"0000000"+String.valueOf(i));
            User u = db.getUser(String.valueOf(i),"0000000"+String.valueOf(i));
            int gold = RandomGenerator.RANDOM_OBJ.nextInt(2140000000);
            u.setGold(gold);
            ArrayList<String> a = new ArrayList<>();
            a.add(String.valueOf(i));
            a.add(String.valueOf(gold));
            ab.add(a);
        }
        Collections.sort(ab, (d,c) -> Integer.valueOf(c.get(1)) - Integer.valueOf(d.get(1)));
        j = 0;
        for(User l:db.top10()){
            assertEquals(l.getNick(),ab.get(j++).get(0));
        }
    }

    @Test
    void getUserByNick() {
        DBManager db = new DBManager();
        String nick = "e";
        String pass = "eqeqeqeq";
        String name = "paco";
        assertNull(db.getUserByNick(nick));
        db.addUser(nick,name,pass);
        User u = db.getUserByNick(nick);
        assertEquals(u.getName(),name);
        db.deletePerson(u);
        assertNull(db.getUserByNick(nick));
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