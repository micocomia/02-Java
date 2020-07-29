
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    private String file = "weblog2_log";
    
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        
        la.readFile(file);
        la.printAll();
    }
    
    public void tester(){
        LogAnalyzer la = new LogAnalyzer();
        String date = "Sep 30";
        ArrayList<String> visits = new ArrayList<String>();
        
        la.readFile(file);
        
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " unique IPs\n");
        
        la.printAllHigherThanNum(400);
        
        visits = la.uniqueIPVisitsOnDay(date);
        System.out.println("\nUnique IP visits on " + date + " : " + visits.size());
        
        int low = 200;
        int max = 299;
        int range = la.countUniqueIPsInRange(low,max);
        System.out.println("\nThere are " + range + " unique IPs whose status code is between "
                            + low +","+max);
                            
        HashMap<String,Integer> count = la.countVisitsPerIP();
        System.out.println("\nVisits by IPs " + count);
        
        int most = la.mostNumberVisitsByIP(count);
        System.out.println("\nMost number of visit by IP/s: " + most);
        
        ArrayList<String> ipsmost = la.iPsMostVisits(count);
        System.out.println("\nIPs with most number of visits: " + ipsmost);
        
        HashMap<String,ArrayList<String>> ipsdays = la.iPsForDays();
        System.out.println("\nIPs per day: " + ipsdays);
        
        String day = la.dayWithMostIPVisits(ipsdays);
        System.out.println("\nDay with most IP Visits: " + day);
        
        ArrayList<String> ipsspec = la.iPsWithMostVisitsOnDay(ipsdays, date);
        System.out.println("\nIP/s with most visits on " + date + ": " + ipsspec);
    }
    
    
}
