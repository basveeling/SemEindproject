/**
 * 
 */
package model;

import java.util.ArrayList;

import model.relations.AssemblyStep;
import model.relations.ProductTypeOrder;

/**
 * @author bas
 * models a productRun with a product to build, an amount of products to build and an assemblyLine to build on
 */
public class ProductRun{
	private int startTime;
	private int length;
	private int unitsToProduce;
	private int finished;
	private AssemblyLine runsOnAssemblyLine;
	private ProductType buildsProduct;
	
	
	
	/**
	 * @param startTime
	 * @param length
	 * @param unitsToProduce
	 * @param finished
	 * @param runsOnAssemblyLine
	 * @param buildsProduct
	 */
	public ProductRun(int startTime, int length, int unitsToProduce, int finished,
			AssemblyLine runsOnAssemblyLine, ProductType buildsProduct) {
		super();
		this.startTime = startTime;
		this.length = length;
		this.unitsToProduce = unitsToProduce;
		this.finished = finished;
		this.runsOnAssemblyLine = runsOnAssemblyLine;
		this.buildsProduct = buildsProduct;
	}
	public ProductRun() {
		
	}


	public AssemblyLine getRunsOnAssemblyLine() {
		return runsOnAssemblyLine;
	}


	public void setRunsOnAssemblyLine(AssemblyLine runsOnAssemblyLine) {
		this.runsOnAssemblyLine = runsOnAssemblyLine;
	}

	
	public ProductType getBuildsProduct() {
		return buildsProduct;
	}


	public void setBuildsProduct(ProductType buildsProduct) {
		this.buildsProduct = buildsProduct;
	}


	public AssemblyLine getAssemblyLine() {
		return runsOnAssemblyLine;
	}


	public void setAssemblyLine(AssemblyLine assemblyLine) {
		this.runsOnAssemblyLine = assemblyLine;
	}


	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getUnitsToProduce() {
		return unitsToProduce;
	}
	public void setUnitsToProduce(int unitsToProduce) {
		this.unitsToProduce = unitsToProduce;
	}
	public int getFinished() {
		return finished;
	}
	public void setFinished(int finished) {
		this.finished = finished;
	}
	
}
