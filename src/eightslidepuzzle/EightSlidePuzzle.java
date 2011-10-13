/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eightslidepuzzle;
import static eightslidepuzzle.Manipulator.*;
/**
 *
 * @author verworn
 */
public class EightSlidePuzzle {
	// Dimension of puzzle. In our example 3.
    public static int MAGIC = 0; 
    
    
    static void random (State G){
    	State X = G.clone();
        for (int i = 0; i < 2000; i++)
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
        Solver A = new Asharp(X,G);
        A.solve();
                   
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        State S = new State(0,1,2,3,4,5,6,7,8);
        S.out();
        random(S);
        S.out();
    }
}
