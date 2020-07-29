
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String result = "";
        String init = dna;
        String temp = dna.toUpperCase();
        int n = 0;
        
        startCodon.toUpperCase();
        stopCodon.toUpperCase();
        
        if (dna != temp){
            dna = temp;
            n = 1;
        }
     
        int start = dna.indexOf(startCodon);
        int end = dna.indexOf(stopCodon,start + 3);
        
        if (start == -1 || end == -1){
            return result = "";
        }
        
        if (((end-start)%3) == 0){
            result = dna.substring(start,end+3);
        } else {
            return result = "";
        }
        
        if (n == 1){
            result = result.toLowerCase();
        }
        
        return result;
    }
    
    public void testSimpleGene(){
        String dna1 = "ATTTGTATATTA"; // no ATG
        String dna2 = "ATGTATAATATA"; // no TTA
        String dna3 = "TATATATAATAA"; // no ATG or TTA
        String dna4 = "AAATGCCCTAACTAGATTAAGAAACC"; // okay
        String dna5 = "atgcgtcgcgtatta"; // okay
        String dna6 = "ATGCGTGCGTATTA"; // not a multiple of 3
        
        int n = 0;
        
        String res1 = findSimpleGene(dna1,"ATG","TTA");
        String res2 = findSimpleGene(dna2, "ATG","TTA");
        String res3 = findSimpleGene(dna3, "ATG","TTA");
        String res4 = findSimpleGene(dna4, "ATG","TAA");
        String res5 = findSimpleGene(dna5, "ATG","TTA");
        String res6 = findSimpleGene(dna5, "ATG","TTA");
        
        System.out.println("1 " + res1);
        System.out.println("2 " + res2);
        System.out.println("3 " + res3);
        System.out.println("4 " + res4);
        System.out.println("5 " + res5);
    }
}
