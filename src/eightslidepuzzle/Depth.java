package eightslidepuzzle;

import java.util.Arrays;
import java.util.Stack;
import static eightslidepuzzle.Manipulator.*;
/**
 * @author  Evan Verworn <ev09qz@brocku.ca>
 *          4582938
 * @version COSC 3P71 Assign 1
 * 
 * Depth first search, it's not optimal but it'll work every time.
 */
public class Depth implements Solver{
    Stack<String>   goalStack;
    State           current,goal;
    long            calc;
    
    private Depth(){}
    public  Depth(State S, State G){
        goalStack = new Stack<String>();
        current = S;
        goal = G;
        calc = 0;
        System.out.println("\nNOW SOLVING VIA DEPTH FIRST SEARCH");
        System.out.println("==================================");
    }
    
    public boolean goDeeper(State S){
        calc++;
        if (Arrays.equals(S.map, goal.map))
            return true;
        if (S.depth==32)
            return false;
        
        State temp = S.clone();
        temp.depth++;
        
        if(testUP(temp))
            if (goDeeper(UP(temp.clone())))
            {goalStack.add("UP"); return true;}
        if(testLEFT(temp))
            if (goDeeper(LEFT(temp.clone())))
            {goalStack.add("LEFT"); return true;}
        if(testDOWN(temp))
            if (goDeeper(DOWN(temp.clone())))
            {goalStack.add("DOWN"); return true;}
        if(testRIGHT(temp))
            if (goDeeper(RIGHT(temp.clone())))
            {goalStack.add("RIGHT"); return true;}
        
        
        return false;
    }
    
    
    @Override
    public void solve() {
        if (goDeeper(current))
            while(!goalStack.isEmpty())
                System.out.print(goalStack.pop()+", ");
        else
            System.out.println("UNSOLVABLE");
        System.out.println(calc+" tested.");
    }
    
}
