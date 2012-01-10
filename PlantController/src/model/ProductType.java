/**
 * 
 */
package model;

import java.util.ArrayList;

import model.relations.ProductPart;

/**
 * @author bas
 *
 */
public class ProductType extends Part {
	private int assemblyTime;
	private ArrayList<ProductPart> madeFrom;

	/**
	 * @param inStock
	 */
	public ProductType(int inStock, int assemblyTime) {
		super(inStock);
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
	
	
	
}
