package MrSSS.IO.Loader;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class DirLoader {
    private List<DirLoader > subDir;
    private List<BufferedImage> items;
    String dirName;

    public DirLoader(String name) {
		dirName = name;
		items = new ArrayList<BufferedImage>();
		File folder = new File(name);
		readDir(folder);
    }

    public DirLoader(File folder) {
		dirName = folder.getName();
		items = new ArrayList<BufferedImage>();
		readDir(folder);
    }

    public BufferedImage fileNumber(int i) {
		if (items.size() > i)
			return items.get(i);
		else
			return null;
    }

    public void readDir(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
			if (subDir == null) {
				subDir = new ArrayList<DirLoader >();
			}
			//System.out.println(fileEntry.getName());
			subDir.add(new DirLoader(fileEntry));
			}
			else {
			readFile(fileEntry.getPath());
			}
		}
    }
    
    public void readFile(String filename) {
		BufferedImage img = null;
		try {
			//System.out.println(filename);
			File temp = new File(filename);
			img = ImageIO.read(temp);
			items.add(img);
			
		} catch (IOException e) {
			System.out.println("Error in loading picture");
			System.out.println(e);
		}
    }

    public List<BufferedImage> getDir(String filename) {
		for (int i = 0; i < subDir.size(); i++){
			//System.out.println(subDir.get(i).dirName);
			if (subDir.get(i).dirName.equals(filename)){
			return subDir.get(i).items;
			}
			
		}
		
		return null;
    }

    public List<BufferedImage> getImgs() { return items; }

    public DirLoader getDirLoader(String filename) {
		for (int i = 0; i < subDir.size(); i++){
			//System.out.println(subDir.get(i).dirName);
			if (subDir.get(i).dirName.equals(filename)){
			return subDir.get(i);
			}
			
		}

		return null;
    }
}
