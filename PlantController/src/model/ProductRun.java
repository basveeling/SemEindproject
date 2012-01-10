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
	private AssemblyLine assemblyLine;
	
	
	
	/**
	 * @param startTime
	 * @param length
	 * @param unitsToProduce
	 * @param state
	 * @param assemblyLine
	 */
	public ProductRun(int startTime, int length, int unitsToProduce, int state,
			AssemblyLine assemblyLine) {
		super();
		this.startTime = startTime;
		this.length = length;
		this.unitsToProduce = unitsToProduce;
		this.state = state;
		this.assemblyLine = assemblyLine;
	}
	
	
	public AssemblyLine getAssemblyLine() {
		return assemblyLine;
	}


	public void setAssemblyLine(AssemblyLine assemblyLine) {
		this.assemblyLine = assemblyLine;
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
