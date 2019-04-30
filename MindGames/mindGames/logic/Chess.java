package mindGames.logic;

import java.awt.List;

import mindGames.communication.components.*;
import mindGames.controller.MindGameController;
import mindGames.gamesPieces.Bishop;
import mindGames.gamesPieces.BlankSpace;
import mindGames.gamesPieces.King;
import mindGames.gamesPieces.Knight;
import mindGames.gamesPieces.Pawn;
import mindGames.gamesPieces.Queen;
import mindGames.gamesPieces.Rook;

public class Chess extends CommunicationComponent {

	protected String Position1;
	protected String Position2;
	protected List Coordinates;
	protected String GamePiece;
	protected Player myPlayer1;
	protected Player myPlayer2;
	protected static final Board board = new Board();;

	public Chess() {

	}

	public Chess(String Position1, String Position2, List Coordinates, String GamePiece) {

		setPosition1(Position1);
		setPosition2(Position2);
		setCoordinates(Coordinates);
		setGamePiece(GamePiece);
	}

	public String getPosition1() {
		return Position1;
	}

	public void setPosition1(String position1) {
		Position1 = position1;
	}

	public String getPosition2() {
		return Position2;
	}

	public void setPosition2(String position2) {
		Position2 = position2;
	}

	public List getCoordinates() {
		return Coordinates;
	}

	public void setCoordinates(List coordinates) {
		Coordinates = coordinates;
	}

	public String getGamePiece() {
		return GamePiece;
	}

	public void setGamePiece(String gamePiece) {
		GamePiece = gamePiece;
	}

	public Player getMyPlayer1() {
		return myPlayer1;
	}

	public void setMyPlayer1(Player myPlayer1) {
		this.myPlayer1 = myPlayer1;
	}

	public Player getMyPlayer2() {
		return myPlayer2;
	}

	public void setMyPlayer2(Player myPlayer2) {
		this.myPlayer2 = myPlayer2;
	}

	@Override
	protected void starGame(String player1, String player2) {

		Player whitePly = new Player(player1, "white");
		Player blackPly = new Player(player2, "black");

		setup(); // get board set

		while (true) {

			for (int runNum = 1; runNum <= 2; runNum++) { // run for each player
				board.drawBoardForChess(); // show board

				int move[][] = new int[2][2];

				while (true) {

					if (runNum == 1) { // first run
						move = whitePly.getMove();
					} else { // second run
						move = blackPly.getMove();
					}

					if (move[0][0] == -1) { // restarting loop if input is wrong
						System.out.println("Invalid location. Try again.");
						continue;
					}

					int[] moveFrom = move[0];
					int[] moveTo = move[1];
					Squares fromSquare = board.square[moveFrom[1]][moveFrom[0]];

					boolean checkValue;
					if (runNum == 1) {
						checkValue = fromSquare.checkMove(moveFrom, moveTo, "white", false); // checking for pawn move
																								// validity
					} else {
						checkValue = fromSquare.checkMove(moveFrom, moveTo, "black", false);
					}
					if (checkValue) {
						update(moveFrom, moveTo);

						if (runNum == 1) {
							if (checkForCheckOrMate("white") == "check") {
								System.out.println("Check!");
							}
						} else {
							if (checkForCheckOrMate("black") == "check") {
								System.out.println("Check!");
							}
						}
						break;
					}
					System.out.println("Invalid move. Try again."); // not printed if break is called
				}
			}
		}

	}

	protected static void setup() {

		
		
		// board array and location x,y are inverted
		board.square[0][0] = new Rook("black");
		board.square[0][1] = new Knight("black");
		board.square[0][2] = new Bishop("black");
		board.square[0][3] = new Queen("black");
		board.square[0][4] = new King("black");
		board.square[0][5] = new Bishop("black");
		board.square[0][6] = new Knight("black");
		board.square[0][7] = new Rook("black");

		// pawns
		for (int i = 0; i < 8; i++) {
			board.square[1][i] = new Pawn("black");
		}

		for (int i = 2; i < 6; i++) { // creating blank spaces in middle
			for (int j = 0; j < 8; j++) {
				board.square[i][j] = new BlankSpace();
			}
		}

		// pawns
		for (int i = 0; i < 8; i++) {
			board.square[6][i] = new Pawn("white");
		}

		board.square[7][0] = new Rook("white");
		board.square[7][1] = new Knight("white");
		board.square[7][2] = new Bishop("white");
		board.square[7][3] = new Queen("white");
		board.square[7][4] = new King("white");
		board.square[7][5] = new Bishop("white");
		board.square[7][6] = new Knight("white");
		board.square[7][7] = new Rook("white");

	}

	private static String checkForCheckOrMate(String plyColor) { // check for win or check
		for (int kingY = 0; kingY < 8; kingY++) {
			for (int kingX = 0; kingX < 8; kingX++) {
				Squares kingSquare = board.square[kingY][kingX];

				String kingColor;
				if (plyColor == "white") {
					kingColor = "black";
				} else { // black
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
