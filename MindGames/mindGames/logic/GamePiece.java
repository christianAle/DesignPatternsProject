package mindGames.logic;

public class GamePiece  extends Squares {
	

   public GamePiece(String color ,String typeIn) {
		super(typeIn);
		squareColor=color;
		// TODO Auto-generated constructor stub
	}
      
	
	@Override
	public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {
		// TODO Auto-generated method stub
		return false;
	}


}
