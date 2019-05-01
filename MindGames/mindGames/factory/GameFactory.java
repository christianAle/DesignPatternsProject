package mindGames.factory;

import mindGames.communication.components.CommunicationComponent;
import mindGames.enums.Games;
import mindGames.logic.Chess;
import mindGames.logic.ChineseCheckers;

public class GameFactory {

	public GameFactory() {

	}

	public static CommunicationComponent getGameType(Games game) {

		switch (game) {

		case CHESS:

			return Chess.getInstance();

		case GO:

			return  null;

		case CHINESECHECKERS:

			return  ChineseCheckers.getInstance();

		default:
			throw new RuntimeException("Not implemented");

		}

	}
}
