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
		testMP = new ManufacturingPlant();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testgetFreeProductOfTypeOrder() {
		ProductRun testPR = new ProductRun();
		ProductType testPT = new ProductType("testPT");
		Order testO = new Order(0, "testO");
		Product testP = new Product(1337, testPT, testPR, testO);
		testMP.addProduct(testP);
		assertEquals("Result", null, testMP.getFreeProductOfType(testPT));
	}	
	
	@Test
	public void testgetFreeProductOfTypeNoOrder() {
		ProductRun testPR = new ProductRun();
		ProductType testPT = new ProductType("testPT");
		Product testP = new Product(1337, testPT, testPR, null);
		testMP.addProduct(testP);
		assertEquals("Result", testP, testMP.getFreeProductOfType(testPT));
	}
	
}
