package mindGames.communication.components;


import mindGames.enums.Games;
import mindGames.factory.GameFactory;

public class CommunicationManager {
	public CommunicationManager() {

	}

	public void playGame(Games game ,String player1, String player2) {
		CommunicationComponent communicationComponent = GameFactory.getGameType(game);
		communicationComponent.playGame(player1,player2);
	}

}
 