/**
 * 
 */
package model;

import java.util.ArrayList;

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
	
	/**
	 * Returns the required amount of parts of type part to build one instance of this ProductType
	 * @param part
	 * @return
	 */
	public int amountForPart(Part part) {
		int amount = 0;
		for (AssemblyStep assemblyStep : assemblySteps) {
			if(assemblyStep.getPart().equals(part)) {
				amount += assemblyStep.getAmount();
			}
		}
		return amount;
	}
	
	/**
	 * Gives an estimated assemblyTime for assembling an amount of products of this ProductType on a pipeline
	 * @param amount
	 * @return estimated time in seconds
	 */
	public int estimatedAssemblyTimeForAmount(int amount) {
		int result = 0;
		int timeForOneUnit = 0;
		int slowestAssemblyTime = 0;
		for (AssemblyStep assemblyStep : assemblySteps) {
			timeForOneUnit+= assemblyStep.getAssemblyTime();
			if(slowestAssemblyTime <= assemblyStep.getAssemblyTime()) 
				slowestAssemblyTime = assemblyStep.getAssemblyTime();
		}
		if(amount > 0) {
			result+= timeForOneUnit;
		}
		if(amount > 1) {
			amount-=1;
			result+= (amount) * (slowestAssemblyTime);
		}
		return result;
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
