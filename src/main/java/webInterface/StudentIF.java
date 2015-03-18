package webInterface;

public interface StudentIF {
    public String getStudents();
    
    public String getStudent(String cpr);
    
    public String submitStudent(String studentAsJson);
}
