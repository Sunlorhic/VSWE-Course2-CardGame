package players;

import game.UserInterface;
import cards.CardTable;

public class UserPlayer extends Player {
	
	private UserInterface userInterface;

	public UserPlayer(String name, UserInterface userInterface)
	{
		super(name);
		this.userInterface = userInterface;
	}
	
	public void selectNextCardToPlay(CardTable table) 
	{
		String message = "" +
				"It is your turn " + this.getName() + "\n" +
				"Your current hand is: \n";
		int i;
		for( i = 0; i < this.numberOfCards(); i++ )
		{
			message += "  " + (i+1) + ". " + this.getCardAt(i) + "\n";
		}
		
		this.userInterface.display(message);
		int selectedIndex = this.userInterface.askForIntInRange("Which card would you like to play?", 1, i) - 1;
		this.selectCardIndex(selectedIndex);
	}
}
