package mindGames.logic;

import java.lang.Thread.State;
import java.util.Scanner;

import src.mindGames.UI.UI;

public class Player {

	private String namePlayer;
	private String color;
	State state;

	private final Scanner scanner = new Scanner(System.in);

	public Player() {

	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Player(String namPlayer, String color) {
		this.namePlayer = namPlayer;
		this.color = color;
	}

	public void setNamePlayer(String namePlayer) {
		this.namePlayer = namePlayer;
	}

	public String getNamePlayer() {

		return this.namePlayer;
	}

	private int convertCharToNum(char charIn) {
		int numOut = -1;

		for (int i = 0; i < Board.siideLetters.length; i++) {

			if (Board.siideLetters[i] == charIn) {
				numOut = i;
			}
		}
		return numOut;
	}

	private int convertCharNumtoNum(char charIn) {
		int numOut = -1;
		int convertedNum = Character.getNumericValue(charIn);

		for (int i : Board.sideNumbers) {
			if (i == convertedNum) {
				numOut = convertedNum;
			}
		}
		return numOut;
	}

	public int[][] getMove() {
		String out = "";
		int[][] move = new int[2][2];

		for (int runNum = 1; runNum <= 2; runNum++) {

			while (true) {
				if (runNum == 1) {
					UI.print(namePlayer + ", indique la ficha que quiere mover. (EX: E4)\n>> ");
				} else {
					UI.print(namePlayer + ", indique donde quiere que la ficha se mueva. (EX: E4)\n>> ");
				}
				String moveIn = scanner.nextLine().trim();

				if (!moveIn.isEmpty() && moveIn.length() <= 2 && !(moveIn.contains(" ") || moveIn.contains("\t"))) {

					if (!Character.isDigit(moveIn.charAt(0)) && Character.isDigit(moveIn.charAt(1))) {
						int x, y;

						if ((x = convertCharToNum(Character.toUpperCase(moveIn.charAt(0)))) != -1) {
							if ((y = convertCharNumtoNum(moveIn.charAt(1))) != -1) {
								y = 8 - y;
								int tempArray[] = { x, y };
								if (runNum == 1) {
									if (Board.square[y][x].getType() == "blank"
											|| Board.square[y][x].getSquareColor() != color) {

										tempArray[0] = -1;
										tempArray[1] = -1;
										int[][] errorArray = { tempArray, tempArray };
										return errorArray;
									}
								}

								move[runNum - 1] = tempArray;
								break;
							}
						}
					}
				}
				UI.print("Invalid location. Try again.");
			}
		}
		return move;
	}

}
