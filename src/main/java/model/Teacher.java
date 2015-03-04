/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.*;
/**
 *
 * @author ThomasHedegaard
 */
@Entity
public class Teacher extends Person implements Serializable {
    
    public Teacher() {
    }
    
    public Teacher(String cprNummer, String firstName, String lastName, String email) {
        super(cprNummer, firstName, lastName, email);
    }
    
    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
