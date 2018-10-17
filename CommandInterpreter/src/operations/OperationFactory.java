package operations;

import java.util.Map;

import exceptions.NoSuchOperationException;
import my_types.MyType;

public class OperationFactory {
	private Map<Character, Operation> possibleOperations;

	public OperationFactory(Map<Character, Operation> possibleOperations) throws NoSuchOperationException {
		this.possibleOperations = possibleOperations;
	}

	public MyType exec(char operation, MyType firstType, MyType secondType) {
		if(possibleOperations.containsKey(operation)) {
			return possibleOperations.get(operation).execute(firstType, secondType);
		}else {
			throw new NoSuchOperationException("There is no such operation");
		}
	}
	
}
