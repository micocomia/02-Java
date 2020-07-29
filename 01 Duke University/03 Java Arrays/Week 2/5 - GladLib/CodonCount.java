import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String, Integer> map; 
    
    public CodonCount(){
        map = new HashMap<String, Integer>();
    }
    
    private void buildCodonMap(int start, String dna){
        dna = dna.toUpperCase();
	dna = dna.substring(start,dna.length());
	
	for(int k = 0; k+3 < dna.length() ; k += 3){
	    String temp = dna.substring(k, k+3);
	    char tempChar = dna.charAt(k+3);
	    
	    if (!Character.isLetter(tempChar)){
	        break;
	    }
	    
	    if (!map.containsKey(temp)){
                map.put(temp,1);
            }else {
                map.put(temp,map.get(temp)+1);
            }
        }
    }
    
    private String getMostCommonCodon(){
        String common = "";
        int max = 0;
        int temp = 0;
        
        for(String s : map.keySet()){
            temp = map.get(s);
            
            if(temp > max){
                max =  temp;
                common = s;
            }
        }
        
        return common;
    }
    
    private void printCodonCounts(int min, int max){
        int temp = 0;
        
        for(String s : map.keySet()){
            temp = map.get(s);
            
            if(temp >= min && temp <= max){
                System.out.println (s + "\t" + temp);
            }
        }
    }
    
    public void test(){
        map.clear();
        FileResource fr = new FileResource();
        String dna = fr.asString();
        int start = 2;
        int min = 6;
        int max = 8;
        
        buildCodonMap(start, dna);
        System.out.println("1) Starting at " + start + 
                            " results to " + map.size() + 
                            " unique codons.\n");
                            
        String common = getMostCommonCodon();                    
        System.out.println("2) Most common codon is " + common 
                            + " with count " + map.get(common) 
                            + ".\n");
        
        System.out.println("3) Codons between " + min + " & "
                            + max + " inclusive: ");
        printCodonCounts(min,max);
    }
}
