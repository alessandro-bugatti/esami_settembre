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


public class Session {
    private final ArrayList <Classroom> classrooms;
    private final int max_classrooms_in_session;

    public Session(int max_classrooms_in_session) {
        classrooms = new ArrayList<>();
        this.max_classrooms_in_session = max_classrooms_in_session;
    }
    
    public boolean addExam(Exam insertingExam){
        for (Classroom c: classrooms)
            if (c.canContainAnotherExam(insertingExam))
            {
                c.addExam(insertingExam);
                return true;
            }
        if (classrooms.size() >= max_classrooms_in_session)
            return false;
        for (Classroom c: classrooms)
            if (!c.isCompatible(insertingExam))
                return false;
        
        Classroom newClassroom = new Classroom(25);
        newClassroom.addExam(insertingExam);
        classrooms.add(newClassroom);
        return true;
        
    }
    
    @Override
    public String toString(){
        return "\nEsami in questa sessione: " + classrooms.toString();
    }
    
}
