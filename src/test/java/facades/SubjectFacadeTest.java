/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import static org.junit.Assert.*;
import webInterface.SubjectFacadeIF;

/**
 *
 * @author christophermortensen
 */
public class SubjectFacadeTest {
    
    SubjectFacadeIF instance;
    GsonBuilder gsonBuilder;
    Gson trans;

    /**
     * Test of getFirstElectiveSubjects method, of class SubjectFacade.
     */
    @Test
    public void testGetFirstElectiveSubjects() {
        System.out.println("getFirstElectiveSubjects");
        SubjectFacade instance = null;
        String expResult = "";
        String result = instance.getFirstElectiveSubjects();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of submittedFirstElectiveSubjects method, of class SubjectFacade.
     */
    @Test
    public void testSubmittedFirstElectiveSubjects() {
        System.out.println("submittedFirstElectiveSubjects");
        String subjectAsJson = "";
        SubjectFacade instance = null;
        String expResult = "";
        String result = instance.submittedFirstElectiveSubjects(subjectAsJson);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSecondElectiveSubjects method, of class SubjectFacade.
     */
    @Test
    public void testGetSecondElectiveSubjects() {
        System.out.println("getSecondElectiveSubjects");
        SubjectFacade instance = null;
        String expResult = "";
        String result = instance.getSecondElectiveSubjects();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteElectiveSubject method, of class SubjectFacade.
     */
    @Test
    public void testDeleteElectiveSubject() {
        System.out.println("deleteElectiveSubject");
        long id = 0L;
        SubjectFacade instance = null;
        String expResult = "";
        String result = instance.deleteElectiveSubject(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
