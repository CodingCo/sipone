package facades;

import com.google.gson.Gson;
import java.util.List;
import javax.persistence.EntityManager;
import model.ElectiveCourse;
import model.Student;
import webInterface.StudentIF;
import webServer.Factory;

public class StudentFacade implements StudentIF {

    private Gson gson;

    public StudentFacade(Gson gson) {
        this.gson = gson;
    }
    
    @Override
    public String getStudents() {
        EntityManager em = Factory.getInstance().getManager();
        List<ElectiveCourse> students = em.createQuery("SELECT s FROM Student s").getResultList();
        em.close();
        return gson.toJson(students);
    }

    @Override
    public String getStudent(String cpr) {
        EntityManager em = Factory.getInstance().getManager();
        Student student = null;
        try {
            student = em.find(Student.class, cpr);
        } catch (Exception e) {
            System.err.println("Exception was thrown");
            return "{\n"
                    + "  err: true,\n"
                    + "  title: “Student doesn't exists!”\n"
                    + "}";
        } finally {
            em.close();
        }
        return gson.toJson(student);
    }

    @Override
    public String submitStudent(String studentAsJson) {
        EntityManager em = Factory.getInstance().getManager();
        Student student = gson.fromJson(studentAsJson, Student.class);

        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Exception was thrown");
            return "{\n"
                    + "  err: true,\n"
                    + "  title: “Student already exists!”\n"
                    + "}";
        } finally {
            em.close();
        }
        return gson.toJson(student);
    }
    
}
