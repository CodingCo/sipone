package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import model.ElectiveCourse;
import model.Student;
import webInterface.StudentFacadeIF;

public class StudentFacadeMock implements StudentFacadeIF {

    private GsonBuilder gsonBuilder;
    private Gson gson;
    private List<Student> students;
    
    public StudentFacadeMock(){
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        students = new ArrayList<>();
    }
    
    @Override
    public String getStudents() {
        return gson.toJson(students);
    }

    @Override
    public String getStudent(String cpr) {
        for(Student student : students){
            if(student.getCprNumber().equalsIgnoreCase(cpr)){
                return gson.toJson(student);
            }
        }
        return "{\n"
            + "  err: true,\n"
            + "  title: “Student with cpr " + cpr + " doesn't exist!”\n"
            + "}";
    }

    @Override
    public String submitStudent(String studentAsJson) {
        Student studentToCreate = gson.fromJson(studentAsJson, Student.class);
        students.add(studentToCreate);
        return gson.toJson(studentToCreate);
    }

}
