/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public int getRank(int year, String name, String gender){
        FileResource fr = new FileResource("data/yob"+year+".csv");
        //FileResource fr = new FileResource("testing/yob"+year+"short.csv");
        int rank = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String currName = rec.get(0);
            
            if (rec.get(1).equals(gender)) {
                if (currName.equals(name)){
                    rank++;
                    return rank;
                }else{
                    rank++;
                }
            }
        }
        
        rank = -1;
        return rank;
    }
    
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource("data/yob"+year+".csv");
        //FileResource fr = new FileResource("testing/yob"+year+"short.csv");
        
        int currRank = 0;
        String name = null;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String currName = rec.get(0);
            
            if (rec.get(1).equals(gender)) {
                currRank++;
                
                if (currRank == rank){
                    name = currName;
                    return name;
                }else{
                    name = "NO NAME";
                }
            }
        }
        
        return name;
    }
    
    public void whatIsNameInYear (String name, int year, int newYear, String gender){
        int rank = 0;
        String newName = null;
        
        rank = getRank(year, name, gender);
        newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear + ".");
    }
    
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int numBoysName = 0;
        int numGirlsName = 0;
        int numName = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                numBoysName += 1;
            }
            else {
                totalGirls += numBorn;
                numGirlsName += 1;
            }
        }
        
        numName = numBoysName + numGirlsName;
        
        System.out.println("\nTotal births = " + totalBirths);
        System.out.println("Female girls = " + totalGirls);
        System.out.println("Male boys = " + totalBoys);
        
        System.out.println("\nTotal names = " + numName);
        System.out.println("Female names = " + numGirlsName);
        System.out.println("Male names = " + numBoysName);
    }
    
    public int yearOfHighestRank(String name, String gender){
        int highYear = 0;
        int currRank = 0;
        int highRank = 10000;
        int currYear = 1880;
                
        // iterate over files
        while (currYear < 2015){
            currRank = getRank(currYear, name, gender);
            
            if (currRank < highRank && currRank != -1){
                highRank = currRank;
                highYear = currYear;
            }
            
            currYear++;
        }
        
        return highYear;
    }
    
    public double getAverageRank(String name, String gender){
        double average = 0;
        int currRank = 0;
        int total = 0;
        int currYear = 1880;
        
        // iterate over files
        while (currYear < 2015){
            currRank = getRank(currYear, name, gender);
            
            if (currRank != -1){
                average += currRank;
                total++;
            }
            
            currYear++;
        }
        
        if(average != 0){
            average = average/total;
        }else{
            average = -1;
        }
        
        return average;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int births = 0;
        int rank = getRank(year,name,gender);
        
        FileResource fr = new FileResource("data/yob"+year+".csv");
        //FileResource fr = new FileResource("testing/yob"+year+"short.csv");
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String currName = rec.get(0);
            String currGender = rec.get(1);
            
            if(currGender.equals(gender)){
                int currRank = getRank(year,currName,gender);
                
                if (currRank < rank && currRank != -1) {
                    births += Integer.parseInt(rec.get(2));
                }else if (currRank >= rank && currRank != -1){
                    System.out.println(rank);
                    System.out.println(currRank);
                    break;
                }
            }
        }
        
        return births;
    }

    public void testTotalBirths () {
        FileResource fr = new FileResource();
        
        int rank = getRank(1960,"Emily","F");
        System.out.println("Rank of " + "Mason" + " is " + rank);
        
        String name = getName(2012,100,"M");
        System.out.println("Name at rank " + 100 + " is " + name);
        
        whatIsNameInYear("Isabella", 2012, 2014, "F");
        //FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
        
        //int year = yearOfHighestRank("Mason","M");
        //System.out.println("\nYear of Highest Rank for " + "Mason" + " is " + year);
        
        //double average = getAverageRank("Ethan","M");
        //System.out.println("\nAverage rank of " + "Ethan" + " is " + average);
        
        int births = getTotalBirthsRankedHigher(2012,"Ethan","M");
        System.out.println("\nTotal births higher than " + "Ethan" + " is " + births);
    }
}
