public class Approach extends Routine{
    private GameObject following;
    private MoveTo location;
    private int personalSpace;
    public Approach(GameObject following){
	this.following = following;
	personalSpace = 30;
	location = new MoveTo(following.getX(),following.getY());
	location.setCloseEnough(personalSpace);
	location.start();
    }
    public void act(GameObject droid, Room board){
	if (following == null){
	    fail();
	    return;
	}
	if(location == null || !location.isAtDestination(following) ||
	   !location.isRunning()){
	    reset();
	}
	location.act(droid, board);
	if (location.isAtDestination(droid)){
	    succeed();
	}
    }
    public void reset() {
	if (following != null && location == null){
	    location = new MoveTo(following.getX(),following.getY());
	    location.setCloseEnough(personalSpace);
	    location.start();
	}
	else if (following != null){
	    location.setDestX(following.getX());
	    location.setDestY(following.getY());
	    location.reset();
	}
    }
    public int getPersonalSpace(){
	return personalSpace;
    }
    public void setPersonalSpace(int ps){
	personalSpace = ps;
    }
    
}
