package BackEnd;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.imageio.ImageIO;

public class Target {
	
	private BufferedImage img;
	private double width, height, sourceWidth, sourceHeight;
	private double targetRatio, sourceRatio;
	
	public Target(int count, Path address, List<Picture> pictures) throws IOException{
		img = ImageIO.read(address.toFile());
		height = img.getHeight();
		width = img.getWidth();
		sourceHeight = pictures.get(0).getHeight();
		sourceWidth = pictures.get(0).getWidth();
		System.out.println(height + " " + width);
		targetRatio = width/height;
		sourceRatio = sourceWidth/sourceHeight;
		System.out.println(targetRatio + " " + sourceRatio);
		
	}
}
/*29 pictures 3200:2400
final 3200:2400 1.333

along 4 down 3
4:3*/
