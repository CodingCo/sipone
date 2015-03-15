package webServer;

import com.google.gson.Gson;
import facades.StudentFacade;
import facades.SubjectFacade;
import facades.SubjectVoteFacade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import model.ElectiveCourse;
import model.Student;
import model.SubjectVote;
import webInterface.StudentIF;
import webInterface.SubjectFacadeIF;
import webInterface.SubjectVoteFacadeIF;

public class ServerExecutor {

    static BufferedReader input;
    static Server webServer;

    public static void main(String[] args) {
        try {
            SubjectFacadeIF subjectFacade = new SubjectFacade(new Gson());
            StudentIF studentFacade = new StudentFacade(new Gson());
            SubjectVoteFacadeIF subjectVoteFacade = new SubjectVoteFacade(new Gson());
            webServer = new Server();
            webServer.start();
            
            // ------------------------------------------------------------------------
            System.out.println("Entering area 51");
            
            
            
            
            
            
            
            System.out.println("Leaving area 51");
            // ------------------------------------------------------------------------
            

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
