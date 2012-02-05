package test;

import static org.junit.Assert.*;

import model.*;
import model.relations.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestAssemblyLine {
	
	private AssemblyLine testAssemblyLine;
	private ProductRun testProductRun;

	@Before
	public void setUp() throws Exception {
		testAssemblyLine= new AssemblyLine(1337);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testaddRobot() {
		testAssemblyLine.addRobot();
		testAssemblyLine.addRobot();
		testAssemblyLine.addRobot();
		
		assertEquals("Result", 3, testAssemblyLine.getRobots().size());
		assertEquals("Result", 0, testAssemblyLine.getRobots().get(0).getRobotId());
		assertEquals("Result", 1, testAssemblyLine.getRobots().get(1).getRobotId());
		assertEquals("Result", 2, testAssemblyLine.getRobots().get(2).getRobotId());
	}
	

	@Test	
	public void testconfigForProductRun() {
		ProductType testProductType = new ProductType("testProductType");		
		Part testPart1 = new Part("testPart1");
		Part testPart2 = new Part("testPart2");		
		AssemblyStep testAssemblyStep1 = new AssemblyStep(testProductType, testPart1, 1, 5);
		AssemblyStep testAssemblyStep2 = new AssemblyStep(testProductType, testPart2, 2, 3);
		testProductType.add(testAssemblyStep1);
		testProductType.add(testAssemblyStep2);
		
		testAssemblyLine.addRobot();
		testAssemblyLine.addRobot();
		
		ProductRun testPR = new ProductRun(10, 20, 3, 0, testAssemblyLine, testProductType);
		testAssemblyLine.configForProductRun(testPR);
		
		assertEquals("Result", testAssemblyStep1, testAssemblyLine.getRobots().get(0).getAssemblyStep());
		assertEquals("Result", testAssemblyStep2, testAssemblyLine.getRobots().get(1).getAssemblyStep());
		assertEquals("Result", testAssemblyLine.getRobots().get(1), testAssemblyLine.getRobots().get(0).getNextRobot());
		assertEquals("Result", null, testAssemblyLine.getRobots().get(1).getNextRobot());
	}
	@Test
	public void testrunProductRun(){
		fail("nog niet geimplementeerd");
	}
}
