/**
 * 
 */
package model;

import model.relations.AssemblyStep;

/**
 * @author bas
 *
 */
public class Robot extends Thread{
	protected Robot nextRobot;
	protected AssemblyStep assemblyStep;
	protected Product syncProduct = null;
	public void setAssemblyStep(AssemblyStep assemblyStep) {
		this.assemblyStep = assemblyStep;
	}
	protected void addNewProduct() {
		ProductType productType = assemblyStep.getProductType();
		synchronized (this) {
			while(syncProduct != null) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			syncProduct = new Product(productType.getNewSerialNumber(), productType, null, null);
			this.notifyAll();
		}
	}
	protected void setNextRobot(Robot robot) {
		nextRobot = robot;
	}
	protected void performAssemblyStepFor(Product product) {
		assemblyStep.performStep();
		if(nextRobot != null) {
			synchronized (nextRobot) {
				while(nextRobot.syncProduct != null) {
					try {
						nextRobot.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					nextRobot.syncProduct = product;
				}
				nextRobot.notifyAll();
			}
		} else {
			product.getType().getPartBin().addOnePart();
		}
	}
	
	public void setProduct(Product product) {
		this.syncProduct = product;
	}

	@Override
	public void run() {
		super.run();
		Product newProduct;
		synchronized (this) {
			while(syncProduct == null) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(syncProduct != null) {
					newProduct = syncProduct;
					syncProduct = null;
					performAssemblyStepFor(newProduct);
				}
				this.notifyAll();
			}
		}
		
	}
	
}
