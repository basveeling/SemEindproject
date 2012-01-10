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
	private ArrayList<ProductTypeOrder> madeFrom;
	
	/**
	 * @param inStock
	 */
	public ProductType(int inStock, int assemblyTime) {
		super(inStock);
		this.assemblyTime = assemblyTime;
	}

	public int getAssemblyTime() {
		return assemblyTime;
	}

	public void setAssemblyTime(int assemblyTime) {
		this.assemblyTime = assemblyTime;
	}
	
	public boolean add(ProductTypeOrder e) {
		return madeFrom.add(e);
	}

	public boolean remove(ProductTypeOrder arg0) {
		return madeFrom.remove(arg0);
	}
	
	
	
}
