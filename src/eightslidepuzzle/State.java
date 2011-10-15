package eightslidepuzzle;
import static java.lang.Math.sqrt;
import static eightslidepuzzle.EightSlidePuzzle.MAGIC;

/**
 * @author  Evan Verworn <ev09qz@brocku.ca>
 *          4582938
 * @version COSC 3P71 Assign 1
 * 
 * This class holds a 'State' of numbers, depth, score and what was last moved.
 */
public class State implements Comparable<State> {
    public int[] 	map = null;
    public int      x,y,pos;     // co-ordinates for the blank.
    public int		score,depth;
    public String 	lastMove = "-";
    public State        parent;
    
    private State(){}
    public State(int ... A){
        MAGIC = (int) sqrt(A.length);
        map = A;
        for (int i = 0; i<map.length; i++){
        	if (map[i]==0){
        		pos = i;
        		x = i % MAGIC;
        		y = i / MAGIC;
        		break;
        	}
        }
    }
    
    @Override
    public State clone (){
        State r = new State();
        r.depth = this.depth;
        r.lastMove = this.lastMove;
        r.map = this.map.clone();
        r.pos = this.pos;
        r.x = this.x;
        r.y = this.y;
        r.score = this.score;
        return r;
    }
    
    /**
     * Allows for Sorting.
     * @param S State to compare to.
     * @return 
     */
    @Override
    public int compareTo(State S){
    	if (S.score < score)
    		return 1;
    	else if (S.score > score)
    		return -1;
    	else
    		return 0;
    }
    
    /**
     * For debugging.
     */
    public void out(){
        for(int i = 0; i<map.length; i++)
                System.out.print(map[i]+" ");
        System.out.println(score+" "+depth);
    }
}
