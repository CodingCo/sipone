package facades;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import model.ElectiveCourse;
import webInterface.SubjectFacadeIF;
import webServer.Factory;

public class SubjectFacade implements SubjectFacadeIF {

    private Gson gson;

    public SubjectFacade(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String getFirstElectiveSubjects() {
        EntityManager em = Factory.getInstance().getManager();
        List<ElectiveCourse> course = em.createQuery("SELECT e FROM ElectiveCourse e").getResultList();
        List<ElectiveCourse> courseToReturn = new ArrayList();

        // TODO: Check for iterator, it gave me exceptions, not allowed
        for (int i = 0; i < course.size(); i++) {
            if (course.get(i).getRound() == 1) {
                courseToReturn.add(course.get(i));
            }
        }
        em.close();
        return gson.toJson(courseToReturn);
    }

    @Override
    public String submittedFirstElectiveSubjects(String subjectAsJson) {
        EntityManager em = Factory.getInstance().getManager();
        ElectiveCourse courseToCreate = gson.fromJson(subjectAsJson, ElectiveCourse.class);
        
        
        try {
            em.getTransaction().begin();
            em.persist(courseToCreate);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Exception was thrown");
            return "{\n"
                    + "  err: true,\n"
                    + "  title: “Elective course already exists!”\n"
                    + "}";
        }finally{
            em.close();
        }

        return gson.toJson(courseToCreate);
    }
}
