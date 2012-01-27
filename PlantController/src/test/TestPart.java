package test;

import static org.junit.Assert.*;

import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPart {
	
	private Part testPart;

	@Before
	public void setUp() throws Exception {
		testPart = new Part("TestPart");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPartBin() {
		assertEquals("Result", null, testPart.getPartBin());
	}

	@Test
	public void testSetPartBin() {
		PartBin testPartBin = new PartBin(1,1);
		testPart.setPartBin(testPartBin);
		assertEquals("Result", testPartBin, testPart.getPartBin());
	}

}
