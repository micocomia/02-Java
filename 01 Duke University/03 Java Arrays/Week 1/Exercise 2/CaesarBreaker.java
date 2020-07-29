import edu.duke.*;
import java.io.*;

public class CaesarBreaker {   
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for (int k=0; k < vals.length; k++){
            if (vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    public int[] countLetters(String encrypted){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int counts[] = new int[26];
        
        for(int i = 0; i < encrypted.length(); i++){
            char ch = Character.toLowerCase(encrypted.charAt(i));
            int dex = alph.indexOf(ch);
            
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        
        return counts;
    }
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        
        int maxDex = getKey(encrypted);
        int dkey = maxDex-4;
         
        if (maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        
        return cc.encrypt(encrypted,26-dkey);
    }
    
    public String halfOfString(String message, int start){
        int i = 0;
        StringBuilder init = new StringBuilder(message);
        String half = "";
        
        for (i = start; i<message.length(); i += 2){
            char temp = init.charAt(i);
            
            half += temp;
        }
        
        return half.toString();
    }
    
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int key = maxIndex(freqs);
        
        return key;
    }
    
    public String decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        
        String firstHalf = halfOfString(encrypted,0);
        String secondHalf = halfOfString(encrypted,1);
        
        int maxDex1 = getKey(firstHalf);
        int maxDex2 = getKey(secondHalf);
        
        int dkey1 = maxDex1-4;
        int dkey2 = maxDex2-4;
        
        if (maxDex1 < 4){
            dkey1 = 26 - (4-maxDex1);
        }
        
        if (maxDex2 < 4){
            dkey2 = 26 - (4-maxDex2);
        }
        
        System.out.println("Keys 1 & 2 are: " + dkey1 + "," + dkey2);
        return cc.encryptTwoKeys(encrypted,26-dkey1,26-dkey2);
    }
    
    public void testDecrypt(){
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encrypt("Just a test string with lots of eeeeeeeeeeeeeeeees", 15);
        System.out.println(encrypted);
        
        System.out.println(decrypt(encrypted));
        System.out.println(halfOfString("Qbkm Zgis",1));
        System.out.println(halfOfString("Qbkm Zgis",0));
        System.out.println(decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
    }
}
