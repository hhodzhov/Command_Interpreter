package general;

import java.util.Map;
import my_types.MyType;
import operations.OperationFactory;
import type_container.TypeContainer;

public abstract class Command implements Executable {

	protected String[] expression;
	protected TypeContainer myTypeContainer;
	protected Map<String, MyType> availableTypes;
	protected OperationFactory operationFactory;

	public Command(TypeContainer myTypeContainer, Map<String, MyType> availableTypes) {
		this.myTypeContainer = myTypeContainer;
		this.availableTypes = availableTypes;
	}

	public Command(TypeContainer myTypeContainer, OperationFactory operationFactory) {
		this.myTypeContainer = myTypeContainer;
		this.operationFactory = operationFactory;
	}

	public Command(TypeContainer myTypeContainer) {
		this.myTypeContainer = myTypeContainer;
	}

	public Command() {

	}

	protected String interpretCommand(String command) {
		expression = expressionWithoutCommand(command);
		return execute();
	}

	private String[] expressionWithoutCommand(String expr) {
		String[] current = expr.split("\\s+");
		String[] toReturn = new String[current.length - 1];
		for (int i = 1; i < current.length; i++) {
			toReturn[i - 1] = current[i];
		}
		return toReturn;
	}

}
