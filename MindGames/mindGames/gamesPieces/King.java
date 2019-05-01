package mindGames.gamesPieces;


import mindGames.logic.Board;
import mindGames.logic.GamePiece;
import mindGames.logic.Squares;

public class King extends GamePiece {

	public King(String colorIn) {
		super(colorIn,"king");

		if (squareColor== "white") {
			pieceSymbol = "wKi";
		} else {
			pieceSymbol = "bKi";
		}
	}
	public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {
		
		int moveFromX = moveFromReq[0];
		int moveFromY = moveFromReq[1];
		int moveToX = moveToReq[0];
		int moveToY = moveToReq[1];
		
		Squares toSquare = Board.square[moveToY][moveToX];
		
		for (int moveAwayX = -1; moveAwayX <= 1; moveAwayX++){
			for (int moveAwayY = -1; moveAwayY <= 1; moveAwayY++){
				if(moveToX == moveFromX + moveAwayX && moveToY == moveFromY + moveAwayY){
					if((toSquare.getType() != "blank") && (toSquare.getSquareColor() != plyColor)){
						return true;
					}
					else if(toSquare.getType() == "blank"){
						return true;
					}
				}
			}
		}
		
		return false;
	}

	
}
