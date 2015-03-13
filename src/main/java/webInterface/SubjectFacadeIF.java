package webInterface;

public interface SubjectFacadeIF {

    public String getFirstElectiveSubjects();

    public String getSecondElectiveSubjects();
    
    public String submittedFirstElectiveSubjects(String subjectAsJson);
    
    public String deleteElectiveSubject(long id);

}
