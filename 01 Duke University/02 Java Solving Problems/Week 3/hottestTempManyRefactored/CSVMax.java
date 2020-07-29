/**
 * Find the highest (hottest) temperature in any number of files of CSV weather data chosen by the user.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar, 0);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar, 1);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        double average = 0;
        double currentTemp = 0;
        int total = 0;
 
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            total++;
            currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            
            average = currentTemp + average;
        }
        
        average = average/total;
        //The largestSoFar is the answer
        return average;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        //start with largestSoFar as nothing
        double average = 0;
        double currentTemp = 0;
        double currentHumidity = 0;
        int total = 0;
        
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));

            if (currentHumidity >= value){
                total++;
                average = currentTemp + average;
            }
        }
        
        if (total == 0){
            average = 0;
        }else{
            average = average/total;
        }
        
        return average;
    }


    public void testInDay () {
        FileResource fr = new FileResource();
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        CSVRecord smallesttemp = coldestHourInFile(fr.getCSVParser());
        CSVRecord smallesthumi = lowestHumidityInFile(fr.getCSVParser());
        double average = averageTemperatureInFile(fr.getCSVParser());
        double temphumid = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        
        System.out.println("Hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("DateUTC"));
        System.out.println("Coldest temperature was " + smallesttemp.get("TemperatureF") +
                   " at " + smallesttemp.get("DateUTC"));
        System.out.println("Lowest humidity was " + smallesthumi.get("Humidity") +
                   " at " + smallesthumi.get("DateUTC"));
        System.out.println("Average temperature in file is " + average);
        
        if (temphumid == 0){
            System.out.println("No temperatures with that humidity");
        }else{
            System.out.println("Average temperature when high Humidity is " + temphumid);
        }
    }

    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            // use method to compare two records
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar, 1);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
    
    public File fileWithColdestTemperature() {
        CSVRecord smallestSoFar = null;
        CSVRecord temp = null;
        File smallestFile = null;
        
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            // use method to compare two records
            temp = smallestSoFar;
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar, 0);
            
            if (smallestSoFar != temp){
                smallestFile = f;
            }
        }
        
        //The smallestSoFar is the answer
        FileResource fr = new FileResource(smallestFile);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        
        System.out.println("Coldest daw was in file" + smallestFile);
        System.out.println("Coldest temperature on that day was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("TimeEST"));
        System.out.println("All temperatures on the coldest day were:");
        
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser){
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
	}
	
	return smallestFile;
    }

    public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar) {
        //If largestSoFar is nothing
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp > largestTemp) {
                //If so update largestSoFar to currentRow
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }
    
    public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar, int mode) {
        //If largestSoFar is nothing
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        //Otherwise
        else {
            double current = 0;
            double smallest = 0;
            
            if (mode == 0){
                current = Double.parseDouble(currentRow.get("TemperatureF"));
                smallest = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            }else if (mode == 1){
                String check = currentRow.get("Humidity");
                if (check.contains("N/A")){
                    return smallestSoFar;
                }
                
                current = Double.parseDouble(currentRow.get("Humidity"));
                smallest = Double.parseDouble(smallestSoFar.get("Humidity"));
            }
            
            //Check if currentRow’s temperature > largestSoFar’s
            if (current < smallest && current != -9999) {
                //If so update largestSoFar to currentRow
                smallestSoFar = currentRow;
            }
	}
	
	return smallestSoFar;
    }

    public void testInManyDays () {
        CSVRecord largest = hottestInManyDays();
        CSVRecord smallesthumidity = lowestHumidityInManyFiles();
        File smallestFile = null;
	System.out.println("hottest temperature was " + largest.get("TemperatureF") +
				   " at " + largest.get("DateUTC") + " \n");
	System.out.println("Lowest Humidity was " + smallesthumidity.get("Humidity") +
				   " at " + smallesthumidity.get("DateUTC")+ " \n");
				 
	smallestFile = fileWithColdestTemperature();
    }
}
