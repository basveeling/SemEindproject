/**
 * 
 */
package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.ManufacturingPlantController;
import controller.OrderController;
import controller.ProductRunController;

import model.*;
import model.relations.*;

/**
 * @author bas
 * 
 */
public class ManufacturingPlantTUI {

	private ManufacturingPlant plant;
	public boolean running = true;
	private static Scanner sc = new Scanner(System.in);
	private int state = 0;

	private static ArrayList<Command> commands = new ArrayList<Command>();

	/**
	 * @param plant
	 */
	public ManufacturingPlantTUI() {
		super();
		this.plant = ManufacturingPlant.getInstance();
		plant.setPlantName("UseCase Industries");

		Fixtures.addFixtures(plant);
		plant.start();
		commands.add(new HelpCommand());
		commands.add(new ExitCommand());
		commands.add(new PrintInventoryCommand());
		commands.add(new PlaceOrderCommand());
		commands.add(new CreateProductRunCommand());
		commands.add(new OrderOverviewCommand());
		commands.add(new AssemblyLineOverviewCommand());
		commands.add(new FinishOrderCommand());
		commands.add(new AddPartsCommand());
		commands.get(0).execute(0 ,null, null);

	}

	public class HelpCommand extends Command {
		public HelpCommand() {
			super('h', 0, "help (this overview)");
		}

		public void execute(int state, String par1, String par2) {
			System.out.println("ManufacturingPlant " + plant.getPlantName());
			System.out.println("Command's:");

			for (int i = 0; i < commands.size(); i++) {
				System.out.println(String.format("   %c %s", commands.get(i)
						.getSign(), commands.get(i).getDescription()));
			}
		}
	}

	public class ExitCommand extends Command {
		public ExitCommand() {
			super('x', 0, "exit");
		}

		public void execute(int state, String par1, String par2) {
			System.out.println("Terminating application!");
			running = false;
		}
	}

	public class PrintInventoryCommand extends Command {
		public PrintInventoryCommand() {
			super('i', 0, "print inventory");
		}
		
		public void execute(int state, String par1, String par2) {
			System.out.println(ManufacturingPlantController.overviewOfUnitsToProduce(plant));
		}
	}
	
	public class PlaceOrderCommand extends Command {
		public PlaceOrderCommand() {
			super('p', 0, "Place order");
		}
		
		public void execute(int state, String par1, String par2) {
			String orderId = leesRegel("Give Order ID (string): ");
			Order newOrder = new Order(0, orderId);
			plant.addOrder(newOrder);
			
			boolean addingProducts = true;
			while(addingProducts) {
				System.out.println("ProductTypes: ");
				for(int i = 0; i < plant.getProductTypes().size(); i++) {
					System.out.println("\t #" + (i + 1) + " " + plant.getProductTypes().get(i).getName());
				}
				int productIndex = leesInt("Product nummer (0 voor geen producten meer toevoegen):");
				if(productIndex > 0 && productIndex <= plant.getProductTypes().size()) {
					int amount = leesInt("Aantal:");
					newOrder.addProductTypeOrder(plant.getProductTypes().get(productIndex -1), amount);
				} else if (productIndex == 0)  {
					addingProducts = false;
				} else {
					System.out.println("Ongeldig product nummer, probeer opnieuw.");
				}
			}
			System.out.println("Order placed: " + newOrder);
		}
	}
	/**
	 * @author bas
	 *
	 */
	// TODO: timeslot selecteren toevoegen
	public class CreateProductRunCommand extends Command {
		public CreateProductRunCommand() {
			super('r', 0, "Create a product run");
		}
		
