package webInterface;

public interface ElectiveCourseFacadeIF {

    public String getAll();
    
    public String getFirstElectiveSubjects();

    public String getSecondElectiveSubjects();
    
    public String getOne(long id);
    
    public String submitElectiveSubject(String subjectAsJson);
    
    public String deleteElectiveSubject(long id);
    
    // living on the edge
    public String emptyTable();

}
