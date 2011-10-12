/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eightslidepuzzle;

/**
 *
 * @author verworn
 */
public class EightSlidePuzzle {
    
    static void random (state X){
        for (int i = 0; i < 2000; i++)
            switch((int)(java.lang.Math.random()*4)){
                case 0:
                    X.UP(); break;
                case 1:
                    X.DOWN(); break;
                case 2:
                    X.LEFT(); break;
                case 3:
                    X.RIGHT(); break;
                case 4:
                default:
                    System.out.println("really...");    
            }
                   
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        state S = new state(0,1,2,3,4,5,6,7,8);
        S.out();
        random(S);
        S.out();
    }
}
