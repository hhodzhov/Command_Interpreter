package operations;

import java.util.Map;

import exceptions.OperationNotAllowedException;
import my_types.MyType;

public class OperationFactory {
	private Map<Character, Operation> possibleOperations;

	public OperationFactory(Map<Character, Operation> possibleOperations) throws OperationNotAllowedException {
		this.possibleOperations = possibleOperations;
	}

	public MyType exec(char operation, MyType firstType, MyType secondType) {
		if(possibleOperations.containsKey(operation)) {
			return possibleOperations.get(operation).execute(firstType, secondType);
		}else {
			throw new OperationNotAllowedException("No such operation possible!");
		}
	}
	
}
