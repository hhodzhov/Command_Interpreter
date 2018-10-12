package general;

import java.util.Arrays;

public abstract class Command implements Executable {

	protected String[] expression;

	public Command() {

	}

	protected String interpretCommand(String command) {
		expression = expressionWithoutCommand(command);
		return execute();
	}

	private String[] expressionWithoutCommand(String expr) {
		String[] current = expr.split(" ");
		String[] toReturn = new String[current.length - 1];
		for (int i = 1; i < current.length; i++) {
			toReturn[i - 1] = current[i];
		}
		return toReturn;
	}

}
