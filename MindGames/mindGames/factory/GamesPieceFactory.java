package mindGames.factory;

import mindGames.enums.ColorFicha;
import mindGames.enums.GamePieces;
import mindGames.gamesPieces.Bishop;
import mindGames.gamesPieces.Ficha;
import mindGames.gamesPieces.King;
import mindGames.gamesPieces.Knight;
import mindGames.gamesPieces.Pawn;
import mindGames.gamesPieces.Queen;
import mindGames.gamesPieces.Rook;
import mindGames.logic.GamePiece;
import mindGames.logic.Squares;

public class GamesPieceFactory {
	public static GamePiece getGamePiece(GamePieces enums,String color) {
		
		switch(enums){
		  
		case BISHOP:
			return new Bishop(color);
			
		case KING:
			return new King(color);
		 
		case KNIGHT:
			return new Knight(color);
			
		case PAW: 
			return new Pawn(color);
			
		case QUEEN:
			return new Queen(color);
		
		case ROOK:
			return new Rook(color);
		
		default:
			throw new RuntimeException("Not implemented");	
			
		}
	}
	
	public static Ficha getBlackChecker() {
		return new Ficha(ColorFicha.Black);
	}
	
	public static Ficha getWhiteChecker() {
		return new Ficha(ColorFicha.White);
	}
	
}
