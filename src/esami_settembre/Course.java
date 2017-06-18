/*
  Copyright (C) 2013 Alessandro Bugatti (alessandro.bugatti@istruzione.it)

  This program is free software; you can redistribute it and/or
  modify it under the terms of the GNU General Public License
  as published by the Free Software Foundation; either version 2
  of the License, or (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software
  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/

/*! \file
 *  \brief 
 *  \author Alessandro Bugatti
 *  \version 0.1
 *  \date  Creazione 13-giu-2017
 *  \date  Ultima modifica 13-giu-2017
 */

package esami_settembre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Course implements Comparable<Course>{
    private String name;
    ArrayList <Exam> exams;
    private final int studentNumber;

    public Course(String name) {
        this.name = name;
        this.exams = new ArrayList<>();
        Random r = new Random();
        studentNumber = r.nextInt(10) + 20;
    }

    public String getName() {
        return name;
    }
    
    public String getShortName() {
        return name.substring(0, 7);
    }
    
    /*
    public void createExams(int n){
        Random r = new Random();
        ArrayList <Integer> s = new ArrayList<>();
        for (int i = 0; i < studentNumber; i++)
            s.add(i);
        n = r.nextInt(n) + 1;
        for (int i = 0; i < n; i++){
            Collections.shuffle(s);
            Exam e = new Exam(this);
            int ns = r.nextInt(studentNumber/2) + 1;
            for (int j = 0; j<ns; j++)
                e.addStudent(s.get(j));
            exams.add(e);
        }
    }
    */
    @Override
    public String toString() {
        return name + " studentNumber = " + studentNumber + 
                "num. exams = " + exams.size() + ", exams: " + exams;
    }
    
    public boolean allAccomodated()
    {
        for (Exam e: exams)
            if(!e.isAccomodated())
                return false;
        return true;
    }
    
    public Exam getExam(int index){
        return exams.get(index);
    }
    
    public int getNumberOfExams(){
        return exams.size();
    }
    
    public String examsSituation(){
        if (allAccomodated())
            return "All exams accomodated.";
        String s = "Exams not accomodated: ";
        int cont = 0;
        for (Exam e : exams)
            if (!e.isAccomodated())
                cont++;
        s += cont;
        return s;
    }
    
    private boolean alreadyInserted(String name)
    {
        for (Exam e: exams)
            if (e.getSubject().equals(name))
                return true;
        return false;
    }
    
    private Exam getExamBySubject(String s){
        for (Exam e:exams)
            if (e.getSubject().equals(s))
                return e;
        return null;          
    }
    
    public void addExam(String examName, String student, String teacher)
    {
        if(alreadyInserted(examName))
            getExamBySubject(examName).addStudent(student);
        else{
            Exam e  = new Exam(this, teacher);
            e.setSubject(examName);
            e.addStudent(student);
            exams.add(e);
        }
    }
    
    @Override
    public int compareTo(Course c){
        return name.compareTo(c.name);
    }
            
}
