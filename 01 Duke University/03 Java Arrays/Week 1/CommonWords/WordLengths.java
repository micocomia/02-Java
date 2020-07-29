import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        for (String s: resource.words()){
           char init = s.charAt(0);
           char fin = s.charAt(s.length()-1);
           int length = s.length();
           
           if(!Character.isLetter(init)){
               length--;
           } 
           
           if(!Character.isLetter(fin)){
               length--;
           }
         
           if(length < counts.length){
              counts[length] += 1;
           }else{
              counts[counts.length - 1] += 1; 
           }
        }
        
        int i = 0;
        
        for (i = 1; i < counts.length; i++){
            if (counts[i] != 0 && i != (counts.length - 1)){
                System.out.println("Number of words with length " + i + ": " + counts[i]);
            }else if (counts[i] != 0 && i == (counts.length - 1)){
                System.out.println("Number of words with length >= " + i + ": " + counts[i]);
            }
        }
    }
    
    public int indexOfMax(int[] values){
        int max = 0;
        int temp = 0;
        int maxtemp = 0;
        
        for (int i = 0; i < values.length; i++){
            temp = values[i];
            
            if (temp > maxtemp){
                maxtemp = temp;
                max = i;
            }
        }
        
        return max;
    }
    
    public void test(){
        FileResource fr = new FileResource();
        int[] counts = new int[1000000000];
        
        countWordLengths(fr, counts);
        System.out.println(indexOfMax(counts));
    }
}
