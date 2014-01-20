package players;

import java.util.ArrayList;

import cards.Card;
import cards.CardSuits;
import cards.CardTable;
import cards.Deck;
import cards.ICardHolder;

public abstract class Player implements ICardHolder {
	
	protected int maxHandSize;
	protected ArrayList<Card> hand;
	protected String name;
	protected int points;
	
	protected int selectedCardIndex;
	
	public Player(String name)
	{
		this.name = name;
		this.maxHandSize = 5;
		this.points = 0;
		this.selectedCardIndex = -1;
		this.hand = new ArrayList<Card>(this.maxHandSize);
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getPoints()
	{
		return this.points;
	}
	
	public abstract void selectNextCardToPlay(CardTable table);
	
	public Player scoreAPoint()
	{
		this.points++;
		return this;
	}
	
	public Player resetPoints()
	{
		this.points = 0;
		return this;
	}
	
	public boolean drawACardFrom(Deck deck)
	{
		if( this.numberOfCards() >= this.maxCards() )
		{
			return false;
		}
		else
		{
			this.hand.add( deck.drawACard() );
			return true;
		}
	}

	public Player drawFullHandFrom(Deck deck)
	{
		while( this.drawACardFrom(deck) );
		return this;
	}
	
	public Player selectCardIndex(int index)
	{
		this.selectedCardIndex = index;
		return this;
	}
	
	public Card playCardOn(CardTable table)
	{
		if( this.hand.isEmpty() || this.selectedCardIndex < 0 || this.selectedCardIndex >= this.maxCards() )
		{
			return null;
		}
		else 
		{
			Card card = this.getCardAt(this.selectedCardIndex);
			if( table.setCardFromPlayer(card, this) )
			{
				this.hand.remove(this.selectedCardIndex);
				this.selectedCardIndex = -1;
				return card;
			}
			else
			{
				return null;
			}
		}
	}
	
	public Card getSelectedCard() {
		if( this.selectedCardIndex >= 0 && this.selectedCardIndex < this.maxCards() )
		{
			return this.getCardAt(this.selectedCardIndex);
		}
		else
		{
			return null;
		}
	}

	@Override
	public int numberOfCards()
	{
		return this.hand.size();
	}
	
	@Override
	public Card getCardAt(int index)
	{
		return this.hand.get(index);
	}
	
	@Override
	public boolean giveCard(Card card)
	{
		return false;
	}
	
	@Override
	public int maxCards()
	{
		return this.maxHandSize;
	}

	protected boolean hasCardOfSuit(CardSuits suit) 
	{
		for( Card card : this.hand )
		{
			if( card.getSuit() == suit ) return true;
		}
		return false;
	}
}
