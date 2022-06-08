package Fibonacci;

public class Producer extends Thread{
    Consumer consumer;
    public Producer(Consumer consumer){
        this.consumer = consumer;
    }
    @Override
    public void run() {
        while (consumer.isAlive()){
            int number = App.fNumbers[App.fPosition].getNumber()+
                    App.fNumbers[App.getNextPosition()].getNumber();
            App.fNumbers[App.getNextInputPosition()] = new FNumber(number);
            App.nextPosition();
            try {
                synchronized (consumer){
                    consumer.notify();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
