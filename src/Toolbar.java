import java.awt.Graphics;
import java.util.List;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import java.awt.Color;

public class Toolbar implements KeyWatcher{

  private JTextField Input = new JTextField();

  private DirLoader imgFolder;
  private int toolState;
  private final static int INV = 0;
  private final static int SKILLS = 1;
  private Button startServer;
  private Button joinServer;
  private Button inventory;
  public boolean joiningServer;
  public Toolbar(Player player){
    imgFolder = Game.images.getDirLoader("toolbar");
    toolState = INV;
    startServer = new Button(0, 0, "Start Server", 100, 50);
    joinServer = new Button(100, 0, "Join Server", 100, 50);
    inventory = new Button(Game.SCRNWIDTH - 100, Game.SCRNHEIGHT - 50,
    "INV", 20, 20);
    inventory.setExpandable(false);
    Color n = new Color(127, 51, 0);
    inventory.setBoxColor(n);
    n = new Color(61, 24, 0);
    inventory.setOutline(n, 3);
    inventory.setToggleColor(n, new Color(48, 13, 0));
    Game.input.addObserver(this);
  }
  public void mouseClick(int tX, int tY){//MouseInput mI){
    //int tX = mI.getXi();
    //int tY = mI.getYi();
    if (toolState == INV){
      Item temp = Game.player.getEntity().getInv().getItem(tX, tY);
      if (temp != null){
        if (!temp.isEquipped()){
          temp.equip();
        }
        else{
          temp.unequip();
        }
      }
      if (inventory.contains(tX, tY)) {
        inventory.toggle();
      }
    }
    else if (toolState == SKILLS) {
    }
    if(startServer.contains(tX, tY)) {
      Game.broadcasting = true;
      System.out.println("Button Pressed");
    }
    if(joinServer.contains(tX, tY) && !Game.broadcasting && !Game.inServer) {
      joinServer.setText("");
      //Game.input.clearInput();

      Game.input.addInputs(joinServer.getText());
      //joinServer.setText(Game.input.getInput());
      joiningServer = true;
    }
  }
  public void tick(){
    if (toolState == INV) {
      Game.player.getEntity().getInv().tick();
    }
    else if (toolState == SKILLS) {
    }
  }
  public void render(Graphics g){
    if (toolState == INV) { // && !inventory.isPressed()){
      Game.player.getEntity().getInv().render(g);
    }
    else if (toolState == SKILLS) {
    }
    startServer.render(g);
    /*
    if (joiningServer)
    joinServer.setText(Game.input.getInput());*/
    joinServer.render(g);
    inventory.render(g);

  }
  public void update(KeyInput ki){
    if (joiningServer) {
      //joinServer.setText(ki.getInput());
      ki.removeInputs(joinServer.getText());
      joiningServer = false;
    }
  }
}
