package commands;

import general.Command;
import general.Executable;

public class Reverse extends Command implements Executable {

	@Override
	public String execute() {
		String expressionAsString = String.join(" ", expression);
		char[] expressionAsArray = expressionAsString.toCharArray();

		if (expressionAsArray.length == 0) {
			throw new IllegalArgumentException("Argument expected! reverse <string>");
		}

		String reversed = "";
		for (int i = expressionAsArray.length - 1; i >= 0; i--) {
			reversed = reversed + expressionAsArray[i];
		}
		return reversed;
	}

}
