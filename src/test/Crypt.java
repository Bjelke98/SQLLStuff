package test;

public class Crypt {
    public static void main(String[] args) {

        encrypt("Hei", 3);

        decrypt("HeiHeiHei", 3);

    }

    private static void encrypt(String input, int algo){

        for (int i = 0; i < algo; i++) {
            System.out.print(input);
        }
        System.out.println();

    }

    private static void decrypt(String cryptInput, int algo){
        System.out.println(cryptInput.substring(0, cryptInput.length()/algo));
    }
}
