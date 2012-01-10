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

	/**
	 * @param number
	 */
	public PartBin(int number) {
		super();
		this.number = number;
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
