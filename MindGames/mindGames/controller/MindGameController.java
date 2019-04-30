package mindGames.controller;

import mindGames.communication.components.CommunicationManager;
import mindGames.enums.Games;
import src.mindGames.UI.UI;

public class MindGameController {

	private static MindGameController instance;

	private MindGameController() {
	}

	public void startGame(Games game, String player1, String player2) {
		CommunicationManager conManager = new CommunicationManager();
		conManager.playGame(game, player1, player2);

	}

	public void print(String text) {
		UI.print(text);
	}

	public static MindGameController getInstance() {
		if (instance == null) {
			instance = new MindGameController();
		}
		return instance;
	}

}
