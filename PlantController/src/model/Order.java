/**
 * 
 */
package model;

import java.util.ArrayList;

import model.relations.*;

/**
 * @author Patrick
 *
 */
public class Order {
	private int state;
	private String OrderId;
	private ArrayList<ProductTypeOrder> productTypes = new ArrayList<ProductTypeOrder>();
	
	public static final int STATE_PLACED = 0;
	public static final int STATE_SHIPPED = 1;
	
	/**
	 * @param state
	 * @param orderId
	 */
	public Order(int state, String orderId) {
		super();
		this.state = state;
		OrderId = orderId;
	}

	/**
	 * Set this order as shipped
	 */
	public void setFinshed(){
		this.state = STATE_SHIPPED;
	}
	
	
	/**
	 * Returns the amount for the producttype type for this order
	 * @param type
	 * @return
	 */
	public int amountForProductType(ProductType type) {
		int amount = 0;
		for (ProductTypeOrder pto : getProductTypes()) {
			if(pto.getProductType().equals(type)) {
				amount += pto.getAmount();
			}
		}
		return amount;
	}
	public ArrayList<ProductTypeOrder> getProductTypes() {
		return productTypes;
	}

	public boolean add(ProductTypeOrder e) {
		return productTypes.add(e);
	}

	public boolean remove(ProductTypeOrder o) {
		return productTypes.remove(o);
	}
	
	public void addProductTypeOrder(ProductType productType, int amount) {
		add(new ProductTypeOrder(amount, productType, this));
	}
	
	public int getState() {
		return state;
	}
	
	public String getStateToString() {
		String result = null;
		switch (state) {
		case STATE_PLACED:
			result = "placed";
			break;

		case STATE_SHIPPED:
			result = "shipped";
			break;
		}
		return result;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getOrderId() {
		return OrderId;
	}

	public void setOrderId(String orderId) {
		OrderId = orderId;
	}

	@Override
	public String toString() {
		String result = "Order(" + getOrderId() + ") | state: " + getStateToString() + " | products:\n";
		for (ProductTypeOrder productTypeOrder : productTypes) {
			result += "\t" + productTypeOrder.getProductType().getName() + " | amount: " + productTypeOrder.getAmount() + 
					" | in stock: " + productTypeOrder.getProductType().getInStock() + "\n";
		}
		return result;
	}
	
	/**
	 * Checks if this order already has a ProductTypeOrder for this ProductType
	 * @param type
	 * @return true if productTypes
	 */
	public boolean hasPTOForProductType(ProductType type) {
		for (ProductTypeOrder pto : productTypes) {
			if(pto.getProductType().equals(type)) {
				return true;
			}
		}
		return false;
	}
}
