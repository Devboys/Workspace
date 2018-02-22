import javafx.scene.paint.Color;

public class Tesla implements Car{

    boolean running;
    String licensePlate = "ABC123";
    Engine engine = new TeslaEngine();
    Color carColor = Color.RED;
    public double speedX = 0.0;
    public double speedY = 0.0;
    public double posX = 0.0;
    public double posY = 0.0;

    private enum Direction {LEFT, RIGHT, STRAIGHT};
    Direction directionLR = Direction.STRAIGHT;

    public Tesla(Color color) {
        carColor = color;
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
        if(running = false) {
            engine.start();
            running = true;
            return true;
        }
        else return false;
    }

    @Override
    public boolean stop() {
        if(running = true) {
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
            throw new IsNotStartedException();
        }
    }

    @Override
    public double driveBackwards(int power) throws IsNotStartedException{
        if(running == false) {
            throw new IsNotStartedException();
        }
    }
}
