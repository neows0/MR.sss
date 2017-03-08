import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

public abstract class Item extends GameObject{

  protected boolean onGround = false;
  protected ITMID itId;
  protected boolean canEquipped = false;
  protected boolean equipped = false;
  protected int relativeX;
  protected int relativeY;
  //change player to something like living (have to create a living first)
  protected GameObject owner;
  public Item(ITMID itId, GameObject owner){
    this(-1, -1, itId, false);
    this.owner = owner;
  }
  public Item(int x, int y, ITMID itId, boolean onGround){
    super(x, y, ID.Item);
    this.onGround = onGround;
    this.itId = itId;
  }
  public abstract void use();
  public abstract void invRender(Graphics g, int x, int y, int w, int h);

  public void setOnGround(boolean onGround){this.onGround = onGround;}
  public void setEquipped(boolean equipped){
    if (canEquipped){
      this.equipped = equipped;
    }
  }

  public void equip(){
    if (owner != null && !isEquipped()){
      equipped = true;
      this.setDir(owner.getDir());
    }
  }

  public void unequip(){
    if (owner != null && isEquipped()){
      equipped = false;
      this.setDir(new Direction());
    }
  }

  public void setRelativeX(int rX) {relativeX = rX;}
  public void setRelativeY(int rY) {relativeY = rY;}
  public void setOwner(GameObject owner) {this.owner = owner;}
  public GameObject getOwner() {return owner;}
  public int getRelativeX(){return relativeX;}
  public int getRelativeY(){return relativeY;}
  public boolean getOnGround(){return onGround;}
  public boolean isEquipped() {return equipped;}
  public ITMID getITMID() {return itId;}

}
