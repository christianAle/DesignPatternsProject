package mindGames.communication.components;

public abstract class CommunicationComponent {
	public final void playGame(String player1, String player2)
	  {
		 starGame(player1,player2);
		  
	  }
 	

	protected abstract void starGame(String player1, String player2);

	
	
}
