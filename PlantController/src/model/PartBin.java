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
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	
	public int getContainsAmount() {
		return containsAmount;
	}

	public void setContainsAmount(int containsAmount) {
		this.containsAmount = containsAmount;
	}
	
	public void takeOnePart() {
		takePart(1);
	}
	
	public void addPart(int amount) {
		this.containsAmount = this.containsAmount + amount;
	}
	
	public void addOnePart() {
		addPart(1);
	}
	
	public void takePart(int amount) {
		this.containsAmount = this.containsAmount - amount;
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
	public boolean isEmty(){
		return false;
	}
	
	/**
	 * @param part the part contained in this PartBin
	 */
	public void setPart(Part part){
		
	}

}
