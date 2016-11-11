import java.awt.Graphics;
import java.awt.Color;
import java.awt.FontMetrics;


public class TextBox{
    protected int x, y, WIDTH, HEIGHT;
    protected MyString str;
    protected Color boxColor;
    protected Color textColor;
    public TextBox(int x, int y, String str, int WIDTH, int HEIGHT) {
	this.x = x;
	this.y = y;
	this.str = new MyString(str);
	//this.str.data = str;
	this.WIDTH = WIDTH;
	this.HEIGHT = HEIGHT;
	boxColor = new Color(0, 0, 0, 200);
	textColor = Color.BLUE;
    }

    public void render(Graphics g) {
	Color temp = g.getColor();	
	g.setColor(boxColor);
	g.fillRect(x, y, WIDTH, HEIGHT);
	g.setColor(textColor);
	//printNewLine(g, x, y + 10, str);
	wrapText(g, x, y + 11, str.data, WIDTH);
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
}
