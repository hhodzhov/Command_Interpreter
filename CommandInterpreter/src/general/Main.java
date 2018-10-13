package general;

import java.util.HashMap;
import java.util.Map;

import commands.CountWords;
import commands.Reverse;
import commands.ReverseWords;
import commands.SetVariables;
import my_types.MyNumber;
import my_types.MyString;
import my_types.MyType;
import type_container.TypeContainer;

public class Main {
	
	public static void main(String[] args) {
		
		
		Map<String, MyType> variableContainer = new HashMap<>();
		Map<String, MyType> availableTypes = new HashMap<>();
		availableTypes.put("string", new MyString());
		availableTypes.put("number", new MyNumber());
		
		TypeContainer myTypeContainer = new TypeContainer(variableContainer, availableTypes);
		
		
		
		Map<String, Command> commandsToInterpret = new HashMap<>();
		commandsToInterpret.put("count-words", new CountWords());
		commandsToInterpret.put("reverse", new Reverse());
		commandsToInterpret.put("reverse-words", new ReverseWords());
		commandsToInterpret.put("set", new SetVariables(myTypeContainer, availableTypes));
		
		
		CommandInterpreter commandInterpreter = new CommandInterpreter(commandsToInterpret);
		commandInterpreter.start();
		
	}
}
