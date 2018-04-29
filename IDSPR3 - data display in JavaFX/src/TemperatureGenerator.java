import java.util.Random;

public class TemperatureGenerator extends Thread implements InputSource {

    private static final int maxChange = 4;
    private static final int maxTemp = 1024;

    private Random rand = new Random();
    private double currTemp = 20;

    public double getNextCelsius(){
        boolean incrementing = rand.nextBoolean();
        currTemp += incrementing
                ? rand.nextInt(maxChange) + rand.nextDouble()
                : -(rand.nextInt(maxChange) + rand.nextDouble());
        currTemp = currTemp % maxTemp; //bounds temperature
        return currTemp;
    }
}
