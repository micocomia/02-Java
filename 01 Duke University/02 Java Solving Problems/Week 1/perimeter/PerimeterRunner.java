import edu.duke.*;
import java.io.File;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import edu.duke.*;
import java.io.File;

public class PerimeterRunner {
    
    private static DecimalFormat df2 = new DecimalFormat("#.##");
        
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
    
    public int getNumPoints (Shape s){
        int npoints = 0;
        
        for (Point currPt: s.getPoints()){
            npoints = npoints + 1;
        }
        
        return npoints;
    }
    
    public double getAverageLength(Shape s){
        double aveLength = 0.0;
       
        double length = getPerimeter(s);
        int npoints = getNumPoints(s);
        
        aveLength = length/npoints;
        
        // aveLength is the answer
        return aveLength;
    }
    
    public double getLargestSide(Shape s){
        double largest = 0.0;
        
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            if (currDist > largest){
                largest = currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }

        return largest;
    }

    public double getLargestX(Shape s){
        double largestX = 0.0;
        
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currX = currPt.getX();
            
            // Update totalPerim by currDist
            if (currX > largestX){
                largestX = currX;
            }
            
            // Update prevPt to be currPt
            prevPt = currPt;
        }

        return largestX;
    }
    
    public double getLargestPerimeterMultipleFiles(){
        double LargestPerim = 0.0;
        
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()){
            
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            
            double length = getPerimeter(s);
            
            if (length > LargestPerim){
                LargestPerim = length;
            }
        }
        
        return LargestPerim;
    }
    
    public File getFileWithLargestPerimeter(){
        File largest = new File("/");
        DirectoryResource dr = new DirectoryResource();
        
        double LargestPerim = 0.0;
        
        for (File f : dr.selectedFiles()){
            
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            
            double length = getPerimeter(s);
            
            if (length > LargestPerim){
                LargestPerim = length;
                largest = f;
            }
        }
        
        return largest;
    }
    
    public void testPerimeterMultipleFiles(){
        double LargestPerim = getLargestPerimeterMultipleFiles();
        File largest = getFileWithLargestPerimeter();
        
        System.out.println("largest perimeter = " + LargestPerim);
        System.out.println("largest file = " + largest);
    }
    
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        
        double length = getPerimeter(s);
        int npoints = getNumPoints(s);
        double avelength = getAverageLength(s);
        double largest = getLargestSide(s);
        double largestX = getLargestX(s);
        
        System.out.println("perimeter = " + df2.format(length));
        System.out.println("no. of points = " + npoints);
        System.out.println("ave. length = " + df2.format(avelength));
        System.out.println("largest side = " + df2.format(largest));
        System.out.println("largest X = " + largestX);
    }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        //pr.testPerimeterMultipleFiles();
        pr.testPerimeter();
    }
}
