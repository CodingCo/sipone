package webServer;

import com.google.gson.Gson;
import facades.SubjectFacade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import model.ElectiveCourse;
import webInterface.SubjectFacadeIF;

public class ServerExecutor {

    static BufferedReader input;
    static Server webServer;

    public static void main(String[] args) {
        try {
            EntityManager em = Factory.getInstance().getManager(); // manager
            SubjectFacadeIF facade = new SubjectFacade(new Gson(), em);
            webServer = new Server(facade);
            webServer.start();

            

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
