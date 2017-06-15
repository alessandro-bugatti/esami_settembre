/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esami_settembre;

import esami_settembre.Exam;
import esami_settembre.Course;
import java.util.ArrayList;
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
public class ExamTest {
    
    public ExamTest() {
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
     * Test of isCompatible method, of class Exam.
     */
    @Test
    public void testIsCompatible() {
        System.out.println("isCompatible");
        Exam e = new Exam(new Course("3AI"), "Bugatti");
        e.addStudent("Piero");
        e.addStudent("Geppo");
        e.addStudent("Plino");
        e.addStudent("Anna");
        Exam instance = new Exam(new Course("3AI"),"Tognini");
        instance.addStudent("Ado");
        instance.addStudent("Vecchio");
        instance.addStudent("Morfino");
        boolean expResult = true;
        boolean result = instance.isCompatible(e);
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of isCompatible method, of class Exam.
     */
    @Test
    public void testIsIncompatible() {
        System.out.println("isIncompatible");
        Exam e = new Exam(new Course("3AI"), "Bugatti");
        e.addStudent("Piero");
        e.addStudent("Geppo");
        e.addStudent("Plino");
        e.addStudent("Anna");
        Exam instance = new Exam(new Course("3AI"),"TogniniS");
        instance.addStudent("Geppo");
        instance.addStudent("Vecchio");
        instance.addStudent("Morfino");
        boolean expResult = false;
        boolean result = instance.isCompatible(e);
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of toString method, of class Exam.
     */
    @Test
    public void toStringExam() {
        System.out.println("toString");
        Exam e = new Exam(new Course("3AI"), "Bugatti");
        e.addStudent("Piero");
        e.addStudent("Geppo");
        e.addStudent("Plino");
        e.addStudent("Anna");
        String expResult = "Class: 3AI Students: [1, 2, 5, 7]";
        String result = e.toString();
        //assertEquals(expResult, result);
        
    }
    
    
    
}
