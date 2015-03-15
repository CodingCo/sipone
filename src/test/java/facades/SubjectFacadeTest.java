/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.ElectiveCourse;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import webInterface.SubjectFacadeIF;

/**
 *
 * @author christophermortensen
 */
public class SubjectFacadeTest {
    
    SubjectFacadeIF instance;
    GsonBuilder gsonBuilder;
    Gson gson;
    boolean isMock = false;

    @Before 
    public void setUp(){
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        
        instance = isMock ? new SubjectFacadeMock() : new SubjectFacade(gson);
        addDummyData();
    }
    
    @After
    public void tearDown(){
        emptyTable();
    }
    
    // ID - Autogenerated - Starts on 100000 (inclusive)
    public void addDummyData(){
        // adds to round 1 when i is < 10 else 2
        for(int i = 0; i < 20; i++){
            ElectiveCourse electiveCourse = new ElectiveCourse("Elective Course " + i, "TestDescription nr. " + i, "1", i < 10 ? 1 : 2);
            instance.submitElectiveSubject(gson.toJson(electiveCourse));
        }
    }
    
    public void emptyTable(){
        instance.emptyTable();
    }
    
    /**
     * Test of getAll method, of class SubjectFacade.
     */
    @Test
    public void testGetAll(){
        System.out.println("getAll");
        int expResult = 20;
        String receivedCourses = instance.getAll();
        int result = receivedCourses.substring(1, receivedCourses.length()-1).split("},").length;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getFirstElectiveSubjects method, of class SubjectFacade.
     */
    @Test
    public void testGetFirstElectiveSubjects() {
        System.out.println("getFirstElectiveSubjects");
        int expLength = 10;
        String result = instance.getFirstElectiveSubjects();
        String[] resultsInJson = result.substring(1, result.length()-1).split("},");
        // test number of items
        
        assertEquals(expLength, resultsInJson.length);
        // test each item is from round 1
        for(String current : resultsInJson){
            current += (current.charAt(current.length()-1) == '}') ? "" : "}";
            ElectiveCourse temp = gson.fromJson(current, ElectiveCourse.class);
            assertEquals(1, temp.getRound());
        }
    }

    /**
     * Test of submittedFirstElectiveSubjects method, of class SubjectFacade.
     * ID - Autogenerated - Starts on 100000 (inclusive) - 20 already added -> 100020<
     */
    @Test
    public void testSubmitElectiveSubject() {
        System.out.println("submittedFirstElectiveSubjects");
        ElectiveCourse expResult = new ElectiveCourse("Elective Course " + 20, "TestDescription nr. " + 20, "1", 1);
        String subjectAsJson = gson.toJson(expResult);
        ElectiveCourse result = gson.fromJson(instance.submitElectiveSubject(subjectAsJson), ElectiveCourse.class);
        
        // Asserts all attributes, but id and creationDate.
        assertEquals(expResult.getElectiveCourseName(), result.getElectiveCourseName());
        assertEquals(expResult.getDescription(), result.getDescription());
        assertEquals(expResult.getPool(), result.getPool());
        assertEquals(expResult.getRound(), result.getRound());
        assertEquals(expResult.getNoOfVotes(), result.getNoOfVotes());
    }

    /**
     * Test of getSecondElectiveSubjects method, of class SubjectFacade.
     */
    @Test
    public void testGetSecondElectiveSubjects() {
        System.out.println("getSecondElectiveSubjects");
        int expLength = 10;
        String result = instance.getSecondElectiveSubjects();
        String[] resultsInJson = result.substring(1, result.length()-1).split("},");
        // test number of items
        
        assertEquals(expLength, resultsInJson.length);
        // test each item is from round 2
        for(String current : resultsInJson){
            current += (current.charAt(current.length()-1) == '}') ? "" : "}";
            ElectiveCourse temp = gson.fromJson(current, ElectiveCourse.class);
            assertEquals(2, temp.getRound());
        }
    }
    
    /**
     * Test of getOne method, of class SubjectFacade.
     */
    @Test
    public void TestGetOne(){
        System.out.println("getOne");
        Long id = 100019L;
        ElectiveCourse expResult = new ElectiveCourse("Elective Course 19", "TestDescription nr. 19", "1", 2);
        expResult.setId(id);
        ElectiveCourse result = gson.fromJson(instance.getOne(id), ElectiveCourse.class);
        // Asserts all attributes, but creationDate.
        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.getElectiveCourseName(), result.getElectiveCourseName());
        assertEquals(expResult.getDescription(), result.getDescription());
        assertEquals(expResult.getPool(), result.getPool());
        assertEquals(expResult.getRound(), result.getRound());
        assertEquals(expResult.getNoOfVotes(), result.getNoOfVotes());
    }

    /**
     * Test of deleteElectiveSubject method, of class SubjectFacade.
     * ID - Autogenerated - Starts on 100000 (inclusive) - 20 already added -> 100020<
     */
    @Test
    public void testDeleteElectiveSubject() {
        System.out.println("deleteElectiveSubject");
        // Succesful delete
        long expResult_1 = 100019L;
        long result_1 = gson.fromJson(instance.deleteElectiveSubject(expResult_1), ElectiveCourse.class).getId();
        assertEquals(expResult_1, result_1);
        
        String expResult_2 = "{\n"
                    + "  err: true,\n"
                    + "  title: “Elective Course with id " + expResult_1 + " doesn't exist!”\n"
                    + "}";
        String result_2 = instance.getOne(expResult_1);
        assertEquals(expResult_2, result_2);
        //----------------------------------------------------------------------------------------------------------
        // Unsuccesful delete
        Long invalidId = 424242L;
        String expResult_3 = "{\n"
                    + "  err: true,\n"
                    + "  title: “Elective Course with id " + invalidId + " doesn't exist!”\n"
                    + "}";
        String result_3 = instance.deleteElectiveSubject(invalidId);
        assertEquals(expResult_3, result_3);
    }
    
    @Test 
    public void testEmptyTable(){
        System.out.println("emptyTable");
        // Succesful empty
        String expResult_1 = "{\n"
                + "  err: false,\n"
                + "  title: “Table cleared”\n"
                + "}";
        String result_1 = instance.emptyTable();
        assertEquals(expResult_1, result_1);
        //----------------------------------------------------------------------------------------------------------
        // Unsuccesful delete
        String expResult_2 = "[]";
        String result_2 = instance.getAll();
        assertEquals(expResult_2, result_2);
    }
    
}
