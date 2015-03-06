package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class SubjectVote implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "electiveCourseIdGen")
    @SequenceGenerator(name = "electiveCourseIdGen", sequenceName = "ELECTIVE_COURSE_SEQ", initialValue = 100000, allocationSize = 1)
    private Long id;
    
    @OneToOne
    private Student student;
    
    @OneToOne
    private ElectiveCourse course;

    public SubjectVote() {
    }
    
    public SubjectVote(Student student, ElectiveCourse course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public ElectiveCourse getCourse() {
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
