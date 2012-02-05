/**
 * 
 */
package controller;

import java.util.ArrayList;

import model.*;
import model.relations.*;

/**
 * @author bas
 *
 */
public class OrderController {
	public static boolean allPartsAvailable(Order order) {
		ArrayList<ProductTypeOrder> productTypeOrders = order.getProductTypes();
		for (ProductTypeOrder pto : productTypeOrders) {
			ProductType productType = pto.getProductType();
			if(productType.getInStock() < pto.getAmount()) { //er is minder beschikbaar dan er besteld is bij deze order
				return false;
			}
		}
		return true;
	}
}
