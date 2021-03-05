
public class Card {
	private String name;
	private int value; 
	private boolean isFlipped;//true - card down
	
	/**
	 * Card Constructor
	 * @param name represents the name
	 * @param value Numerical value of the car
	 */
	public Card(String name,int value){
		this.name = name;
		this.value = value;
		
		isFlipped = false;
	}
	
	@Override
	public String toString() {
		String s = "";
		if(isFlipped) {//face down
			s = String.format("XX");
		} else {//face up
			s = String.format("%s",this.name);
		}
		return s;
	}
	
	/**
	 * @return value of Card
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * @return True if card is flipped down False if not
	 */
	public boolean isFlipped() {
		return isFlipped;
	}
	/**
	 * flips card
	 */
	public void flip() {
		isFlipped = !isFlipped;
	}
}
