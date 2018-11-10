package operations.add_operations;

import java.util.Calendar;

import my_types.MyDate;
import my_types.MyType;
import operations.Calculable;

public class AddDateNumber implements Calculable {



	@Override
	public MyType startCalculations(MyType firstType, MyType secondType) {
		
		String currentDate = firstType.getValue();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(MyDate.parseGivenDate(currentDate));
		
		calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(secondType.getValue()));
		
		return new MyDate(MyDate.format(calendar.getTime()));
	}

}
