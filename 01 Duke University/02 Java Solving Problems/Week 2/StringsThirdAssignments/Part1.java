import edu.duke.*;
import java.io.*;


public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon, startIndex);
        
        if (stopIndex == -1){
            return -1;
        }
        
        if ((stopIndex - startIndex) % 3 == 0){
            return stopIndex;
        }
        
        return -1;
    }
    
    public String findGene(String dna, int start){
        int startIndex = dna.indexOf("ATG", start);
        
        if (startIndex == -1){
            return "";
        }
        
        int stop1 = findStopCodon(dna, startIndex, "TAA");  
        int stop2 = findStopCodon(dna, startIndex, "TAG");  
        int stop3 = findStopCodon(dna, startIndex, "TGA");
        
        int stopIndex = 0;
        
        if (stop1 == -1 || (stop2 != -1 && stop2 < stop1)){
            stopIndex = stop2;
        }else{
            stopIndex = stop1;
        }
        
        if (stopIndex == -1 || (stop3 != -1 && stop3 < stopIndex)){
            stopIndex = stop3;
        }
        
        if (stopIndex == -1){
            return "";
        }
        
        return dna.substring(startIndex, stopIndex+3);
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
    
    public void processGenes(StorageResource sr){
        StorageResource nine = new StorageResource();
        StorageResource cg = new StorageResource();
        StorageResource genes = new StorageResource();
        
        String temp = "";
        String templongest = "";
        String longest = "";
        
        for (String s: sr.data()){
            
           if (s.length() > 60){
                nine.add(s);
           }
            
            if (cgRatio(s) > 0.35){
                cg.add(s);
           }
        }
        
        for (String s: nine.data()){
            System.out.println(s);
        }
        System.out.println(">60 Characters: " + nine.size() + "\n");
        
        for (String s: cg.data()){
            System.out.println(s);
        }
        System.out.println("CG Ratio >0.35: " + cg.size()+ "\n");
        
        for (String s: sr.data()){
            if (s.length() > longest.length()){
                longest = s;
            }
        }
        System.out.println("Longest gene: " + longest + "\n");
        System.out.println("Length of longest gene: " + longest.length() + "\n");
        
        for (String s: sr.data()){
            System.out.println(countCTG(s) + "\n");
        }
    }
    
    public void testProcessGenes(){
        int startIndex = 0;
        int count = 0;
        int sixty = 0;
        
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();

        while(true){
            String gene = findGene(dna, startIndex);
            
            if (gene == "") {
                break;
            }
                
            count++;
            
            startIndex = dna.indexOf(gene, startIndex) + gene.length();

            if(gene.length() > 60) {
                sixty++;
            }
        }
        
        System.out.println("Genes: " + count);
        System.out.println("Genes >60: " + sixty);
    }
}
