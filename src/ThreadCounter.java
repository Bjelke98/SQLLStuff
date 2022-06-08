import java.util.concurrent.atomic.AtomicInteger;

public class ThreadCounter extends Thread {

//    public static AtomicInteger count = new AtomicInteger(0);

    public static int count = 0;
    private final int ammount;

    ThreadCounter(int ammount){
        this.ammount = ammount;
    }

    @Override
    public void run() {
        for (int i = 0; i < ammount; i++) {
            increment();
        }
        System.out.println(count);
    }

    private void increment(){
        count++;
    }

    public static void main(String[] args) throws InterruptedException {

        try {
            new Thread(()->{
                ThreadCounter t1 = new ThreadCounter(100000000);
                ThreadCounter t2 = new ThreadCounter(100000000);

                t1.start();

                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                t2.start();

            }).start();
        } finally {
            System.out.println(count);
        }

    }
}
