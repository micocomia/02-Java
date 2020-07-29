import java.util.*;
import java.io.*;
import edu.duke.*;

public class WordsInFiles {
    private HashMap<String,ArrayList<String>> map;
    
    public WordsInFiles(){
        map = new HashMap<String,ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        
        for (String word:fr.words()){
            //word = word.toLowerCase();
            String filename = f.getName();
            
            if (!map.containsKey(word)){ 
                ArrayList<String> temp2 = new ArrayList<String>();
                temp2.add(filename);
                map.put(word,temp2);
            }else{
                ArrayList<String> temp2 = new ArrayList<String>();
                temp2 = map.get(word);
                if(!temp2.contains(filename)){
                    temp2.add(filename);
                }
            }
        }
    }
    
    private void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        
        for (File f:dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber(){
        int max = 0;
        int temp = 0;
        ArrayList<String> temp2 = new ArrayList<String>();
        
        for(String s : map.keySet()){
            temp2 = map.get(s);
            temp = temp2.size();
            
            if(temp > max){
                max =  temp;
            }
        }
        
        return max;
    }
    
    private ArrayList wordsInNumFiles(int number){
        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<String> temp2 = new ArrayList<String>();
        int size = 0;
        
        for(String s : map.keySet()){
            temp2 = map.get(s);
            size = temp2.size();
            
            if(size == number){
                temp.add(s);
            }
        }
        
        return temp;
    }
    
    private void printFilesIn(String word){
        word = word.toLowerCase();
        ArrayList<String> temp = new ArrayList<String>();
        
        for(String s : map.keySet()){
            if(s.equals(word)){
                temp = map.get(s);
                for (int k = 0; k < temp.size(); k++){
                    System.out.println(temp.get(k));
                }
            }
        }
    }
    
    private void printMap(){
        ArrayList<String> temp = new ArrayList<String>();
        System.out.println("\nHash Map: ");
        
        for(String s: map.keySet()){
            temp = map.get(s);
            
            System.out.println("\nKey: " + s);
            for (int k = 0; k < temp.size(); k++){
                System.out.println(temp.get(k));
            }
        }
    }
    
    public void tester(){
        buildWordFileMap();
        ArrayList<String> temp = new ArrayList<String>();
        
        int max = maxNumber();
        String word = "laid";
        int num = 7;
        
        System.out.println("Max number: " + max + "\n");
        
        System.out.println("Filenames in which the word \"" + word + "\" appear(s):");
        printFilesIn(word);
        
        System.out.println("\nNo. of words that appear in exactly " + num + " files(s):");
        temp = wordsInNumFiles(num);
        System.out.println(temp.size());  
        
    }
}
