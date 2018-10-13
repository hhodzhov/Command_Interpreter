package general;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;


import exceptions.CommandNotFoundException;
import exceptions.TypeNotFoundException;
import exceptions.VariableNotFoundException;

public class CommandInterpreter {

	private Map<String, Command> commandsToInterpret;

	public CommandInterpreter(Map<String, Command> commandsToInterpret) {
		this.commandsToInterpret = commandsToInterpret;
	}

	private String startInterpretation(String expression) throws CommandNotFoundException{
		expression = expression.trim();
		String command;
		if(expression.indexOf(' ') == -1) {
			command = expression;
		}else {
			command = expression.substring(0, expression.indexOf(' '));
		}
		Command typeOfCommand;

		if (commandsToInterpret.containsKey(command)) {
			typeOfCommand = commandsToInterpret.get(command);
		}
		else {
			throw new CommandNotFoundException("There is no such command");
		}

		return typeOfCommand.interpretCommand(expression);
	}
	
	public void start() {
		Scanner input = new Scanner(System.in);
		
		while(input.hasNext()) {
			try {
				System.out.println(startInterpretation(input.nextLine()));

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
}
