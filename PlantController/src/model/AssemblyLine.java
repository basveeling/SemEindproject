/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Iterator;

import model.relations.AssemblyStep;

/**
 * @author bas
 *
 */
public class AssemblyLine extends Thread{
	/**
	 * Identifying number of assemblyline
	 */
	private int idNumber;
	/**
	 * Active productrun on this assemblyline (null means there is no active productrun)
	 */
	private ProductRun currentProductRun = null;
	private boolean addingProducts = false;
	/**
	 * List of robots available on this assemblyline
	 */
	private ArrayList<Robot> robots = new ArrayList<Robot>();
	public AssemblyLine(int idNumber) {
		super();
		setName("AssemblyLine #"+idNumber);
		this.idNumber = idNumber;
	}

	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}
	/**
	 * Add a robot and give it the number of the total amount of robots
	 */
	public void addRobot() {
		robots.add(new Robot(robots.size()));
	}
	/**
	 * Checks if there is is a running productRun on this assemblyline 
	 * (checks if all the robots are not working on a product)
	 * @return currentProductRun != null && robotsBusy()
	 */
	public boolean isOccupied() {
		return (currentProductRun != null && robotsBusy()) || addingProducts;
	}
	
	/**
	 * Checks if there are any robots busy with assembling a product
	 * @return true if any of the robots is busy, false if none are
	 */
	public boolean robotsBusy() {
		for (Robot robot : robots) {
			if(robot.isBusy()) 
				return true;
		}
		return false;
	}
	public ProductRun getCurrentProductRun() {
		return currentProductRun;
	}

	/**
	 * Tries to set a new productRun
	 * @param productRun the productRun to run
	 * @return true if successful, false if assemblyLine isOccupied
	 */
	public boolean setProductRun(ProductRun productRun) {
		boolean result = false;
		synchronized (this) {
			if(currentProductRun == null && !isOccupied()) {
				currentProductRun = productRun;
				result = true;
			}
			this.notifyAll();
		}
		return result;
	}

	/**
	 * Configurates the robots to the assemblySteps required for this productRun
	 * @require robots.size() >= productRun.getBuildsProduct().assemblySteps.size()
	 * @param productRun
	 */
	public void configForProductRun(ProductRun productRun) {
		ArrayList<AssemblyStep> assemblySteps = productRun.getBuildsProduct().getAssemblySteps();
		assert(robots.size() >= assemblySteps.size());
		int i = 0;
		for (AssemblyStep assemblyStep : assemblySteps) {
			Robot robot = robots.get(i);
			robot.setAssemblyStep(assemblyStep);
			robot.setNextRobot(null);//Reset nextRobot
			if(i > 0) {
				robots.get(i - 1).setNextRobot(robot);//setNextRobot for the previous robot to this one
			}
			i++;
		}
	}
	
	/**
	 * Configurates the line for a productRun and then starts pushing unfinished products into the pipeline
	 * @param productRun
	 * @require productRun != null
	 */
	public void runProductRun(ProductRun productRun) {
		addingProducts = true;
		assert(productRun != null);
		configForProductRun(productRun);
		Robot firstRobot = robots.get(0);
		for (int i = 0; i < productRun.getUnitsToProduce(); i++) {
			System.out.println("AssemblyLine #" + this.getIdNumber() + " presenting new product("+productRun.getBuildsProduct().getName()+") on pipeline.");
			firstRobot.addNewProduct();
		}
		addingProducts = false;
	}
	
	/**
	 * Set the assemblyLine as finished, to be called by the last robot in the pipeline
	 */
	public void setFinished() {
		System.out.println("AssemblyLine #" + this.getIdNumber() + " is done with productRun for "+currentProductRun.getUnitsToProduce()+" products of type "+currentProductRun.getBuildsProduct().getName()+".");
		synchronized (this) {
			currentProductRun = null;
			this.notifyAll();
		}
	}
	
	@Override
	public void run() {
		for (Robot robot : robots) {
			robot.setAssemblyLine(this);
			robot.start();
		}
		while(true) {
			synchronized (this) {
				while(currentProductRun == null || robotsBusy()) {
					try {
						this.notifyAll();
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if(currentProductRun != null && !isOccupied()) {
				runProductRun(currentProductRun);
			}
		} 
	}

	/**
	 * @return the robots
	 */
	public ArrayList<Robot> getRobots() {
		return robots;
	}
	/**
	 * Checks if this assemblyLine has enough robots to perform all the AssemblySteps for an productType
	 * @param buildsProduct
	 * @return
	 */
	public boolean hasEnoughRobotsFor(ProductType buildsProduct) {
		return buildsProduct.getAssemblySteps().size() <= robots.size();
	}
}	
