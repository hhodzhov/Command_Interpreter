package commands;

import exceptions.VariableNotFoundException;
import general.Command;
import general.Executable;
import my_types.MyType;
import type_container.TypeContainer;

public class GetVariables extends Command implements Executable {

	public GetVariables(TypeContainer myTypeContainer) {
		super(myTypeContainer);
	}

	@Override
	public String execute() throws VariableNotFoundException {

		if (expression.length != 1) {
			throw new IllegalArgumentException("Wrong arguments!\n" + "1 argument expected: get <variableName>");
		}

		MyType myType = myTypeContainer.getType(expression[0]);

		return myType.getType() + " " + myType.getValue();
	}
}
