package cards;

import java.util.Hashtable;
import java.util.Map.Entry;

import players.Player;

public class CardTable {
	
	private Hashtable<Player, Card> plays;
	private CardSuits valuedSuit;
	private Deck tableDeck;
	private int numberOfPlayers;
	
	public CardTable(int numberOfPlayers, Deck tableDeck)
	{
		this.numberOfPlayers = numberOfPlayers;
		this.tableDeck = tableDeck;
		this.clearTable();
	}
	
	public CardTable shuffleIntoDeck()
	{
		for( Card card : this.plays.values() )
		{
			this.tableDeck.giveCard(card);
		}
		this.tableDeck.shuffle(10);
		return this.clearTable();
	}

	public CardTable clearTable()
	{
		this.plays = new Hashtable<Player, Card>(this.numberOfPlayers);
		this.valuedSuit = null;
		return this;
	}
	
	public boolean setCardFromPlayer(Card card, Player player)
	{
		if( this.plays.size() >= this.numberOfPlayers )
		{
			return false;
		}
		if( this.plays.isEmpty() )
		{
			this.valuedSuit = card.getSuit();
		}
		this.plays.put(player, card);
		return true;
	}
	
	public Entry<Player, Card> getWinner()
	{
		return this.getBestEntry();
	}

	public CardSuits getValuedSuit() 
	{
		return this.valuedSuit;
	}
	
	public Entry<Player, Card> getBestEntry()
	{
		Entry<Player, Card> bestEntry = null;

		for( Entry<Player, Card> entry : this.plays.entrySet() )
		{
			Card card = entry.getValue();
			if( card.getSuit() == this.valuedSuit )
			{
				if( bestEntry == null || card.getValue() > bestEntry.getValue().getValue() )
				{
					bestEntry = entry;
				}
			}
		}
		
		return bestEntry;
	}

	public boolean isEmpty()
	{
		return this.plays.isEmpty();
	}
}
