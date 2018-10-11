package commands;

import java.util.Arrays;

import general.Command;
import general.Executable;

public class ReverseWords extends Command implements Executable{

	@Override
	public String execute() {
		String[] wordsReversed = new String[expression.length];
        int index = 0;
        String result = "";
        for (int i = expression.length - 1; i >= 0; i--) {
            wordsReversed[index++] = expression[i];
        }
        return Arrays.toString(wordsReversed).join(" ", wordsReversed);

	}

}
