package players;

import java.util.Random;

import cards.Card;
import cards.CardSuits;
import cards.CardTable;

public class BotPlayer extends Player {
	
	private static Random randomGen = new Random();
	
	public BotPlayer(String name)
	{
		super(name);
	}

	public void selectNextCardToPlay(CardTable table) 
	{
		int randomInt = randomGen.nextInt(100) + 1;
		int mistakeChance = 30;
		
		if( randomInt <= mistakeChance )
		{
			this.selectRandomCard();
		}
		else
		{
			this.selectBestMove(table);
		}
	}
	
	private void selectRandomCard() 
	{
		this.selectCardIndex( randomGen.nextInt( this.numberOfCards() ) );
	}

	private void selectBestMove(CardTable table)
	{
		if( table.isEmpty() )
		{
			this.selectHighestValueCard();
		}
		else 
		{
			Card cardToBeat = table.getBestEntry().getValue();
			if( this.hasCardOfSuit(cardToBeat.getSuit()) )
			{
				Card myCard = this.highestValueCard( cardToBeat.getSuit() );
				if( myCard.getValue() > cardToBeat.getValue() )
				{
					this.selectCardIndex( this.hand.indexOf( myCard ) );
				}
				else
				{
					this.selectLowestValueCard();
				}
			}
			else
			{
				this.selectLowestValueCard();
			}
		}
	}

	private Card highestValueCard(CardSuits suit) 
	{
		Card highCard = null;
		for( Card card : this.hand )
		{
			if( highCard == null || card.getValue() > highCard.getValue() )
			{
				if( suit == null || suit == card.getSuit() )
				{
					highCard = card;
				}
			}
		}
		return highCard;
	}
	
	private void selectHighestValueCard(CardSuits suit)
	{
		this.selectCardIndex( this.hand.indexOf( this.highestValueCard(suit) ) );
	}
	
	private void selectHighestValueCard()
	{
		this.selectHighestValueCard(null);
	}

	private Card lowestValueCard( CardSuits suit )
	{
		Card lowCard = null;
		for( Card card : this.hand )
		{
			if( lowCard == null || card.getValue() < lowCard.getValue() )
			{
				if( suit == null || suit == card.getSuit() )
				{
					lowCard = card;
				}
			}
		}
		return lowCard;
	}
	
	private void selectLowestValueCard( CardSuits suit )
	{
		this.selectCardIndex( this.hand.indexOf( this.lowestValueCard(suit) ) );
	}

	private void selectLowestValueCard()
	{
		this.selectLowestValueCard(null);
	}

}
