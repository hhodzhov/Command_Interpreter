package operations.subtract_operations;

import my_types.MyNumber;
import my_types.MyType;
import operations.Calculable;

public class SubtractNumbers implements Calculable{

	@Override
	public MyType startCalculations(MyType firstType, MyType secondType) {
		return new MyNumber(Integer.parseInt(firstType.getValue()) - Integer.parseInt(secondType.getValue()));
	}

}
