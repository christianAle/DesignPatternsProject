package mindGames.logic;

import java.util.Scanner;

import mindGames.enums.Games;
import mindGames.gamesPieces.Bishop;
import mindGames.gamesPieces.BlankSpace;
import mindGames.gamesPieces.King;
import mindGames.gamesPieces.Knight;
import mindGames.gamesPieces.Pawn;
import mindGames.gamesPieces.Queen;
import mindGames.gamesPieces.Rook;

public class Board {
	public static Squares square[][] = new Squares[8][8];
	public final static char siideLetters[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
	public final static int sideNumbers[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
	public static final Scanner scanner = new Scanner(System.in);

	public Board() {

	}

	public void drawBoardForChess() {

		System.out.print("\n   ");

		for (char i : siideLetters) { // printing letters across the top
			System.out.print("  " + i + "  ");
		}
		System.out.print("\n   ");

		for (int i = 0; i < 8; i++) {
			System.out.print(" --- ");
		}

		System.out.print("\n");
		for (int i = 0; i < 8; i++) { // looping through the board and printing symbols
			System.out.print(" " + (8 - i) + " "); // print number on left side

			for (Squares j : square[i]) {
				System.out.print("|" + j.getPieceSymbol() + "|");
			}
			System.out.print(" " + (8 - i) + " "); // number on right side

			System.out.print("\n   ");// to get next line

			for (int j = 0; j < 8; j++) {
				System.out.print(" --- ");
			}
			System.out.print("\n");
		}
		System.out.print("   ");
		for (char i : siideLetters) { // printing letters across the bottom
			System.out.print("  " + i + "  ");
		}
		System.out.print("\n\n");

	}

}
