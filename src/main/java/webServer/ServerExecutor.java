package webServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class ServerExecutor {

    static BufferedReader input;
    static Server webServer;

    public static void main(String[] args) {
        try {
            webServer = new Server();
            webServer.start();
            
            //
            EntityManager manager = Factory.getInstance().getManager();

            serverCommands();
        } catch (IOException ex) {
            Logger.getLogger(ServerExecutor.class.getName()).log(Level.SEVERE, ex.toString());
        }
    }

    public static void serverCommands() {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        try {
            String command = r.readLine();
            do {
                switchCommands(command);
            } while (!(command = r.readLine()).contains("killall"));
            r.close();
        } catch (IOException e) {

        }
        System.exit(0);
        System.out.println("server shutdown");
    }

    private static void switchCommands(String command) throws IOException {

        switch (command) {
            case "stop":
                webServer.closeHttpServer();
                System.exit(0);
                break;
            default:
                if (!command.equals("killall")) {
                    System.out.println("unknow command");
                }
        }
    }
}
