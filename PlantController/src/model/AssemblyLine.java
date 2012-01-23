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
//		robots.get(0).performAssemblyStepFor(null); //Start assembly
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// TODO: hier code die tijd checkt en productRun's start!
		for (Robot robot : robots) {
			robot.start();
		}
		while(productRuns.size() == 0) {
			try {
				Thread.sleep(1000 * 1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		runProductRun(productRuns.get(0));
	}
}	
