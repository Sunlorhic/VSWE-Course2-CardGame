package cards;

public interface ICardHolder {
	
	int numberOfCards();
	
	/**
	 * Returns the card at a given index.
	 * @param index
	 * @return Card
	 */
	Card getCardAt(int index);
	
	boolean giveCard(Card card);
	
	int maxCards();

}
