package LFobj2100eksamen2021V;

import LFobj2100eksamen2021V.client.App;
import LFobj2100eksamen2021V.server.Server;

public class LaunchBoth {
    public static void main(String[] args) {

        new Thread(() -> Server.main(args)).start();
        new Thread(() -> App.main(args)).start();

    }
}
