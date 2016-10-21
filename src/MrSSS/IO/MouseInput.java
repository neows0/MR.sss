package MrSSS.IO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseMotionListener;

import MrSSS.Game;

public class MouseInput extends MouseAdapter implements MouseMotionListener{
    Game game;
    private volatile int xi = 0;
    private volatile int yi = 0;
    private volatile int xf = 0;
    private volatile int yf = 0;
    private volatile int x = 0;
    private volatile int y = 0;
    private boolean leftClick = false;
    public boolean actionTaken = true;
    
    public MouseInput(Game game) {
		this.game = game;
    }

    public MouseInput() {}
    
    public void mousePressed(MouseEvent e) {
		xi = e.getX();
		yi = e.getY();
		//System.out.println("mouse clicked");
		leftClick = true;
		actionTaken = true;
		//210 150 200 64
    }

    public void mouseReleased(MouseEvent e) {
		xf = e.getX();
		yf = e.getY();
		//System.out.println(Integer.toString(mx));
		leftClick = false;
		actionTaken = false;
    }
    public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
    }
    public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
    }

    public boolean getLeftClick() { return leftClick; }
    public int getXi() { return xi; }
    public int getYi() { return yi; }
    public int getXf() { return xf; }
    public int getYf() { return yf; }
    public int getX()  { return x;  }
    public int getY()  { return y;  }

    private boolean mouseOver(int mx, int my, int x, int y,
			      int width, int height){
		if (mx > x && mx < x + width){
			if (my > y && my < y + height) {
			return true;
			}
			else
			return false;
		}
		else
			return false;
	}
}
