package eightslidepuzzle;

import java.util.Stack;
import java.util.Collections;
import java.util.Arrays;
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
    private List<State>	check,done;
    private State 	GOAL,current;
    // Reverses the order of operations.
    private Stack<String> theGoalStack;
    private int calc = 0;
    
    
    public Asharp(State S,State G){
    	check = new ArrayList<State>();
        done  = new ArrayList<State>();
    	S.depth = 0;
        S.lastMove = "START";
        check.add(S);
    	GOAL = G;
        current = S;
    }
    
    @Override
    public void solve(){
    	long time=0;
        while(!isGOALreached(current)){
            GENERATE(current);
            Collections.sort(check);
            current = check.get(0);
            /*
            while(done.contains(current)){
            	//done.add();
            	check.remove(current);
            	current = check.get(0);
            }
            */
            done.add(current);
            check.remove(current);
            if(calc%4000==0){
                System.out.print(check.size()+":: ");
                current.out();                
            }
        }
    }
        
    public void GRADE(State S){
    	int cost = 0;
    	if (Arrays.equals(S.map, GOAL.map))
    		System.out.println("GOAL\n"+S.toString()+" :::"+calc);
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
            temp.lastMove = "UP";
            GRADE(temp);
            check.add(temp);
    	}
        if(testLEFT(S)){
            temp = LEFT(S);
            temp.depth = S.depth+1;
            temp.lastMove = "LEFT";
            GRADE(temp);
            check.add(temp);
        }
        if(testDOWN(S)){
            temp = DOWN(S);
            temp.depth = S.depth+1;
            temp.lastMove = "DOWN";
            GRADE(temp);
            check.add(temp);
        }
        if(testRIGHT(S)){
            temp = RIGHT(S);
            temp.depth = S.depth+1;
            temp.lastMove = "RIGHT";
            GRADE(temp);
            check.add(temp);
        }
    }
    
    public boolean isGOALreached(State S){
        return (Arrays.equals(S.map, GOAL.map));
    }
}
