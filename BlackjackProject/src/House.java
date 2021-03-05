import java.util.Scanner;

public class House extends GenericPlayer{

	public House(String name) {
		super(name);
	}

	
	/**
	 * house is hitting cards until its value it more than 16
	 */
	@Override
	public boolean isHitting(Scanner s) {
		flipAll();
		if(getValue() < 16 && !isBusted) {
			return true;
		}
		return false;
	}
	/**
	 * flips the dealers first card
	 */
	public void flipFirstCard() {
		cards.get(0).flip();
	}
	/**
	 * flips all the card once bets are done
	 */
	private void flipAll() {
		for(Card c : cards) {
			if(c.isFlipped())c.flip();
		}
	}

}
