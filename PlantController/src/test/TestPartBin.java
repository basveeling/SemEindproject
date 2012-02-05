package test;

import static org.junit.Assert.*;

import model.PartBin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPartBin {
	
	private PartBin testPartBin;

	@Before
	public void setUp() throws Exception {
		testPartBin = new PartBin(1,0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testgetContainsAmount() {
		assertEquals("Result", 0, testPartBin.getContainsAmount());
	}
	@Test
	public void testsetContainsAmount() {
		testPartBin.setContainsAmount(10);
		assertEquals("Result", 10, testPartBin.getContainsAmount());
	}
	@Test
	public void testaddPart() {
		testPartBin.addPart(10);
		assertEquals("Result", 10, testPartBin.getContainsAmount());
	}
	@Test
	public void testtakePart() {
		testPartBin.setContainsAmount(10);
		testPartBin.takePart(4);
		assertEquals("Result", 6, testPartBin.getContainsAmount());
	}
	@Test
	public void testtakePartteveel() {
		testPartBin.setContainsAmount(10);
		testPartBin.takePart(20);
		assertEquals("Result", -10, testPartBin.getContainsAmount());
	}
	@Test
	public void testisEmtyempty() {
		assertTrue(testPartBin.isEmpty());
	}
	@Test
	public void testisEmtynotempty() {
		testPartBin.setContainsAmount(10);
		assertTrue(!testPartBin.isEmpty());
	}

}
