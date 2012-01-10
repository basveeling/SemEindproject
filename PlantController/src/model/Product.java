/**
 * 
 */
package model;

/**
 * @author bas
 *
 */
public class Product {
	private String serialNumber;
	private ProductType type;
	private ProductRun buildIn;
	private Order soldWithOrder;
	

	

	/**
	 * @param serialNumber
	 * @param type
	 * @param buildIn
	 * @param soldWithOrder
	 */
	public Product(String serialNumber, ProductType type, ProductRun buildIn,
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

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
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
		this.soldWithOrder = soldWithOrder;
	}
	
	
	
	
}
