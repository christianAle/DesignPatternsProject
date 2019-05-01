package src.mindGames.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

import mindGames.controller.MindGameController;
import mindGames.enums.Games;

public class UI {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static PrintStream out = System.out;
	static MindGameController mainController = new MindGameController();
    static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException, Exception {
		int opc;
		boolean exit = true;

		do {
			showMenu();
			opc = readOption();
			exit = executeAction(opc);
		} while (exit);

	}

	static void showMenu() {
		out.println();
		out.println("Opciones");
		out.println();
		out.println("1.  Comenzar Partida.");
		out.println("2.  Salir del Sistema.");
		out.println();
	}

	static int readOption() throws IOException {
		int opc;

		out.print("Selecciones su opcion: ");
		opc = Integer.parseInt(in.readLine());
		out.println();

		return opc;
	}

	public static boolean executeAction(int popcion) throws IOException, Exception {
		boolean exit = true;
		boolean validacion = true;

		switch (popcion) {
		case 1:
			chooseGame();
			break;
		case 2:
			exit = false;
			break;
		default:
			out.println("Opcion Invalidad");
			out.println();
			break;
		}
		return exit;
	}

	public static void chooseGame() throws NumberFormatException, IOException {
		int opc = 0;
		
		out.println();
		out.println("Opciones");
		out.println();
		out.println("1.  Ajedres.");
		out.println("2.  Damas.");
		out.println("3.  Go.");
		out.println("4.  Atrás.");
		out.println();
		

		out.print("Selecciones su opcion: ");
		opc = Integer.parseInt(in.readLine());
		out.println();
		
	     String pl1= "";
		 String pl2= "";
		 
		switch (opc) {
		case 1:
			
			pl1 = getName(1,null );
			pl2= getName(2,pl1 );
			mainController.startGame(Games.CHESS,pl1,pl2);
			
			
			break;
		case 2:
			pl1 = getName(1,null );
			pl2= getName(2,pl1 );
			mainController.startGame(Games.CHINESECHECKERS,pl1,pl2);
			break;
		case 3:
			pl1 = getName(1,null );
			pl2= getName(2,pl1 );
			mainController.startGame(Games.GO,pl1,pl2);
			break;
		
		case 4:
			showMenu();
			break;
		default:
			out.println("Opcion Invalidad");
			out.println();
			break;
		}
		
		
	}
	
	private static String getName(int playerNum, String prevName) {
		String name;

		while (true) {
			System.out.print("Jugador " + playerNum + " por favor indique su nombre \n>> ");
			name = scanner.nextLine().trim();

			if (!name.isEmpty() && !(name.contains(" ") || name.contains("\t")) && !name.equals(prevName))

				break; // otherwise the user will be asked t o enter again
			else
				System.out.println("Invalid name. Try again.");
		}
		return name;	
	}

	public static void print(String string) {
		System.out.println(string);
	}	
	
    
}
