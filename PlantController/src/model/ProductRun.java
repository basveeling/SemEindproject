/**
 * 
 */
package model;

/**
 * @author bas
 *
 */
public class ProductRun {
	private int startTime;
	private int length;
	private int unitsToProduce;
	private int state;
	private AssemblyLine runsOnAssemblyLine;
	private ProductType buildsProduct;
	
	
	
	/**
	 * @param startTime
	 * @param length
	 * @param unitsToProduce
	 * @param state
	 * @param runsOnAssemblyLine
	 * @param buildsProduct
	 */
	public ProductRun(int startTime, int length, int unitsToProduce, int state,
			AssemblyLine runsOnAssemblyLine, ProductType buildsProduct) {
		super();
		this.startTime = startTime;
		this.length = length;
		this.unitsToProduce = unitsToProduce;
		this.state = state;
		this.runsOnAssemblyLine = runsOnAssemblyLine;
		this.buildsProduct = buildsProduct;
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
	
}
