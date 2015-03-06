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

    @OneToOne
    private ElectiveCourse course;

    @Column(name = "ROUND_NUMBER")
    private int roundNo;

    public Rounds() {
    }

    public Rounds(int roundNo, ElectiveCourse elCourse) {
        this.roundNo = roundNo;
        this.course = elCourse;
    }

    public int getRoundNo() {
        return roundNo;
    }

    public Long getId() {
        return id;
    }

    public ElectiveCourse getElectiveCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "model.Rounds[ id=" + id + " ]";
    }

}
