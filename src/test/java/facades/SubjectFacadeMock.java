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
        gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
    }
    
    @Override
    public String getFirstElectiveSubjects() {
        List<ElectiveCourse> tmp = new ArrayList<ElectiveCourse>();
        
        for (ElectiveCourse tmpCourse : tmp) {
            if (tmpCourse.getRound() == 1) {
                
                tmp.add(tmpCourse);
                
            }
        }
        
        return gson.toJson(tmp);
    }

    @Override
    public String getSecondElectiveSubjects() {
           List<ElectiveCourse> tmp = new ArrayList<ElectiveCourse>();
        
        for (ElectiveCourse tmpCourse : tmp) {
            if (tmpCourse.getRound() == 2) {
                
                tmp.add(tmpCourse);
                
            }
        }
        
        return gson.toJson(tmp);
    }

    @Override
    public String submittedFirstElectiveSubjects(String subjectAsJson) {
       ElectiveCourse courseToCreate = gson.fromJson(subjectAsJson, ElectiveCourse.class);
       subjects.add(courseToCreate);
       return gson.toJson(courseToCreate);
    }

    @Override
    public String deleteElectiveSubject(long id) {
        for (ElectiveCourse subject : subjects) {
            if (subject.getId() == id) {
                return gson.toJson(subjects.remove(subject));
            }
        } 
        return "";   
    }
}
