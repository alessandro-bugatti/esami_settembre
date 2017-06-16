/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esami_settembre;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alessandro Bugatti <alessandro.bugatti@gmail.com>
 */
public class Esami_settembre {

    private static Day days[];
    private static ArrayList <Course> classes;
    private static int NUMBER_OF_DAYS = 4;
    private static int NUMBER_OF_SESSIONS = 6;
    private static int MAX_CLASSROMS_IN_SESSION = 10;
    private static Map<String, ArrayList<String[]> > importedData;
    
    /*
    private static void createFixture(int classes, int exams_per_classes){
        for (int i = 0; i < classes; i++)
            Esami_settembre.classes.add(new Course("Class " + i));
        for (Course i: Esami_settembre.classes){
            i.createExams(exams_per_classes);
        }
    }*/
    
    
    private static void createCalendar(){
        Collections.sort(Esami_settembre.classes);
        for (Course i: Esami_settembre.classes)
        {
            int currentExam = 0;
            while (currentExam < i.getNumberOfExams()){
                int day = 0;
                int session = 0;
                while(!i.getExam(currentExam).isAccomodated() && day < NUMBER_OF_DAYS)
                {
                    if (days[day].getSession(session).addExam(i.getExam(currentExam)))
                        i.getExam(currentExam).accomodate();
                    else{
                        session++;
                        if (session == NUMBER_OF_SESSIONS)
                        {
                            session = 0;
                            day++;
                        }
                    }
                }
                currentExam++;
            }
        }
    }
    
    private static void loadData(String filename) throws FileNotFoundException{
        BufferedReader buf = new BufferedReader(new FileReader(filename));
        String s;
        try {
            while ((s = buf.readLine()) != null){
                String data[] = s.split(";");
                if (!importedData.containsKey(data[0]))
                {
                    ArrayList<String[]> line = new ArrayList<>();
                    line.add(data);
                    importedData.put(data[0], line);
                }
                else
                {
                    importedData.get(data[0]).add(data);
                }
            }   
        } catch (IOException ex) {
            Logger.getLogger(Esami_settembre.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (String classe : importedData.keySet()){
            Course c = new Course(classe);
            try{
                for ( String[] line:importedData.get(classe))
                {
                    c.addExam(line[3], line[1] + " " + line[2], line[5]);
                }
            }
            catch(Exception ex){
                Logger.getLogger(Esami_settembre.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            classes.add(c);
        }
        
    }
    
    private static void writeHumanReadaleFile(String filename)
    {
        BufferedWriter bufOut = null;
            try {
                bufOut = new BufferedWriter(new FileWriter(filename  + ".txt"));
            } catch (IOException ex) {
                Logger.getLogger(Esami_settembre.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (Day d: days){
                try {
                    bufOut.write(d.toString());
                    bufOut.write("\n");
                } catch (IOException ex) {
                    Logger.getLogger(Esami_settembre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                bufOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Esami_settembre.class.getName()).log(Level.SEVERE, null, ex);
            }
    }   
    
    private static void writeCSVFile(String filename)
    {
        BufferedWriter bufOut = null;
            try {
                bufOut = new BufferedWriter(new FileWriter(filename  + ".csv"));
            } catch (IOException ex) {
                Logger.getLogger(Esami_settembre.class.getName()).log(Level.SEVERE, null, ex);
            }
            int number = 1;
            for (Day d: days){
                try {
                    bufOut.write("Giornata " + number + ";" + d.getDateAsString() + ";;;;;;;;");
                    int session_number = 1;
                    for (Session s: d.getSessions()){
                        bufOut.write("\nSessione " + session_number + ";;;;;;;;;");
                        bufOut.write("\nClasse 1");
                        for (int i = 2; i <= MAX_CLASSROMS_IN_SESSION; i++)
                        {
                            bufOut.write(";Classe " + i);
                        }
                        bufOut.write("\n");
                        for (Classroom c: s.getClassrooms())
                        {
                            bufOut.write(c.toCSV()+";");
                        }
                        bufOut.write("\n");
                        session_number++;
                    }
                    bufOut.write("\n\n");
                    number++;
                } catch (IOException ex) {
                    Logger.getLogger(Esami_settembre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                bufOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Esami_settembre.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Esami_settembre.days = new Day[NUMBER_OF_DAYS];
        importedData = new HashMap<>();
        GregorianCalendar c = new GregorianCalendar(2017, Calendar.AUGUST, 28);
        for (int i = 0; i < NUMBER_OF_DAYS; i++){
            days[i] = new Day(NUMBER_OF_SESSIONS);
            days[i].setDate(c);
            c.add(Calendar.DAY_OF_MONTH, 1);
            //System.out.println(c.get(Calendar.DAY_OF_WEEK));
            if (c.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 1)
                c.add(Calendar.DAY_OF_MONTH, 1);
            for (int j = 0; j < NUMBER_OF_SESSIONS; j++)
                days[i].addSession(new Session(MAX_CLASSROMS_IN_SESSION));
            //System.out.println(d);
        }
        //System.out.println(days[0].getSession(0));
        classes = new ArrayList<>();
        try {
            loadData("prove2016completo.csv");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Esami_settembre.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        createCalendar();
        writeHumanReadaleFile("output");
        writeCSVFile("output");
        
        
        for (Day d: days){
            System.out.println(d);
        }
        int totalExam = 0;
        for (Course i: classes){
            System.out.println(i.getName());
            System.out.println(i.examsSituation());
            totalExam += i.getNumberOfExams();
        }
        System.out.println("Total number of exams: " + totalExam);
    }

    
    
}
