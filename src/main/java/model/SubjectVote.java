package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class SubjectVote implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "electiveCourseIdGen")
    @SequenceGenerator(name = "electiveCourseIdGen", sequenceName = "ELECTIVE_COURSE_SEQ", initialValue = 100000, allocationSize = 1)
    private Long id;
    
    @JoinColumn(name = "STUDENT")
    private Student student;
    
    @JoinColumn(name = "ROUND")
    private Rounds round;

    public SubjectVote() {
    }
    
    public SubjectVote(Student student, Rounds round) {
        this.student = student;
        this.round = round;
    }

    public Student getStudent() {
        return student;
    }

    public Rounds getRounds() {
        return round;
    }
    
    public Long getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "model.SubjectVote[ id=" + id + " ]";
    }
    
}
