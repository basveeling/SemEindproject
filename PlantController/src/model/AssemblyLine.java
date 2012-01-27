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
	private int idNumber;

	private ArrayList<ProductRun> productRuns = new ArrayList<ProductRun>();
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
	public void addRobot() {
		robots.add(new Robot(robots.size()));
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
	/**
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
				robots.get(i - 1).setNextRobot(robot);//setNextRobot for the previous rebot to this one
			}
			i++;
		}
	}
	public void runProductRun(ProductRun productRun) {
		configForProductRun(productRun);
		Robot firstRobot = robots.get(0);
		for (int i = 0; i < productRun.getUnitsToProduce(); i++) {
			System.out.println("AssemblyLine #" + this.getIdNumber() + " presenting new product("+productRun.getBuildsProduct().getName()+") on pipeline.");
			firstRobot.addNewProduct();
			
		}
		System.out.println("AssemblyLine #" + this.getIdNumber() + " is done with productRun for "+productRun.getUnitsToProduce()+"product("+productRun.getBuildsProduct().getName()+").");
		
//		robots.get(0).performAssemblyStepFor(null); //Start assembly
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// TODO: hier code die tijd checkt en productRun's start!
		for (Robot robot : robots) {
			robot.start();
		}
		int oldSize = 0;
		//TODO: save shutdown assemblyline
		while(true) {
			synchronized (this) {
				while(productRuns.size() == oldSize) {
					try {
						this.notifyAll();
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			if(productRuns.size() > oldSize) {
				runProductRun(productRuns.get(0));
			}
			oldSize = productRuns.size();
			
		} 
	}
}	
