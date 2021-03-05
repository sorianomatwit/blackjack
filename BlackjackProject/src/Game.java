import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Game {
	/**
	 * @author Manyeuris Soriano
	 * @author Micah D. Shuster Date complete: Feburary 12 2021 this is the game
	 *         blackjack Players bet certain amount of chps on whther they can get
	 *         the highest value of cards under 21
	 */

	public static boolean yesOrNo(String question, Scanner s) {
		String answer = " ";
		boolean wow = true;
		do {
			System.out.printf(question);
			answer = s.next();
			answer = answer.toLowerCase();
			if (answer.contentEquals("y") || answer.contentEquals("n")) {
				wow = false;
			} else {
				System.out.printf("Please answer y/n%n");
				wow = true;
			}
		} while (wow);
		if (answer.contentEquals("y"))
			return true;
		return false;
	}
	
	public static boolean isOnlyNumbers(String s) {
		for(char c: s.toCharArray()) {
			if(Character.getNumericValue(c) > 9 || Character.getNumericValue(c) < 0) {
				if(Character.getNumericValue(s.charAt(0)) == -1) {
					continue;
				}
				return false;
			}
		}
		return true; 
	}
	public static int digitCharToInt(String s) {
		int d = 0;
		char[] c = s.toCharArray();
		for(int i = c.length - 1;i >= 0;i--) {
			if(Character.getNumericValue(c[i]) == -1) {
				d *= -1;
			} else {
				d += (int) (Character.getNumericValue(c[i]) * Math.pow(10, (c.length-1) - i));
			}
		}
		return d;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		Deck bicycle = new Deck();
		boolean restart;
		String placeholder = " ";
		do {
			System.out.printf("%nWelcome to BlackJack!%n%n");
			int numPlayers = 0;
			do {
				System.out.printf("Enter how many players (max 6): ");
				placeholder = input.next();
				if(isOnlyNumbers(placeholder)) {
					numPlayers = digitCharToInt(placeholder);
				} else {
					System.out.printf("Must be a number!%n");
				}
				if (numPlayers > 6)
					System.out.printf("Too many players!%n");
				if (numPlayers < 0)
					System.out.printf("Not enough players!%n");
			} while (numPlayers > 6 || numPlayers < 0  || !(isOnlyNumbers(placeholder)));
			int aiCount = 0;
			if (yesOrNo("Do you want yo play with AI (y/n):  ", input)) {
				do {
					System.out.printf("How many AI player do you want (max %d): ", (6 - numPlayers));
					placeholder = input.next();
					if(isOnlyNumbers(placeholder)) {
						aiCount = digitCharToInt(placeholder);
					} else {
						System.out.printf("Must be a number!%n");
					}
					if (aiCount > (6 - numPlayers) || aiCount < 0) {
						System.out.printf("Maximum is %d!%n", (6 - numPlayers));
					}
				} while (aiCount > (6 - numPlayers) || aiCount < 0 || !(isOnlyNumbers(placeholder)));
			}
			ArrayList<GenericPlayer> players = new ArrayList<GenericPlayer>(numPlayers + 1);
			// add all the players
			for (int i = 0; i < numPlayers; i++) {
				System.out.printf("Enter Player #%d name: ", i + 1);
				players.add(new Player(input.next()));
			}
			for (int i = 0; i < aiCount; i++) {
				players.add(new AiPlayer());
			}
			House casino = new House("Casino");
			players.add(casino);
			boolean repeat;
			do {
				for(GenericPlayer p: players) {
					// where the player place their bets
					if (p instanceof Player && !(p instanceof AiPlayer)) {
						Player b = (Player) p;
						do {
							if (b.isBetting(input) != -1) {
								System.out.printf("%n");
								break;
							}
						} while (true);
					}
				}
				for (GenericPlayer p : players) {
					// deals fist cards
					bicycle.draw(p);
					bicycle.draw(p);
					// house draws card
					if (p instanceof House) {
						House h = (House) p;
						h.flipFirstCard();
						System.out.printf("%s%n", p);
					}
				}
				for (GenericPlayer p : players) {
					if (p instanceof Player) {
						System.out.printf("%s%n", p);
					}
				}
				for (GenericPlayer p : players) {
					
					// will player hit
					if (!(p instanceof AiPlayer)) {
						while (!p.isBusted()) {
							System.out.printf("%s%n", p);

							if (p.isHitting(input)) {

								bicycle.draw(p);
							} else {
								break;
							}
							// if player bust
							if (p.getValue() > 21) {
								p.busted();
							}
						}
					}

					if (p instanceof AiPlayer) {
						AiPlayer a = (AiPlayer) p;
						while (!a.isBusted()) {
							if (a.isHitting(players)) {
								bicycle.draw(a);
							} else
								break;
							if (a.getValue() > 21) {
								a.busted();
							}
						}
					}
					if (p instanceof House) {
						p.isHitting(input);
					}

				}
				// final print statement
				System.out.printf("%n Final Hands:%n");
				for (GenericPlayer p : players) {
					System.out.printf("%s%n", p);
				}
				System.out.printf("%n");
				int houseValue = casino.getValue();
				if (players.size() > 1) {
					for (GenericPlayer p : players) {
						if (p instanceof House) {
							continue;
						}
						// ai win condition
						if (p instanceof AiPlayer) {
							AiPlayer a = (AiPlayer) p;
							if (a.isBlackJack() || !a.isBusted() && houseValue > 21
									|| !a.isBusted() && a.getValue() > houseValue) {
								if (a.isBlackJack()) {
									System.out.printf("%n BLACKJACK! %n");
									a.win(1.5);
								} else {
									a.win(1);
								}
							} else if (a.getValue() == houseValue && !a.isBusted()) {
								a.push();
							} else {
								a.loss(-1);
								a.updateBustCount();
							}
						}
						// win condition)
						if (p instanceof Player && !(p instanceof AiPlayer)) {
							Player b = (Player) p;
							if (b.isBlackJack() || !b.isBusted() && houseValue > 21
									|| !b.isBusted() && b.getValue() > houseValue) {
								if (b.isBlackJack()) {
									System.out.printf("%n BLACKJACK! %n");
									b.win(1.5);
								} else {
									b.win(1);
								}
							} else if (b.getValue() == houseValue && !b.isBusted()) {
								b.push();
							} else {
								b.loss(-1);
							}
						}

					}
					// if player runs out of chips get kicked out
					for (int i = 0; i < players.size(); i++) {
						GenericPlayer p = players.get(i);
						if (p instanceof Player) {
							Player b = (Player) p;
							if (b.getChips() < 10) {
								System.out.printf("Sorry %s you cannot play due to insufficient amount of chips%n",
										b.getName());
								players.remove(i);
							}
						}
					}
				}
				repeat = yesOrNo("Do you want to play again (y/n)?", input);
				// Checked if the player used a valid answer on whether they would like to play
				// or not
				if (repeat) {
					if (bicycle.getSize() < players.size() * 5) {
						bicycle.shuffle();
					}
					for (GenericPlayer p : players)
						p.reset();
				}
			} while (repeat && players.size() > 1);
			if (players.size() == 1) {
				System.out.printf("There are no players left!%n");
			}
			restart = yesOrNo("Do you want to play new game (y/n)?", input);
			if (restart) {
				for (GenericPlayer p : players)
					p.reset();
				bicycle.shuffle();
			}
		} while (restart);
	}
}
