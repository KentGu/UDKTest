package my.selenium.cases;

import org.testng.Assert;

public class ValueCheckExpectResult implements ExpectResult {
	private Object expect;
	private Object actual;

	public ValueCheckExpectResult (int expect, int actual){
		this.expect = expect;
		this.actual = actual;
	}
	
	@Override
	public void checkExpectResult() {
		// TODO Auto-generated method stub
		Assert.assertEquals(actual,expect);
	}

}