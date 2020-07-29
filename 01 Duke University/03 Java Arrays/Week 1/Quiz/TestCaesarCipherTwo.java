import edu.duke.*;

public class TestCaesarCipherTwo {
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
    
    public void breakCaesarCipher(String input){
        String firstHalf = halfOfString(input,0);
        String secondHalf = halfOfString(input,1);
        
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
        
        CaesarCipherTwo cr = new CaesarCipherTwo(dkey1,dkey2);
        String decrypted = cr.decrypt(input);
        
        System.out.println("Break Decrypt: " + decrypted);
        System.out.println("Keys: " + dkey1 + "," +dkey2);
    }
    
    void simpleTests(){
        //FileResource fr = new FileResource();
        FileResource fs = new FileResource();
        CaesarCipherTwo cc = new CaesarCipherTwo(14,24);
        
        //String message = fr.asString();
        //String encrypted = cc.encrypt(message);

        breakCaesarCipher(fs.asString());
        
        //String test = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        //breakCaesarCipher(test);
        
        //System.out.println("Message: " + message);
        //System.out.println("Encrypted: " + encrypted);
        //System.out.println("Decrypt: " + decrypted);
    }
}
