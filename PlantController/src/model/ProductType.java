/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Iterator;

import model.relations.*;
/**
 * @author bas
 *
 */
public class ProductType extends Part {
	
	private ArrayList<ProductTypeOrder> orders = new ArrayList<ProductTypeOrder>();
	private ArrayList<AssemblyStep> assemblySteps = new ArrayList<AssemblyStep>();
	private int lastSerialNumber = 0;
	/**
	 * @param inStock
	 */
	public ProductType(String name) {
		super(name);
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean add(AssemblyStep e) {
		return assemblySteps.add(e);
	}
	
	public void addAssemblyStep(Part part, int amount, int assemblyTime) {
		AssemblyStep pp = new AssemblyStep(this, part, amount, assemblyTime);
		this.add(pp);
	}
	public int getNewSerialNumber() {
		return lastSerialNumber++;
	}
	/**
	 * @param o
	 * @return
	 * @see java.util.ArrayList#remove(java.lang.Object)
	 */
	public boolean remove(Object o) {
		return assemblySteps.remove(o);
	}
	
	public boolean add(ProductTypeOrder e) {
		return orders.add(e);
	}

	public boolean remove(ProductTypeOrder arg0) {
		return orders.remove(arg0);
	}
	
	public int amountForPart(Part part) {
		int amount = 0;
		for (AssemblyStep productPart : assemblySteps) {
			if(productPart.getPart().equals(part)) {
				amount += productPart.getAmount();
			}
		}
		return amount;
	}
	
	public int estimatedAssemblyTimeForAmount(int amount) {
//		return assemblyTime * amount;
		return 1337;
	}
	
	@Override
	public String toString() {
		String result = "ProductType(" + getName() + ") parts:[\n";
		for (AssemblyStep productPart : assemblySteps) {
			result += "\t" + productPart.getPart() + "\n";
		}
		result += "]\n";
		return result;
	}

	public ArrayList<AssemblyStep> getAssemblySteps() {
		return assemblySteps;
	}
	
	
}
