package maventest;

import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BirthdayCalculatorTest {
	
	@InjectMocks
	private BirthdayCalculator birthdayCalculator;
	
	@Before
	public void setUp() {
		Date fixedDateTime=new Date("2019/09/14");
		DateTimeUtils.setCurrentMillisFixed(fixedDateTime.getTime());
	}
	
	@After
	public void tearDown() {
		DateTimeUtils.setCurrentMillisSystem();
	}
	
	//输入日期为空字符串
	@Test(expected=RuntimeException.class)
	public void testBirthdayCalculatorWithSpace() {
		birthdayCalculator.calculate("");
	}
	
	//输入日期为null
	@Test(expected=RuntimeException.class)
	public void testBirthdayCalculatorWithNull() {
		birthdayCalculator.calculate(null);
	}
	
	//输入日期为全角
	@Test(expected=RuntimeException.class)
	public void testBirthdayCalculatorWithQuanjiao() {
		birthdayCalculator.calculate("２０１９－０１－０１");
	}
	
	//输入日期格式不正确
	@Test(expected=RuntimeException.class)
	public void testBirthdayCalculatorWrongFormat() {
		birthdayCalculator.calculate("2019 01 01");
	}
	
	//输入日期无效
	@Test(expected=RuntimeException.class)
	public void testBirthdayCalculatorWrongDate() {
		birthdayCalculator.calculate("2019-02-29");
		birthdayCalculator.calculate("2019-02-30");
	}
	
	//测试结果是否正确
	@Test
	public void testBirthdayCalculatorResult() throws Exception{
		assertEquals(0,birthdayCalculator.calculate("2019-09-14"));
		assertEquals(364,birthdayCalculator.calculate("2019-09-13"));
		assertEquals(0,birthdayCalculator.calculate("2020-09-14"));
		assertEquals(9,birthdayCalculator.calculate("2019-09-23"));
	}

}
