
public class counterThread extends Thread{

    int count;

    private Object lock1;

    public counterThread(Object lock1){

        this.lock1 = lock1;
        count = 0;
    }

    public void run(){
        boolean running = true;
        try {
            while (running) {
                synchronized (lock1) {
                    doCount();

                    if (count >= 100) {
                        running = false;
                    }
                    lock1.wait();
                }
            }
        } catch (InterruptedException e){ e.printStackTrace(); }
        System.out.println("heyo");
    }

    private void doCount(){
        count++;
    }
    public int getCount(){ return count; }
}
