package webServer;

import javax.persistence.EntityManager;

public class App {

    public static void main(String[] args) {

        try {
            Server server = new Server();
            server.start();
            
            EntityManager em = Factory.getInstance().getManager();
            
            
            
            
            
            
            
            
            
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }

    }

}
