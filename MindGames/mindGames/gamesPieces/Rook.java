package mindGames.gamesPieces;

import mindGames.logic.Board;
import mindGames.logic.GamePiece;
import mindGames.logic.Squares;

public class Rook extends GamePiece {

	public Rook(String colorIn) {
		super(colorIn, "rook");

		if (squareColor == "white") {
			pieceSymbol = "wRo";
		} else {
			pieceSymbol = "bRo";
		}
	}
	
	public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {
		
		int moveFromX = moveFromReq[0];
		int moveFromY = moveFromReq[1];
		int moveToX = moveToReq[0];
		int moveToY = moveToReq[1];
		
		Squares toSquare = Board.square[moveToY][moveToX];
		
		String direction;
		
		if(!testKing){
			if(toSquare.getType() == "king"){
				return false; //can't move to take a king
			}
		}
		
		if(moveToY == moveFromY){
			if(moveToX > moveFromX){
				direction = "rite";
			}
			else{
				direction = "left";
			}
		}
		
		else if(moveToX == moveFromX){
			if(moveToY > moveFromY){
				direction = "bot";
			}
			else{
				direction = "top";
			}
		}
		else{
			return false;
		}
		
		Squares testSquare;
		
		if((direction == "rite") || (direction == "left")){
			int displaceMax = Math.abs(moveToX - moveFromX); //displacement max depending on what the move to values are
		
			for(int displace = 1; displace <= displaceMax; displace++){ //looping through squares on the rooks path
				if(direction == "rite"){
					testSquare = Board.square[moveFromY][moveFromX + displace];
					
					if((testSquare.getType() != "blank") && (displace != displaceMax)){
						return false;
					}
					else if((displace == displaceMax) && ((testSquare.getType() == "blank") || (testSquare.getSquareColor() != plyColor))){
						return true;
					}
				}
				else{
					testSquare = Board.square[moveFromY][moveFromX - displace];
					
					if((testSquare.getType() != "blank") && (displace != displaceMax)){
						return false;
					}
					else if((displace == displaceMax) && ((testSquare.getType() == "blank") || (testSquare.getSquareColor() != plyColor))){
						return true;
					}
				}
			}
		}
		else{ // direction : top or bot
			int displaceMax = Math.abs(moveToY - moveFromY); //displacement max depending on what the move to values are
				
			for(int displace = 1; displace <= displaceMax; displace++){ //looping through squares on the rooks path	
				
				if(direction == "top"){
					testSquare = Board.square[moveFromY - displace][moveFromX];
					
					if((testSquare.getType() != "blank") && (displace != displaceMax)){
						return false;
					}
					else if((displace == displaceMax) && ((testSquare.getType() == "blank") || (testSquare.getSquareColor() != plyColor))){
						return true;
					}
				}
				else{
					testSquare = Board.square[moveFromY + displace][moveFromX];
					
					if((testSquare.getType() != "blank") && (displace != displaceMax)){
						return false;
					}
					else if((displace == displaceMax) && ((testSquare.getType() == "blank") || (testSquare.getSquareColor() != plyColor))){
						return true;
					}
				}
			}
		}
		return false;
	}


}
