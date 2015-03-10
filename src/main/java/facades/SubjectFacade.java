package facades;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import model.ElectiveCourse;
import webInterface.SubjectFacadeIF;

public class SubjectFacade implements SubjectFacadeIF {

    private Gson gson;
    private EntityManager em;

    public SubjectFacade(Gson gson, EntityManager em) {
        this.gson = gson;
        this.em = em;
    }

    @Override
    public String getFirstElectiveSubjects() {
        List<ElectiveCourse> course = em.createQuery("SELECT e FROM ElectiveCourse e").getResultList();
        List<ElectiveCourse> courseToReturn = new ArrayList();

        // TODO: Check for iterator, it gave me exceptions, not allowed
        for (int i = 0; i < course.size(); i++) {
            if (course.get(i).getRound() == 1) {
                courseToReturn.add(course.get(i));
            }
        }

        return gson.toJson(courseToReturn);
    }

    @Override
    public String submittedFirstElectiveSubjects(String subjectAsJson) {
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
        }

        return gson.toJson(courseToCreate);
    }
}
