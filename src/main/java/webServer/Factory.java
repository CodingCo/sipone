package webServer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Factory {

    private static Factory instance;
    private EntityManagerFactory emf;
    
    private Factory() {
        createFactory();
    }
    
    public static Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }
    
    private void createFactory(){
        emf = Persistence.createEntityManagerFactory("sipPU");
    }
    
    public EntityManager getManager() {
        return emf.createEntityManager(); 
    }
    
    public void restart(){
        instance = new Factory();
    }
    
}
