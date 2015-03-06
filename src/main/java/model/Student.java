package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Student extends Person implements Serializable {

//    @Column(name = "COURSE_1")
    @JoinColumn(name = "COURSE_1")
    private Course course1;
    
//    @Column(name = "COURSE_2")
    @JoinColumn(name = "COURSE_2")
    private Course course2;
    
    public Student() {
    }
    
    public Student(String cprNummer, String firstName, String lastName, String email) {
        super(cprNummer, firstName, lastName, email);
    }

    public Course getCourse1() {
        return course1;
    }

    public Course getCourse2() {
        return course2;
    }

    public void setCourse1(Course course1) {
        this.course1 = course1;
    }

    public void setCourse2(Course course2) {
        this.course2 = course2;
    }

    @Override
    public String toString() {
        return "model.Student[ id=" + 0 + " ]";
    }

}
