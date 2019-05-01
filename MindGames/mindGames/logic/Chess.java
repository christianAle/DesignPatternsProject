package mindGames.logic;

import java.awt.List;

import mindGames.communication.components.*;
import mindGames.controller.MindGameController;
import mindGames.enums.GamePieces;
import mindGames.factory.GamesPieceFactory;
import mindGames.gamesPieces.Bishop;
import mindGames.gamesPieces.BlankSpace;
import mindGames.gamesPieces.King;
import mindGames.gamesPieces.Knight;
import mindGames.gamesPieces.Pawn;
import mindGames.gamesPieces.Queen;
import mindGames.gamesPieces.Rook;

public class Chess extends CommunicationComponent {

	protected Player myPlayer1;
	protected Player myPlayer2;
	protected static final Board board = new Board();;
	protected MindGameController controller =new MindGameController();

	public Chess() {

	}

	

	@Override
	protected void starGame(String player1, String player2) {

		this.myPlayer1 = new Player(player1, "white");
		this.myPlayer2 = new Player(player2, "black");
        
		setup(); // get board set

		while (true) {

			for (int runNum = 1; runNum <= 2; runNum++) { // run for each player
				board.drawBoardForChess(); // show board

				int move[][] = new int[2][2];

				while (true) {

					if (runNum == 1) { // first run
						move = myPlayer1.getMove();
					} else { // second run
						move = myPlayer2.getMove();
					}

					if (move[0][0] == -1) {
						System.out.println("Invalid location. Try again.");
						continue;
					}

					int[] moveFrom = move[0];
					int[] moveTo = move[1];
					Squares fromSquare = board.square[moveFrom[1]][moveFrom[0]];

					boolean checkValue;
					if (runNum == 1) {
						checkValue = fromSquare.checkMove(moveFrom, moveTo, "white", false);
					} else {
						checkValue = fromSquare.checkMove(moveFrom, moveTo, "black", false);
					}
					if (checkValue) {
						update(moveFrom, moveTo);

						if (runNum == 1) {
							if (checkForCheckOrMate("white") == "check") {
								this.controller.print("Check!");
							}
						} else {
							if (checkForCheckOrMate("black") == "check") {
								this.controller.print("Check!");
							}
						}
						break;
					}
					this.controller.print("Invalid move. Try again.");
				}
			}
		}

	}


	protected static void setup() {

		// board array and location x,y are inverted
		board.square[0][0] = GamesPieceFactory.getGamePiece(GamePieces.ROOK, "black");
		board.square[0][1] = GamesPieceFactory.getGamePiece(GamePieces.KNIGHT, "black");
		board.square[0][2] = GamesPieceFactory.getGamePiece(GamePieces.BISHOP, "black");
		board.square[0][3] = GamesPieceFactory.getGamePiece(GamePieces.QUEEN, "black");
		board.square[0][4] = GamesPieceFactory.getGamePiece(GamePieces.KING, "black");
		board.square[0][5] = GamesPieceFactory.getGamePiece(GamePieces.BISHOP,"black");
		board.square[0][6] = GamesPieceFactory.getGamePiece(GamePieces.KNIGHT,"Black");
		board.square[0][7] = GamesPieceFactory.getGamePiece(GamePieces.ROOK, "black");
		// pawns
		
		for (int i = 0; i < 8; i++) {
			board.square[1][i] = GamesPieceFactory.getGamePiece(GamePieces.PAW, "black");
		}

		for (int i = 2; i < 6; i++) { // creating blank spaces in middle
			for (int j = 0; j < 8; j++) {
				board.square[i][j] = new BlankSpace();
			}
		}

		// pawns
		for (int i = 0; i < 8; i++) {
			board.square[6][i] = GamesPieceFactory.getGamePiece(GamePieces.PAW, "white");
		}

		board.square[7][0] = GamesPieceFactory.getGamePiece(GamePieces.ROOK, "white");
		board.square[7][1] = GamesPieceFactory.getGamePiece(GamePieces.KNIGHT, "white");
		board.square[7][2] = GamesPieceFactory.getGamePiece(GamePieces.BISHOP, "white");
		board.square[7][3] = GamesPieceFactory.getGamePiece(GamePieces.QUEEN, "white");
		board.square[7][4] = GamesPieceFactory.getGamePiece(GamePieces.KING, "white");
		board.square[7][5] = GamesPieceFactory.getGamePiece(GamePieces.BISHOP, "white");
		board.square[7][6] = GamesPieceFactory.getGamePiece(GamePieces.KNIGHT, "white");
		board.square[7][7] = GamesPieceFactory.getGamePiece(GamePieces.ROOK, "white");

	}
	
	
	
	private static String checkForCheckOrMate(String plyColor) { // check for win or check
		for (int kingY = 0; kingY < 8; kingY++) {
			for (int kingX = 0; kingX < 8; kingX++) {
				Squares kingSquare = board.square[kingY][kingX];

				String kingColor;
				if (plyColor == "white") {
					kingColor = "black";
				} else {
					kingColor = "white";
				}

				if ((kingSquare.getType() == "king") && (kingSquare.getSquareColor() == kingColor)) {

					for (int threatY = 0; threatY < 8; threatY++) {
						for (int threatX = 0; threatX < 8; threatX++) {
							Squares threatSquare = board.square[threatY][threatX];

							if ((threatSquare.getType() != "blank") && (threatSquare.getSquareColor() == plyColor)) {
								int[] moveFrom = { threatX, threatY };
								int[] moveTo = { kingX, kingY };

								if (threatSquare.checkMove(moveFrom, moveTo, plyColor, true)) {
									return "check";
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	private static void update(int[] origLoc, int[] newLoc) {
		board.square[newLoc[1]][newLoc[0]] = board.square[origLoc[1]][origLoc[0]];
		board.square[origLoc[1]][origLoc[0]] = new BlankSpace();
	}

}
