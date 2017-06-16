/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esami_settembre;

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
public class ClassroomTest {
    
    public ClassroomTest() {
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
     * Test of addExam method, of class Classroom.
     */
    @Test
    public void testAddExamWithDifferentTeachers() {
        System.out.println("addExam with different teacher");
        Classroom instance = new Classroom(25);
        Exam e = new Exam(new Course("3AI"), "Bugatti");
        e.setSubject("Storia");
        e.addStudent("Piero");
        e.addStudent("Geppo");
        e.addStudent("Plino");
        e.addStudent("Anna");
        instance.addExam(e);
        Exam second = new Exam(new Course("4AI"),"Tugatti");
        second.setSubject("Chimica");
        second.addStudent("Ardiccio");
        second.addStudent("Astolfa");
        boolean expResult = false;
        boolean result = instance.addExam(second);
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of addExam method, of class Classroom.
     */
    @Test
    public void testAddExamWithSameTeacher() {
        System.out.println("addExam with the same teacher");
        Classroom instance = new Classroom(25);
        Exam e = new Exam(new Course("3AI"), "Bugatti");
        e.setSubject("Storia");
        e.addStudent("Piero");
        e.addStudent("Geppo");
        e.addStudent("Plino");
        e.addStudent("Anna");
        instance.addExam(e);
        Exam second = new Exam(new Course("4AI"),"Bugatti");
        second.setSubject("Chimica");
        second.addStudent("Ardiccio");
        second.addStudent("Astolfa");
        boolean expResult = true;
        boolean result = instance.addExam(second);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of addExam method, of class Classroom.
     */
    @Test
    public void testAddExamTooStudents() {
        System.out.println("addExam with too students");
        Classroom instance = new Classroom(5);
        Exam e = new Exam(new Course("3AI"), "Bugatti");
        e.setSubject("Storia");
        e.addStudent("Piero");
        e.addStudent("Geppo");
        e.addStudent("Plino");
        e.addStudent("Anna");
        instance.addExam(e);
        Exam second = new Exam(new Course("4AI"),"Bugatti");
        second.setSubject("Chimica");
        second.addStudent("Ardiccio");
        second.addStudent("Astolfa");
        boolean expResult = false;
        boolean result = instance.addExam(second);
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of isCompatible method, of class Classroom.
     */
    @Test
    public void testIsIncompatible() {
        System.out.println("is Incompatible");
        Classroom instance = new Classroom(25);
        Exam e = new Exam(new Course("3AI"), "Bugatti");
        e.setSubject("Storia");
        e.addStudent("Piero");
        e.addStudent("Geppo");
        e.addStudent("Plino");
        e.addStudent("Anna");
        instance.addExam(e);
        Exam second = new Exam(new Course("3AI"),"Bugelli");
        second.setSubject("Chimica");
        second.addStudent("Plino");
        second.addStudent("Anna");
        boolean expResult = false;
        boolean result = instance.isCompatible(second);
        assertEquals(expResult, result);
       
    }
    
    /**
     * Test of isCompatible method, of class Classroom.
     */
    @Test
    public void testIsCompatible() {
        System.out.println("is Compatible");
        Classroom instance = new Classroom(25);
        Exam e = new Exam(new Course("3AI"), "Bugatti");
        e.setSubject("Storia");
        e.addStudent("Piero");
        e.addStudent("Geppo");
        e.addStudent("Plino");
        e.addStudent("Anna");
        instance.addExam(e);
        Exam second = new Exam(new Course("4AI"),"Bugelli");
        second.setSubject("Chimica");
        second.addStudent("Ardiccio");
        second.addStudent("Astolfa");
        boolean expResult = true;
        boolean result = instance.isCompatible(second);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of canContainAnotherExam method, of class Classroom.
     */
    @Test
    public void testCanContainAnotherExam() {
        System.out.println("canContainAnotherExam");
        Classroom instance = new Classroom(25);
        Exam e = new Exam(new Course("3AI"), "Bugatti");
        e.setSubject("Storia");
        e.addStudent("Piero");
        e.addStudent("Geppo");
        e.addStudent("Plino");
        e.addStudent("Anna");
        instance.addExam(e);
        Exam second = new Exam(new Course("4AI"),"Bugatti");
        second.setSubject("Chimica");
        second.addStudent("Ardiccio");
        second.addStudent("Astolfa");
        boolean expResult = true;
        boolean result = instance.canContainAnotherExam(second);
        assertEquals(expResult, result);
    }

    
    
}
