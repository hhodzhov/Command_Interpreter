package operations.subtract_operations;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import my_types.MyDate;
import my_types.MyNumber;
import my_types.MyType;
import operations.Calculable;

public class DiffBetweenTwoDates implements Calculable {

	@Override
	public MyType startCalculations(MyType firstType, MyType secondType) {
		Date firstDate = MyDate.parseGivenDate(firstType.getValue());
		Date secondDate = MyDate.parseGivenDate(secondType.getValue());
		
		long difference = firstDate.getTime() - secondDate.getTime();
		int differenceInDays = (int) TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
		
		return new MyNumber(differenceInDays);
	}

}
