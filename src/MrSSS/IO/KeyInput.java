package MrSSS.IO;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

    private boolean WPressed;
    private boolean APressed;
    private boolean SPressed;
    private boolean DPressed;
    private boolean IPressed;
    private boolean Space;
    

    public KeyInput() {
		WPressed = false;
		APressed = false;
		SPressed = false;
		DPressed = false;
		IPressed = false;
		Space = false;
    }
    
    public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		//System.out.println(key);
		   
		//System.out.println("found player");
		if(key == KeyEvent.VK_W) WPressed = true;
		if(key == KeyEvent.VK_S) SPressed = true;
		if(key == KeyEvent.VK_A) APressed = true;
		if(key == KeyEvent.VK_D) DPressed = true;
		if(key == KeyEvent.VK_I) IPressed = true;
		if(key == KeyEvent.VK_SPACE) Space = true; 
	}

    public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W) WPressed = false;
		if(key == KeyEvent.VK_S) SPressed = false;
		if(key == KeyEvent.VK_A) APressed = false;
		if(key == KeyEvent.VK_D) DPressed = false;
		if(key == KeyEvent.VK_I) IPressed = false;
		if(key == KeyEvent.VK_SPACE) Space = false;	
    }

    public boolean getW() { return WPressed; }
    public boolean getA() { return APressed; }
    public boolean getS() { return SPressed; }
    public boolean getD() { return DPressed; }
    public boolean getI() { return IPressed; }
    public boolean getSpace(){ return Space; }
}
