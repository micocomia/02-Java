import edu.duke.*;
import java.io.*;

public class Quiz {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while(currIndex != -1) {
            int diff = currIndex - startIndex;
            if(diff % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }

        return -1;
    }
    
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1 || where == -1) {
            return "";
        }

        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        int minIndex = 0;
        
        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }

        if(minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }

        if(minIndex == -1) {
            return "";
        }
        
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public StorageResource getAllGenes(String dna){
        String gene = "";
        int startIndex = 0;

        StorageResource sr = new StorageResource();
        
        while (true){
            String currentGene = findGene(dna, startIndex);
            
            if (currentGene.isEmpty()){
                break;
            }
            
            sr.add(currentGene);
            
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        
        return sr;
    }
    
    public double cgRatio(String dna){
        double res = 0;
        double c = 0;
        double g = 0;
        double length = dna.length();
        
        int temp = 0;
        int startIndex = 0;
        
        while (temp != -1){
            temp = dna.indexOf("C", startIndex);
            
            if (temp != -1){    
                c = c + 1;
            }   
          
            startIndex = temp + 1;
        }
        
        temp = 0;
        startIndex = 0;
        
        while (temp != -1){
            temp = dna.indexOf("G", startIndex);
            
            if (temp != -1){    
                g = g + 1;
            }   
          
            startIndex = temp + 1;
        }
        
        res = (c+g)/length;       
        return res;
    }
    
    public int countCTG(String dna){
        int res = 0;
        int temp = 0;
        int startIndex = 0;
        
        while (temp != -1){
            temp = dna.indexOf("CTG", startIndex);
            
            if (temp != -1){    
                res = res + 1;
            }   
          
            startIndex = temp + 3;
        }
        
        return res;
    }
    
    public void process() {
        int startIndex = 0;
        int count = 0;
        int sixty = 0;
        int cg = 0;
        int ctg = 0;
        String longest = "";
        
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        StorageResource sr = getAllGenes(dna);

        while (true) {
            String gene = findGene(dna, startIndex);
            
            if (gene == "") {
                break;
            }
            
            count++;
            
            if(gene.length() > 60) {
                sixty++;
            }
            
            if (cgRatio(gene) > 0.35){
                cg++;
            }
            
            startIndex = dna.indexOf(gene, startIndex) + gene.length();

        }
        
        for(String s : sr.data()) {
            if(s.length() > longest.length()) {
                longest = s;
            }
        }
        
        ctg = countCTG(dna);
        

        System.out.println("Genes are: " + count);
        System.out.println("Genes >60: " + sixty);
        System.out.println("Genes >0.35: " + cg);
        System.out.println("Genes CTG: " + ctg);
        System.out.println("Longest: " + longest.length());
    }
    
}
