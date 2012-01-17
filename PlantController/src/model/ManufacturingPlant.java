/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author bas
 *
 */
public class ManufacturingPlant {
	private String name;
	private ArrayList<Part> parts = new ArrayList<Part>();
	private ArrayList<ProductType> productTypes = new ArrayList<ProductType>();
	private ArrayList<ProductRun> productRuns = new ArrayList<ProductRun>();
	private ArrayList<PartBin> partBins = new ArrayList<PartBin>();
	private ArrayList<Product> products = new ArrayList<Product>();
	private ArrayList<Order> orders = new ArrayList<Order>();
	private ArrayList<AssemblyLine> assemblyLines = new ArrayList<AssemblyLine>();
	
	public ManufacturingPlant(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean addOrder(Order e) {
		return orders.add(e);
	}

	public Iterator<Order> ordersIterator() {
		return orders.iterator();
	}

	public String ordersToString() {
		return orders.toString();
	}

	public boolean addProductType(ProductType e) {
		return productTypes.add(e);
	}

	public ArrayList<AssemblyLine> getAssemblyLines() {
		return assemblyLines;
	}

	public boolean addAssemblyLine(AssemblyLine e) {
		return assemblyLines.add(e);
	}
	
	public boolean addPart(Part e) {
		return parts.add(e);
	}

	public Iterator<ProductType> productTypesIterator() {
		return productTypes.iterator();
	}

	public int productTypesSize() {
		return productTypes.size();
	}

	public ArrayList<Part> getParts() {
		return parts;
	}

	public ArrayList<ProductType> getProductTypes() {
		return productTypes;
	}

	public ArrayList<ProductRun> getProductRuns() {
		return productRuns;
	}

	public ArrayList<PartBin> getPartBins() {
		return partBins;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}
	
	
	
}
		