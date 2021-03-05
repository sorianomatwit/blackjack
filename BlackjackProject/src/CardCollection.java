import java.util.ArrayList;

abstract public class CardCollection {
	protected ArrayList<Card> cards;
	
	protected CardCollection(int cards) {
			this.cards = new ArrayList<Card>(cards);
	}
	/**
	 * @param c Card to add
	 */
	public void addCard(Card c) {
		cards.add(c);
	}
	/**
	 * @param c Card to remove
	 */
	public void removeCard(Card c) {
		cards.remove(c);
	}
}
