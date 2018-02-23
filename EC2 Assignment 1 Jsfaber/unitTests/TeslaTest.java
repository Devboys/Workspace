import javafx.scene.paint.Color;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeslaTest {

    private Tesla testla;

    @Before
    public void setUp() throws Exception {
        testla = new Tesla();
    }

    @Test
    public void start() {
        assertEquals(true, testla.start());
        assertEquals(false, testla.start());
    }

    @Test
    public void stop() {
        assertEquals(false, testla.stop());

        testla.start();
        assertEquals(true, testla.stop());
    }

    @Test
    public void steerLeft() {
        assertEquals(-90, testla.steerLeft(), 0.001);
    }

    @Test
    public void steerRight() {
        assertEquals(90, testla.steerRight(), 0.001);
    }

    @Test
    public void driveForwardCarStarted() {
        int powerInput = 5;

        testla.start();
        try {
            Assert.assertEquals(5.0, testla.driveForward(powerInput), 0.001);
        }catch(IsNotStartedException e){}
    }

    @Test
    public void driveBackwardsCarStarted() {
        int powerInput = 5;

        testla.start();
        try {
            Assert.assertEquals(-5.0, testla.driveBackwards(powerInput), 0.001);
        }catch(IsNotStartedException e){}

    }

    @Test
    public void driveForwardsCarNotStarted() {
        int powerInput = 5;

        try{
            testla.driveForward(powerInput);

            fail("Exception expected, but not thrown");
        }catch(IsNotStartedException e){}
    }

    @Test
    public void driveBackwardsCarNotStarted() {
        int powerInput = 5;

        try{
            testla.driveBackwards(powerInput);

            fail("Exception expected, but not thrown");
        }catch(IsNotStartedException e){}
    }

    @Test
    public void colorSpecifiedInConstructor(){
        Color customColor = Color.BEIGE;

        Tesla coloredTesla = new Tesla(customColor, "ABC123");
        Assert.assertEquals(customColor, coloredTesla.getColor());
    }

    @Test
    public void licenseplateSpecifiedInConstructor(){
        String customLP = "CustomPlate";

        Tesla coloredTesla = new Tesla(Color.RED, customLP);
        Assert.assertEquals(customLP, coloredTesla.getLicensePlate());
    }

    @Test
    public void testGetterStart() {
        testla.start();
        Assert.assertEquals(true, testla.isStarted());
    }

}