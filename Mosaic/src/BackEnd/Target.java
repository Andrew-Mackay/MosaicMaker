package BackEnd;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.imageio.ImageIO;

public class Target {
	
	private BufferedImage img;
	private int width, height, sourceDiagonal;
	private double targetRatio, sourceRatio;
	private int targ;
	private int count;
	
	public Target(int count, Path address, List<Picture> pictures) throws IOException{
		this.count = count;
		img = ImageIO.read(address.toFile());
		height = img.getHeight();
		width = img.getWidth();
		sourceDiagonal = pictures.get(0).getDiagonal();
		//System.out.println(height + " " + width);
		//targ = width*height;
		//sourceRatio = sourceDiagonal/sourceDiagonal;
		//System.out.println(targetRatio + " " + sourceRatio);
		computeRects();
		
	}
	
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
/*29 pictures 3200:2400
final 3200:2400 1.333

along 4 down 3
4:3*/
