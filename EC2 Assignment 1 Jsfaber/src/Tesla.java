import javafx.scene.paint.Color;

public class Tesla implements Car{

    boolean running = false;
    String licensePlate = "ABC123";
    Engine engine = new TeslaEngine();
    Color carColor = Color.RED;
    private double posY = 0.0;


    public Tesla(Color customColor, String licensePlate) {
        carColor = customColor;
        this.licensePlate = licensePlate;
    }

    public Tesla() {
        //Default constructor...
    }

    @Override
    public boolean isStarted() {
        return running;
    }

    @Override
    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    public Color getColor() {
        return carColor;
    }

    @Override
    public boolean start() {
        if(running == false) {
            engine.start();
            running = true;
            return true;
        }
        else return false;
    }

    @Override
    public boolean stop() {
        if(running == true) {
            engine.stop();
            running = false;
            return true;
        }
        else return false;
    }

    @Override
    public double steerLeft() { return -90;}

    @Override
    public double steerRight() { return 90; }

    @Override
    public double driveForward(int power) throws IsNotStartedException {
        if(running == false) {
            throw new IsNotStartedException("Car has not been started");
        }
        else {
            posY += power;
            return posY;
        }
    }

    @Override
    public double driveBackwards(int power) throws IsNotStartedException{
        if(running == false) {
            throw new IsNotStartedException("Car has not been started");
        }
        else {
            posY -= power;
            return posY;
        }
    }
}
