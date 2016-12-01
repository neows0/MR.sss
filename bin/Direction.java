public class Direction{
    private boolean forward = true;
    private boolean backward = false;
    private boolean left = false;
    private boolean right = false;
    private int angle;
    
    public Direction(){
	forward = true;
	backward = false;
	left = false;
	right = false;
	angle = 270;
    }
    public boolean getForward(){ return forward; }
    public boolean getBackward(){ return backward; }
    public boolean getLeft(){ return left; }
    public boolean getRight(){ return right; }
    public int getAngle(){ return angle; }
    public void setForward(boolean forward) {
	this.forward = forward;
    }
    public void setBackward(boolean backward) {
	this.backward = backward;
    }
    public void setLeft(boolean left) {
	this.left = left;
    }
    public void setRight(boolean right) {
	this.right = right;
    }
    public void setAngle(int angle) {
	if (angle < 0) {
	    angle = -angle;
	    angle = 360 - angle % 360;
	    if (angle == 360)
		angle = 0;
	}
	else
	    angle = angle % 360;
	
	this.angle = angle;
    }
    public void moveTowards(int idle) {
	moveTowards(idle, 1);
    }
    public void moveTowards(int idle, int aChange) {
        if (idle < 0) {
	    idle = -idle;
	    idle = idle % 360;
	    if (idle != 0)
		idle = 360 - idle;
	}
	idle = idle % 360;
	if (idle != angle) {
	    int halfWay = angle;
	    if (idle > 180) {
		halfWay = idle - 180;
		if (angle < idle && angle > halfWay){
		    angle += aChange;
		    angle = angle % 360;
		}
		else {
		    angle -= aChange;
		    if (angle < 0)
			angle += 360;
		}
	    }
	    else {
		halfWay = idle + 180;
		if (angle > idle && angle < halfWay) {
		    angle -= aChange;
		    if (angle < 0)
			angle += 360;
		}
		else {
		    angle += aChange;
		    angle = angle % 360;
		}
	    }
	    /* if (idle < angle && idle > halfWay){
		angle += aChange;
		angle = angle % 360;
	    }
	    else if (idle > angle && idle > halfway) {
	    }*/
	}
    }
    public void setDirection(Direction d){
	this.forward = d.forward;
	this.backward = d.backward;
	this.left = d.left;
	this.right = d.right;
	this.angle = d.angle;
    }
    public Direction reset(){
	forward = true;
	backward = false;
	left = false;
	right = false;
	angle = -90;
	return this;
    }
}
