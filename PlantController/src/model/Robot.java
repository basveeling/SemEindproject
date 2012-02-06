/**
 * 
 */
package model;

import model.relations.AssemblyStep;

/**
 * @author bas
 * A class that models a robot that can perform an assemblyStep. Extends a Thread to run autonomous. Is coupled to a assemblyline
 */
public class Robot extends Thread{
	protected Robot nextRobot;
	protected AssemblyLine assemblyLine;
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
	
	
	public AssemblyLine getAssemblyLine() {
		return assemblyLine;
	}
	public void setAssemblyLine(AssemblyLine assemblyLine) {
		this.assemblyLine = assemblyLine;
	}

	public AssemblyStep getAssemblyStep() {
		return assemblyStep;
	}
	public void setAssemblyStep(AssemblyStep assemblyStep) {
		this.assemblyStep = assemblyStep;
	}
	
	/**
	 * Start a new product on the assemblyLine. Add's a unique serial number to the unfinished product.
	 * @require this.id = 0 (this is the first robot in line)
	 */
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
	/**
	 * Returns the next robot on the assemblyLine
	 * @return next robot on the assemblyLine, null if last robot
	 */
	public Robot getNextRobot() {
		return nextRobot;
	}
	protected void setNextRobot(Robot robot) {
		nextRobot = robot;
	}
	
	/**
	 * Perform assemblyStep on this robot, push product to next robot after. If this is the last robot in line, finish product and add to stock
	 * @param product
	 * @require product != null
	 */
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
		} else { //last robot in line, finish product
			System.out.println("AssemblyLine finished a product: " + product.getType().getName());
			product.getType().getPartBin().addOnePart();
			ManufacturingPlant.getInstance().addProduct(product);
			syncProduct = null;
			if(!assemblyLine.isOccupied()) {
				assemblyLine.setFinished();
			}
		}
	}
	
	public void setProduct(Product product) {
		this.syncProduct = product;
	}
	public boolean isBusy() {
		return syncProduct != null;
	}
	
	/**
	 * Waits for a new product from the robot before or the assemblyline if first in line. 
	 */
	@Override
	public void run() {
		super.run();
		Product newProduct;
		synchronized (this) {
			while(syncProduct == null) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(syncProduct != null) {
					newProduct = syncProduct;
					performAssemblyStepFor(newProduct);
					syncProduct = null;
				}
				this.notifyAll();
			}
		}
		
	}
	
}
