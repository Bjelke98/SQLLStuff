package Fibonacci;

public class Consumer extends Thread{
    @Override
    public void run() {
        while (App.fNumbers[App.getNextPosition()].getNumber()<10000){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(App.fNumbers[App.getNextPosition()]);
        }
    }
}
