package cards;

import java.util.LinkedList;
import java.util.Random;

public class Deck implements ICardHolder
{
	
	private LinkedList<Card> cards;
	
	public Deck()
	{
		this.reset();
	}
	
	public Deck reset()
	{
		return this.rebuild().shuffle(100);
	}
	
	public Deck rebuild()
	{
		this.cards = new LinkedList<Card>();
		
		for( CardSuits suit : CardSuits.values() )
		{
			for( int i = 0; i <= 12; i++ )
			{
				this.cards.add(new Card(i, suit));
			}
		}
		
		return this;
	}
	
	/**
	 * Shuffles the deck by creating a new pile of cards
	 * randomly drawn from the old pile.
	 * @return this
	 */
	public Deck shuffle()
	{
		Random shuffler = new Random();
		LinkedList<Card> newCards = new LinkedList<Card>();
		
		while( !this.cards.isEmpty() )
		{
			newCards.add(this.cards.remove(shuffler.nextInt(this.cards.size())));
		}
		
		this.cards = newCards;
		
		return this;
	}
	
	/**
	 * Shuffles the deck the given number of times
	 * @param times
	 * @return this
	 */
	public Deck shuffle(int times)
	{
		for( int i = 0; i < times; i++ )
		{
			this.shuffle();
		}
		return this;
	}
	
	public Card drawACard()
	{
		return this.cards.removeFirst();
	}

	@Override
	public int numberOfCards() 
	{
		return this.cards.size();
	}

	@Override
	public Card getCardAt(int index)
	{
		return this.cards.get(index);
	}
	
	@Override
	public boolean giveCard(Card card)
	{
		if( this.numberOfCards() >= this.maxCards())
		{
			return false;
		}
		else
		{
			this.cards.add(card);
			return true;
		}
	}

	@Override
	public int maxCards()
	{
		return 52;
	}
}
