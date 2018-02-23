public interface Engine {

    /**
     * starts the engine
     * @return true if it wasn't already started
     */
    boolean start();

    /**
     * stops the engine
     * @return true if it was started
     */
    boolean stop();

    /**
     * set the engine to move backwards
     * @return if engine successfully changed (false if already in backwards)
     */
    boolean backwards();

    /**
     * set the engine to move forward
     * @return if engine successfully changed (false if already in forwards)
     */
    boolean forward();

    /**
     *  Drives the engine
     * @param power the amount of energy you give give the engine. between 0 and 100
     * @return speed in km/h
     */
    double drive(int power);
}