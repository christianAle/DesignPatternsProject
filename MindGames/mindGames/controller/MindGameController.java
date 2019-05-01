package mindGames.controller;

import mindGames.communication.components.CommunicationManager;
import mindGames.enums.Games;
import src.mindGames.UI.UI;

public class MindGameController {


	public  MindGameController() {
	}

	public void startGame(Games game, String player1, String player2) {
		CommunicationManager conManager = new CommunicationManager();
		conManager.playGame(game, player1, player2);

	}

	public void print(String text) {
		UI.print(text);
	}

	

}
