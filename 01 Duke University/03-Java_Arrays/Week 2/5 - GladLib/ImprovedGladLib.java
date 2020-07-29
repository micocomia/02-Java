import edu.duke.*;
import java.util.*;

public class ImprovedGladLib {
    private HashMap<String, ArrayList<String>> myMap; 
    private ArrayList<String> used;
    private ArrayList<String> categories;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    
    public ImprovedGladLib(){
        used = new ArrayList<String>();
        categories = new ArrayList<String>();
        myMap = new HashMap<String, ArrayList<String>>();
        
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    private void totalWordsInMap(){
        ArrayList<String> temp = new ArrayList<String>();
        int temp2 = 0;
        int current = 0;
        
        for(String s: myMap.keySet()){
            temp = myMap.get(s);
            temp2 = temp.size();
            current += temp2;
        }
        
        System.out.println("Total words in map: " + current);
    }
    
    private void totalWordsConsidered(){
       ArrayList<String> temp = new ArrayList<String>();
       int temp2 = 0;
       int considered = 0;
       
       for(String s: myMap.keySet()){
            temp = myMap.get(s);
            temp2 = temp.size();
            
            if(categories.indexOf(s) != -1){
                considered += temp2;
            }
       }
        
       System.out.println("Total words considered: " + considered);
    }
    
    public ImprovedGladLib(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"country","noun","animal",
                            "adjective","name","color",
                            "timeframe","verb","fruit"};
                            
        for (String s: labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        ArrayList<String> temp = new ArrayList<String>();     
        int index = categories.indexOf(label);
        
        if (index == -1){
            categories.add(label);
        }
        
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        int index = used.indexOf(sub);
        
        while (true){
            if (index == -1){
                used.add(sub);
                break;
            }
            
            sub = getSubstitute(w.substring(first+1,last));
            index = used.indexOf(sub);
        }
        
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        used.clear();
        categories.clear();
        
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n\nNumber of words replaced: " + used.size());
        totalWordsInMap();
        totalWordsConsidered();    
    }
}
