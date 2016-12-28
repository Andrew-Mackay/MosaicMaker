package BackEnd;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.imageio.ImageIO;

public class Target {
	
	private BufferedImage img;
	private int width, height, sourceDiagonal;
	private int targ;
	private int count;
	
	public Target(int count, Path address, List<Picture> pictures) throws IOException{
		this.count = count;
		img = ImageIO.read(address.toFile());
		height = img.getHeight();
		width = img.getWidth();
		sourceDiagonal = pictures.get(0).getDiagonal();
		computeRects();
		
	}
	
	//number of rectangles to divide target up into
	private void computeRects(){
		int gcd = gcd(height, width);
		targ = (width/gcd) * (height/gcd);
		int numPics = (count/targ)*targ;
		System.out.println(gcd);
		System.out.println(count);
		System.out.println(numPics);
	}
	

    static int gcd(int x, int y)
    {
        int r=0, a, b;
        a = (x > y) ? x : y; // a is greater number
        b = (x < y) ? x : y; // b is smaller number
 
        r = b;
        while(a % b != 0)
        {
            r = a % b;
            a = b;
            b = r;
        }
        return r;
    }
}
/*
 * 4:3 image
 * 24 1:1
 * width every 8*gcd??
 * height every 6*gcd
 * 
 */
