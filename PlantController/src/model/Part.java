/**
 * 
 */
package model;

import java.util.ArrayList;

import model.relations.*;

/**
 * @author Patrick
 *
 */
public class Part {
	private String name;
	private PartBin partBin;
	private ArrayList<AssemblyStep> productType;
	
	/**
	 * @param inStock
	 */
	public Part(String name) {
		this.name = name;
	}
	

	/**
	 * @return the inStock
	 */
	public int getInStock() {
//		return inStock; 
		return partBin.getContainsAmount(); //halen uit o
	}

	/**
	 * @return the partBin
	 */
	public PartBin getPartBin() {
		return partBin;
	}

	/**
	 * @param partBin the partBin to set
	 */
	public void setPartBin(PartBin partBin) {
		this.partBin = partBin;
		partBin.setPart(this);
	}

	/**
	 * @param arg0
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean add(AssemblyStep arg0) {
		return productType.add(arg0);
	}

	/**
	 * @param arg0
	 * @return
	 * @see java.util.ArrayList#remove(java.lang.Object)
	 */
	public boolean remove(Object arg0) {
		return productType.remove(arg0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		String result = "Part(" + getName() + ") ";
		return result;
	}
}
