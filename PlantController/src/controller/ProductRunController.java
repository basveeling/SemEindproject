/**
 * 
 */
package controller;

import java.util.ArrayList;

import model.*;
import model.relations.*;

/**
 * @author bas
 *
 */
public class ProductRunController {
	public static boolean allPartsAvailable(ProductRun run) {
		//TODO: als twee assemblySteps de zelfde part gebruiken, wordt deze functie niet goed berekend.
		ArrayList<AssemblyStep> assemblySteps = run.getBuildsProduct().getAssemblySteps();
		for (AssemblyStep as : assemblySteps) {
			Part part = as.getPart();
			if(part.getInStock() < (as.getAmount() * run.getUnitsToProduce())) { //er is minder beschikbaar dan er nodig is
				return false;
			}
		}
		return true;
	}
}
