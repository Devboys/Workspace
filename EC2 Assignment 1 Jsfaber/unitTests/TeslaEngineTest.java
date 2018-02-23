import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeslaEngineTest {


    TeslaEngine engine;

    //setUp() is run before each test-method call, so we don't have to re-init in every method.
    @Before
    public void setUp() throws Exception {
        //Create a default engine so that we can test its methods.
        engine = new TeslaEngine();
    }

    @Test
    public void start() {
        Assert.assertEquals(true, engine.start());

        //A started engine cannot start again.
        Assert.assertEquals(false, engine.start());
    }

    @Test
    public void stop() {
        //a stopped engine cannot stop.
        Assert.assertEquals(false, engine.stop());

        engine.start();
        Assert.assertEquals(true, engine.stop());
    }

    @Test
    public void backwards() {
        assertEquals(true, engine.backwards());
        assertEquals(false, engine.backwards());
    }

    @Test
    public void forward() {
        assertEquals(false, engine.forward());

        engine.backwards();
        assertEquals(true, engine.forward());
    }

    @Test
    public void drive() {
        int powerInput = 5;
        Assert.assertEquals(powerInput * 2 + 1, engine.drive(powerInput), 0.001);
    }
}