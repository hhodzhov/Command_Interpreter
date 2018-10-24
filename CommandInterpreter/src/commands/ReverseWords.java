package commands;

import java.util.Arrays;

import general.Command;
import general.Executable;

public class ReverseWords extends Command implements Executable {

	@SuppressWarnings("static-access")
	@Override
	public String execute() {

		if (expression.length == 0) {
			throw new IllegalArgumentException("Argument expected! reverse-words <string>");
		}

		String[] wordsReversed = new String[expression.length];
		int index = 0;
		for (int i = expression.length - 1; i >= 0; i--) {
			wordsReversed[index++] = expression[i];
		}
		return Arrays.toString(wordsReversed).join(" ", wordsReversed);

	}

}
