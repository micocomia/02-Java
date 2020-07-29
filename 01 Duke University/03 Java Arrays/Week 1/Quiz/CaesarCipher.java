import edu.duke.*;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) +
                            alphabet.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt(String input) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            char tempUpper = Character.toUpperCase(currChar);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(tempUpper);
            
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                
                if(Character.isLowerCase(currChar) == true){
                    newChar = Character.toLowerCase(newChar);
                }
                
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }

    public String decrypt(String input){
        CaesarCipher cr = new CaesarCipher(26 - mainKey);
        return cr.encrypt(input);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1)+ alphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2)+ alphabet.substring(0,key2);
        
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            char tempUpper = Character.toUpperCase(currChar);
            int idx = alphabet.indexOf(tempUpper);
            
            //If currChar is in the alphabet
            if(idx != -1){
                char newChar = 'a';
                
                if ((i+1)%2 == 0){
                    newChar = shiftedAlphabet2.charAt(idx);
                }else if ((i+1)%2 == 1){
                    newChar = shiftedAlphabet1.charAt(idx);
                }
                
                
                if(Character.isLowerCase(currChar) == true){
                    newChar = Character.toLowerCase(newChar);
                }
                
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            //Otherwise: do nothing
        }
        
        
        return encrypted.toString();
    }
}

