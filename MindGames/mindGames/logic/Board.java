package mindGames.logic;

import java.util.Scanner;

import mindGames.controller.MoveResult;
import mindGames.enums.ColorFicha;
import mindGames.enums.GamePieces;
import mindGames.enums.Games;
import mindGames.factory.GamesPieceFactory;
import mindGames.gamesPieces.Bishop;
import mindGames.gamesPieces.BlankSpace;
import mindGames.gamesPieces.Ficha;
import mindGames.gamesPieces.King;
import mindGames.gamesPieces.Knight;
import mindGames.gamesPieces.Pawn;
import mindGames.gamesPieces.Queen;
import mindGames.gamesPieces.Rook;

public class Board {

	public static final Scanner scanner = new Scanner(System.in);
	
	public static Squares square[][] = new Squares[8][8];
	public final static char siideLetters[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
	public final static int sideNumbers[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
    public final static int a[] = { 0, 1, 2, 3, 4, 5, 6, 7 };



	private final int MaxRows = 8;
    private final int MaxCols = 8;
    
    private Ficha board[][] = new Ficha[MaxRows][MaxCols];
    
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
	
	 public void init() {
	        board[0][1] = GamesPieceFactory.getWhiteChecker();
	        board[0][3] = GamesPieceFactory.getWhiteChecker();
	        board[0][5] = GamesPieceFactory.getWhiteChecker();
	        board[0][7] = GamesPieceFactory.getWhiteChecker();
	        board[2][1] = GamesPieceFactory.getWhiteChecker();
	        board[2][3] = GamesPieceFactory.getWhiteChecker();
	        board[2][5] = GamesPieceFactory.getWhiteChecker();
	        board[2][7] = GamesPieceFactory.getWhiteChecker();
	        board[1][0] = GamesPieceFactory.getWhiteChecker();
	        board[1][2] = GamesPieceFactory.getWhiteChecker();
	        board[1][4] = GamesPieceFactory.getWhiteChecker();
	        board[1][6] = GamesPieceFactory.getWhiteChecker();

	        board[5][0] = GamesPieceFactory.getBlackChecker();
	        board[5][2] = GamesPieceFactory.getBlackChecker();
	        board[5][4] = GamesPieceFactory.getBlackChecker();
	        board[5][6] = GamesPieceFactory.getBlackChecker();
	        board[6][1] = GamesPieceFactory.getBlackChecker();
	        board[6][3] = GamesPieceFactory.getBlackChecker();
	        board[6][5] = GamesPieceFactory.getBlackChecker();
	        board[6][7] = GamesPieceFactory.getBlackChecker();
	        board[7][0] = GamesPieceFactory.getBlackChecker();
	        board[7][2] = GamesPieceFactory.getBlackChecker();
	        board[7][4] = GamesPieceFactory.getBlackChecker();
	        board[7][6] = GamesPieceFactory.getBlackChecker();

	    }
	  public void moveFicha(int filInicio, int colInicio, int filFinal, int colFinal) {
	        boolean comer = quieroComer(filInicio, filFinal);
	        if (comer == true) {
	            Ficha inicio = getFicha(filInicio, colInicio);
	            Ficha medio = getJumpFicha(filInicio, colInicio, filFinal, colFinal);

	            boolean sameTeam = sameColor(inicio, medio);
	            if (sameTeam != true) {
	                clearSpace(medio);

	                clearSpace(inicio);
	                fillSpace(filFinal, colFinal, inicio);
	            }
	        } else {
	            Ficha temp = getFicha(filInicio, colInicio);
	            clearSpace(temp);
	            fillSpace(filFinal, colFinal, temp);
	        }
	    }


	    public void print() {
	        System.out.print("\n   ");
	        for (int s : a) {
	            System.out.print(" " + s + " ");
		}
	        System.out.println();

	        for (int i = 0; i < MaxRows; i++) {
	            System.out.print(" " + ( 0 + i ) + " ");
	            
	            for (int j = 0; j < MaxCols; j++) {
	                Ficha ficha = board[i][j];
	                if (ficha == null) {
	                    System.out.print("[" + " " + "]");
	                } else {
	                    if (ficha.getColorFicha() == ColorFicha.Black) {
	                        System.out.print("[" + "B" + "]");
	                    } else {
	                        System.out.print("[" + "W" + "]");
	                    }
	                }
	            }
	            System.out.print(" " + ( 0 + i ) + " ");
	            System.out.println();
	        }
	        System.out.println();
	    
	   }
	    public MoveResult movementIsValid(int filInicio, int colInicio, int filFinal, int colFinal) {
	        if (filFinal < 0 || filFinal > 7) {
	            return new MoveResult(false, "La nueva fila debe ser mayor a cero y menor a 7.");
	        }
	        if (colFinal < 0 || colFinal > 7) {
	            return new MoveResult(false, "La nueva columna debe ser mayor a cero y menor a 7.");
	        }

	        Ficha nextPosition = getFicha(filFinal, colFinal);
	        if (nextPosition != null) {
	            return new MoveResult(false, "La nueva casilla debe estar desocupada.");
	        }

	        int diffCols = Math.abs(colFinal - colInicio);
	        if (diffCols > 2) {
	            return new MoveResult(false, "Las fichas solo pueden moverse un maximo de dos columnas");
	        }

	        Ficha current = getFicha(filInicio, colInicio);
	        int diffFil = filFinal - filInicio;
	        if (current.getColorFicha() == ColorFicha.White && !(0 < diffFil && diffFil < 3)) {
	            return new MoveResult(false, "Las fichas blancas solo pueden avanzar un maximo de dos filas si no estan coronadas.");
	        }
	        if (current.getColorFicha() == ColorFicha.Black && !(-3 < diffFil && diffFil < 0)) {
	            return new MoveResult(false, "Las fichas negras solo pueden avanzar un maximo de dos filas si no estan coronadas.");
	        }

	        return new MoveResult();
	    }

	    public Ficha getFicha(int fil, int col) {
	        return board[fil][col];
	    }

	    private void fillSpace(int fil, int col, Ficha ficha) {
	        board[fil][col] = ficha;
	    }

	    private void clearSpace(Ficha ficha) {
	        for (int i = 0; i < MaxRows; i++) {
	            for (int j = 0; j < MaxCols; j++) {
	                if (board[i][j] == ficha) {
	                    board[i][j] = null;
	                }
	            }
	        }
	    }

	    public boolean quieroComer(int filInicio, int filFinal) {
	        if (filFinal - filInicio > 1 || filFinal - filInicio == -2) {
	            return true;
	        } else {
	            return false;
	        }
	    }

	    public Ficha getJumpFicha(int filInicio, int colInicio, int filFInal, int colFinal) {
	        int filMedio = filInicio + ((filFInal - filInicio) / 2);
	        int colMedio = colInicio + ((colFinal - colInicio) / 2);

	        return getFicha(filMedio, colMedio);
	    }

	    public boolean sameColor(Ficha ficha1, Ficha ficha2) {
	        // aqui va a dar un null pointer exception si la ficha2 o la ficha1 es null
	        if (ficha1.getColorFicha() == ficha2.getColorFicha()) {
	            return true;
	        } else {
	            return false;
	        }
	    }

	    public void eatFicha(int filInicio, int colInicio, int filFinal, int colFinal) {

	        boolean movement = quieroComer(filInicio, filFinal);
	        if (movement == true) {
	            Ficha inicio = getFicha(filInicio, colInicio);
	            Ficha medio = getJumpFicha(filInicio, colInicio, filFinal, colFinal);

	            boolean sameTeam = sameColor(inicio, medio);
	            if (sameTeam != true) {
	                clearSpace(medio);
	                //moveFicha(filInicio, colInicio, filFinal, colFinal);
	            }
	        }
	    }
}
