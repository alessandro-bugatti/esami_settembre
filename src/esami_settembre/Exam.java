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
 *  \date  Creazione 12-giu-2017
 *  \date  Ultima modifica 12-giu-2017
 */

package esami_settembre;

import java.util.ArrayList;
import java.util.Random;


public class Exam {
    private Course name;
    private String subject;
    private ArrayList <String> students;
    private boolean accomodated = false;
    
    public Exam(Course className){
        Random r = new Random();
        this.name  = className;
        this.students = new ArrayList<>();
   }

    public boolean isAccomodated() {
        return accomodated;
    }

    public void accomodate() {
        this.accomodated = true;
    }
    
    public String getClassName() {
        return name.getName();
    }

    public void setClassName(Course className) {
        this.name = className;
        
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public ArrayList<String> getStudents() {
        return students;
    }

    public void addStudent(String student) {
        students.add(student);
    }
    
    public boolean isCompatible(Exam e){
        if( !this.name.getName().equals(e.name.getName()))
            return true;
        ArrayList <String> l = new ArrayList<>(students);
        return !l.removeAll(e.getStudents());
    }
    
    @Override
    public String toString(){
        String r = "\nClasse: " + name.getName() + "\nMateria: " + this.subject;
        r += "\nStudenti: " +  students.toString();
        return r;
    }
}
