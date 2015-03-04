package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;
import org.eclipse.persistence.annotations.TimeOfDay;
import sun.util.calendar.Gregorian;

@Entity
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courseIdGen")
    @SequenceGenerator(name = "courseIdGen", sequenceName = "COURSE_SEQ", initialValue = 100000, allocationSize = 1)
    private Long id;

    @Column(name = "Teacher")
    private Teacher teacher;

    @Column(name = "COURSE_NAME")
    private String courseName;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATION_DATE")
    private Date creationDate;

    public Course() {
    }

    public Course(Long id, Teacher teacher, String courseName) {
        this.id = id;
        this.teacher = teacher;
        this.courseName = courseName;
        setDate();
    }

    private void setDate() {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
            String currentDate = sdf.format(new Date());
            creationDate = sdf.parse(currentDate);
        } catch (ParseException ex) {
            System.err.println("PROBLEM IN FORMATTING DATE!!!");
        }

    }

    // GETTERS AND SETTERS
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "model.Course[ id=" + id + " ]";
    }

}
