public class Direction{
    private boolean forward = true;
    private boolean backward = false;
    private boolean left = false;
    private boolean right = false;
    public Direction(){
	forward = true;
	backward = false;
	left = false;
	right = false;
    }
    public boolean getForward(){ return forward; }
    public boolean getBackward(){ return backward; }
    public boolean getLeft(){ return left; }
    public boolean getRight(){ return right; }
    public void setForward(boolean forward) {this.forward = forward;}
    public void setBackward(boolean backward) {this.backward = backward;}
    public void setLeft(boolean left) {this.left = left;}
    public void setRight(boolean right) {this.right = right;}
    public void setDirection(Direction d){
	this.forward = d.forward;
	this.backward = d.backward;
	this.left = d.left;
	this.right = d.right;
    }
    public Direction reset(){
	forward = true;
	backward = false;
	left = false;
	right = false;
	return this;
    }
}