package game;

import java.util.LinkedList;
import java.util.Map.Entry;

import cards.Card;
import cards.CardTable;
import cards.Deck;
import players.BotPlayer;
import players.Player;
import players.UserPlayer;

public class Controller {
	
	public static void main(String[] args)
	{
		new Controller();
	}
	
	private LinkedList<Player> players;
	private UserInterface userInterface;
	private CardTable table;
	private Deck deck;
	private int turns, winningScore;
	private Player winningPlayer;
	
	public Controller()
	{
		this.initUserControls();
		this.initPlayers();
		this.initGameBoard();
		
		this.playGame();
	}
	
	private Controller initUserControls() {
		this.userInterface = new UserInterface();
	
		return this;
	}

	private Controller initPlayers()
	{
		this.players = new LinkedList<Player>();
		this.players.add(0, new UserPlayer(userInterface.ask("What is your name Player 1?")));
		for( int i = 1; i < 4; i++ )
		{
			String name = "Bot"+i;
			this.players.add(i, new BotPlayer(name));
		}
		
		this.winningPlayer = null;
		
		return this;
	}

	private Controller initGameBoard() {
		this.deck = new Deck();
		for(Player player : this.players)
		{
			player.drawFullHandFrom(this.deck);
		}
		
		this.table = new CardTable(this.players.size(), deck);
		
		this.turns = 0;
		
		this.winningScore = this.userInterface.askForIntInRange("How many points would you like to play to?", 1, 100);
		
		return this;
	}

	private Controller playGame() {
		
		while( this.winningPlayer == null )
		{
			this.userInterface.line();
			this.doTurn();
		}
		
		this.doEndGame();

		return this;
	}
	
	private void doTurn()
	{
		
		this.turns++;
		this.userInterface.display("Turn number " + this.turns + ".\n");
		
		for( Player player : this.players )
		{
			if( player instanceof UserPlayer )
			{
				this.doUserTurn((UserPlayer)player);
			}
			else if( player instanceof BotPlayer )
			{
				((BotPlayer)player).selectNextCardToPlay(this.table);
			}
			
			Card card = player.playCardOn(this.table);
			this.userInterface.display(player.getName() + " played the " + card + "\n");
		}
		
		Entry<Player, Card> winningEntry = this.table.getWinner();
		Player turnWinner = winningEntry.getKey();
		turnWinner.scoreAPoint();
		
		this.userInterface.line();
		this.showTurnResults(winningEntry);		
		this.showScores();
		
		if( turnWinner.getPoints() >= this.winningScore )
		{
			this.winningPlayer = turnWinner;
			// We have a winner, so stop playing
			return;
		}
		
		this.table.shuffleIntoDeck();
		this.putPlayerFirst(turnWinner);
		
		for( Player player : this.players )
		{
			player.drawACardFrom(this.deck);
		}		
		
	}
	
	private Controller doEndGame()
	{
		this.userInterface.line(2);
		this.userInterface.display("**** " + this.winningPlayer.getName() + " has won the game! *****\n");
		this.userInterface.line(2);
		return this;
	}
	
	private Player showTurnResults(Entry<Player, Card> winner)
	{
		Player turnWinner = winner.getKey();
		Card card = winner.getValue();

		this.userInterface.display("** " + turnWinner.getName() + " won with the " + card + " **\n");
		
		return turnWinner;
	}
	
	private void showScores()
	{
		String scoreBoard = "";
		for( Player player : this.sortedPlayersByScore() )
		{
			String s = player.getPoints() == 1 ? "" : "s";
			scoreBoard += player.getName() + " has " + player.getPoints() + " point" + s + "\n";
		}
		this.userInterface.display(scoreBoard);
	}
	
	private void putPlayerFirst(Player player)
	{
		if( this.players.contains(player) )
		{
			while( this.players.getFirst() != player )
			{
				Player p = this.players.removeFirst();
				this.players.add(p);
			}
		}
	}

	private void doUserTurn(UserPlayer player)
	{
		String message = "" +
				"It is your turn " + player.getName() + "\n" +
				"Your current hand is: \n";
		for( int i = 0; i < player.numberOfCards(); i++ )
		{
			message += "  " + (i+1) + ". " + player.getCardAt(i) + "\n";
		}
		
		this.userInterface.display(message);
		int selectedIndex = this.userInterface.askForIntInRange("Which card would you like to play?", 1, player.numberOfCards()) - 1;
		player.selectCardIndex(selectedIndex);
	}
	
	private LinkedList<Player> sortedPlayersByScore()
	{
		LinkedList<Player> newList = new LinkedList<Player>(this.players);
		
		for( int i = 0; i < newList.size() - 1; i++ )
		{
			for( int j = i + 1; j < newList.size(); j++ )
			{
				if( newList.get(j).getPoints() > newList.get(i).getPoints() )
				{
					Player tempPlayer = newList.get(j);
					newList.set(j, newList.get(i));
					newList.set(i, tempPlayer);
				}
			}
		}
		
		return newList;
	}

}
