package FlightManagementSystem.FlightManagementt;


import org.junit.BeforeClass;
import org.junit.Test;

import com.cpg.fms.service.Operations;
import junit.framework.TestCase;

public class OperationsTest extends TestCase {

	private static Operations oper;
	
	@BeforeClass
	public static void init() 
	{
		oper = new Operations();
	}
	
	@Test
	public void testPhone() 
	{
		boolean flag = oper.validPhoneNum("7976648278");
		assertTrue(flag);
	}
}