import java.io.FileNotFoundException;

public class Human extends Minion{
    private String loyalty;
    // Constructor de humano
    public Human(String directorio){
        super();
        try {
            laodMinion(directorio,"human");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    // Cargar datos del .txt
    @Override
    public void readLine(String[] var) {
        if (null != var[0]) switch (var[0]) {
            case "name":
                setName(var[1]);
                break;
            case "life":
                setLife(Integer.valueOf(var[1]));
                break;
            case "loyalty":
                setLoyalty(var[1]);
                break;
            default:
                break;

        }
    }

    public String getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(String loyalty) {
        this.loyalty = loyalty;
    }
}
