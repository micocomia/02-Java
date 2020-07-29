
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public String twoOccurences (String stringa, String stringb){
        int length = stringa.length();
        String result = "";
        
        int check1 = 0;
        int check2 = 0;

        check1 = stringb.indexOf(stringa);
        check2 = stringb.indexOf(stringa, check1+length);
        
        if (check1 == -1 || check2 == -1){
            return result = "False";
        } else{
            return result = "True";
        }
    }
    
    public String lastPart  (String stringa, String stringb){
        int length = stringa.length();
        int lengthb = stringb.length();
        String result = "";
        
        int check1 = 0;
        int check2 = 0;

        check1 = stringb.indexOf(stringa);
        
        if (check1 == -1){
            return result = stringb;
        } else{
            return result = stringb.substring(check1+length,lengthb);
        }
    }
    
    public void testing (){
        String test1 = "A story by Abby Long";
        String test2 = "banana";
        String test3 = "ctgtatgta";
        String test4 = "forest";
        
        // twoOccurences
        String res1 = twoOccurences("by",test1);
        String res2 = twoOccurences("a",test2);
        String res3 = twoOccurences("atg",test3);
        
        System.out.println("Result 1: " + res1);
        System.out.println("Result 2: " + res2);
        System.out.println("Result 3: " + res3);
        
        // lastPart
        
        res1 = lastPart("an",test2);
        res2 = lastPart("zoo",test4);
        System.out.println("\nResult 1: " + res1);
        System.out.println("Result 2: " + res2);
        
        
    }
}
