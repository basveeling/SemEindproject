/**
 * 
 */
package model;

/**
 * @author Patrick
 *
 */
public class Part {
	
	private int inStock;
	
	/**
	 * @param inStock
	 */
	public Part(int inStock) {
		super();
		this.inStock = inStock;
	}

	/**
	 * @return the inStock
	 */
	public int getInStock() {
		return inStock;
	}

	/**
	 * @param inStock the inStock to set
	 */
	public void setInStock(int inStock) {
		this.inStock = inStock;
	}
	
	
}
