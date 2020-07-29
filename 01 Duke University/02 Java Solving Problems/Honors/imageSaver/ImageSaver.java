/**
 * Make copies of all images selected within a directory (or folder).
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.File;

public class ImageSaver {
    public void doSave() {
        DirectoryResource dr = new DirectoryResource();
        int n = 0;
        
        for (File f : dr.selectedFiles()) {
            String name = f.getParentFile().getName();
            String fname = f.getName();
            int index = 0;
            
            if (n == 0){
                System.out.println(name + "\n");
            }
        
            
            n++;
        }
    }
}
