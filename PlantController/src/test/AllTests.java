package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAssemblyLine.class, TestManufacturingPlant.class,
		TestOrder.class, TestPart.class, TestPartBin.class, TestProduct.class,
		TestProductType.class })
public class AllTests {

}
