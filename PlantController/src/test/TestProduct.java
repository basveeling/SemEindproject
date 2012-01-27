package test;

import static org.junit.Assert.*;

import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestProduct {
	
	private Product testProduct;
	private ProductType testProductType;
	private ProductRun testProductRun;
	private Order testOrder;

	@Before
	public void setUp() throws Exception {
		testProductType = new ProductType("testProductType");
		testProductRun = new ProductRun();
		testOrder = new Order(0, "test");
		testProduct = new Product(10, testProductType, testProductRun, testOrder);
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testgetType() {
		assertEquals("Result", testProductType, testProduct.getType());
	}
	@Test
	public void testsetType() {
		ProductType testProductType2 = new ProductType("testProductType");
		testProduct.setType(testProductType2);
		assertEquals("Result", testProductType2, testProduct.getType());
	}
	@Test
	public void testgetSoldWithOrder() {
		assertEquals("Result", testOrder, testProduct.getSoldWithOrder());
	}
	@Test
	public void testsetSoldWithOrder() {
		Order testOrder2 = new Order(0, "test2");
		testProduct.setSoldWithOrder(testOrder2);
		assertEquals("Result", testOrder2, testProduct.getSoldWithOrder());
	}

}
