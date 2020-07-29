import java.util.*;
import edu.duke.*;

public class VigenereBreaker{
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sliced = new StringBuilder();
        
        for (int k = whichSlice; k < message.length(); k += totalSlices){
            char temp = message.charAt(k);
            sliced.append(temp);
        }
        
        return sliced.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        String[] sliced = new String[klength];
       
        CaesarCracker cc = new CaesarCracker();
        
        for (int k = 0; k < klength; k++){
            sliced[k] = sliceString(encrypted, k, klength);
            key[k] = cc.getKey(sliced[k]);
            
        }
        
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary = new HashSet<String>();
        
        for (String line: fr.lines()){
            String temp = line.toLowerCase();
            dictionary.add(temp);
        }
        
        return dictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        
        for(String s : message.split("\\W+")){
            s = s.toLowerCase();
            if (dictionary.contains(s)){
                count += 1;
            }
        }
        
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary, int mode){
        String decrypted = "";
        String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        int max = 0;
        int length = 0;
        int wordcount = 0;
        char common = mostCommonCharIn(dictionary);
        System.out.println(common);
        
        
        for (int k = 1; k<100; k++){
            int[] tempKey = tryKeyLength(encrypted, k, common);
            VigenereCipher vc = new VigenereCipher(tempKey);
            String temp = vc.decrypt(encrypted);         
            int words = countWords(temp, dictionary);
            
            if (words > max){
                max = words;
                decrypted = temp;
                length = k;
            }
            
        }
        
        for (String s: decrypted.split("\\W+")){
            wordcount += 1;
        }
        
        if (mode == 1){
            int[] key = tryKeyLength(encrypted, length, common);
            System.out.println("\nThe key length is \"" + key.length + "\" and the keys are:");
            
            for (int i = 0; i < key.length; i++){
                System.out.println(key[i] + "\t=\t" + alph.charAt(key[i]));
            }
            
            System.out.println("Valid words to total: " + max + "/" + wordcount);
        }
          
        return decrypted;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> count = new HashMap<Character, Integer>();
        int max = 0;
        char common = ' ';
        
        for (String s: dictionary){
            String temp = s.toLowerCase();
            
            for (char c: s.toCharArray()){
                if (!count.containsKey(c)){
                    count.put(c,1);
                }else{
                    count.put(c, count.get(c) + 1);
                }
            }
        }
        
        for (char c: count.keySet()){
            int temp = count.get(c);
            
            if (temp > max){
                common = c;
                max = temp;
            }
        }
        
        return common;
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> map){
        String decrypted = "";
        String language = "";
        int max = 0;
        int i = 0;
        
        for (String s : map.keySet()){  
            String temp = breakForLanguage(encrypted, map.get(s),0);
            i = countWords(temp, map.get(s));
            //System.out.println("\nCurrent language: " + s);
            
            if (i > max){
                max = i;
                language = s;
                decrypted = temp;
            }
        }
        
        for (String s: map.keySet()){
            String temp = breakForLanguage(encrypted, map.get(s),0);
            i = countWords(temp, map.get(s));
            
            if (i == max){
                temp = breakForLanguage(encrypted, map.get(s),1);
                System.out.println("\nThe language of the text is: " + language);
            }
        }
 
        return decrypted;
    }
    
    public void breakVigenere () {
        // Load dictionaries
        String[] languages = {"Danish","Dutch","English","French","German","Italian"
                                ,"Portuguese","Spanish"};
        HashMap<String, HashSet<String>> map = new HashMap<String,HashSet<String>>();
        
        for (String s: languages){
            FileResource fd = new FileResource("dictionaries/"+s);
            HashSet<String> dictionary = readDictionary(fd);
            
            if(!map.containsKey(s)){
                map.put(s,dictionary);
                System.out.println(s + " dictionary was loaded");
            }
        }

        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        // Get and print keys of message
        String decrypted = breakForAllLangs(encrypted, map);
        System.out.println("\nDecrypted Message:");
        System.out.println(decrypted);
    }
    
}
