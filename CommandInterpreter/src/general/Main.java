package general;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import commands.Calc;
import commands.CountWords;
import commands.GetVariables;
import commands.Reverse;
import commands.ReverseWords;
import commands.SetVariables;
import exceptions.CommandNotFoundException;
import exceptions.NoSuchOperationException;
import exceptions.TypeNotFoundException;
import exceptions.VariableNotFoundException;
import javafx.util.Pair;
import my_types.MyNumber;
import my_types.MyString;
import my_types.MyType;
import operations.Add;
import operations.AddNumbers;
import operations.Calculable;
import operations.Operation;
import operations.OperationFactory;
import type_container.TypeContainer;

public class Main {
	
	public static void startExecution(Scanner input, CommandInterpreter commandInterpreter) {
		
		while(input.hasNext()) {
			try {
				System.out.println(commandInterpreter.startInterpretation(input.nextLine()));

			}catch(CommandNotFoundException cmd) {
				
				System.err.println(cmd.getMessage());
			}
			catch(IllegalArgumentException illegalArgumentException) {
				
				System.err.println(illegalArgumentException.getMessage());
			}
			catch (TypeNotFoundException typeNotFoundException) {
				
				System.err.println(typeNotFoundException.getMessage());
			}
			catch(VariableNotFoundException variableNotFoundException) {
				
				System.err.println(variableNotFoundException.getMessage());
				
			}
			catch(NoSuchOperationException noSuchOperation) {
				System.err.println(noSuchOperation.getMessage());
			}
		}
	}
	
	public static void main(String[] args) {
		
		
		Map<String, MyType> variableContainer = new HashMap<>();
		Map<String, MyType> availableTypes = new HashMap<>();
		availableTypes.put("string", new MyString());
		availableTypes.put("number", new MyNumber());
		
		TypeContainer myTypeContainer = new TypeContainer(variableContainer, availableTypes);
		
		
		HashMap<Pair<String, String>, Calculable> addOperations = new HashMap<>();
		addOperations.put(new Pair<>(MyNumber.class.getSimpleName(), MyNumber.class.getSimpleName()), new AddNumbers());
		
		
		
		Map<Character, Operation> possibleOperations = new HashMap<>();
		possibleOperations.put('+', new Add(addOperations));
		
		OperationFactory operationFactory = new OperationFactory(possibleOperations);
		
		
		Map<String, Command> commandsToInterpret = new HashMap<>();
		commandsToInterpret.put("count-words", new CountWords());
		commandsToInterpret.put("reverse", new Reverse());
		commandsToInterpret.put("reverse-words", new ReverseWords());
		commandsToInterpret.put("set", new SetVariables(myTypeContainer, availableTypes));
		commandsToInterpret.put("get", new GetVariables(myTypeContainer));
		commandsToInterpret.put("calc", new Calc(myTypeContainer, operationFactory));
		
		
		CommandInterpreter commandInterpreter = new CommandInterpreter(commandsToInterpret);
		Scanner input = new Scanner(System.in);
		
		startExecution(input,commandInterpreter);
		
		
	}
}
