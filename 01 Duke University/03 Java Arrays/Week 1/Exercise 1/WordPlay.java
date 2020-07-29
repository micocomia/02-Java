
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
public class WordPlay {
    public boolean isVowel(char ch){
        ch = Character.toUpperCase(ch);
        
        if (ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U'){
            return true;
        }
        
        return false;
    }
    
    public String replaceVowels(String phrase, char ch){
        int i = 0;
        StringBuilder phrasetemp = new StringBuilder(phrase);
        
        for (i = 0; i<phrase.length(); i++){
            char temp= phrasetemp.charAt(i);
            
            if (isVowel(temp) == true){
                phrasetemp.setCharAt(i, ch);
            }else{
                phrasetemp.setCharAt(i, temp);
            }

        }
        
        return phrasetemp.toString();
    }
    
    public String emphasize(String phrase, char ch){
        int i = 0;
        StringBuilder phrasetemp = new StringBuilder(phrase);
        
        for (i = 0; i<phrase.length(); i++){
            char temp= phrasetemp.charAt(i);
            
            if (temp == ch && ((i+1)%2 == 0)){
                phrasetemp.setCharAt(i, '+');
            }else if (temp == ch && ((i+1)%2 == 1)){
                phrasetemp.setCharAt(i, '*');
            }else{
                phrasetemp.setCharAt(i, temp);
            }

        }
        
        return phrasetemp.toString();
    }
    
    public void test(){
        if( isVowel('a') == true){
            System.out.println("Letter is a vowel");
        }
        
        System.out.println(replaceVowels("Hello World", '*'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}

