/**
 * 
 */
package model;

/**
 * @author bas
 *	models an instance of a Product with a serialNumber
 */
public class Product {
	private int serialNumber;
	private ProductType type;
	private ProductRun buildIn;
	private Order soldWithOrder;
	

	

	/**
	 * @param serialNumber
	 * @param type
	 * @param buildIn
	 * @param soldWithOrder
	 */
	public Product(int serialNumber, ProductType type, ProductRun buildIn,
			Order soldWithOrder) {
		super();
		this.serialNumber = serialNumber;
		this.type = type;
		this.buildIn = buildIn;
		this.soldWithOrder = soldWithOrder;
	}

	public ProductRun getBuildIn() {
		return buildIn;
	}

	public void setBuildIn(ProductRun buildIn) {
		this.buildIn = buildIn;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public Order getSoldWithOrder() {
		return soldWithOrder;
	}

	public void setSoldWithOrder(Order soldWithOrder) {
		//TODO: recursive with parts
		this.soldWithOrder = soldWithOrder;
	}
	
	
	
	
}
