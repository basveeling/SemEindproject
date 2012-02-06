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
	 * Initializes the plant with the commands
	 * 
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
		commands.get(0).execute(null, null);

	}

	// ================================
	// ========= Commands =============
	// ================================

	/**
	 * Prints a help screen with all the command's available
	 * 
	 * @author bas
	 * 
	 */
	public class HelpCommand extends Command {
		public HelpCommand() {
			super('h', 0, "help (this overview)");
		}

		public void execute(String par1, String par2) {
			System.out.println("ManufacturingPlant " + plant.getPlantName());
			System.out.println("Command's:");

			for (int i = 0; i < commands.size(); i++) {
				System.out.println(String.format("   %c %s", commands.get(i)
						.getSign(), commands.get(i).getDescription()));
			}
		}
	}

	/**
	 * Command that exits the program
	 * 
	 * @author bas
	 * 
	 */
	public class ExitCommand extends Command {
		public ExitCommand() {
			super('x', 0, "exit");
		}

		public void execute(String par1, String par2) {
			System.out.println("Terminating application!");
			running = false;
		}
	}

	/**
	 * Display a list of the available inventory and the amount to produce/buy
	 * 
	 * @author bas
	 * 
	 */
	public class PrintInventoryCommand extends Command {
		public PrintInventoryCommand() {
			super('i', 0, "print inventory");
		}

		public void execute(String par1, String par2) {
			System.out.println(ManufacturingPlantController
					.overviewOfUnitsToProduce(plant));
		}
	}

	/**
	 * A command that steps the user to placing a new order
	 * 
	 * @author bas
	 * 
	 */
	public class PlaceOrderCommand extends Command {
		public PlaceOrderCommand() {
			super('p', 0, "Place order");
		}

		public void execute(String par1, String par2) {
			String orderId = readString("Give Order ID (string): ");
			Order newOrder = new Order(0, orderId);

			boolean addingProducts = true;
			boolean productAdded = false;
			while (addingProducts) {
				System.out.println("ProductTypes: ");
				for (int i = 0; i < plant.getProductTypes().size(); i++) {
					System.out.println("\t #" + (i + 1) + " "
							+ plant.getProductTypes().get(i).getName());
				}
				int productIndex = readInt("Product nummer (0 voor geen producten meer toevoegen):");
				if (productIndex > 0
						&& productIndex <= plant.getProductTypes().size()) {
					if (!newOrder.hasPTOForProductType(plant.getProductTypes()
							.get(productIndex - 1))) {
						int amount = readInt("Aantal:");
						newOrder.addProductTypeOrder(plant.getProductTypes()
								.get(productIndex - 1), amount);
						productAdded = true;
					} else {
						System.out
								.println("Product al toegevoegd aan dit order, kies een andere of sluit af met 0.");
					}
				} else if (productIndex == 0) {
					addingProducts = false;
				} else {
					System.out
							.println("Ongeldig product nummer, probeer opnieuw.");
				}
			}
			if (productAdded) {
				plant.addOrder(newOrder);
				System.out.println("Order placed: " + newOrder);
			} else {
				System.out.println("Order without products not allowed!");
			}
		}
	}

	/**
	 * A command that steps the user through creating a productRun, starts the
	 * productrun as soon as the user finishes the last step of this command
	 * 
	 * @author bas
	 * 
	 */
	// TODO: timeslot selecteren toevoegen
	public class CreateProductRunCommand extends Command {
		public CreateProductRunCommand() {
			super('r', 0, "Create a product run");
		}

		public void execute(String par1, String par2) {
			ProductRun productRun = new ProductRun();
			int productIndex = 0;
			while (productIndex == 0) {
				System.out.println("ProductType to produce: ");
				System.out.println(ManufacturingPlantController
						.overviewOfProductsProduce(plant));
				productIndex = readInt("Product number:");
				if (productIndex > 0
						&& productIndex <= plant.getProductTypes().size()) {
					productRun.setBuildsProduct(plant.getProductTypes().get(
							productIndex - 1));

				} else {
					productIndex = 0;
				}
			}
			int amount = readInt("Units to produce:");
			productRun.setUnitsToProduce(amount);
			if (ProductRunController.allPartsAvailable(productRun)) {
				System.out.println("Estimated assemblytime is "
						+ productRun.getBuildsProduct()
								.estimatedAssemblyTimeForAmount(amount));
				System.out.println("Required robots is "
						+ productRun.getBuildsProduct().getAssemblySteps()
								.size());

				int assemblyLineIndex = 0;
				while (assemblyLineIndex == 0) {
					System.out.println("Choose AssemblyLine: ");
					System.out.println(ManufacturingPlantController
							.overviewOfAssemblyLines(plant));
					assemblyLineIndex = readInt("AssemblyLine:");
					if (assemblyLineIndex > 0
							&& assemblyLineIndex <= plant.getAssemblyLines()
									.size()) {
						AssemblyLine assemblyLine = plant.getAssemblyLines()
								.get(assemblyLineIndex - 1);
						if (!assemblyLine.isOccupied()) {
							if (assemblyLine.hasEnoughRobotsFor(productRun
									.getBuildsProduct())) {
								productRun.setAssemblyLine(assemblyLine);
							} else {
								System.out
										.println("This assemblyLine doesn't have enough robots to produce this productType.");
								assemblyLineIndex = 0;
							}
						} else {
							System.out
									.println("This assemblyLine is currently occupied, try again.");
							assemblyLineIndex = 0;
						}

					} else {
						assemblyLineIndex = 0;
					}
				}
				System.out.println("ProductRun created: " + productRun);
				plant.addProductRun(productRun);
			} else {
				System.out
						.println("Niet genoeg parts beschikbaar, bestel meer parts");
			}
		}
	}
	
	/**
	 * Prints an overview of all the orders and the required parts 
	 * @author bas
	 *
	 */
	public class OrderOverviewCommand extends Command {
		public OrderOverviewCommand() {
			super('o', 0, "Overview of orders");
		}

		public void execute(String par1, String par2) {
			System.out.print("Order overview:");
			System.out.println(ManufacturingPlantController
					.overviewOfOrders(plant));
		}
	}
	
	/**
	 * Prints a list of the assemblyLines with state of occupation
	 * @author bas
	 *
	 */
	public class AssemblyLineOverviewCommand extends Command {
		public AssemblyLineOverviewCommand() {
			super('l', 0, "Overview of assemblylines");
		}

		public void execute(String par1, String par2) {
			System.out.print("Assemlyline overview:");
			System.out.println(ManufacturingPlantController
					.overviewOfAssemblyLines(plant));
		}
	}
	
	/**
	 * Sets on order as finished, adds products to shipment
	 * @author bas
	 *
	 */
	public class FinishOrderCommand extends Command {
		public FinishOrderCommand() {
			super('f', 0, "set an order as finished");
		}

		public void execute(String par1, String par2) {
			int orderIndex = -1;
			Order order = null;
			while (orderIndex == -1) {
				System.out.println("Choose an order (0 to cancel): ");
				System.out.println(ManufacturingPlantController
						.overviewOfOrders(plant));
				orderIndex = readInt("Select order #:");
				if (orderIndex > 0 && orderIndex <= plant.getOrders().size()) {
					order = plant.getOrders().get(orderIndex - 1);
					if (order.getState() != Order.STATE_PLACED) {
						orderIndex = 0;
						System.out.println("Order has been shipped already");
					}
				} else if (orderIndex == 0) {
					break;
				} else {
					orderIndex = -1;
				}
			}
			if (orderIndex != 0) {
				if (OrderController.allPartsAvailable(order)) { // check met
					// TODO: dit naar order verplaatsen
					Product tempProduct;
					String lijst = "Serialnumbers:\n";
					for (ProductTypeOrder pto : order.getProductTypes()) {
						lijst += pto.getProductType().getName() + ":\n";
						for (int i = 0; i < pto.getAmount(); i++) {
							tempProduct = plant.getFreeProductOfType(pto
									.getProductType());
							tempProduct.setSoldWithOrder(order);
							pto.getProductType().getPartBin().takeOnePart();
							lijst += tempProduct.getSerialNumber() + ", ";
						}
					}
					order.setFinshed();
					System.out.println(lijst);
				} else {
					System.out.println("Inventory not sufficient.");
				}
			}
		}
	}
	/**
	 * A command that lets the user add parts to the partbin
	 * @author bas
	 *
	 */
	public class AddPartsCommand extends Command {
		public AddPartsCommand() {
			super('a', 0, "Add a part to the partbin");
		}

		public void execute(String par1, String par2) {

			int partIndex = 0;
			Part part = null;
			while (partIndex == 0) {
				// TODO: add check
				System.out.println("Choose a part: ");
				System.out.println(ManufacturingPlantController
						.overviewOfPartsToProduce(plant));
				partIndex = readInt("Select part #:");
				if (partIndex > 0 && partIndex <= plant.getParts().size()) {
					part = plant.getParts().get(partIndex - 1);
				} else {
					partIndex = 0;
				}
			}
			int amount = -1;
			while (!part.getPartBin().addPart(amount)) {
				amount = readInt("Aantal:");
			}
			System.out.println(amount + " Units of " + part
					+ "have been added to " + part.getPartBin());

		}
	}

	/**
	 * Print promt en vraagt de standaard regel om een regel invoer.
	 * 
	 * @param String
	 *            promt
	 * @return String invoer of null indien lege regel
	 */
	public static String readString(String prompt) {

		String result = null;
		System.out.print("" + prompt + " ");
		while (result == null || result.equals("")) {
			if (sc.hasNextLine()) {
				result = sc.nextLine();
				result = result.equals("") ? null : result;
			}
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
	public static int readInt(String prompt) {

		int result = -1;
		System.out.print("\n" + prompt + " ");
		while (result < 0) {
			while (!sc.hasNextInt()) {
				System.out.print("Ongeldige invoer, probeer opnieuw: ");
				sc.next();
			}
			result = sc.nextInt();
			if (result < 0) {
				System.out.println("Alleen positieve getallen:");
			}
		}
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
			regel = readString("MP>");
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
			if (commands.get(i).canExecute(teken.charAt(0), par1, par2)) {
				commands.get(i).execute(par1, par2);
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
