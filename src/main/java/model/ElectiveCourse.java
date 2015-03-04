package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

@Entity
public class ElectiveCourse implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "electiveCourseIdGen")
    @SequenceGenerator(name = "electiveCourseIdGen", sequenceName = "ELECTIVE_COURSE_SEQ", initialValue = 100000, allocationSize = 1)
    private Long id;

    @Column(name = "ELECTIVE_COURSE_NAME")
    private String electiveCourseName;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATION_DATE")
    private Date creationDate;
    
    @Column(name = "NO_OF_VOTES")
    private int noOfVotes;
    
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "POOL")
    private String pool;

    public ElectiveCourse() {
    }

    public ElectiveCourse(Long id, String electiveCourseName, Date creationDate, int noOfVotes, String description, String pool) {
        this.id = id;
        this.electiveCourseName = electiveCourseName;
        this.noOfVotes = 1;
        this.description = description;
        this.pool = pool;
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
    
    public void voteForCourse(){
        noOfVotes++;
    }
    
    // GETTERS AND SETTERS
    public String getElectiveCourseName() {
        return electiveCourseName;
    }

    public void setElectiveCourseName(String electiveCourseName) {
        this.electiveCourseName = electiveCourseName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getNoOfVotes() {
        return noOfVotes;
    }

    public void setNoOfVotes(int noOfVotes) {
        this.noOfVotes = noOfVotes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    
    
    public Long getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "model.ElectiveCourse[ id=" + id + " ]";
    }
    
}
