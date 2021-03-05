import java.util.Scanner;

public class Player extends GenericPlayer {
	protected int chips = 100;
	protected int wager = 0;
	protected int score = 0;

	public Player(String name) {
		
		super(name);
	}
	/**
	 * player get higher value than dealer without busting
	 * @param wager mathematical action to do to bet
	 */
	public void win(double wager) {
		score++;
		chipsUpdate(wager);
		System.out.printf("%s Wins!%n Score: %d%n Chips: %d%n%n", name, score, chips);

	}
	/**
	 * dealer get higher value than player without busting or player bust
	 * @param wager mathematical action to do to bet
	 */
	public void loss(double wager) {
		chipsUpdate(wager);
		System.out.printf("%s Lose!%n Score: %d%n Chips: %d%n%n", name, score, chips);

	}
	/**
	 * player and dealer tie
	 */
	public void push() {
		System.out.printf("%s Pushed!%n Score: %d%n Chips: %d%n%n", name, score, chips);
	}

	/**
	 * @return num of chips
	 */
	public int getChips() {
		return chips;
	}

	/**
	 *  updates chips based off bet
	 * @param mod mathmatical change to the number (will always but multiplied)
	 */
	private void chipsUpdate(double mod) {
		this.chips += wager * mod;
	}

	/**
	 * ]player decide whether or not to bet
	 * @param s - input from the console
	 * @return betting amount
	 */
	public int isBetting(Scanner s) {
		System.out.printf("How much would %s like to bet?%nMinimum: 10%n(Chips: %d): ", name, chips);
		String placeholder = s.next();
		if(Game.isOnlyNumbers(placeholder)) {
			this.wager = Game.digitCharToInt(placeholder);
		} else {
			System.out.printf("Must be a number!%n");
			return -1;
		}
		if (this.wager >= 10 && this.wager <= chips) {
			return this.wager;
		} else if (this.wager < 10) {
			System.out.printf("You cannot bet this low!%n");
			return -1;
		} else {
			System.out.printf("You cannot bet this much!%n");
			return -1;
		}
	}

	/**
	 * player decide whether or not to hit
	 */
	@Override
	public boolean isHitting(Scanner s) {
		do {
			System.out.printf("%s: Do you want to hit (y/n)? ", name);
			String response = s.next();
			if (response.length() == 0) {
				continue;
			}
			if (response.charAt(0) == 'y') {
				return true;
			} else if (response.charAt(0) == 'n') {
				return false;
			} else {
				System.out.printf("Please Respond with y or n.%n");
			}
		} while (true);
	}
}
