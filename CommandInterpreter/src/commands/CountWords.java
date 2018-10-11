package commands;

import general.Command;
import general.Executable;

public class CountWords extends Command implements Executable{

	@Override
	public String execute() {
		
		if(expression.length == 0) {
			return String.valueOf(0);
		}else {
			return String.valueOf(expression.length);
		}
	}

}
