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
	protected int id;
	
	
	/**
	 * 
	 */
	public Robot(int id) {
		super();
		this.id = id;
		super.setName("Robot #"+id);
	}
	public int getRobotId(){
		return id;
	}
	
	/**
	 * @return the assemblyStep
	 */
	public AssemblyStep getAssemblyStep() {
		return assemblyStep;
	}
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
			if(syncProduct == null) {
				syncProduct = new Product(productType.getNewSerialNumber(), productType, null, null);
				System.out.println("Robot #" + id + " got new request for("+ assemblyStep.getPart().getName() +") on pipeline.");
			}
			this.notifyAll();
		}
		
	}

	public Robot getNextRobot() {
		return nextRobot;
	}
	protected void setNextRobot(Robot robot) {
		nextRobot = robot;
	}
	protected void performAssemblyStepFor(Product product) {

		System.out.println("\tRobot #" + id +" performing assemblyStep [adding part " + assemblyStep.getPart().getName() + "]");
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
				}
				nextRobot.syncProduct = product;
				nextRobot.notifyAll();
			}
		} else {
			System.out.println("AssemblyLine finished a product: " + product.getType().getName());
			
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
