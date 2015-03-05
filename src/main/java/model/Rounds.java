/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

@Entity
public class Rounds implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roundsIdGen")
    @SequenceGenerator(name = "roundsIdGen", sequenceName = "ROUDNS_SEQ", initialValue = 100000, allocationSize = 1)
    private Long id;

    @Column(name = "ELECTIVE_COURSE")
    @OneToMany(fetch = FetchType.EAGER) 
    private Collection<ElectiveCourse> electiveCourse;

    @Column(name = "ROUND_NUMBER")
    private int roundNo;

    public Rounds() {
    }

    public Rounds(int roundNo) {
        this.electiveCourse = new ArrayList();
        this.roundNo = roundNo;
    }

    public void addElectiveCourse(ElectiveCourse course) {
        electiveCourse.add(course);
    }

    public int getRoundNo() {
        return roundNo;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "model.Rounds[ id=" + id + " ]";
    }

}
