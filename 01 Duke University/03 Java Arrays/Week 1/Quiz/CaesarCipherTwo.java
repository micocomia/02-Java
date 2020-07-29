
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1)+ 
                            alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2)+ 
                            alphabet.substring(0,key2);
                            
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input) {
    StringBuilder encrypted = new StringBuilder(input);
    
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
    
    public String decrypt(String input){
        CaesarCipherTwo cr = new CaesarCipherTwo(26-mainKey1,26-mainKey2);
        return cr.encrypt(input);
    }
}
