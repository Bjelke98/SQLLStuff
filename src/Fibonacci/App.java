package Fibonacci;

public class App {
    public static FNumber[] fNumbers = new FNumber[10];
    public static int fPosition = 0;
    public static void main(String[] args) {
        fNumbers[0] = new FNumber(0);
        fNumbers[1] = new FNumber(1);

        Consumer consumer = new Consumer();
        Producer producer = new Producer(consumer);

        consumer.start();
        producer.start();
    }
    public static int getNextPosition(){
        if(fPosition>=9){
            return 0;
        }
        return fPosition+1;
    }
    public static int getNextInputPosition(){
        if(fPosition==8){
            return 0;
        }
        if(fPosition==9){
            return 1;
        }
        return fPosition+2;
    }
    public static void nextPosition(){
        if(fPosition>=9){
            fPosition=0;
        } else {
            fPosition++;
        }

    }
}
