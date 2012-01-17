/**
 * 
 */
package controller;

import java.util.ArrayList;

import model.*;
import model.relations.ProductTypeOrder;

/**
 * @author bas
 *
 */
public class ManufacturingPlantController {
	public static int calculateProductsToProduceFor(ManufacturingPlant plant, ProductType productType) {
		int result = -1 * productType.getInStock();
		ArrayList<Order> orders = plant.getOrders();
		ArrayList<ProductTypeOrder> productTypes;
		for (Order order : orders) {
			if(order.getState() == Order.STATE_PLACED) {
				result += order.amountForProductType(productType);
			}
		}
		result+= calculatePartsToBuyFor(plant, productType);
		return result;
	}
	
	public static int calculatePartsToBuyFor(ManufacturingPlant plant, Part part) {
		int result = -1 * part.getInStock();
		ArrayList<ProductType> productTypes = plant.getProductTypes();
		for (ProductType productType : productTypes) {
			if(!productType.equals(part) && productType.amountForPart(part) > 0) {
				result += calculateProductsToProduceFor(plant, productType) * productType.amountForPart(part);
			}
		}
		return result;
	}
	public static String productTypesToString(ManufacturingPlant plant) {
		return plant.getProductTypes().toString();
	}
	
	public static String overviewOfUnitsToProduce(ManufacturingPlant plant) {
		String result = overviewOfProductsProduce(plant);
		result += "==Parts:==\n";
		ArrayList<Part> parts = plant.getParts();
		for (Part part : parts) {
				result +=  "#" + (parts.indexOf(part) +1)  + " Part: " + part.getName() + 
					" | To buy: " + calculatePartsToBuyFor(plant, part) + "\n";
		}
		return result;
	}
	
	public static String overviewOfProductsProduce(ManufacturingPlant plant) {
		String result = "==Products:==\n";
		ArrayList<ProductType> productTypes = plant.getProductTypes();
		for (ProductType productType : productTypes) {
			result += "#" + (productTypes.indexOf(productType) + 1) + " Product: " + productType.getName() + 
					" | To produce: " + calculateProductsToProduceFor(plant, productType) + "\n";
		}
		return result;
	}
	
	public static String overviewOfAssemblyLines(ManufacturingPlant plant) {
		String result = "==AssemblyLines:==\n";
		ArrayList<AssemblyLine> assemblyLines = plant.getAssemblyLines();
		for (AssemblyLine assemblyLine : assemblyLines) {
			result += "#" + (assemblyLines.indexOf(assemblyLine) + 1) + " Assemblyline: " + assemblyLine.getIdNumber() + "\n";
		}
		return result;
	}
}
