package commands;

import general.Command;
import general.Executable;

public class CountWords extends Command implements Executable {

	@Override
	public String execute() {

		if (expression.length == 0) {
			throw new IllegalArgumentException("Argument expected! count-words <string>");
		} else {
			return String.valueOf(expression.length);
		}
	}

}
