package perimeter_quiz;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    
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
 
        System.out.println("largest perimeter = " + df2.format(LargestPerim));
    }
    

    public void testFileWithLargestPerimeter() {
        File largest = getFileWithLargestPerimeter();
        
        System.out.println("largest file = " + largest);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
