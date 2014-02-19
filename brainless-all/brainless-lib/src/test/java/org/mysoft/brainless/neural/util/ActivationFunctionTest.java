package org.mysoft.brainless.neural.util;

import org.junit.Assert;
import org.junit.Test;

public class ActivationFunctionTest {

	@Test
	public void test_logSigmoid() {
		
		for(int i=-100; i<=100; i++) {
			double d = 0.1 * i;
			Double r = ActivationFunction.logSigmoid(d, 5.0);
			Assert.assertTrue("input and output signum do not match", Math.signum(d) == Math.signum(r));
			Assert.assertTrue("expected values from <-1, 1>", r >= -1.0 && r <= 1.0);
			
			if(i == 0) {
				Assert.assertTrue("f(0) should return 0", r == 0.0);
			}
			
			System.out.println(d + " -> " + r);
		}
 		
		
	}
	
}

