package mooc01;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DateUtilTest {
	@Test
	public void test() {
		assertEquals(false, new DateUtil().isLeapYear(1900));	
		assertEquals(false, new DateUtil().isLeapYear(-100)); 
		assertEquals(false, new DateUtil().isLeapYear(1000)); 
		assertEquals(true, new DateUtil().isLeapYear(20000)); 
		assertEquals(true, new DateUtil().isLeapYear(2020)); 
		assertEquals(false, new DateUtil().isLeapYear(2019)); 
		assertEquals(true, new DateUtil().isLeapYear(2000)); 
	}
}