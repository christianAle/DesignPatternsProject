package mindGames.logic;

public abstract class Squares {
	
  public String pieceSymbol;
  public String squareColor; 
  public String type;

  
  public Squares(String typeIn){
		this.type = typeIn;
	}

	
	public String getPieceSymbol() {
		return pieceSymbol;
	}
	
	
	public void setPieceSymbol(String pieceSymbol) {
		this.pieceSymbol = pieceSymbol;
	}
	
	
	public String getSquareColor() {
		return squareColor;
	}
	
	
	public void setSquareColor(String squareColor) {
		this.squareColor = squareColor;
	}
	
	
	public String getType() {
		return type;
	}
	
	
	public void setType(String type) {
		this.type = type;
	}
	
	
	public abstract boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing);

	


  
}
