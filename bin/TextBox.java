import java.awt.Graphics;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;


public class TextBox{
    protected int x, y, WIDTH, HEIGHT;
    protected MyString str;
    protected Color boxColor;
    protected Color textColor;
    protected boolean expandable;
    protected boolean isOutlined;
    protected Color outlineColor;
    protected int oT;
    
    public TextBox(int x, int y, String str, int WIDTH, int HEIGHT) {
	this.x = x;
	this.y = y;
	this.str = new MyString(str);
	//this.str.data = str;
	this.WIDTH = WIDTH;
	this.HEIGHT = HEIGHT;
	boxColor = new Color(0, 0, 0, 200);
	textColor = Color.BLUE;
	expandable = true;
	isOutlined = false;
    }

    public void render(Graphics g) {
	Color temp = g.getColor();	
	

	//g.setColor(textColor);
	//wrapText(g, x, y + 11, str.data, WIDTH);
	
	
	BufferedImage tmp1 =
	    new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
	Graphics gr = tmp1.getGraphics();
	
	gr.setColor(textColor);
	//printNewLine(g, x, y + 10, str);
	
	int newHeight = 11 * wrapText(gr, 0, 11, str.data, WIDTH) + 3;

	if (HEIGHT < newHeight && expandable)
	    HEIGHT = newHeight;

	if (isOutlined == true) {
	    g.setColor(outlineColor);
	    g.fillRect(x - oT, y - oT,
		       WIDTH + oT * 2,
		       HEIGHT + oT * 2);
	}
	
	g.setColor(boxColor);
	g.fillRect(x, y, WIDTH, HEIGHT);

	

	g.drawImage(tmp1, x, y, null);
	
	
	//g.drawString(str, x, y);
	g.setColor(temp);
    }

    public void printNewLine(Graphics g, int x, int y, String str){
	String[] lines = str.split("\n");
	for (int i = 0; i < lines.length; i++){
	    g.drawString(lines[i], x, y + i * 11);
	}
    }
    public int wrapText(Graphics g, int x, int y, String str, int width){
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
	return line;
    }
    public void setBoxColor(Color myColor) {
	this.boxColor = myColor;
    }
    public Color getBoxColor() {
	return boxColor;
    }
    public void setTextColor(Color myColor) {
	this.textColor = myColor;
    }
    public Color getTextColor() {
	return textColor;
    }
    public void setText(MyString str) {
	this.str = str;
    }
    public void setText(String str) {
	this.str.data = str;
    }
    public MyString getText() {
	return str;
    }
    public String getString() {
	return str.data;
    }
    public boolean getExpandable() { return expandable; }
    public void setExpandable(boolean e) { expandable = e;}
    public void setOutline(Color myColor) { setOutline(myColor, 1); }
    public void setOutline(Color myColor, int t) {
	outlineColor = myColor;
	oT = t;
	isOutlined = true;
    }
    public void removeOutline() { isOutlined = false; }
}
