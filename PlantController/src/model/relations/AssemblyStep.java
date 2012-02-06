package model.relations;

import model.*;

/**
 * @author Patrick
 * Models a assemblyStep for a productType that can be performed 
 */
public class AssemblyStep {
	
	private ProductType productType;
	private Part part;
	private int amount;
	private int assemblyTime;
	
	
	
	
	/**
	 * @param productType
	 * @param part
	 * @param amount
	 * @param assemblyTime
	 */
	public AssemblyStep(ProductType productType, Part part, int amount,
			int assemblyTime) {
		super();
		this.productType = productType;
		this.part = part;
		this.amount = amount;
		this.assemblyTime = assemblyTime;
	}
	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public Part getPart() {
		return part;
	}
	public void setPart(Part part) {
		this.part = part;
	}
	
	public int getAssemblyTime() {
		return assemblyTime;
	}
	public void setAssemblyTime(int assemblyTime) {
		this.assemblyTime = assemblyTime;
	}
	
	/**
	 * Perform this assemblyStep, Sleeps for assemblyTime' seconds
	 */
	public void performStep() {
		part.getPartBin().takePart(amount);
//		try {
//			Thread.sleep(assemblyTime * 1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} //Sleep for the required amount
	}
	
}
