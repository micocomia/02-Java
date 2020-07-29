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
    
    public String findGene (String dna){
        int startIndex = dna.indexOf("ATG");
        int stopIndex = 0;
        int stop1 = 0;
        int stop2 = 0;
        int stop3 = 0;
        String gene = "";
        
        if (startIndex == -1){
            return gene = "";
        }
        
        stop1 = findStopCodon(dna, startIndex, "TAA");  
        stop2 = findStopCodon(dna, startIndex, "TAG");  
        stop3 = findStopCodon(dna, startIndex, "TGA");
        
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
        }else{
            gene = dna.substring(startIndex, stopIndex+3);
        }
        
        return gene;
    }
    
    public void printAllGenes(String dna){
        String gene = "";
        int startIndex = 0;
        int stopIndex = 0;
        int stop1 = 0;
        int stop2 = 0;
        int stop3 = 0;
        
        while (true){
            startIndex = dna.indexOf("ATG", stopIndex);
        
            if (startIndex == -1){
                break;
            }
        
            stop1 = findStopCodon(dna, startIndex, "TAA");  
            stop2 = findStopCodon(dna, startIndex, "TAG");  
            stop3 = findStopCodon(dna, startIndex, "TGA");
        
            if (stop1 == -1 || (stop2 != -1 && stop2 < stop1)){
                stopIndex = stop2;
            }else{
                stopIndex = stop1;
            }
            
            if (stopIndex == -1 || (stop3 != -1 && stop3 < stopIndex)){
                stopIndex = stop3;
            }
        
            if (stopIndex == -1){
                break;
            }else{
                System.out.println(dna.substring(startIndex, stopIndex+3));
            }
        }
    }
    
    public void testFindStopCodon(){
        String test1 = "xxxATGxxxyyyxxxyyyTAA";
        String test2 = "ATGxxxyyyTGA";
        String test3 = "xxxxxxATGxxxyyyxxxTAG";
        String test4 = "xATGyyyyxxxxTAAxTGAxyxTAG";
        String test5 = "xyyyyxxxxTAAxTGAxyxTAG";
        String test6 = "xATGyxxxxTAAxyTGAxyxTAG";
        String test7 = "xxxATGxxxyyyxxxyyyTAAxyyyyxxxxTAAxTGAxyxTAGATGxxxyyyTGA";
        
        System.out.println(test1);
        System.out.println("1: " + findGene(test1));
        System.out.println("\n" + test2);
        System.out.println("2: " + findGene(test2));
        System.out.println("\n" + test3);
        System.out.println("3: " + findGene(test3));
        System.out.println("\n" + test4);
        System.out.println("4: " + findGene(test4));
        System.out.println("\n" + test5);
        System.out.println("5: " + findGene(test5));
        System.out.println("\n" + test6);
        System.out.println("6: " + findGene(test6));
        System.out.println("\n" + test7);
        printAllGenes(test7);
    }
}
