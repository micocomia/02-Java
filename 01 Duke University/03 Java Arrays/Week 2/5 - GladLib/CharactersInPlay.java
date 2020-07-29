import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> name;
    private ArrayList<Integer> count;
 
    public CharactersInPlay(){
        name = new ArrayList<String>();
        count = new ArrayList<Integer>();
    }
    
    public void update(String person){
         int pIndex = name.indexOf(person);
            
         if(pIndex == -1){
            name.add(person);
            count.add(1);
         }else{
            int counts = count.get(pIndex) + 1;
            count.set(pIndex, counts);
         }
    }
    
    
    public void findAllCharacter(){
        FileResource fr = new FileResource();
        
        for (String line : fr.lines()){
            String temp = line.toUpperCase();
            int index = temp.indexOf(".");
            
            if (index != -1){
                String person = line.substring(0,index);
                update(person);
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2){
        for (int k = 0; k < name.size() ; k++){
            if (count.get(k) >= num1 && count.get(k) <= num2){
                System.out.println(name.get(k) + "\t" + count.get(k));
            }
        }
    }
    
    public int findMax(){
        int max = count.get(0);
        int maxIndex = 0;
        for(int k=0; k < count.size(); k++){
            if (count.get(k) > max){
                max = count.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    
    public void tester(){
        name.clear();
        count.clear();
        findAllCharacter();
        
        int index = findMax();
        int min = 50;
        int max = 150;
        
        System.out.println("Max Character/Count: "+ name.get(index)+" "+count.get(index));
        System.out.println("Characters with minimum of " + min + " and maximum of " + max + " parts:");
        charactersWithNumParts(min, max);
    }
}
