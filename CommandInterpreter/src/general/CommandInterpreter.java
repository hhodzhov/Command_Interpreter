package general;

import java.util.Map;
import exceptions.CommandNotFoundException;

public class CommandInterpreter {

	private Map<String, Command> commandsToInterpret;

	public CommandInterpreter(Map<String, Command> commandsToInterpret) {
		this.commandsToInterpret = commandsToInterpret;
	}

	public String startInterpretation(String expression){
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
}
