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
	private int assemblyTime;
	
	private ArrayList<ProductTypeOrder> orders = new ArrayList<ProductTypeOrder>();
	private ArrayList<ProductPart> madeFrom = new ArrayList<ProductPart>();
	/**
	 * @param inStock
	 */
	public ProductType(String name, int inStock, int assemblyTime) {
		super(name, inStock);
		this.assemblyTime = assemblyTime;
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean add(ProductPart e) {
		return madeFrom.add(e);
	}
	
	public void addProductPart(Part part, int amount) {
		ProductPart pp = new ProductPart(this, part, amount);
		this.add(pp);
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.ArrayList#remove(java.lang.Object)
	 */
	public boolean remove(Object o) {
		return madeFrom.remove(o);
	}

	public int getAssemblyTime() {
		return assemblyTime;
	}

	public void setAssemblyTime(int assemblyTime) {
		this.assemblyTime = assemblyTime;
	}
	
	public boolean add(ProductTypeOrder e) {
		return orders.add(e);
	}

	public boolean remove(ProductTypeOrder arg0) {
		return orders.remove(arg0);
	}
	
	public int amountForPart(Part part) {
		int amount = 0;
		for (ProductPart productPart : madeFrom) {
			if(productPart.getPart().equals(part)) {
				amount += productPart.getAmount();
			}
		}
		return amount;
	}
	
	public int estimatedAssemblyTimeForAmount(int amount) {
		return assemblyTime * amount;
	}
	
	@Override
	public String toString() {
		String result = "ProductType(" + getName() + ") parts:[\n";
		for (ProductPart productPart : madeFrom) {
			result += "\t" + productPart.getPart() + "\n";
		}
		result += "]\n";
		return result;
	}
	
}
