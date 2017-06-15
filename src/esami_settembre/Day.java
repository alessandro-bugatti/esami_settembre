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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class Day {
    
    private final ArrayList<Session> sessions;
    private final int maxSessions;
    private GregorianCalendar date;

    public Day(int maxSessions) {
        this.sessions = new ArrayList<>();
        this.maxSessions = maxSessions;
    }

    public void setDate(GregorianCalendar myDate) {
        this.date = (GregorianCalendar)myDate.clone();
    }
    
    

    /**
     * Get the value of sessions
     *
     * @return the value of sessions
     */
    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public boolean addSession(Session s){
        if (sessions.size() < maxSessions){
            sessions.add(s);
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
        fmt.setCalendar(date);
        String s = "Data: " + fmt.format(date.getTime()) + "\nSessioni in questa giornata: " + sessions.size();
        s += "\n";
        for (int i=0 ; i < sessions.size(); i++)
            s += "\nSessione " + (i+1)  + "\n" + sessions.get(i);
        return s;
    }
    
    public Session getSession(int index){
        return sessions.get(index);
    }

}
