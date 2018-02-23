import org.junit.Test;

import static org.junit.Assert.*;

public class DriverTest {

    @Test
    public void drive() {
        Driver testDriver = new Driver();
        assertEquals("Im driving", testDriver.drive());
    }
}