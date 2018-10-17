package commands;

import general.Command;
import general.Executable;
import operations.OperationFactory;
import type_container.TypeContainer;

public class Calc extends Command implements Executable {

	public Calc(TypeContainer myTypeContainer, OperationFactory operationFactory) {
		super(myTypeContainer, operationFactory);
	}

	@Override
	public String execute() {

		if (expression.length != 4) {
			throw new IllegalArgumentException("Wrong arguments!!!");
		}

		char operation = expression[2].charAt(0);
		myTypeContainer.addMyType(expression[0], operationFactory.exec(operation,
				myTypeContainer.getType(expression[1]), myTypeContainer.getType(expression[3])));
		
		return "Ok";
	}

}
