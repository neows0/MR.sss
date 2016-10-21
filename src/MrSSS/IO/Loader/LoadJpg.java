package MrSSS.IO.Loader;

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

    public List<String> dirNames;

    private List<List<BufferedImage> > images;

    private LoadJpg(){
		imageList = new ArrayList<BufferedImage>();
		images = new ArrayList<List<BufferedImage> >();
		//images.add(new ArrayList<BufferedImage>());
		dirNames = new ArrayList<String>();
		final File folder = new File("../images");
		listFilesForFolder(folder);
		//load("../images/1.png");
    }

    public List<BufferedImage> getDir(String dirName) {
		for (int i = 0; i < dirNames.size(); i++){
			//System.out.println("looking for " + dirName);
			if (dirNames.get(i).equals(dirName)) {
			return images.get(i);
			}
		}

		return null;
    }

    public static LoadJpg getInstance() {
		if (instance == null) {
			instance = new LoadJpg();
		}
		return instance;
    }

    public static BufferedImage load(String filename) {
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
			images.add(new ArrayList<BufferedImage>());
			dirNames.add(fileEntry.getName());
			//System.out.println(fileEntry.getName());
			listFilesForFolder(fileEntry);
			} else {
			readFile(fileEntry.getPath());
			}
		}
    }

     public void readFile(String filename) {
		BufferedImage img = null;
		try {
			File temp = new File(filename);
			img = ImageIO.read(temp);
			imageList.add(img);
			//System.out.println(Integer.toString(images.size()));
			if (getDir(temp.getParentFile().getName()) != null)
			getDir(temp.getParentFile().getName()).add(img);
			else {
			images.add(new ArrayList<BufferedImage>());
			dirNames.add(temp.getParentFile().getName());
			getDir(temp.getParentFile().getName()).add(img);
			}
		} catch (IOException e) {
			System.out.println("Error in loading picture");
			System.out.println(e);
		}
    }
}
