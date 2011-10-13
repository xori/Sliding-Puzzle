package eightslidepuzzle;

import java.util.List;
import java.util.ArrayList;
import static java.lang.Math.abs;
import static eightslidepuzzle.EightSlidePuzzle.MAGIC;
import static eightslidepuzzle.Manipulator.*;
/**
 *
 * @author verworn
 */
public class Asharp implements Solver{
    private Node 	todo;
    private State 	GOAL;
    
    public Asharp(State S,State G){
    	todo = new Node(S,null);
    	todo.depth = 0;
    	GOAL = G;
    }
    
    public void solve(){
    	GENERATE(todo);
    	System.out.println(todo.toString());
    	for (Node n : todo.children){
    		GENERATE(n);
    		System.out.println(n.toString());
    	}
    }
        
    public void GRADE(State S){
    	int cost = 0;
    	if (S.map.equals(GOAL.map))
    		System.out.println("GOAL\n"+S.toString());
    	// Optimized grading. Instead of 9^2 comparisons
    	//	I instead do 4 'complex' calculations
    	for (int i = 0; i < S.map.length; i++){
    		cost += abs(GOAL.map[i]%MAGIC - S.map[i]%MAGIC)+
    			abs(S.map[i]/MAGIC - GOAL.map[i]/MAGIC);
    	}
    	S.score = cost+S.depth;
    }
    
    // This will create a branch of new nodes waiting to be graded.
    public void GENERATE(Node n){
    	State temp;
    	
    	if(testUP(n.data)){
    	}
    }
    
    public void CHOOSEBEST(State S){
        
    }
    
    private void ADD2LIST(State S){
        
    }
    
    private class Node {
	    public State data;
        public Node parent;
        public List<Node> children;
        public int depth;
        public int rating;
        public String move;
	
	    public Node(State rootData, Node P) {
	        parent = P;
	        data = rootData;
	        children = new ArrayList<Node>();
	        depth = (parent==null)?0:parent.depth+1;
	    }
	    
	    public String toString (){
	    	String out = "";
	    	out += this.hashCode() + "-> ";
	    	for (Node n : children){
	    		out += n.hashCode()+", ";
	    	}
	    	return out;
	    }
	}

}
