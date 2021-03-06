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
 *  \date  Creazione 15-giu-2017
 *  \date  Ultima modifica 15-giu-2017
 */

package esami_settembre;

import java.util.ArrayList;


public class Classroom {
    private ArrayList <Exam> exams;
    private int students_in_classroom;
    private final int max_student_in_classroom;

    public Classroom(int max_student_in_classroom) {
        exams = new ArrayList<>();
        students_in_classroom = 0;
        this.max_student_in_classroom = max_student_in_classroom;
    }
    
    public boolean addExam(Exam insertingExam){
        if (students_in_classroom + insertingExam.getStudents().size() > max_student_in_classroom)
            return false;
        for (Exam e: exams)
            if (!e.isCompatible(insertingExam))
                return false;
        for (Exam e: exams)
            if (!e.getTeacher().equals(insertingExam.getTeacher()))
                return false;
        exams.add(insertingExam);
        students_in_classroom += insertingExam.getStudents().size();
        return true;
    }
    
    public boolean isCompatible(Exam insertingExam){
        for (Exam e : exams){
            if(!e.isCompatible(insertingExam))
                return false;
        }
        return true;
    }
    
    public boolean canContainAnotherExam(Exam insertingExam){
        for (Exam e : exams){
            if (e.getTeacher().equals(insertingExam.getTeacher()) &&
                    students_in_classroom + insertingExam.getStudents().size() <= max_student_in_classroom)
            {
                return true;
            }
        }
        return false;
    }
    
    public String toCSV()
    {
        String s = "Studenti: " + students_in_classroom + " classi: " + exams.size() + "  ";
        for (Exam e : exams)
            s += " - " + e.toCSV();
        return s;
    }
    
    @Override
    public String toString() {
        return "\n\nStudenti nella classe = " + students_in_classroom +  
                 " classi in contemporanea = " + exams.size() + "\n"+ exams;
    }
    
    
    
}
