package BackEnd;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MosaicMain {
	
	private int count = 0;
	private Path folder;
	private Path targetAddress;
	private static List<Picture> pictures = new ArrayList<>();

	public MosaicMain(String pictureAddress, String folderAddress) throws IOException{
		folder = Paths.get(folderAddress);
		targetAddress = Paths.get(pictureAddress);
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder)){
			for (Path entry: stream) {
				count++;
				
				Picture pic = new Picture(entry);
				pictures.add(pic);
			}
		}catch (IOException e) {
		    e.printStackTrace();
		}
		
		processTarget();
		create();
		
	}
	
	private void processTarget() throws IOException{
		Target target = new Target(count, targetAddress, pictures);
		// target. doEverythingPlz
	}
	
	private void create(){
		//hey
	}
	
}
