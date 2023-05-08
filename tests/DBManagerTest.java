import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DBManagerTest {

    @BeforeAll
    static void setUp() {
        RandomGenerator.initRandomGenerator();
        RandomGenerator.setSeed(1234);
    }

    @Test
    void lastRequestMoreThanADayAgoTest() {
        DBManager db = new DBManager();
        assertTrue(db.lastRequestMoreThanADayAgo("0","1"));
        assertTrue(db.lastRequestMoreThanADayAgo("1","0"));
        db.addLastRequestDate("0","1");
        assertFalse(db.lastRequestMoreThanADayAgo("0","1"));
        assertTrue(db.lastRequestMoreThanADayAgo("1","0"));
    }

    @Test
    void addLastRequestDateTest() {
        DBManager db = new DBManager();
        assertTrue(db.lastRequestMoreThanADayAgo("0","1"));
        assertTrue(db.lastRequestMoreThanADayAgo("1","0"));
        db.addLastRequestDate("0","1");
        assertFalse(db.lastRequestMoreThanADayAgo("0","1"));
        assertTrue(db.lastRequestMoreThanADayAgo("1","0"));
    }

    @Test
    void getUserTest() {
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
    void getAdminTest() {
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
    void inDataBaseTest() {
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
    void existingUserTest() {
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
    void adminPasswordCheckTest() {
        DBManager db = new DBManager();
        assertTrue(db.adminPasswordCheck("80085"));
        assertFalse(db.adminPasswordCheck("rjhgasbnbge"));
        assertFalse(db.adminPasswordCheck("80084"));
        assertFalse(db.adminPasswordCheck(""));
        assertFalse(db.adminPasswordCheck("wrteyhdjhfsgdafaghjdgnxbzvfhsjgnbzv<sfagshjxnbzvfGAHSFNXVBZV<FGAhsfxnbzvg\nregfsf"));
    }

    @Test
    void addUserTest() {
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
    void addAdminTest() {
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
    void hasCharacterTest() {
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
    void deletePersonTest() {
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
    void addRequestTest() {
        DBManager db = new DBManager();
        String nick = "e";
        String pass = "eqeqeqeq";
        String name = "paco";
        char c = 'a';
        for(int i=0;i<25;i++){
            db.addRequest(String.valueOf(c),String.valueOf(++c),i);
        }
        c = 'a';
        for(int i=0;i<25;i++){
            assertEquals(db.getRequests().get(i).get(0),String.valueOf(c++));
            assertEquals(db.getRequests().get(i).get(1),String.valueOf(c));
            assertEquals(db.getRequests().get(i).get(2),String.valueOf(i));
        }
    }

    @Test
    void top10Test() {
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
    void getUserByNickTest() {
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
    void getBannedUsersTest() {
        DBManager db = new DBManager();
        String nick = "e";
        String pass = "eqeqeqeq";
        String name = "paco";
        db.addUser(nick,name,pass);
        User u = db.getUser(nick,pass);
        u.setBan(true);
        for(User i:db.getBannedUsers()){
            assertEquals(i.getNick(),nick);
        }
        u.setBan(false);
        for(User i:db.getBannedUsers()){
            assertEquals(i.getNick(),nick);
        }
    }

    @Test
    void getRequestsTest() {
        DBManager db = new DBManager();
        String nick = "e";
        String pass = "eqeqeqeq";
        String name = "paco";
        char c = 'a';
        for(int i=0;i<25;i++){
            db.addRequest(String.valueOf(c),String.valueOf(++c),i);
        }
        c = 'a';
        for(int i=0;i<25;i++){
            assertEquals(db.getRequests().get(i).get(0),String.valueOf(c++));
            assertEquals(db.getRequests().get(i).get(1),String.valueOf(c));
            assertEquals(db.getRequests().get(i).get(2),String.valueOf(i));
        }
    }
}