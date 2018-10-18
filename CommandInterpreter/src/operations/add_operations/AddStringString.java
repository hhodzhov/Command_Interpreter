package operations.add_operations;

import my_types.MyString;
import my_types.MyType;
import operations.Calculable;

public class AddStringString implements Calculable{

	@Override
	public MyType startCalculations(MyType firstType, MyType secondType) {
		return new MyString(firstType.getValue() + secondType.getValue());
	}

}
