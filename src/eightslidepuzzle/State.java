package eightslidepuzzle;
import static java.lang.Math.sqrt;
import static eightslidepuzzle.EightSlidePuzzle.MAGIC;
/**
 *
 * @author verworn
 */
public class State implements Comparable<State> {
    public int[] 	map = null;
    public int      x,y,pos;     // co-ordinates for the blank.
    public int		score,depth;
    public String 	lastMove;
    
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
        return new State(map.clone());
    }
    
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
        System.out.println();
    }
    
    
}
