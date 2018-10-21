package commands;

import general.Command;
import general.Executable;
import my_types.MyType;
import operations.OperationFactory;
import type_container.TypeContainer;

public class Calc extends Command implements Executable {

	public Calc(TypeContainer myTypeContainer, OperationFactory operationFactory) {
		super(myTypeContainer, operationFactory);
	}

	@Override
	public String execute() {

		if (expression.length != 4) {
			throw new IllegalArgumentException(
					"Wrong arguments!\n" + "4 arguments expected: calc <newVarName> <varName1> <operation> <varName2>");
		}

		char operation = expression[2].charAt(0);
		MyType newVariable = operationFactory.exec(operation, myTypeContainer.getType(expression[1]),
				myTypeContainer.getType(expression[3]));
		myTypeContainer.addMyType(expression[0], newVariable);

		return "Ok";
	}

}
