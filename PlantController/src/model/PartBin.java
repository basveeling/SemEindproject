/**
 * 
 */
package model;

/**
 * @author Patrick
 * 
 */
public class PartBin {

	private int number;
	private Part part;
	private int containsAmount;

	/**
	 * @param number
	 * @require containsAmount >= 0
	 */
	public PartBin(int number, int containsAmount) {
		super();
		this.number = number;
		this.containsAmount = containsAmount;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	public int getContainsAmount() {
		return containsAmount;
	}

	/**
	 * @require containsAmount >= 0
	 */
	public void setContainsAmount(int containsAmount) {
		this.containsAmount = containsAmount;
	}
	
	/**
	 * Lazy man's function for removing one part
	 * @require this.containsAmount >= 1
	 */
	public void takeOnePart() {
		takePart(1);
	}
	/**
	 * Add an amount from the stock
	 * @param amount
	 * @return true if amount >= 0
	 */
	public boolean addPart(int amount) {
		if (amount >= 0) {
			this.containsAmount = this.containsAmount + amount;
			return true;
		}
		return false;
	}
	/**
	 * Lazy man's function for adding one part
	 */
	public void addOnePart() {
		addPart(1);
	}

	/**
	 * Remove an amount of part from the stock
	 * @require amount <= this.containsAmount
	 */
	public boolean takePart(int amount) {
		if (amount >= 0) {
			this.containsAmount = this.containsAmount - amount;
			return true;
		}
		return false;
	}

	/**
	 * @return the part
	 */
	public Part getPart() {
		return part;
	}

	/**
	 * @return if PartBin is Empty or not
	 */
	public boolean isEmpty() {
		return containsAmount == 0;
	}

	/**
	 * @param part
	 *            the part contained in this PartBin
	 */
	public void setPart(Part part) {
		this.part = part;
	}
	
	@Override
	public String toString() {
		return "PartBin #" + number;
		
	}
}
