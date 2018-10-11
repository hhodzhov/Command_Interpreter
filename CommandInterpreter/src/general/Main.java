package general;

import java.util.HashMap;
import java.util.Map;

import commands.CountWords;
import commands.Reverse;

public class Main {
	
	public static void main(String[] args) {
		
		
		Map<String, Command> commandsToInterpret = new HashMap<>();
		commandsToInterpret.put("count-words", new CountWords());
		commandsToInterpret.put("reverse", new Reverse());
		
		
		CommandInterpreter commandInterpreter = new CommandInterpreter(commandsToInterpret);
		commandInterpreter.start();
		
	}
}
