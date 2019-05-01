package mindGames.logic;

import java.util.Scanner;

import mindGames.communication.components.CommunicationComponent;
import mindGames.controller.MoveResult;

public class ChineseCheckers extends CommunicationComponent {
  
    Scanner scanner = new Scanner(System.in);
    Player player1 = new Player();
	private  static ChineseCheckers instance;
	private  ChineseCheckers () 
	{
		
	}
	
	
	public static synchronized ChineseCheckers getInstance() {
		if(instance ==null) {
			instance = new ChineseCheckers();
		}
		return instance;
	}

	@Override
	protected void starGame(String player1, String player2) {

		String jugador = player1;
 
		Board gameBoard = null;
		String opcion = "";
		while (opcion.compareToIgnoreCase("SALIR") != 0) {
			
			if (gameBoard == null) {
				gameBoard = new Board();
				gameBoard.init();
				gameBoard.print();
			
			}
			
			System.out.println(jugador + " -> " + "Indique la posición de la ficha que quiere mover, por ej: Primero fila, despues columna(1, 3). ");
			opcion = scanner.nextLine();
			opcion = opcion.replace("(", "").replace(")", "");

			if (opcion.compareToIgnoreCase("salir") != 0) {
				System.out.println(jugador + " -> " + "Indique la casilla a la que quiere moverse, por ej: Primero fila, despues columna(1, 3). ");
				String sigtePosicion = scanner.nextLine();
				sigtePosicion = sigtePosicion.replace("(", "").replace(")", "");

				int filInicio = Integer.parseInt(opcion.split(",")[0].trim());
				int colInicio = Integer.parseInt(opcion.split(",")[1].trim());

				int filFinal = Integer.parseInt(sigtePosicion.split(",")[0].trim());
				int colFinal = Integer.parseInt(sigtePosicion.split(",")[1].trim());

				System.out.println(String.format("Moviendo [%s, %s] → [%s, %s]", filInicio, colInicio, filFinal, colFinal));
				MoveResult resultado = gameBoard.movementIsValid(filInicio, colInicio, filFinal, colFinal);
				if (resultado.isValid) {
					// muevase
					gameBoard.moveFicha(filInicio, colInicio, filFinal, colFinal);
					gameBoard.print();
				} else {
					System.out.println("Movimiento no permitido: " + resultado.reason);
				}
			}
			if(jugador.contentEquals(player1)) {
				jugador = player2;
			}else {
				jugador = player1;
			}
		}

	}

}
