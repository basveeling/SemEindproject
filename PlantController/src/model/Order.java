/**
 * 
 */
package model;

/**
 * @author Patrick
 *
 */
public class Order {
	private int state;
	private String OrderId;
	
	
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

}
