
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         
         for (String f: fr.lines()){
             records.add(WebLogParser.parseEntry(f));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPsInRange (int low, int high){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         
         for (LogEntry le : records){
             String IP = le.getIpAddress();
             int status = le.getStatusCode();
             
             if(status <= high && status >= low && !uniqueIPs.contains(IP)){
                 uniqueIPs.add(IP);
             }
         }
         
         //for (int k = 0; k < uniqueIPs.size() ; k++){
           // System.out.println(uniqueIPs.get(k));
         //}
         
         return uniqueIPs.size();
     }
        
     public ArrayList uniqueIPVisitsOnDay(String someday){
         ArrayList<String> visits = new ArrayList<String>();
         
         for (LogEntry le : records){
             Date d = le.getAccessTime();
             String date = d.toString();
             String IP = le.getIpAddress();
             
             if (date.contains(someday) && !visits.contains(IP)){     
                 visits.add(IP);
             }
         }
         return visits;
     }
     
     public void printAllHigherThanNum(int num){
         int f = 0;
         ArrayList<Integer> unique = new ArrayList<Integer>();
         
         for (LogEntry le : records){
             int status = le.getStatusCode();
             
             if(status > num){
                 f++;
                 
                 if (!unique.contains(status)){
                     unique.add(status);
                 }
             }
         }
        
         System.out.println("Entries that have a status code greater than " +
                            num + ": " + f);
         System.out.println("Unique status codes: ");
         for (int k = 0; k < unique.size(); k++){
             System.out.println(unique.get(k));
         }
        
     }
     
     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> count = new HashMap<String, Integer>();
         
         for (LogEntry le: records){
             String ip = le.getIpAddress();
             
             if (!count.containsKey(ip)){
                 count.put(ip,1);
             }else{
                 count.put(ip,count.get(ip) + 1);
             }
         }
         
         return count;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> map){
         int visits  = 0;
         
         for (String s: map.keySet()){
             int temp = map.get(s);
             
             if (temp > visits){
                 visits = temp;
             }
         }
         
         return visits;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map){
         ArrayList<String> ips = new ArrayList<String>();
         
         int max = mostNumberVisitsByIP(map);
         
         for (String s: map.keySet()){
             int temp = map.get(s);
             
             if (temp == max){
                 ips.add(s);
             }
         }
         
         return ips;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> count = new HashMap<String, ArrayList<String>>();
         
         for (LogEntry le : records){
             Date d = le.getAccessTime();
             String date = d.toString();
             String IP = le.getIpAddress();
             
             date = date.substring(4,10);
             
             if (!count.containsKey(date)){
                 ArrayList<String> visits = new ArrayList<String>();
                 visits.add(IP);
                 
                 count.put(date, visits);
             }else{
                ArrayList<String> visits = count.get(date);
                visits.add(IP);
                 
                count.put(date, visits);
             }
         }
         
         return count;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map){
         String day = "";
         int max = 0;
         
         for (String s: map.keySet()){
             ArrayList<String> temp = map.get(s);
             int temp2 = temp.size();
             
             if (temp2 > max){
                 max = temp2;
                 day = s;
             }
         }
         
         return day;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay (HashMap<String, ArrayList<String>> map, String day){
         ArrayList<String> f = new ArrayList<String>();
         
         if (map.containsKey(day)){
            HashMap<String, Integer> temp= new HashMap<String, Integer>();
            ArrayList<String> temp2 = map.get(day);
             
            for (int k = 0; k < temp2.size(); k++){
                String s = temp2.get(k);
                
                if (!temp.containsKey(s)){
                    temp.put(s,1);
                }else{
                    temp.put(s,temp.get(s) + 1);
                }
            }
             
            f = iPsMostVisits(temp);
         }
         
         return f;
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         
         for (LogEntry le : records){
             String IP = le.getIpAddress();
             
             if(!uniqueIPs.contains(IP)){
                 uniqueIPs.add(IP);
             }
         }
         
         return uniqueIPs.size();
     }
     
}
