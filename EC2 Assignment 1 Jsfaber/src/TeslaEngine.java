public class TeslaEngine implements Engine {

    boolean started = false;
    private enum Direction {FORWARD, BACKWARD}
    private Direction directionFB = Direction.FORWARD;

    @Override
    public boolean start() {
        if(started == false){
            started = true;
            return true;
        }
        else return false;
    }

    @Override
    public boolean stop() {
        if(started == true) {
            started = false;
            return true;
        }
        else return false;
    }

    @Override
    public boolean backwards() {
        if(directionFB != Direction.BACKWARD) {
            directionFB = Direction.BACKWARD;
            return true;
        }
        else return false;
    }

    @Override
    public boolean forward() {
        if(directionFB != Direction.FORWARD) {
            directionFB = Direction.FORWARD;
            return true;
        }
        else return false;
    }

    @Override
    public double drive(int power) {
        return ((power * 2) + 1);
    }
}
