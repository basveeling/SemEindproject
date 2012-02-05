package test;

import static org.junit.Assert.*;

import model.*;
import model.relations.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestOrder {
	
	private Order testOrder;
	private ProductTypeOrder testProductTypeOrder;

	@Before
	public void setUp() throws Exception {
		testOrder = new Order(0, "testOrder");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testamountForProductType() {
		ProductType testPT = new ProductType("testPT");
		testOrder.addProductTypeOrder(testPT, 10);
		assertEquals("Result", 10, testOrder.amountForProductType(testPT));
	}
	@Test
	public void testadd() {
		ProductType testProductType = new ProductType("testProductType");
		testProductTypeOrder = new ProductTypeOrder(10, testProductType, testOrder);
		testOrder.add(testProductTypeOrder);
		assertTrue(testOrder.getProductTypes().contains(testProductTypeOrder));
	}
	@Test
	public void testremove() {
		testOrder.remove(testProductTypeOrder);
		assertTrue(!testOrder.getProductTypes().contains(testProductTypeOrder));
	}
}
