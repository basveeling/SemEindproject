package test;

import static org.junit.Assert.*;

import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestManufacturingPlant {
	
	ManufacturingPlant testMP;
	
	@Before
	public void setUp() throws Exception {
		testMP = new ManufacturingPlant("test");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testaddProductRun() {
		ProductRun testPR = new ProductRun();
		AssemblyLine testAL = new AssemblyLine(0);
		testPR.setRunsOnAssemblyLine(testAL);
		testMP.addProductRun(testPR);
		assertEquals("Result", testPR, testMP.getProductRuns().get(testMP.getProductRuns().size()-1).getAssemblyLine().getProductRuns().get(testMP.getProductRuns().get(testMP.getProductRuns().size()-1).getAssemblyLine().getProductRuns().size()-1));
	}
	
	@Test
	public void testgetFreeProductOfTypeOrder() {
		ProductRun testPR = new ProductRun();
		ProductType testPT = new ProductType("testPT");
		Product testP = new Product(1337, testPT, testPR, null);
		testMP.addProduct(testP);
		assertEquals("Result", testP, testMP.getFreeProductOfType(null));		
		fail("productType wordt niet gebruikt");
	}	
	
	@Test
	public void testgetFreeProductOfTypeNoOrder() {
		ProductRun testPR = new ProductRun();
		ProductType testPT = new ProductType("testPT");
		Product testP = new Product(1337, testPT, testPR, null);
		assertEquals("Result", null, testMP.getFreeProductOfType(null));		
		fail("productType wordt niet gebruikt");
	}
	
}
