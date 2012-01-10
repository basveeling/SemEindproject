package model.relations;

import model.*;

/**
 * @author Patrick
 *
 */
public class ProductPart {
	
	private ProductType productType;
	private Part part;
	private int amount;
	
	
	
	/**
	 * @param productType
	 * @param part
	 * @param amount
	 */
	public ProductPart(ProductType productType, Part part, int amount) {
		super();
		this.productType = productType;
		this.part = part;
		this.amount = amount;
	}
	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
