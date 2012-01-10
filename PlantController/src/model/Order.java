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
	private ArrayList<ProductTypeOrder> usedIn;
	
	/**
	 * @param state
	 * @param orderId
	 */
	public Order(int state, String orderId) {
		super();
		this.state = state;
		OrderId = orderId;
	}

	public void setFinshed(){
		
	}

	public boolean add(ProductTypeOrder e) {
		return usedIn.add(e);
	}

	public boolean remove(Object o) {
		return usedIn.remove(o);
	}
	
	
}
