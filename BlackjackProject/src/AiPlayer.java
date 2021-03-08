import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AiPlayer extends Player {
	Random rand;
	int bustCount = 0;
	String[] names = {"Charles", "Xavier","Raul" , "Reymond","Stephanie"};
	int trueCount = 0;
	boolean[][] hardTotals = { 
			{ false, false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, true, true, true, true, true },
			{ false, false, false, false, false, true, true, true, true, true },
			{ false, false, false, false, false, true, true, true, true, true },
			{ false, false, false, false, false, true, true, true, true, true },
			{ true, true, false, false, false, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true } };

	boolean[][] softTotals = { 
			{ false, false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true, true, true, true } };

	public AiPlayer() {
		super(" ");
		rand = new Random();
		changeName();
	}

	public void updateBustCount() {
		this.bustCount++;
	}
	int count = 1;
	private void changeName() {
		//name = names[4];
		name = names[rand.nextInt(names.length)];
	}

	/**
	 * 
	 * @param ArrayList of player
	 * @return whether the specific player should hit
	 */
	public boolean isHitting(ArrayList<GenericPlayer> p) {
		if (name.contentEquals(names[0])) {//Charles
			// basic player
			if (chips > 33) {
				wager = (int) (chips * (.30));
			} else
				wager = chips;
			if (getValue() <= 16) {
				return true;
			} else
				return false;
		} else if (name.contentEquals(names[1])) {//Xavier
			// copycat player
			if (p.size() > 2) {
				for (int i = 0; i < p.size(); i++) {
					GenericPlayer m = p.get(0);

					if (m instanceof Player) {
						Player b = (Player) m;
						if (b.wager <= chips) {
							wager = b.wager;
						} else
							wager = chips;
						if (getValue() <= 17) {
							if (b.cards.size() > this.cards.size()) {
								return true;
							} else
								return false;
						}
						return false;
					}
				}
			} else {
				changeName();
			}
		} else if (name.contentEquals(names[2])) {//Raul
			// im feeling lucky
			if (bustCount >= 2 && chips > 10) {
				wager = (int) (chips * .80);
			} else if (chips > 34) {
				wager = (int) (chips * .30);
			} else {
				wager = chips;
			}
			if (getValue() <= 16 && getValue() != 13) {
				return true;
			} else
				return false;

		} else if (name.contentEquals(names[3])) {//Reymond
			// card count strat
			for (GenericPlayer m : p) {
				if (m.cards.get(0).isFlipped()) {
					for (int i = 0; i < m.cards.size(); i++) {
						if (m.cards.get(i).getValue() == 10 || m.cards.get(i).getValue() == 1) {
							trueCount--;
						} else if (m.cards.get(i).getValue() >= 2 || m.cards.get(i).getValue() <= 6) {
							trueCount++;
						}
					}
				}
			}
			if (trueCount >= 1) {
				wager = (int) (chips * .80);
			} else if (chips > 66) {
				wager = (int) (chips * .15);
			} else
				wager = chips;

			for (GenericPlayer m : p) {
				if (m instanceof House) {
					int d = m.getValue();
					if (m.checkAce()) {
						d = 11;
					}
					if (getValue() >= 3 && getValue() <= 10 && checkAce()) {
						return softTotals[Math.abs(getValue() - 9)][d - 2];
					}
					if (getValue() <= 17 && getValue() >= 8) {
						return hardTotals[Math.abs(getValue() - 17)][d - 2];
					} else if (getValue() < 8) {
						return true;
					}
				}
			}
			return false;
		} else if (name.contentEquals(names[4])) { //Stephanie
			int count = 0;
			for (GenericPlayer m : p) {
				count+= this.compareTo(m);
			}
			if(count > 0 && chips >= 15) {
				this.wager = (int) (this.chips * .70);
			} else if (count < 0 && chips >= 33) {
				this.wager = (int) (chips * .30);
			} else if(count == 0 && chips >= 20){
				this.wager = (int) (chips * .50);
			} else {
				wager = chips;
			}
			if(count <= 0 && getValue() < 21) {
				return true;
			}
			return false;
		}
		return false;
	}
}