		public void execute(int state, String par1, String par2) {
			ProductRun productRun = new ProductRun();
			int productIndex = 0;
			while(productIndex == 0) {
				System.out.println("ProductType to produce: ");
				System.out.println(ManufacturingPlantController.overviewOfProductsProduce(plant));
				productIndex = leesInt("Product number:");
				if(productIndex > 0  && productIndex <= plant.getProductTypes().size()) {
					productRun.setBuildsProduct(plant.getProductTypes().get(productIndex -1));
					
				} else {
					productIndex = 0;
				}
			}
			int amount = leesInt("Units to produce:");
			productRun.setUnitsToProduce(amount);
			if(ProductRunController.allPartsAvailable(productRun)) {
				System.out.println("Estimated assemblytime is " + productRun.getBuildsProduct().estimatedAssemblyTimeForAmount(amount));
				System.out.println("Required robots is " + productRun.getBuildsProduct().getAssemblySteps().size());
				
				int assemblyLineIndex = 0;
				while(assemblyLineIndex == 0) {
					System.out.println("Choose AssemblyLine: ");
					System.out.println(ManufacturingPlantController.overviewOfAssemblyLines(plant));
					assemblyLineIndex = leesInt("AssemblyLine:");
					if(assemblyLineIndex > 0 && assemblyLineIndex <= plant.getAssemblyLines().size()) {
						AssemblyLine assemblyLine = plant.getAssemblyLines().get(assemblyLineIndex -1);
						if(!assemblyLine.isOccupied()) {
							if(assemblyLine.hasEnoughRobotsFor(productRun.getBuildsProduct())) {
								productRun.setAssemblyLine(assemblyLine);
							} else {
								System.out.println("This assemblyLine doesn't have enough robots to produce this productType.");
								assemblyLineIndex = 0;
							}
						} else {
							System.out.println("This assemblyLine is currently occupied, try again.");
							assemblyLineIndex = 0;
						}
						
						
					} else {
						assemblyLineIndex = 0;
					}
				}
				System.out.println("ProductRun created: " + productRun);
				plant.addProductRun(productRun);
			} else {
				System.out.println("Niet genoeg parts beschikbaar, bestel meer parts");
			}
		}
	}

	public class OrderOverviewCommand extends Command {
		public OrderOverviewCommand() {
			super('o', 0, "Overview of orders");
		}
		
		public void execute(int state, String par1, String par2) {
			System.out.print("Order overview:");
			System.out.println(ManufacturingPlantController.overviewOfOrders(plant));
		}
	}
	
	public class AssemblyLineOverviewCommand extends Command {
		public AssemblyLineOverviewCommand() {
			super('l', 0, "Overview of assemblylines");
		}
		
		public void execute(int state, String par1, String par2) {
			System.out.print("Assemlyline overview:");
			System.out.println(ManufacturingPlantController.overviewOfAssemblyLines(plant));
		}
	}
	
	public class FinishOrderCommand extends Command {
		public FinishOrderCommand() {
			super('f', 0, "set an order as finished");
		}
		
		public void execute(int state, String par1, String par2) {
			int orderIndex = 0;
			Order order = null;
			while(orderIndex == 0) {
				System.out.println("Choose an order: ");
				System.out.println(ManufacturingPlantController.overviewOfOrders(plant));
				orderIndex = leesInt("Select order #:");
				if(orderIndex > 0 && orderIndex <= plant.getOrders().size()) {
					 order = plant.getOrders().get(orderIndex - 1);
					 if(order.getState() != Order.STATE_PLACED) {
						 orderIndex = 0;	
						 System.out.println("Order has been shipped already");
					 }
				} else {
					orderIndex = 0;
				}
			}
			if(OrderController.allPartsAvailable(order)) { // check met 
				//TODO: dit naar order verplaatsen
				Product tempProduct;
				for (ProductTypeOrder pto : order.getProductTypes()) {
					for(int i = 0; i < pto.getAmount(); i++) {
						tempProduct = plant.getFreeProductOfType(pto.getProductType());
						tempProduct.setSoldWithOrder(order);
						pto.getProductType().getPartBin().takeOnePart();
					}
				}
				order.setFinshed();
			} else {
				System.out.println("Inventory not sufficient.");
			}
		}
	}
	
//	public class FinishProductRunCommand extends Command {
//		public FinishProductRunCommand() {
//			super('o', 0, "Set a productrun as finished");
//		}
//		
//		public void execute(int state, String par1, String par2) {
//			int productRunIndex = 0;
//			ProductRun productRun = null;
//			while(productRunIndex == 0) {
//				//TODO: add check
//				System.out.println("Choose an product run: ");
//				System.out.println(ManufacturingPlantController.overviewOfUnfinishedProductRuns(plant));
//				productRunIndex = leesInt("Select productrun #:");
//				if(productRunIndex > 0 && productRunIndex <= plant.getProductRuns().size()) {
//					productRun = plant.getProductRuns().get(productRunIndex - 1);
//				} else {
//					productRunIndex = 0;
//				}
//			}
//			int serialnumber;
//			serialnumber = leesInt("Choose a starting serialnumber for " + productRun.getBuildsProduct().getName() + ":");
//			Product tempProduct;
//			for(int i = 0; i < productRun.getUnitsToProduce(); i++) {
//				tempProduct = new Product((serialnumber + i), productRun.getBuildsProduct(), productRun, null);
//				plant.addProduct(tempProduct);
//				productRun.addProduct(tempProduct);
//			}
//			productRun.setFinished(1);
//		}
//	}
	
