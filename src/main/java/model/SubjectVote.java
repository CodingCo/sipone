package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class SubjectVote implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "electiveCourseIdGen")
    @SequenceGenerator(name = "electiveCourseIdGen", sequenceName = "ELECTIVE_COURSE_SEQ", initialValue = 100000, allocationSize = 1)
    private Long id;
    
    @OneToOne
    private Student student;
    
    @OneToMany // TODO: Check this
    private List<ElectiveCourse> course;

    public SubjectVote() {
    }
    
    public SubjectVote(Student student, List electiveCourses) {
        this.student = student;
        this.course = electiveCourses;
    }

    public Student getStudent() {
        return student;
    }

    public List getElectivesCourses() {
        return course;
    }
    
    public Long getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "model.SubjectVote[ id=" + id + " ]";
    }
    
}
