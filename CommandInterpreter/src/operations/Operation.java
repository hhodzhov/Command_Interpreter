package operations;

import java.util.HashMap;
import java.util.Map;

import exceptions.NoSuchOperationException;
import javafx.util.Pair;
import my_types.MyType;

public abstract class Operation {
	protected Map<Pair<String, String>, Calculable> operations;
	
	
	
	public Operation(Map<Pair<String, String>, Calculable> operations) {
		this.operations = operations;
	}



	public MyType execute(MyType firstType, MyType secondType) {
		Pair<String, String> currentPair = new Pair<>(firstType.getClass().getSimpleName(), secondType.getClass().getSimpleName());
		
		if(operations.containsKey(currentPair)) {
			return operations.get(currentPair).startCalculations(firstType, secondType);
		}
		else {
			throw new NoSuchOperationException("No such operation!");
		}
	}
}
