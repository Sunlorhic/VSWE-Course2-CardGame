package cards;

public class Card {
	
	// 0 = 2, 12 = Ace
	private int value;
	private CardSuits suit;
	
	public Card(int value, CardSuits suit)
	{
		this.setValue(value);
		this.suit = suit;
	}
	
	private Card setValue(int value)
	{
		if(value < 0) { value = 0; }
		else if(value > 12) { value = 12; }
		
		this.value = value;
		return this;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public CardSuits getSuit()
	{
		return this.suit;
	}
	
	public String toString()
	{
		String valueStr;
		switch(value)
		{
		case 9 :
			valueStr = "Jack";
			break;
		case 10 :
			valueStr = "Queen";
			break;
		case 11 :
			valueStr = "King";
			break;
		case 12 :
			valueStr = "Ace";
			break;
		default :
			valueStr = ""+(value + 2);
		}
		
		String suitStr = this.suit.toString().toLowerCase();
		
		// Capitalizes the first letter
		suitStr = "" + (char)(suitStr.charAt(0) - 32) + suitStr.substring(1, suitStr.length());
		
		return valueStr + " of " + suitStr + "s";
	}

}
