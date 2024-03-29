/**
 * 
 */
package controller;

import java.util.ArrayList;

import model.AssemblyLine;
import model.ManufacturingPlant;
import model.Order;
import model.Part;
import model.ProductRun;
import model.ProductType;

/**
 * @author bas
 *
 */
public class ManufacturingPlantController {
	public static int calculateProductsToProduceFor(ManufacturingPlant plant, ProductType productType) {
		int result = 0;
		ArrayList<Order> orders = plant.getOrders();
		for (Order order : orders) {
			if(order.getState() == Order.STATE_PLACED) {
				result += order.amountForProductType(productType);
			}
		}
		result+= calculatePartsRequired(plant, productType);
		return result - productType.getInStock();
	}
	public static int calculatePartsRequired(ManufacturingPlant plant, Part part) {
		int result = 0;
		ArrayList<ProductType> productTypes = plant.getProductTypes();
		for (ProductType productType : productTypes) {
			if(!productType.equals(part) && productType.amountForPart(part) > 0) {
				result += calculateProductsToProduceFor(plant, productType) * productType.amountForPart(part);
			}
		}
		return result;
	}
	public static int calculatePartsToBuyFor(ManufacturingPlant plant, Part part) {
		int toBuy = calculatePartsRequired(plant, part) - part.getInStock();
		if(toBuy<0){
			return 0;
		}
		else{
			return toBuy;
		}		
		//return calculatePartsRequired(plant, part) - part.getInStock();
	}
	
	public static String productTypesToString(ManufacturingPlant plant) {
		return plant.getProductTypes().toString();
	}
	
	public static String overviewOfUnitsToProduce(ManufacturingPlant plant) {
		String result = overviewOfProductsProduce(plant);
		result += overviewOfPartsToProduce(plant);
		return result;
	}
	
	public static String overviewOfPartsToProduce(ManufacturingPlant plant) {
		String result = "==Parts:==\n";
		ArrayList<Part> parts = plant.getParts();
		for (Part part : parts) {
				result +=  "#" + (parts.indexOf(part) +1)  + " Part: " + part.getName() + 
					" | To buy: " + calculatePartsToBuyFor(plant, part) + " | in stock: " + part.getInStock() + "\n";
		}
		return result;
	}
	
	public static String overviewOfProductsProduce(ManufacturingPlant plant) {
		String result = "==Products:==\n";
		ArrayList<ProductType> productTypes = plant.getProductTypes();
		for (ProductType productType : productTypes) {
			result += "#" + (productTypes.indexOf(productType) + 1) + " Product: " + productType.getName() + 
					" | To produce: " + calculateProductsToProduceFor(plant, productType) + " | in stock: " + productType.getInStock() + "\n";
		}
		return result;
	}

	
	public static String overviewOfAssemblyLines(ManufacturingPlant plant) {
		String result = "==Assemblylines:==\n";
		ArrayList<AssemblyLine> assemblyLines = plant.getAssemblyLines();
		for (AssemblyLine assemblyLine : assemblyLines) {
			String occupied;
			if(assemblyLine.isOccupied()){
				occupied = "[occupied]";
			}else{
				occupied = "[free]";
			}
			result += "#" + (assemblyLines.indexOf(assemblyLine) + 1) + 
					" Assemblyline: " + assemblyLine.getIdNumber() 
					+ " Robots: " + assemblyLine.getRobots().size()
					+ " " + occupied
					+ "\n";
		}
		return result;
	}

	public static String overviewOfOrders(ManufacturingPlant plant) {
		String result = "==Orders:==\n";
		ArrayList<Order> orders = plant.getOrders();
		for (Order order : orders) {
			result += "#" + (orders.indexOf(order) + 1) + " " + order;
		}
		return result;
	}

	public static String overviewOfProductRuns(ManufacturingPlant plant) {
		String result = "==ProductRuns:==\n";
		ArrayList<ProductRun> productRuns = plant.getProductRuns();
		for (ProductRun productRun : productRuns) {
			result += "#" + (productRuns.indexOf(productRun) + 1) + " " + productRun;
		}
		return result;
	}
	public static String overviewOfUnfinishedProductRuns(ManufacturingPlant plant) {
		String result = "==ProductRuns:==\n";
		ArrayList<ProductRun> productRuns = plant.getProductRuns();
		for (ProductRun productRun : productRuns) {
			if(productRun.getFinished() == 0)
				result += "#" + (productRuns.indexOf(productRun) + 1) + " " + productRun;
		}
		return result;
	}
}
