package webInterface;

public interface StudentFacadeIF {
    public String getStudents();
    
    public String getStudent(String cpr);
    
    public String submitStudent(String studentAsJson);
}
