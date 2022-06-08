package LFobj2100eksamen2021V.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    private static int port = 8000;

    private Socket socket;

    private ObjectOutputStream out;

    public Server(Socket socket){
        this.socket = socket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject("Sending R: "+(int)(Math.random()*100));
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Server Launch");
        try {
            ServerSocket server = new ServerSocket(port);

            while (true){
                Socket socket = server.accept();
                new Server(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
