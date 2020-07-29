public class Part2 {
    public int howMany(String stringa, String stringb){
        int res = 0;
        int temp = 0;
        int startIndex = 0;
        
        while (temp != -1){
            temp = stringb.indexOf(stringa, startIndex);
            
            if (temp != -1){    
            res = res + 1;
        }   
          
            startIndex = temp + stringa.length();
        }
        
        return res;
    }
    
    public void testHowMany(){
        String test1 = "xxxATGxxxyyyxxxyyyTAA";
        String test2 = "ATGxxxyyyTGA";
        String test3 = "xxxxxxATGxxxyyyxxxTAG";
        
        System.out.println("1: " + howMany("xxx", test1) + "\n");
        System.out.println("2: " + howMany("TGA", test2) + "\n");
        System.out.println("3: " + howMany("xxxx", test3) + "\n");
    }
 
}

