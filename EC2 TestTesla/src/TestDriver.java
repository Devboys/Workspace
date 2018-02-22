public class TestDriver {
    Car testCar = new Tesla();

    public void TestDriver() {
        //replace this with unit tests
        System.out.println("Starting car...");
        System.out.println(testCar.start());
        System.out.println("Driving forward");
        System.out.println(testCar.driveForward());
    }

}
