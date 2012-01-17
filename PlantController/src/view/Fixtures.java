/**
 * 
 */
package view;

import model.*;

/**
 * @author bas
 *
 */
public class Fixtures {
	public static ManufacturingPlant plant;
	public static ProductType muis;
	public static ProductType muissensor;
	public static Part muiswiel;

	public static Part lichtsensor;
	public static Part laser;
	public static Part knop;
	public static Part usbsnoer;
	public static ProductType oplader;
	public static Part stekkersnoer;
	public static Part electrospoel;
	public static Order order1;
	public static Order order2;
	public static Order order3;

	public static AssemblyLine line1;
	public static AssemblyLine line2;
	public static AssemblyLine line3;
	public static AssemblyLine line4;
	
	public static void addFixtures(ManufacturingPlant newPlant) {
		
		
		
		muiswiel = new Part("Muiswiel");
		usbsnoer = new Part("Usbsnoer");
		lichtsensor = new Part("Lichtsensor");
		laser = new Part("SensorLaser");
		knop = new Part("Klik knopje");

		stekkersnoer = new Part("Stekkersnoer");
		electrospoel = new Part("Electrospoel");
		


		muissensor = new ProductType("Muissensor" , 0, 10);
		muissensor.addProductPart(lichtsensor, 1);
		muissensor.addProductPart(laser, 1);
		
		muis = new ProductType("Laptop Muis", 0, 10);
		muis.addProductPart(muissensor, 2);
		muis.addProductPart(muiswiel, 1);
		muis.addProductPart(usbsnoer, 1);
		muis.addProductPart(knop, 3);
		
		oplader = new ProductType("Oplader", 0, 10);
		oplader.addProductPart(stekkersnoer, 1);
		oplader.addProductPart(electrospoel, 2);
		
		order1 = new Order(0, "O1");
		order1.addProductTypeOrder(muis, 100);
		order1.addProductTypeOrder(oplader, 30);
		order2 = new Order(0, "O2");
		order2.addProductTypeOrder(muis, 50);
		order2.addProductTypeOrder(oplader, 70);

		order3 = new Order(0, "O3");
		order3.addProductTypeOrder(muissensor, 50);
		
		line1 = new AssemblyLine(1);
		line2 = new AssemblyLine(2);
		line3 = new AssemblyLine(3);
		line4 = new AssemblyLine(4);
		
		plant = newPlant;
//		plant.addPart(muissensor);
		plant.addPart(muiswiel);
		plant.addPart(usbsnoer);
		plant.addPart(knop);
		plant.addPart(stekkersnoer);
		plant.addPart(electrospoel);
		plant.addPart(lichtsensor);
		plant.addPart(laser);
		
		plant.addProductType(muis);
		plant.addProductType(oplader);
		plant.addProductType(muissensor);
		plant.addOrder(order1);
		plant.addOrder(order2);
		plant.addOrder(order3);
		plant.addAssemblyLine(line1);
		plant.addAssemblyLine(line2);
		plant.addAssemblyLine(line3);
		plant.addAssemblyLine(line4);
		
		
	}
	
	
}
