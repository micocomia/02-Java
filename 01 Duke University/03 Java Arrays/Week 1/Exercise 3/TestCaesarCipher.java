import edu.duke.*;

public class TestCaesarCipher {
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for (int k=0; k < vals.length; k++){
            if (vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    private int[] countLetters(String encrypted){
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
    
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int key = maxIndex(freqs);
        
        return key;
    }
    
    public void breakCaesarCipher(String input){
        int maxDex = getKey(input);
        int dkey = maxDex-4;
         
        if (maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        
        CaesarCipher cr = new CaesarCipher(dkey);
        String decrypted = cr.decrypt(input);
        
        System.out.println("Break Decrypt: " + decrypted);
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        CaesarCipher cc = new CaesarCipher(18);
        
        String message = fr.asString();
        String encrypted = cc.encrypt(message);
        String decrypted = cc.decrypt(encrypted);
        
        breakCaesarCipher(encrypted);
        
        System.out.println("Message: " + message);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypt: " + decrypted);
        
    }
}
