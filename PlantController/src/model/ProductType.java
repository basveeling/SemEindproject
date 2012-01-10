/**
 * 
 */
package model;

/**
 * @author bas
 *
 */
public class ProductType extends Part {
	private int assemblyTime;

	/**
	 * @param inStock
	 */
	public ProductType(int inStock, int assemblyTime) {
		super(inStock);
		this.assemblyTime = assemblyTime;
	}

	public int getAssemblyTime() {
		return assemblyTime;
	}

	public void setAssemblyTime(int assemblyTime) {
		this.assemblyTime = assemblyTime;
	}
	
	
	
}