	public class AddPartsCommand extends Command {
		public AddPartsCommand() {
			super('a', 0, "Add a part");
		}
		
		public void execute(int state, String par1, String par2) {
			
			int partIndex = 0;
			Part part = null;
			while(partIndex == 0) {
				//TODO: add check
				System.out.println("Choose a part: ");
				System.out.println(ManufacturingPlantController.overviewOfPartsToProduce(plant));
				partIndex = leesInt("Select part #:");
				if(partIndex > 0 && partIndex <= plant.getParts().size()) {
					part = plant.getParts().get(partIndex - 1);
				} else {
					partIndex = 0;
				}
			}
			int amount = -1; 
			while(!part.getPartBin().addPart(amount)) {
				amount = leesInt("Aantal:");
			}
			System.out.println(amount + " Units of " + part + "have been added to " + part.getPartBin());
			
		}
	}

	
	

	/**
	 * Print promt en vraagt de standaard regel om een regel invoer.
	 * 
	 * @param String
	 *            promt
	 * @return String invoer of null indien lege regel
	 */
	public static String leesRegel(String prompt) {

		String result = null;
		System.out.print("" + prompt + " ");
		if (sc.hasNextLine()) {
			result = sc.nextLine();
			result = result.equals("") ? null : result;
		}
		// System.out.println();
		return result;
	}
	
	/**
	 * Print promt en vraagt de standaard regel om een regel invoer.
	 * 
	 * @param String
	 *            promt
	 * @return String invoer of null indien lege regel
	 */
	public static int leesInt(String prompt) {

		int result;
		System.out.print("\n" + prompt + " ");
		while(!sc.hasNextInt()) {
			System.out.print("Ongeldige invoer, probeer opnieuw: ");
			sc.next();
		}
		result = sc.nextInt();
		return result;
	}

	/**
	 * Leest een regel in en geeft een scanner terug
	 * 
	 * @return Scanner indien invoer niet leeg.
	 */
	public static Scanner leesCommando() {
		String regel = null;
		while (regel == null) {
			regel = leesRegel("MP>");
		}
		return new Scanner(regel);
	}

	/**
	 * Vraagt een commando aan
	 * 
	 * @return
	 */
	public boolean parseCommando() {
		boolean result = true;
		Scanner inputsc = leesCommando();
		String teken = inputsc.next();
		String par1 = inputsc.hasNext() ? inputsc.next() : null;
		String par2 = inputsc.hasNext() ? inputsc.next() : null;
		boolean gelukt = false;
		for (int i = 0; i < commands.size() && !gelukt; i++) {
			if (commands.get(i).canExecute(teken.charAt(0), state, par1, par2)) {
				commands.get(i).execute(state, par1, par2);
				gelukt = true;
			}
		}
		if (!gelukt) {
			System.out.println("Ongeldig commando");
		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ManufacturingPlantTUI tui = new ManufacturingPlantTUI();

		while (tui.running == true) {
			tui.parseCommando();
		}
	}
}
