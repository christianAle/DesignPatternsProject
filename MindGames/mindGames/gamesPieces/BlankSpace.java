package mindGames.gamesPieces;

import mindGames.logic.Squares;

public class BlankSpace extends Squares {

	public BlankSpace() {
		super("blank");
		 pieceSymbol = "   ";
		 squareColor = null; //a blank square has no color
		
	}
	
	public void move(int[] moveToLoc) {
	}

	@Override
	public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {
		// TODO Auto-generated method stub
		return false;
	}

     
}
