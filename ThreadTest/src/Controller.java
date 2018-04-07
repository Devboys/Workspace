import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    Label modeLabel;
    @FXML
    Canvas canvas;

    counterThread threadA;

    Object lock1;

    public void step(){
        synchronized (lock1){
            lock1.notify();
            System.out.println(threadA.getCount());
        }
    }

    public void start(){
        if(threadA == null) {
            lock1 = new Object();
            threadA = new counterThread(lock1);
            threadA.start();
        }
    }

    public void stop(){
        if(threadA != null) {
            threadA.interrupt();
        }
    }
}
