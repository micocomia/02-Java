import edu.duke.*;
import org.apache.commons.csv.*;

public class test1 {

    public String countryInfo(CSVParser parser, String country){
        String result = "";
        for (CSVRecord record : parser){
            String currCountry = record.get("Country");
            
            if (currCountry.contains(country)){
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                
                result = country + ": " + exports + ": " + value;
            }
        }
        
        return result;
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportitem1, String exportitem2){
        for (CSVRecord record : parser){
            String exports = record.get("Exports");
            
            if (exports.contains(exportitem1) && exports.contains(exportitem2)){
                System.out.println(record.get("Country"));
            }
        }                                                                     
    }
    
    public int numberOfExporters(CSVParser parser, String exportitem){
        int n = 0;
        
        for (CSVRecord record : parser){
            String exports = record.get("Exports");
            
            if (exports.contains(exportitem)){
                n++;
            }
        }
        
        return n;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            
            if (value.length() > amount.length()){
                 System.out.println(record.get("Country") + " " + value);
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        
        System.out.println("Country Info: " + countryInfo(parser, "Nauru") + "\n");
        
        parser = fr.getCSVParser();
        System.out.println("Exporters of 2 Products:");
        listExportersTwoProducts(parser, "cotton", "flowers");
        
        parser = fr.getCSVParser();
        System.out.println("\nNo of Exporters: " + numberOfExporters(parser, "cocoa"));
        
        parser = fr.getCSVParser();
        System.out.println("\nBig Exporters:");
        bigExporters(parser, "$999,999,999,999");
    }
    
}
