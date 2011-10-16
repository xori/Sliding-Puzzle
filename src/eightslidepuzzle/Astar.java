package eightslidepuzzle;

import java.util.Queue;
import java.util.Stack;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import static java.lang.Math.abs;
import static eightslidepuzzle.EightSlidePuzzle.MAGIC;
import static eightslidepuzzle.Manipulator.*;
/**
 * @author  Evan Verworn <ev09qz@brocku.ca>
 *          4582938
 * @version COSC 3P71 Assign 1
 * 
 *  A*, always gives optimal solution. Worst case for 8 puzzle is
 *      8 0 6 5 4 7 2 3 1
 *  
 */
public class Astar implements Solver{
    private Queue<State>	check;
    private List<State> done;
    private State 	GOAL,current;
    // Reverses the order of operations.
    private Stack<String> theGoalStack;
    private int calc,thrown = 0;
    
    
    public Astar(State S,State G){
    	check = new PriorityQueue<State>();
        done  = new ArrayList<State>();
        theGoalStack = new Stack<String>();
    	S.depth = 0;
        S.lastMove = "START";
        check.add(S);
    	GOAL = G;
        current = S;
        System.out.println("NOW SOLVING VIA A* SEARCH");
        System.out.println("=========================");
    }
    
    @Override
    public void solve(){
        while(!isGOALreached(current)){
            // Continue to generate and grade states until the GOAL is found.
            GENERATE(current);
            try{current = check.remove();}
            catch(NoSuchElementException e){
                // OR until we run out of checked nodes.
                //  (will run out of memory first.)
                System.out.println("NO SOLUTION FOUND");
                return;
            }
            done.add(current);
            if(calc%10000==0){
                System.out.print(" "+check.size()+":: ");
                current.out();
            }
        }
        while(!("START".equals(current.lastMove))){
            theGoalStack.push(current.lastMove);
            current = current.parent;
        }
        System.out.print(theGoalStack.size()+" moves: ");
        while(!theGoalStack.isEmpty()){
            System.out.print(theGoalStack.pop()+", ");
        }
        System.out.println();
    }
        
    public void GRADE(State S){
    	int cost = 0;
    	if (Arrays.equals(S.map, GOAL.map))
    		System.out.println("GOAL FOUND ::: "+calc+" tested ::: "+thrown+" thrown away.");
    	// Optimized grading. Instead of 9^2 comparisons
    	//	I instead do 4 'complex' calculations
    	for (int i = 0; i < S.map.length; i++){
    		cost += abs(GOAL.map[i]%MAGIC - S.map[i]%MAGIC)+
    			abs(S.map[i]/MAGIC - GOAL.map[i]/MAGIC);
    	}
    	S.score = cost+ S.depth;
    }
    
    // This will create a branch of new nodes waiting to be graded.
    private void GENERATE(State S){
    	State temp;
    	calc++;
    	if(testUP(S)){
            temp = UP(S);
            temp.depth = S.depth+1;
            temp.parent = S;
            GRADE(temp);
            if(!exists(temp))
            check.add(temp);
    	}
        if(testLEFT(S)){
            temp = LEFT(S);
            temp.depth = S.depth+1;
            temp.parent = S;
            GRADE(temp);
            if(!exists(temp))
            check.add(temp);
        }
        if(testDOWN(S)){
            temp = DOWN(S);
            temp.depth = S.depth+1;
            temp.parent = S;
            GRADE(temp);
            if(!exists(temp))
                check.add(temp);
        }
        if(testRIGHT(S)){
            temp = RIGHT(S);
            temp.depth = S.depth+1;
            temp.parent = S;
            GRADE(temp);
            if(!exists(temp))
                check.add(temp);
        }
    }
    
    private boolean exists(State S){
        for (State i : done){
            if(Arrays.equals(i.map, S.map)){
                thrown++;
                return true;
            }
        }
        return false;
    }
    
    
    public boolean isGOALreached(State S){
        return (Arrays.equals(S.map, GOAL.map));
    }
}
