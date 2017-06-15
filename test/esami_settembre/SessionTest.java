/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esami_settembre;

import esami_settembre.Session;
import esami_settembre.Exam;
import esami_settembre.Course;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alessandro Bugatti <alessandro.bugatti@gmail.com>
 */
public class SessionTest {
    
    public SessionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addExam method, of class Session. Inserting the first exam
     */
    @Test
    public void testAddExamFirst() {
        System.out.println("addExam first element");
        Session s = new Session(2);
        Exam e = new Exam(new Course("3AI"));
         e.addStudent("Piero");
        e.addStudent("Geppo");
        e.addStudent("Plino");
        e.addStudent("Anna");
        
        boolean expResult = true;
        boolean result = s.addExam(e);;
        assertEquals(expResult, result);
        
    }    
    
    /**
     * Test of addExam method, of class Session. Inserting two different classes
     */
    @Test
    public void testAddExamDifferentClasses() {
        System.out.println("addExam different classes");
        Session s = new Session(2);
        Exam e = new Exam(new Course("3AI"));
        e.setSubject("Chimica");
         e.addStudent("Piero");
        e.addStudent("Geppo");
        e.addStudent("Plino");
        e.addStudent("Anna");
        s.addExam(e);
        Exam second = new Exam(new Course("4AI"));
        second.addStudent("Piero");
        second.addStudent("Geppo");
        second.addStudent("Plino");
        second.addStudent("Anna");
        boolean expResult = true;
        boolean result = s.addExam(second);;
        assertEquals(expResult, result);
        
    } 
    
    /**
     * Test of addExam method, of class Session. Same class, different students
     */
    @Test
    public void testAddExamDifferentStudent() {
        System.out.println("addExam different students");
        Session s = new Session(2);
        Exam e = new Exam(new Course("3AI"));
        e.setSubject("Chimica");
        e.addStudent("Piero");
        e.addStudent("Geppo");
        e.addStudent("Plino");
        e.addStudent("Anna");
        s.addExam(e);
        Exam second = new Exam(new Course("3AI"));
        second.setSubject("Geografia");
        second.addStudent("Girbo");
        second.addStudent("Gianna");
        boolean expResult = true;
        boolean result = s.addExam(second);;
        assertEquals(expResult, result);
      
    } 
    
    /**
     * Test of addExam method, of class Session. Same class, some students in common
     */
    @Test
    public void testAddExamStudentsInCommon() {
        System.out.println("addExam students in common");
        Session s = new Session(2);
        Exam e = new Exam(new Course("3AI"));
        e.setSubject("Chimica");
        e.addStudent("Piero");
        e.addStudent("Geppo");
        e.addStudent("Plino");
        e.addStudent("Anna");
        s.addExam(e);
        Exam second = new Exam(new Course("3AI"));
        second.setSubject("Lettere");
        second.addStudent("Piero");
        second.addStudent("Oldo");
        second.addStudent("Plino");
        second.addStudent("Anna");
        boolean expResult = false;
        boolean result = s.addExam(second);;
        assertEquals(expResult, result);
        
    } 
    
    /**
     * Test of addExam method, of class Session. Same class, some students in common
     */
    @Test
    public void testAddExamTooMany() {
        System.out.println("addExam too many exam");
        Session s = new Session(2);
        Exam e = new Exam(new Course("3AI"));
        e.setSubject("Chimica");
        e.addStudent("Piero");
        e.addStudent("Geppo");
        e.addStudent("Plino");
        e.addStudent("Anna");
        s.addExam(e);
        Exam second = new Exam(new Course("4AI"));
        second.setSubject("Chimica");
        second.addStudent("Plino");
        second.addStudent("Anna");
        s.addExam(second);
        Exam third = new Exam(new Course("5AI"));
        e.setSubject("Second");
        third.addStudent("Plino");
        third.addStudent("Anna");
        boolean expResult = false;
        boolean result = s.addExam(third);
        assertEquals(expResult, result);
        
    } 
}
