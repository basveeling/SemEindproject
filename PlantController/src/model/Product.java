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
	
	/**
	 * @param serialNumber
	 * @param type
	 */
	public Product(String serialNumber, ProductType type) {
		super();
		this.serialNumber = serialNumber;
		this.type = type;
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
	
	
	
	
}
