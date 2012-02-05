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
	 * @require this.containsAmount >= 1
	 */
	public void takeOnePart() {
		takePart(1);
	}

	public boolean addPart(int amount) {
		if (amount >= 0) {
			this.containsAmount = this.containsAmount + amount;
			return true;
		}
		return false;
	}

	public void addOnePart() {
		addPart(1);
	}

	/**
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
