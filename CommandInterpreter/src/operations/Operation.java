package operations;

import java.util.Map;

import exceptions.OperationNotAllowedException;
import javafx.util.Pair;
import my_types.MyType;

public abstract class Operation {
	protected Map<Pair<String, String>, Calculable> availableTypesOperation;
	
	
	
	public Operation(Map<Pair<String, String>, Calculable> operations) {
		this.availableTypesOperation = operations;
	}


	public MyType execute(MyType firstType, MyType secondType) {
		Pair<String, String> currentPair = new Pair<>(firstType.getClass().getSimpleName(), secondType.getClass().getSimpleName());
		
		if(availableTypesOperation.containsKey(currentPair)) {
			return availableTypesOperation.get(currentPair).startCalculations(firstType, secondType);
		}
		else {
			throw new OperationNotAllowedException("Operation with these types is not allowed");
		}
	}
}
