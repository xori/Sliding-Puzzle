/*
 * Evan Verworn <ev09qz@brocku.ca>
 * 4582938
 * COSC 3P71 Assign 1
 */
package eightslidepuzzle;
import static eightslidepuzzle.Manipulator.*;
/**
 *
 * @author verworn
 */
public class EightSlidePuzzle {
    // Dimension of puzzle. In our example will be 3.
    public static int MAGIC = 0; 
    
    /**
     * Main function.
     *  When called with no params will randomly shift the default goal state
     *  100 times and use that as the puzzle state.
     * 
     *  If given 9 integers (eg. ./RUN 8 7 6 5 4 3 2 1 0) it will use those 9
     *  integers as the STARTING(S) state.
     * 
     *  if 18 integers are given, it will use the first 9 as the STARTING(S) state
     *  and the remaining 9 for the GOAL state.
     * 
     *  NOTE: No checks are done to assume proper input. 
     *  NOTE2: If A* starts spouting numbers it means that it's just showing that
     *          its still calculating stuff. For example
     *   5635:: 5 3 6 2 4 7 0 1 8 33 15
     *          The first number is how many elements are in the tree. The next 8
     *          numbers is the current best. The last 2 numbers are the score followed
     *          by the current depth.
     */
    public static void main(String[] args) {
        State G = new State(0,1,2,3,4,5,6,7,8);
        State S = null;
        int[] temp = new int[9];
        long time = 0;
        
        // ARGUMENT PARSING
        if (args.length>=9){
            for(int i = 0; i<9; i++)
                temp[i] = new Integer(args[i]);
            S = new State(temp);            
        } else
            S = random(G);
        if (args.length==18){
            for(int j = 0; j<9; j++)
                temp[j] = new Integer(args[j+9]);
            G = new State(temp);
        }
        // END ARGUMENT PARSE.
        
        // Generate Puzzle;
        System.out.print(" GOAL: ");
        G.out();
        System.out.print("STATE: ");
        S.out();
        System.out.println();
        
        // Solve with A*
            time = System.currentTimeMillis();
        Solver Sherlock = new Astar(S,G);
        Sherlock.solve();
            System.out.println(System.currentTimeMillis()-time+"ms to solve");
            
        // Sove with Depth First;
            time = System.currentTimeMillis();
        Sherlock = new Depth(S, G);
        Sherlock.solve();
            System.out.println(System.currentTimeMillis()-time+"ms to solve");
    }
    
    static State random (State G){
    	State X = G.clone();
        for (int i = 0; i < 100; i++)
            switch((int)(java.lang.Math.random()*4)){
                case 0:
                    X=UP(X); break;
                case 1:
                    X=DOWN(X); break;
                case 2:
                    X=LEFT(X); break;
                case 3:
                    X=RIGHT(X); break;
                case 4:
                default:
                    System.out.println("really...");    
            }                   
        return X;
    }
}
