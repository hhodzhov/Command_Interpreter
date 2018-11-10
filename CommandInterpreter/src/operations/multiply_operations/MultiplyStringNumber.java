package operations.multiply_operations;

import my_types.MyString;
import my_types.MyType;
import operations.Calculable;

public class MultiplyStringNumber implements Calculable {

	@Override
	public MyType startCalculations(MyType firstType, MyType secondType) {
		String result = getMultiplicatedResult(firstType, secondType);
		return new MyString(result);
	}

	private String getMultiplicatedResult(MyType firstType, MyType secondType) {
		String toMultiplicate = firstType.getValue();

		int numberTimes = Integer.parseInt(secondType.getValue());
		String result = "";
		for (int i = 0; i < numberTimes; i++) {
			result += toMultiplicate;
		}
		return result;
	}
}
