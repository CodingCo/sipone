package facades;

import com.google.gson.Gson;
import java.util.List;
import javax.persistence.EntityManager;
import model.SubjectVote;
import webInterface.SubjectVoteIF;
import webServer.Factory;

public class SubjectVoteFacade implements SubjectVoteIF {

    private Gson gson;

    public SubjectVoteFacade(Gson gson) {
        this.gson = gson;
    }
    
    @Override
    public String getSubjectVotes() {
        EntityManager em = Factory.getInstance().getManager();
        List<SubjectVote> list = em.createQuery("SELECT s FROM SubjectVote s").getResultList();
        em.close();
        return gson.toJson(list);
    }

    @Override
    public String submitSubjectVote(String subjectVoteAsJson) {
        EntityManager em = Factory.getInstance().getManager();
        SubjectVote subjectVote = gson.fromJson(subjectVoteAsJson, SubjectVote.class);
        
        try {
            em.getTransaction().begin();
            em.persist(subjectVote);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Exception was thrown");
            return "{\n"
                    + "  err: true,\n"
                    + "  title: “SubjectVote already exists!”\n"
                    + "}";
        } finally {
            em.close();
        }
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
