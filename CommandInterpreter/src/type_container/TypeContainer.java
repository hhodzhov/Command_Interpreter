package type_container;

import java.util.Map;

import exceptions.TypeNotFoundException;
import exceptions.VariableNotFoundException;
import my_types.MyType;

public class TypeContainer {
	private Map<String, MyType> variableContainer;
	private Map<String, MyType> availableTypes;

	public TypeContainer(Map<String, MyType> variableContainer, Map<String, MyType> availableTypes) {
		this.variableContainer = variableContainer;
		this.availableTypes = availableTypes;
	}

	private MyType initializeType(String typeOfVariable, String valueOfVariable) throws TypeNotFoundException {
		if (availableTypes.containsKey(typeOfVariable)) {
			MyType toInitialize = availableTypes.get(typeOfVariable).clone();
			toInitialize.setValue(valueOfVariable);
			return toInitialize;
		} else {
			throw new TypeNotFoundException("There is no such type!");
		}
	}

	public void addVariable(String nameOfVariable, String typeOfVariable, String valueOfVariable) {
		try {
			MyType myType = initializeType(typeOfVariable, valueOfVariable);
			addMyType(nameOfVariable, myType);

		} catch (TypeNotFoundException typeNotFound) {
			System.err.println("No such type");
		}
	}

	public void addMyType(String nameOfVariable, MyType myType) {
		variableContainer.put(nameOfVariable, myType);
	}

	public MyType getType(String nameOfVariable) {
		if (!variableContainer.containsKey(nameOfVariable)) {
			throw new VariableNotFoundException("Variable not found!");
		} else {
			return variableContainer.get(nameOfVariable);
		}
	}

}
