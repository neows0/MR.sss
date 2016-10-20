import java.awt.Graphics;
import java.awt.Color;
import java.awt.FontMetrics;

public class TextBox{
    public TextBox(Graphics g, int x, int y, String str, int WIDTH) {
	Color temp = g.getColor();
	
	g.setColor(new Color(0, 0, 0, 200));
	g.fillRect(x, y, WIDTH, 50);
	g.setColor(Color.BLUE);
	//printNewLine(g, x, y + 10, str);
	wrapText(g, x, y + 11, str, WIDTH);
	//g.drawString(str, x, y);
	g.setColor(temp);
    }

    public void printNewLine(Graphics g, int x, int y, String str){
	String[] lines = str.split("\n");
	for (int i = 0; i < lines.length; i++){
	    g.drawString(lines[i], x, y + i * 11);
	}
    }
    public void wrapText(Graphics g, int x, int y, String str, int width){
	String[] lines = str.split("\n");
	int tempWidth;
	FontMetrics fm = g.getFontMetrics();
	int line = 0;
	for (int i = 0; i < lines.length; i++){
	    if (fm.stringWidth(lines[i]) < width){
		g.drawString(lines[i], x, y + line * 11);
		line++;
	    }
	    else {
		String[] words = lines[i].split(" ");
		String buff = null;
		int wLength = words.length;
		for (int j = 0; j < wLength; j++){
		    if (fm.stringWidth(words[j]) > width){
			if (buff != null){
			    g.drawString(buff, x, y + line * 11);
			    line++;
			}
			g.drawString(words[j], x, y + line * 11);
			line++;
		    }
		    else if (buff != null && fm.stringWidth(words[j]) +
			     fm.stringWidth(buff) > width){
			g.drawString(buff, x, y + line * 11);
			line++;
			if (fm.stringWidth(words[j]) > width){
			    g.drawString(words[j], x, y + line * 11);
			    line++;
			    buff = null;
			}
			else {
			    buff = words[j] + " ";
			}
		    }
		    else {
			if (buff != null)
			    buff += words[j] + " ";
			else
			    buff = words[j] + " ";
		    }
		}
		if (buff != null){
		    g.drawString(buff, x, y + line * 11);
		    line++;
		}
	    }
	}
    }
}
