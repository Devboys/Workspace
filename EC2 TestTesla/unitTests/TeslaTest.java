import javafx.scene.paint.Color;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javafx.*;

import static org.junit.Assert.*;

public class TeslaTest {

    Tesla car;

    @Before
    public void setUp() throws Exception {
        //create a car we can test in the test methods
        car = new Tesla();
    }

    @Test
    public void isStarted()  {
    }

    @Test
    public void getLicensePlate()  {
    }

    @Test
    public void getColor()  {

        Assert.assertEquals(car.getColor(), Color.RED);
    }

    @Test
    public void start()  {
    }

    @Test
    public void stop()  {
    }

    @Test
    public void steerLeft()  {
    }

    @Test
    public void steerRight()  {
    }

    @Test
    public void driveForward()  {
    }

    @Test
    public void driveBackwards()  {
    }

    @Test
    public void colorSpecifiedInConstructor() {
        Tesla specifiedCar = new Tesla(Color.BLUE);
        Assert.assertEquals(specifiedCar.getColor(), Color.RED);
    }

}