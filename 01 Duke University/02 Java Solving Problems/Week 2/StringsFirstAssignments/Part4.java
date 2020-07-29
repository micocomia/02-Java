import edu.duke.*;
import java.io.*;

/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public String urlchecker(String url){
        URLResource ur = new URLResource(url);
        String result = "";
        
        for (String word : ur.words()){
            String init = word;
            String temp = word.toLowerCase();
            int pos = temp.indexOf("youtube.com");
            
            if (pos != -1){
                int start = temp.lastIndexOf("\"",pos);
                int end = temp.indexOf("\"",pos+1);
                
                result = init.substring(start+1,end);
                System.out.println(result);
            }
            
        }
        
        return result = "";
    }
    
    public void testing(){
        String test1 = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        urlchecker(test1);
    }
}
