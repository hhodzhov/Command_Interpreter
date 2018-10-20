package operations.subtract_operations;

import exceptions.OperationNotAllowedException;
import my_types.MyString;
import my_types.MyType;
import operations.Calculable;

public class SubtractStringString implements Calculable{

	@Override
	public MyType startCalculations(MyType firstType, MyType secondType) {
		String first = firstType.getValue();
		String second = secondType.getValue();
		
		if(first.length() <= second.length()) {
			throw new IllegalArgumentException("First variable's length must be greater than second's");
		}if(!first.contains(second)) {
			throw new OperationNotAllowedException("First variable does not contain second. Operation cannot be done!");
		}else {
			String result = getSubtractedString(first,second);
			return new MyString(result);
		}
		
	}

	private String getSubtractedString(String first, String second) {
		
		String result = first.replace(second, "");
		return result;
	}
	
}