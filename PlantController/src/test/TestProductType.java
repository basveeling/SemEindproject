package test;

import static org.junit.Assert.*;

import model.*;
import model.relations.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestProductType {
	
	private ProductType testProductType;

	@Before
	public void setUp() throws Exception {
		testProductType = new ProductType("testProductType");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testadd() {
		Part testPart = new Part("testPart");
		AssemblyStep testAssemblyStep = new AssemblyStep(testProductType, testPart, 10, 10);
		testProductType.add(testAssemblyStep);		
		assertTrue(testProductType.getAssemblySteps().contains(testAssemblyStep));
	}
	@Test
	public void testgetNewSerialNumber0() {
		assertEquals("Result", 0, testProductType.getNewSerialNumber());
	}
	@Test
	public void testgetNewSerialNumber1() {
		testProductType.getNewSerialNumber();
		assertEquals("Result", 1, testProductType.getNewSerialNumber());
	}
	@Test
	public void testamountForPart() {
		Part testPart = new Part("testPart");
		Part testPart2 = new Part("testPart2");
		AssemblyStep testAssemblyStep = new AssemblyStep(testProductType, testPart, 10, 15);
		AssemblyStep testAssemblyStep2 = new AssemblyStep(testProductType, testPart2, 6, 20);
		testProductType.add(testAssemblyStep);
		testProductType.add(testAssemblyStep2);
		
		assertEquals("Result", 10, testProductType.amountForPart(testPart));
	}
	@Test
	public void estimatedAssemblyTimeForAmount(){
		fail("Not yet implemented");
	}
	
	

}
