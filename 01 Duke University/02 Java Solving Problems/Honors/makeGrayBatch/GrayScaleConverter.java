/**
 * Convert any number of images to a gray scale version by setting all color components of each pixel to the same value.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    //I started with the image I wanted (inImage)
    public ImageResource makeGray(ImageResource inImage) {
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //compute inPixel's red + inPixel's blue + inPixel's green
            //divide that sum by 3 (call it average)
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
            //set pixels to average
            pixel.setRed(average);
            pixel.setGreen(average);
	    pixel.setBlue(average);
	}
	//outImage is your answer
	return outImage;
    }
    
    public ImageResource inversion(ImageResource inImage) {
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            
            //set pixels to opposite
            pixel.setRed(255 - inPixel.getRed() );
            pixel.setGreen(255 - inPixel.getGreen());
	    pixel.setBlue(255 - inPixel.getBlue());
	}
	//outImage is your answer
	return outImage;
    }

    public void selectAndConvert () {
	DirectoryResource dr = new DirectoryResource();
	for (File f : dr.selectedFiles()) {
		ImageResource inImage = new ImageResource(f);
		ImageResource gray = makeGray(inImage);
		ImageResource invert =inversion(inImage);
		
		String fname = inImage.getFileName();
		String newName = "images/gray/" + "gray-" + fname;
		String newName2 = "images/invert/" + "inverted-" + fname;
		gray.setFileName(newName);
		invert.setFileName(newName2);
		gray.save();
		invert.save();
	}
     }

     public void testGray() {
	ImageResource ir = new ImageResource();
	ImageResource gray = makeGray(ir);
	gray.draw();
    }
}
