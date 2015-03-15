package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import model.ElectiveCourse;
import webInterface.SubjectFacadeIF;

public class SubjectFacadeMock implements SubjectFacadeIF {

    private GsonBuilder gsonBuilder;
    private Gson gson;
    private List<ElectiveCourse> subjects;

    public SubjectFacadeMock() {
        this.subjects = new ArrayList<ElectiveCourse>();
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }
    
    @Override
    public String getAll() {
        return gson.toJson(subjects);
    }

    @Override
    public String getFirstElectiveSubjects() {
        List<ElectiveCourse> tmp = new ArrayList<ElectiveCourse>();

        for (ElectiveCourse current : subjects) {
            if (current.getRound() == 1) {
                tmp.add(current);

            }
        }

        return gson.toJson(tmp);
    }

    @Override
    public String getSecondElectiveSubjects() {
        List<ElectiveCourse> tmp = new ArrayList<ElectiveCourse>();

        for (ElectiveCourse current : subjects) {
            if (current.getRound() == 2) {

                tmp.add(current);

            }
        }

        return gson.toJson(tmp);
    }

    @Override 
    public String getOne(long id){
        for(ElectiveCourse electiveCourse : subjects){
            if(electiveCourse.getId() == id){
                return gson.toJson(electiveCourse);
            }
        }
        return "{\n"
                    + "  err: true,\n"
                    + "  title: “Elective Course with id " + id + " doesn't exist!”\n"
                    + "}";
    }
    
    @Override
    public String submitElectiveSubject(String subjectAsJson) {
        ElectiveCourse courseToCreate = gson.fromJson(subjectAsJson, ElectiveCourse.class);
        courseToCreate.setId(100000 + subjects.size());
        subjects.add(courseToCreate);
        return gson.toJson(courseToCreate);
    }

    @Override
    public String deleteElectiveSubject(long id) {
        System.out.println("inside  delete elctive subject");
        for(int i = 0; i < subjects.size(); i++){
            System.out.println("inside loop ");
            if(subjects.get(i).getId() == id){
                System.out.println("inside if ");
                return gson.toJson(subjects.remove(i));
            }
        }
        /*for (ElectiveCourse subject : subjects) {
            System.out.println("inside loop ");
            if (subject.getId() == id) {
                System.out.println("inside if ");
                return gson.toJson(subjects.remove(subject));
            }
        }*/
        System.out.println("after loop");
        return "{\n"
                    + "  err: true,\n"
                    + "  title: “Elective Course with id " + id + " doesn't exist!”\n"
                    + "}";
    }

    @Override
    public String emptyTable() {
        try {
            subjects.clear();
        } catch (UnsupportedOperationException ex) {
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
