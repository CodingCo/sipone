package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PERSON_TYPE")
public class Person implements Serializable {
    
    // cpr, email, name 
    
    @Id
    @Column(name = "CPR_NUMBER", nullable = false)
    private String cprNummer;
    
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    
    @Column(name = "EMAIL", nullable = false)
    private String email;

    public Person() {
    }

    public Person(String cprNummer, String firstName, String lastName, String email) {
        this.cprNummer = cprNummer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getCprNummer() {
        return cprNummer;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
