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
	
	public static PartBin bin1;
	public static PartBin bin2;
	public static PartBin bin3;
	public static PartBin bin4;
	public static PartBin bin5;
	public static PartBin bin6;
	public static PartBin bin7;
	public static PartBin bin8;
	public static PartBin bin9;
	public static PartBin bin10;
	public static PartBin bin11;
	public static PartBin bin12;
	public static PartBin bin13;
	public static PartBin bin14;
	public static PartBin bin15;
	
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
		bin1 = new PartBin(1,0);
		bin2 = new PartBin(2,0);
		bin3 = new PartBin(3,0);
		bin4 = new PartBin(4,0);
		bin5 = new PartBin(5,0);
		bin6 = new PartBin(6,0);
		bin7 = new PartBin(7,0);
		bin8 = new PartBin(8,0);
		bin9 = new PartBin(9,0);
		bin10 = new PartBin(10,0);
		
		muiswiel = new Part("Muiswiel");
		muiswiel.setPartBin(bin1);
		usbsnoer = new Part("Usbsnoer");
		usbsnoer.setPartBin(bin2);
		lichtsensor = new Part("Lichtsensor");
		lichtsensor.setPartBin(bin3);
		laser = new Part("SensorLaser");
		laser.setPartBin(bin4);
		knop = new Part("Klik knopje");
		knop.setPartBin(bin5);

		stekkersnoer = new Part("Stekkersnoer");
		stekkersnoer.setPartBin(bin6);
		electrospoel = new Part("Electrospoel");
		electrospoel.setPartBin(bin7);
		
		muissensor = new ProductType("Muissensor");
		muissensor.setPartBin(bin8);
		muissensor.addAssemblyStep(lichtsensor, 1, 1);
		muissensor.addAssemblyStep(laser, 1, 1);
		
		muis = new ProductType("Laptop Muis");
		muis.setPartBin(bin9);
		muis.addAssemblyStep(muissensor, 2, 1);
		muis.addAssemblyStep(muiswiel, 1, 5);
		muis.addAssemblyStep(usbsnoer, 1, 3);
		muis.addAssemblyStep(knop, 3, 6);
//		muis.addAssemblyStep(stekkersnoer, 3, 2);
//		muis.addAssemblyStep(electrospoel, 2, 1);
//		muis.addAssemblyStep(laser, 1, 5);
		
		oplader = new ProductType("Oplader");
		oplader.setPartBin(bin10);
		oplader.addAssemblyStep(stekkersnoer, 1, 1);
		oplader.addAssemblyStep(electrospoel, 2, 1);
		
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
		plant.addPart(muiswiel);
		plant.addPart(usbsnoer);
		plant.addPart(knop);
		plant.addPart(stekkersnoer);
		plant.addPart(electrospoel);
		plant.addPart(lichtsensor);
		plant.addPart(laser);

		plant.addPartBin(bin1);
		plant.addPartBin(bin2);
		plant.addPartBin(bin3);
		plant.addPartBin(bin4);
		plant.addPartBin(bin5);
		plant.addPartBin(bin6);
		plant.addPartBin(bin7);
		plant.addPartBin(bin8);
		plant.addPartBin(bin9);
		plant.addPartBin(bin10);
		plant.addPartBin(bin11);
		plant.addPartBin(bin12);
		
		plant.addProductType(muis);
		plant.addProductType(oplader);
		plant.addProductType(muissensor);
		plant.addOrder(order1);
		plant.addOrder(order2);
		plant.addOrder(order3);
		plant.addAssemblyLine(line1);
		for (int i = 0; i < 20; i++) {
			line1.addRobot();
		}
		plant.addAssemblyLine(line2);
		for (int i = 0; i < 10; i++) {
			line2.addRobot();
		}
		plant.addAssemblyLine(line3);for (int i = 0; i < 5; i++) {
			line2.addRobot();
		}
		plant.addAssemblyLine(line4);for (int i = 0; i < 1; i++) {
			line2.addRobot();
		}
	}
	
	
}
