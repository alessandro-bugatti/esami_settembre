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
    /**Lista delle classi che contengono gli esami*/
    private final ArrayList <Classroom> classrooms;
    /**Numero massimo di classi che possono essere usate in una sessione*/
    private final int max_classrooms_in_session; 

    /**
     * Crea la lista delle classi e setta il numero massimo di classi
     * @param max_classrooms_in_session Numero massimo di aule che possono
     * essere contenute in una sessione
     */
    public Session(int max_classrooms_in_session) {
        classrooms = new ArrayList<>();
        this.max_classrooms_in_session = max_classrooms_in_session;
    }
    
    /**
     * Aggiunge un esame nella sessione se è possibile:
     * - se c'è un'aula con lo stesso professore e gli alunni ci stanno viene aggiunto lì
     * - se non ci sono aule libere non lo aggiunge
     * - se ci sono aule libere e l'esame non contiene un allievo già presente in altre aule aggiunge un'aula con l'esame inserito
     * @param insertingExam Esame che deve essere inserito nella sessione
     * @return true se l'esame viene inserito, false altrimenti
     */
    
    public boolean addExam(Exam insertingExam){
        //Controllo ogni aula e se l'esame può essere contenuto in una di 
        //queste perchè ci sono già uno o più esami con lo stesso professore
        //aggiungo l'esame all'aula
        for (Classroom c: classrooms)
            if (c.canContainAnotherExam(insertingExam))
            {
                c.addExam(insertingExam);
                return true;
            }
        //Se l'esame non può essere messo in stanze precedentemente allocate
        //e tutte le aule della sessione sono piene allora non può
        //aggiunto a questa sessione
        if (classrooms.size() >= max_classrooms_in_session)
            return false;
        //Se ci sono ancora stanze che potrebbero essere utilizzate verifico
        //che l'esame sia compatibile con la sessione, cioè che nella aule già 
        //utilizzate non sia presente un alunno presente anche in questo esame
        for (Classroom c: classrooms)
            if (!c.isCompatible(insertingExam))
                return false;
        //Se arrivo qua vuol dire che si sono aule libere e quindi posso
        //creare una nuova aula e inserirgli l'esame
        Classroom newClassroom = new Classroom(25);
        newClassroom.addExam(insertingExam);
        classrooms.add(newClassroom);
        return true;
        
    }

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }
    
    @Override
    public String toString(){
        return "\nEsami in questa sessione: " + classrooms.toString();
    }
    
}
