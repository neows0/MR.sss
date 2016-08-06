
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

    private boolean WPressed;
    private boolean APressed;
    private boolean SPressed;
    private boolean DPressed;
    

    public KeyInput(){
	WPressed = false;
	APressed = false;
	SPressed = false;
	DPressed = false;
    }
    
    public void keyPressed(KeyEvent e){
	int key = e.getKeyCode();

	//System.out.println(key);
       
	//System.out.println("found player");
	if(key == KeyEvent.VK_W) WPressed = true;
	if(key == KeyEvent.VK_S) SPressed = true;
	if(key == KeyEvent.VK_A) APressed = true;
	if(key == KeyEvent.VK_D) DPressed = true;

    }

    public void keyReleased(KeyEvent e) {
	int key = e.getKeyCode();
	
	if(key == KeyEvent.VK_W) WPressed = false;
	if(key == KeyEvent.VK_S) SPressed = false;
	if(key == KeyEvent.VK_A) APressed = false;
	if(key == KeyEvent.VK_D) DPressed = false;
	
    }

    public boolean getW(){ return WPressed; }
    public boolean getA(){ return APressed; }
    public boolean getS(){ return SPressed; }
    public boolean getD(){ return DPressed; }
    
}
