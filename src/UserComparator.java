import java.util.Comparator;

public class UserComparator implements Comparator {

    //Establecerá que los usuarios se compararán en base al oro del que dispongan.
    @Override
    public int compare(Object o1, Object o2) {
        User u1 = (User) o1;
        User u2 = (User) o2;
        return u1.getGold() - u2.getGold();
    }
}
