/**
 * 
 */
package model;


/**
 * @author Patrick
 *
 */
public class Part {
	private String name;
	private PartBin partBin;
	
	/**
	 * @param inStock
	 */
	public Part(String name) {
		this.name = name;
	}
	

	/**
	 * @return the inStock
	 */
	public int getInStock() {
//		return inStock; 
		return partBin.getContainsAmount(); //halen uit o
	}

	/**
	 * @return the partBin
	 */
	public PartBin getPartBin() {
		return partBin;
	}

	/**
	 * Puts this part in partBin
	 * @param partBin the partBin to set
	 * @ensure partBin.part = this
	 */
	public void setPartBin(PartBin partBin) {
		this.partBin = partBin;
		partBin.setPart(this);
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		String result = "Part(" + getName() + ") ";
		return result;
	}
}
