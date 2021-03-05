
public class Hand extends CardCollection implements Comparable<Hand> {

	protected Hand() {
		super(5);
	}

	/**
	 * @return True if card is an ace
	 */
	protected boolean checkAce() {
		for (Card c : cards) {
			if (!c.isFlipped()) {
				if (c.getValue() == 1) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @return value of the cards in hand
	 */
	public int getValue() {
		int value = 0;
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).isFlipped()) {
				continue;
			} else {
				value = value + cards.get(i).getValue();
				if (checkAce() && (value + 10 < 21)) {
					value += 10;
				}
				
			}
		}
		return value;
	}

	/**
	 * @return true if two card int hand equal to 21
	 */
	public boolean isBlackJack() {
		if (cards.size() == 2 && getValue() == 21) {
			return true;
		}
		return false;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < cards.size(); i++) {
			s += cards.get(i) + " ";
		}
		s += String.format("Showing: (%d)", getValue());
		return s;
	}

	@Override
	public int compareTo(Hand o) {
		if(this.getValue() > o.getValue()) {
			return 1;
		} else if(this.getValue() < o.getValue()) {
			return -1;
		}
		return 0;
	}
}
