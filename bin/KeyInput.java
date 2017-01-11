import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.lang.Character;
//import java.util.Observer;

public class KeyInput extends KeyAdapter {

    private boolean Space;
    private boolean Enter;

    private ArrayList<Boolean> pressed;

    private ArrayList<MyString> inputs;
    
    private ArrayList<KeyWatcher> investigators;
    private boolean trackInput = true;

    private String input;

    public KeyInput(){

	Space = false;
	input = new String("");
	investigators = new ArrayList<KeyWatcher>();
	inputs = new ArrayList<MyString>();
	pressed = new ArrayList<Boolean>(26);
	for (int i = 0; i < 26; i++)
	    pressed.add(false);
    }
    
    public void keyPressed(KeyEvent e){
	int key = e.getKeyCode();

	if(key == KeyEvent.VK_SPACE) Space = true;
	if (key >= 65 && key <= 90) 
	    pressed.set(key - 65, true);
    }

    public void keyReleased(KeyEvent e) {
	int key = e.getKeyCode();

	if (key >= 65 && key <= 90) 
	    pressed.set(key - 65, false);
	if (isPrintableChar(e.getKeyChar())){
	    //input += "" + e.getKeyChar();
	    for (MyString s : inputs) {
		s.data += "" + e.getKeyChar();
	    }
	}
	if (key == KeyEvent.VK_BACK_SPACE) {
	    if (!input.isEmpty())
		input = input.substring(0,input.length() - 1);
	}
	if(key == KeyEvent.VK_SPACE) { Space = false; }
	if(key == KeyEvent.VK_ENTER) { Enter = false; notifyObservers();}
	
	
    }

    public boolean isPrintableChar( char c ) {
    Character.UnicodeBlock block = Character.UnicodeBlock.of( c );
    return (!Character.isISOControl(c)) &&
            c != KeyEvent.CHAR_UNDEFINED &&
            block != null &&
            block != Character.UnicodeBlock.SPECIALS;
}

    public boolean isPressed(String s) {
	if (!s.isEmpty()) {
	    char c = s.charAt(0);
	    int i = Character.getNumericValue(c) - 10;
	    //System.out.println(Character.getNumericValue(c - 10));
	    if (i >= 0 && i < pressed.size())
		return pressed.get(i);
	    //return true;
	}
	else
	    return false;
	return false;
    }
    public boolean getW(){ return pressed.get(22); }
    public boolean getA(){ return pressed.get(0); }
    public boolean getS(){ return pressed.get(18); }
    public boolean getD(){ return pressed.get(3); }
    public boolean getI(){ return pressed.get(8); }
    public boolean getSpace(){ return Space; }
    //public String getInput(){ return input; }
    //public void clearInput(){ input = ""; }

    public void addObserver(KeyWatcher o) {
	investigators.add(o);
    }

    public void addInputs(MyString in) {
	inputs.add(in);
	//System.out.println(Integer.toString(inputs.size()));
    }
    public void removeInputs(MyString stopInput) {
	inputs.remove(stopInput);
	//System.out.println(Integer.toString(inputs.size()));
    }

    public void removeObserver(KeyWatcher o) { investigators.remove(o); }

    private void notifyObservers() {
	for (KeyWatcher o : investigators)
	    o.update(this);
    }
    
}

