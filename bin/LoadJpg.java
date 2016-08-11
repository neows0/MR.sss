import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
//import java.awt.Graphics;

/*
 * BufferedImage.getSubimage(int x, int y, int w, int h);
 */

public class LoadJpg {

    private static LoadJpg instance;

    public List<BufferedImage> imageList;

    private LoadJpg(){
	imageList = new ArrayList<BufferedImage>();
	final File folder = new File("../images");
	listFilesForFolder(folder);
	//load("../images/1.png");
    }

    public static LoadJpg getInstance(){
	if (instance == null) {
	    instance = new LoadJpg();
	}
	return instance;
    }
    
    public static BufferedImage load(){
	BufferedImage img = null;
	try {
	    img = ImageIO.read(new File("pixel_practice.png"));
	    //g.drawImage(img, 10, 10, null);
	} catch (IOException e) {
	    System.out.println("Error in loading picture");
	}
	return img;
    }

    public static BufferedImage load(String filename){
	BufferedImage img = null;
	try {
	    img = ImageIO.read(new File(filename));
	    //g.drawImage(img, 10, 10, null);
	} catch (IOException e) {
	    System.out.println("Error in loading picture");
	    System.out.println(filename);
	}
	return img;
    }
    
    public void listFilesForFolder(final File folder) {
	for (final File fileEntry : folder.listFiles()) {
	    if (fileEntry.isDirectory()) {
		listFilesForFolder(fileEntry);
	    } else {
		readFile(fileEntry.getPath());
	    }
	}
    }

     public void readFile(String filename) {
        BufferedImage img = null;
	try {
	    img = ImageIO.read(new File(filename));
	    imageList.add(img);
	    //g.drawImage(img, 10, 10, null);
	} catch (IOException e) {
	    System.out.println("Error in loading picture");
	    System.out.println(e);
	}
    }
    
    
}
