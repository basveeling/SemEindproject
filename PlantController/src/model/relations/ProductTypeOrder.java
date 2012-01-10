package model.relations;

import model.*;

public class ProductTypeOrder {
	private int amount;
	private ProductType productType;
	private Order order;
	/**
	 * @param amount
	 * @param productType
	 * @param order
	 */
	public ProductTypeOrder(int amount, ProductType productType, Order order) {
		super();
		this.amount = amount;
		this.productType = productType;
		this.order = order;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
