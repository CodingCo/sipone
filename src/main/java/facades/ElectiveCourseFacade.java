package facades;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import model.ElectiveCourse;
import org.omg.CORBA.SystemException;
import webInterface.ElectiveCourseFacadeIF;
import webServer.Factory;

public class ElectiveCourseFacade implements ElectiveCourseFacadeIF {

    private Gson gson;

    public ElectiveCourseFacade(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String getAll() {
        EntityManager em = Factory.getInstance().getManager();
        List<ElectiveCourse> courses = em.createQuery("SELECT e FROM ElectiveCourse e").getResultList();
        return gson.toJson(courses);
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
    public String getOne(long id) {
        EntityManager em = Factory.getInstance().getManager();
        ElectiveCourse electiveCourse = em.find(ElectiveCourse.class, id);
        if (electiveCourse != null) {
            return gson.toJson(electiveCourse);
        }
        return "{\n"
                + "  err: true,\n"
                + "  title: “Elective Course with id " + id + " doesn't exist!”\n"
                + "}";
    }

    @Override
    public String submitElectiveSubject(String subjectAsJson) {
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
        } finally {
            em.close();
        }

        return gson.toJson(courseToCreate);
    }

    @Override
    public String getSecondElectiveSubjects() {
        EntityManager em = Factory.getInstance().getManager();
        List<ElectiveCourse> course = em.createQuery("SELECT e FROM ElectiveCourse e").getResultList(); // TODO: 'Where'-clause here
        List<ElectiveCourse> courseToReturn = new ArrayList();

        // TODO: Check for iterator, it gave me exceptions, not allowed
        for (int i = 0; i < course.size(); i++) {
            if (course.get(i).getRound() == 2) {
                courseToReturn.add(course.get(i));
            }
        }
        em.close();
        return gson.toJson(courseToReturn);
    }

    @Override
    public String deleteElectiveSubject(long id) {
        EntityManager em = Factory.getInstance().getManager();
        em.getTransaction().begin();
        ElectiveCourse electiveCourse = em.find(ElectiveCourse.class, id);
        
        if (electiveCourse != null) {
            em.remove(electiveCourse);
            em.getTransaction().commit();
            return gson.toJson(electiveCourse);
        }
        return "{\n"
                + "  err: true,\n"
                + "  title: “Elective Course with id " + id + " doesn't exist!”\n"
                + "}";
    }

    @Override
    public String emptyTable() {
        EntityManager em = Factory.getInstance().getManager();

        try {
            em.getTransaction().begin();
            Query q1 = em.createNativeQuery("DELETE FROM ElectiveCourse");
            q1.executeUpdate();
            em.getTransaction().commit();
        } catch (SystemException | SecurityException | IllegalStateException | RollbackException e) {
            e.printStackTrace();
            System.err.println("Exception was thrown");
            return "{\n"
                    + "  err: true,\n"
                    + "  title: “Couldn't clear table!”\n"
                    + "}";
        }
        return "{\n"
                + "  err: false,\n"
                + "  title: “Table cleared”\n"
                + "}";
    }

}
