package BackEnd;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

public class Picture {

	private Path address;
	private String colour;
	private int width;
	private int height;
	
	public Picture(Path address) throws IOException{
		this.address = address;
		average();
		//System.out.println(colour);
	}
	
	private void average() throws IOException{
		BufferedImage img = ImageIO.read(address.toFile());
		width = img.getWidth();
		height = img.getHeight();
		int red = 0, green = 0, blue = 0, colour;
		// use low medium high here when implemented
		for (int i = 0; i < width; i++){
			for (int j = 0; j < height; j++){
				colour = img.getRGB(i, j);
				red += (colour & 0x00ff0000) >> 16;
				green += (colour & 0x0000ff00) >> 8;
				blue += colour & 0x000000ff;			
			}
		}
	      if ( red > green && red > blue )
	          this.colour = "Red";
	       else if ( green > red && green > blue )
	    	   this.colour = "green";
	       else if ( blue > red && blue > green )
	          this.colour = "blue";
	       else   
	          this.colour = "Green";
	}

	public Path getAddress() {
		return address;
	}


	public String getColour() {
		return colour;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	
}
