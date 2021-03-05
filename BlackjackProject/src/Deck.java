import java.util.Random;

public class Deck extends CardCollection{

	Random rand;
	protected Deck() {
		super(52);
		populateDeck();
		rand = new Random();
	}
	
	/**
	 * creates deck
	 */
	private void populateDeck(){
		cards.clear();
		String[] suits = {"D","C","S","H"};
		int[] values = {2,3,4,5,6,7,8,9,10,10,10,10,1};
		String[] card = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		
		for(int i = 0;i < suits.length;i++) {
			for(int j = 0;j < 13;j++) {
				String s = card[j]+suits[i];
				addCard(new Card(s,values[j]));
			}
		}
	}
	public int getSize(){
		return cards.size();
	}
	/**
	 *re-creates the deck
	 */
	public void shuffle() {
		populateDeck();
	}
	
	//adds a card from the deck to the hand by remove said card and adding that same card to hand
	/** 
	 * @param h the hand of genericplayer
	 */
	public void draw(Hand h) {
		int r = rand.nextInt(cards.size());
		
		h.addCard(cards.get(r));
		cards.remove(r);
	}
}
