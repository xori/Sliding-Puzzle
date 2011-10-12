package eightslidepuzzle;
import static java.lang.Math.sqrt;
/**
 *
 * @author verworn
 */
public class state {
    private int [][] map = null;
    private int      x,y;     // co-ordinates for the blank.
    
    public state(){}
    public state(int ... A){
        int magic = (int) sqrt(A.length);
        map = new int[magic][magic];
        // This hunk of code turns a 1D array into a 2D array.
        for(int Y = 0; Y<magic; Y++)
            for(int X = 0; X<magic; X++){
                map[X][Y] = A[Y*magic+X];
                if (map[X][Y] == 0){
                    this.x = X;
                    this.y = Y;
                }
            }
    }
    public state (int[][] M, int X, int Y){
        map = M;
        x   = X;
        y   = Y;
    }
    
    @Override
    public state clone (){
        return new state(map,x,y);
    }
    /**
     * For debugging.
     */
    public void out(){
        for(int Y = 0; Y<map.length; Y++)
            for(int X = 0; X<map.length; X++)
                System.out.print(map[X][Y]+" ");
        System.out.println();
    }
    
    public boolean LEFT(){
        if (x==0)
            return false;
        map[x][y]   = map[x][y] ^ map[x-1][y];
        map[x-1][y] = map[x][y] ^ map[x-1][y];
        map[x][y]   = map[x][y] ^ map[x-1][y];
        x--;
        return true;
    }
    
    public boolean RIGHT(){
        if (x==map.length-1)
            return false;
        map[x][y]   = map[x][y] ^ map[x+1][y];
        map[x+1][y] = map[x][y] ^ map[x+1][y];
        map[x][y]   = map[x][y] ^ map[x+1][y];
        x++;
        return true;
    }
    
    public boolean UP(){
        if (y==0)
            return false;
        map[x][y]   = map[x][y] ^ map[x][y-1];
        map[x][y-1] = map[x][y] ^ map[x][y-1];
        map[x][y]   = map[x][y] ^ map[x][y-1];
        y--;
        return true;
    }
    
    public boolean DOWN(){
        if (y==map.length-1)
            return false;
        map[x][y]   = map[x][y] ^ map[x][y+1];
        map[x][y+1] = map[x][y] ^ map[x][y+1];
        map[x][y]   = map[x][y] ^ map[x][y+1];
        y++;
        return true;
    }
}
