package maventest;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BirthdayCalculator {
	public int calculate(String birthday) {
		//首先对输入的提起是否是null或者是""进行判断
		if(birthday==null||birthday.isEmpty()) {
			throw new RuntimeException("Birthday should not be null or empty");
		}
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar today=Calendar.getInstance();
		today.setTime(DateTime.now().toDate());
		
		//处理输入的日期恰好等于今年生日的情况
		if(birthday.equals(sdf.format(today.getTime()))) {
			return 0;
		}
		
		//输入日期格式的有效性检查
		Calendar birthDate=Calendar.getInstance();
		try {
			birthDate.setTime(sdf.parse(birthday));
		} catch(ParseException e) {
			throw new RuntimeException("Birthday format is invalid!");
		}
		birthDate.set(Calendar.YEAR,today.get(Calendar.YEAR));
		
		//实际计算的逻辑
		int days;
		if(birthDate.get(Calendar.DAY_OF_YEAR) < today.get(Calendar.DAY_OF_YEAR)) {
			days=today.getActualMaximum(Calendar.DAY_OF_YEAR)-today.get(Calendar.DAY_OF_YEAR);
			days+=birthDate.get(Calendar.DAY_OF_YEAR);
		}else {
			days=birthDate.get(Calendar.DAY_OF_YEAR)-today.get(Calendar.DAY_OF_YEAR);
		}
		
		return days;
	}

}
