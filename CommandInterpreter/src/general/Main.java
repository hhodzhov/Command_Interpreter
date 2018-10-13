package general;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import commands.CountWords;
import commands.GetVariables;
import commands.Reverse;
import commands.ReverseWords;
import commands.SetVariables;
import exceptions.CommandNotFoundException;
import exceptions.TypeNotFoundException;
import exceptions.VariableNotFoundException;
import my_types.MyNumber;
import my_types.MyString;
import my_types.MyType;
import type_container.TypeContainer;

public class Main {
	
	public static void startExecution(Scanner input, CommandInterpreter commandInterpreter) {
		
		while(input.hasNext()) {
			try {
				System.out.println(commandInterpreter.startInterpretation(input.nextLine()));

			}catch(CommandNotFoundException cmd) {
				System.err.println("No such command");
			}
			catch(IllegalArgumentException illegalArgumentException) {
				System.err.println("Incorrect arguments");
			}
			catch (TypeNotFoundException typeNotFoundException) {
				System.err.println("Illegal variable type");
			}
			catch(VariableNotFoundException variableNotFoundException) {
				System.err.println("No such variable");
			}
		}
	}
	
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
		commandsToInterpret.put("get", new GetVariables(myTypeContainer));
		
		
		CommandInterpreter commandInterpreter = new CommandInterpreter(commandsToInterpret);
		Scanner input = new Scanner(System.in);
		
		startExecution(input,commandInterpreter);
		
		
	}
}
