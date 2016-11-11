public class Button extends TextBox {
    //private boolean 
    public Button(int x, int y, String str, int WIDTH, int HEIGHT) {
	super(x,y,str,WIDTH,HEIGHT);
    }

    public boolean contains(int x1, int y1){
	return x1 > x && x1 < x + WIDTH && y1 > y && y1 < y + HEIGHT; 
    }
    
}
