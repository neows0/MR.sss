import java.util.LinkedList;

public class Edges{
    private Boolean inside;
    private Cordinate[] edgelist;
    Edges(Boolean inside, Cordinate... edgelist) {
	this.inside = inside;
	this.edgelist = edgelist;
	if (edgelist.length < 3){
	    System.out.println("Error list must be 3 or greater!");
	}
    }
    public boolean doesCross(Cordinate c1, Cordinate c2){
	if(edgelist != null && edgelist.length >= 3){
	    boolean crosses = false;
	    for(int i = 0; i < edgelist.length; i++){
		Cordinate c3 = edgelist[i];
		Cordinate c4 = edgelist[(i + 1) % edgelist.length];
		crosses = crosses || doesCross(c1,c2,c3,c4);
	    }
	    return crosses;
	}
	return false;
    }
    
    public static boolean doesCross(Cordinate c1, Cordinate c2,
				    Cordinate c3, Cordinate c4){
	float slp1 = slope(c1,c2);
	float slp2 = slope(c3,c4);
	if (slp1 == slp2 && !((between(c1.x, c2.x, c3.x) &&
			       between(c1.y, c2.y, c3.y)) ||
			      (between(c3.x, c4.x, c1.x) &&
			       between(c3.y, c4.y, c1.y)))){
	    return false;         //need to check xy intersect && this
	}
	else if (slp1 == slp2){
	    return (c1.y - slp1 * c1.x) == (c3.y - slp2 * c3.x);
	}
	else{
	    float x1 = c1.x;
	    float x2 = c3.x;
	    float y1 = c1.y;
	    float y2 = c3.y;
	    float xi =((y2 - y1 - (slp2 * x2) + (slp1 * x1)) /
		       (slp1 - slp2));
	    int xf = Math.round(xi);
	    int y = Math.round(slp1 * (xi - x1) + y1);
	    return between(c1.x,c2.x,xf) && between(c3.x,c4.x,xf);
	}
	//return false;
    }
    
    private static float slope(Cordinate c1, Cordinate c2){
	if (c1.x == c2.x && c1.y != c2.y){
	    return 99999999;
	}
	else if (c1.x == c2.x){
	    return 0;
	}
	else{
	    return ((float)(c1.y - c2.y)) / ((float)(c1.x - c2.x));
	}
    }
    private static boolean between(int x1, int x2, int x){
	return (x <= x1 && x >= x2) || (x >= x1 && x <= x2);
    }
    
}
