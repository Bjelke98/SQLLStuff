package Fibonacci;

import java.text.NumberFormat;
import java.util.Locale;

public class Simple {
    public static void main(String[] args) {
        int alt1 = 0;
        int alt2 = 1;
        printNumber(alt1, 1);
        printNumber(alt2, 2);
        int current = 2;
        boolean sw = true;
        while (alt1<1000000000 && alt2<1000000000){
            int sum = alt1+alt2;
            if(sw){
                alt1=sum;
            } else {
                alt2=sum;
            }
            sw=!sw;
            printNumber(sum, ++current);
        }
    }
    private static void printNumber(int number, int current){
        NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
        System.out.format("%25s", nf.format(number)+"   N: "+(current<10?" ":"")+(current<100?" ":"")+current+"\n");
    }
}
