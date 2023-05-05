import java.time.LocalDateTime;
import java.util.Random;
public class RandomGenerator {
    public static Random RANDOM_OBJ;

    public static void initRandomGenerator(){
        RANDOM_OBJ = new Random(System.currentTimeMillis());
    }

    public static void setSeed(long seed){
        RANDOM_OBJ.setSeed(seed);
    }
}
