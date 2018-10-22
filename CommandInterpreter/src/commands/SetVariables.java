package commands;

import java.util.Map;

import exceptions.TypeNotFoundException;
import general.Command;
import general.Executable;
import my_types.MyType;
import type_container.TypeContainer;

public class SetVariables extends Command implements Executable {

	public SetVariables(TypeContainer myTypeContainer, Map<String, MyType> availableTypes) {
		super(myTypeContainer, availableTypes);
	}

	@Override
	public String execute() {

		if (expression.length != 3) {
			throw new IllegalArgumentException("Wrong arguments! 3 arguments expected: set <variableName> <type> <value>");
		}
		if (!availableTypes.containsKey(expression[1])) {
			throw new TypeNotFoundException("Type not found!");
		} else {
			myTypeContainer.addVariable(expression[0], expression[1], expression[2]);
		}

		return "Ok";
	}

}
