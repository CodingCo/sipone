package webInterface;

public interface SubjectFacadeIF {

    public String getFirstElectiveSubjects();

    public String getSecondElectiveSubjects();
    
    public String submitElectiveSubject(String subjectAsJson);
    
    public String deleteElectiveSubject(long id);
    
    // living on the edge
    public String emptyTable();

}
