import java.util.Scanner;

abstract public class GenericPlayer extends Hand{
	
	protected String name;
	protected boolean isBusted = false;
	
	protected GenericPlayer(String name){
		super();
		this.name = name;
	}
	/**
	 * @return name of Generic Player
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return whther player busterd or not
	 */
	public boolean isBusted() {
		return isBusted;
	}
	/**
	 *  reset the playaers hand and change busted to default state of false
	 */
	public void reset() {
		cards.clear();
		isBusted = false;
	}
	/**
	 * change busted to true
	 */
	public void busted() {
		System.out.printf("%s Busted!%n",this.name);
		isBusted = true;
	}
	/**
	 * @param s - input from Player(only use for Player not House)
	 * @return True if Generic player isHitting False if not
	 */
	abstract public boolean isHitting(Scanner s);
	
	@Override
	public String toString() {
		String s = String.format("%s: %s", name,super.toString());
		return s;
	}
}
