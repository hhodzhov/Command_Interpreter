package operations.add_operations;

import my_types.MyNumber;
import my_types.MyType;
import operations.Calculable;

public class AddNumbers implements Calculable {

	@Override
	public MyType startCalculations(MyType firstType, MyType secondType) {
		return new MyNumber(Integer.parseInt(firstType.getValue()) + Integer.parseInt(secondType.getValue()));
	}

}
