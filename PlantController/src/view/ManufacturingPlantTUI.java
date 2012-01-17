/**
 * 
 */
package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.ManufacturingPlantController;

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
		this.plant = new ManufacturingPlant("UseCase Industries");

		Fixtures.addFixtures(plant);

		commands.add(new HelpCommand());
		commands.add(new ExitCommand());
		commands.add(new PrintInventoryCommand());
		commands.add(new PlaceOrderCommand());
		commands.add(new CreateProductRunCommand());
		commands.add(new OrderOverviewCommand());
		commands.get(0).execute(0 ,null, null);

	}

	public class HelpCommand extends Command {
		public HelpCommand() {
			super('h', 0, "help (this overview)");
		}

		public void execute(int state, String par1, String par2) {
			System.out.println("ManufacturingPlant " + plant.getName());
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
				} else {
					addingProducts = false;
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
			while(productIndex == 0 && productIndex <= plant.getProductTypes().size()) {
				System.out.println("ProductType to produce: ");
				System.out.println(ManufacturingPlantController.overviewOfProductsProduce(plant));
				productIndex = leesInt("Product number:");
				if(productIndex > 0) {
					productRun.setBuildsProduct(plant.getProductTypes().get(productIndex -1));
					
				}
			}
			int amount = leesInt("Units to produce:");
			System.out.println("Estimated assemblytime is " + productRun.getBuildsProduct().estimatedAssemblyTimeForAmount(amount));
			
			int assemblyLineIndex = 0;
			while(assemblyLineIndex == 0) {
				System.out.println("Choose AssemblyLine: ");
				System.out.println(ManufacturingPlantController.overviewOfAssemblyLines(plant));
				assemblyLineIndex = leesInt("AssemblyLine:");
				if(assemblyLineIndex > 0 && assemblyLineIndex <= plant.getAssemblyLines().size()) {
					productRun.setAssemblyLine(plant.getAssemblyLines().get(assemblyLineIndex -1));
					
				}
			}
			System.out.println("ProductRun created: " + productRun);
		}
	}
	
	public class OrderOverviewCommand extends Command {
		public OrderOverviewCommand() {
			super('o', 0, "Overview of orders");
		}
		
		public void execute(int state, String par1, String par2) {
			System.out.print("Order overview:");
			for(int i = 0; i < plant.getOrders().size(); i++) {
				System.out.print("\n" + plant.getOrders().get(i));
			}
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

		int result = 0;
		System.out.print("\n" + prompt + " ");
		if (sc.hasNextLine()) {
			result = sc.nextInt();
		}
		// System.out.println();
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
