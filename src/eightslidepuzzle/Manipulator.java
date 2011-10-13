package eightslidepuzzle;

import static eightslidepuzzle.EightSlidePuzzle.MAGIC;

public class Manipulator {
	
	public static State LEFT(State S){
        if (!testLEFT(S))
            return S;
        State nS = S.clone();
        
        int pos = S.pos;
       	
        nS.map[pos]   = nS.map[pos] ^ nS.map[pos-1];
        nS.map[pos-1] = nS.map[pos] ^ nS.map[pos-1];
        nS.map[pos]   = nS.map[pos] ^ nS.map[pos-1];
        
        nS.x--; nS.pos--;
        nS.lastMove = "LEFT";
        return nS;
    }
    
    public static State RIGHT(State S){
        if (!testRIGHT(S))
            return S;
        State nS = S.clone(); int pos = S.pos;
       	
        nS.map[pos]   = nS.map[pos] ^ nS.map[pos+1];
        nS.map[pos+1] = nS.map[pos] ^ nS.map[pos+1];
        nS.map[pos]   = nS.map[pos] ^ nS.map[pos+1];
        
        nS.x++; nS.pos++;
        nS.lastMove = "RIGHT";
        return nS;
    }
    
    public static State UP(State S){
        if (!testUP(S))
        	return S;
       	State nS = S.clone(); int pos = S.pos;
       	
		nS.map[pos]   		= nS.map[pos] ^ nS.map[pos-MAGIC];
        nS.map[pos-MAGIC] 	= nS.map[pos] ^ nS.map[pos-MAGIC];
        nS.map[pos]			= nS.map[pos] ^ nS.map[pos-MAGIC];
		
        nS.y--; nS.pos-=MAGIC;
        nS.lastMove = "UP";
        return nS;
    }
    
    public static State DOWN(State S){
        if (!testDOWN(S))
        	return S;
       	State nS = S.clone(); int pos = S.pos;
       	
		nS.map[pos]   		= nS.map[pos] ^ nS.map[pos+MAGIC];
        nS.map[pos+MAGIC] 	= nS.map[pos] ^ nS.map[pos+MAGIC];
        nS.map[pos]			= nS.map[pos] ^ nS.map[pos+MAGIC];
		
        nS.y++; nS.pos+=MAGIC;
        nS.lastMove = "DOWN";
        return nS;
    }
    
    public static boolean testLEFT	(State S)	{return S.x!=0;}
    public static boolean testRIGHT	(State S)	{return S.x!=MAGIC-1;}
    public static boolean testUP	(State S)	{return S.y!=0;}
    public static boolean testDOWN	(State S)	{return S.y!=MAGIC-1;}
    
    
}
