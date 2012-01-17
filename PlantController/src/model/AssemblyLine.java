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
public class AssemblyLine {
	private int idNumber;

	private ArrayList<ProductRun> productRuns = new ArrayList<ProductRun>();
	public AssemblyLine(int idNumber) {
		super();
		this.idNumber = idNumber;
	}

	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}
	
	public ArrayList<ProductRun> getProductRuns() {
		return productRuns;
	}

	public boolean addProductRun(ProductRun e) {
		return productRuns.add(e);
	}

	public boolean removeProductRun(Object o) {
		return productRuns.remove(o);
	}
	
	
}	
