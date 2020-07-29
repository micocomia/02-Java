import edu.duke.*;
import java.io.*;

public class Part1 {
    public String findSimpleGene(String dna){
        String result = "";
        
        int start = dna.indexOf("ATG");
        int end = dna.indexOf("TTA",start + 3);
        
        if (start == -1 || end == -1){
            return result = "";
        }
        
        if (((end-start)%3) == 0){
            result = dna.substring(start,end+3);
        } else {
            return result = "";
        }
        
        return result;
    }
    
    public void testSimpleGene(){
        String dna1 = "ATTTGTATATTA"; // no ATG
        String dna2 = "ATGTATAATATA"; // no TTA
        String dna3 = "TATATATAATAA"; // no ATG or TTA
        String dna4 = "ATGCGTCGCGTATTA"; // okay
        String dna5 = "ATGCGTGCGTATTA"; // not a multiple of 3
        
        int n = 0;
        
        String res1 = findSimpleGene(dna1);
        String res2 = findSimpleGene(dna2);
        String res3 = findSimpleGene(dna3);
        String res4 = findSimpleGene(dna4);
        String res5 = findSimpleGene(dna5);
        
        System.out.println("1 " + res1);
        System.out.println("2 " + res2);
        System.out.println("3 " + res3);
        System.out.println("4 " + res4);
        System.out.println("5 " + res5);
    }
}
