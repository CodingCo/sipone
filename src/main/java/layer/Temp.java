package layer;

import javax.persistence.*;


public class Temp {

    public static void main(String[] args) {
        
        System.out.println("----------------------> Project is running!");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sipPU"); 
        EntityManager em = emf.createEntityManager();
        System.out.println("----------------------> Project is running indeed!");
        
        
        
    }
    
}
