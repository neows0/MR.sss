import java.awt.Color;

public class Button extends TextBox {
    //private boolean

    private boolean canToggle;
    private boolean pressed;
    private Color boxWP; //box when pressed
    private Color outlineWP; //outline when pressed
    
    public Button(int x, int y, String str, int WIDTH, int HEIGHT) {
	super(x,y,str,WIDTH,HEIGHT);
        canToggle = false;
	pressed = false;
	boxWP = boxColor;
	outlineWP = outlineColor;
    }
    
    public boolean contains(int x1, int y1){
	if (isOutlined) {
	    return x1 > x - oT && x1 < x + WIDTH + oT && y1 > y - oT &&
		y1 < y + HEIGHT + oT; 
	}
	return x1 > x && x1 < x + WIDTH && y1 > y && y1 < y + HEIGHT; 
    }

    public void setToggle(boolean t) { canToggle = t; }
    public boolean doesToggle() { return canToggle; }
    public boolean isPressed() { return pressed; }
    public void setPressed(boolean p) { pressed = p; }
    public void setToggleColor(Color b) { setToggleColor(b, outlineColor); }
    public void setToggleColor(Color b, Color o) {
	boxWP = b;
	outlineWP = o;
	canToggle = true;
    }
    public void toggle() {
	if (canToggle) {
	    pressed = !pressed;
	    Color temp = boxColor;
	    boxColor = boxWP;
	    boxWP = temp;

	    temp = outlineColor;
	    outlineColor = outlineWP;
	    outlineWP = temp;
	}
    }
    
    
}
