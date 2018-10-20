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
import exceptions.OperationNotAllowedException;
import exceptions.TypeNotFoundException;
import exceptions.VariableNotFoundException;
import javafx.util.Pair;
import my_types.MyNumber;
import my_types.MyString;
import my_types.MyType;
import operations.Calculable;
import operations.Operation;
import operations.OperationFactory;
import operations.add_operations.Add;
import operations.add_operations.AddNumbers;
import operations.add_operations.AddStringNumber;
import operations.add_operations.AddStringString;
import operations.multiply_operations.Multiply;
import operations.multiply_operations.MultiplyNumbers;
import operations.multiply_operations.MultiplyStringNumber;
import operations.subtract_operations.Subtract;
import operations.subtract_operations.SubtractNumbers;
import operations.subtract_operations.SubtractStringString;
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
			catch(OperationNotAllowedException noSuchOperation) {
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
		addOperations.put(new Pair<>(MyString.class.getSimpleName(), MyNumber.class.getSimpleName()), new AddStringNumber());
		addOperations.put(new Pair<>(MyString.class.getSimpleName(), MyString.class.getSimpleName()), new AddStringString());
		
		
		HashMap<Pair<String, String>, Calculable> multiplyOperations = new HashMap<>();
		multiplyOperations.put(new Pair<>(MyNumber.class.getSimpleName(), MyNumber.class.getSimpleName()), new MultiplyNumbers());
		multiplyOperations.put(new Pair<>(MyString.class.getSimpleName(), MyNumber.class.getSimpleName()), new MultiplyStringNumber());
		
		HashMap<Pair<String, String>, Calculable> subtractOperations = new HashMap<>();
		subtractOperations.put(new Pair<>(MyNumber.class.getSimpleName(), MyNumber.class.getSimpleName()), new SubtractNumbers());
		subtractOperations.put(new Pair<>(MyString.class.getSimpleName(), MyString.class.getSimpleName()), new SubtractStringString());
		
		Map<Character, Operation> possibleOperations = new HashMap<>();
		possibleOperations.put('+', new Add(addOperations));
		possibleOperations.put('*', new Multiply(multiplyOperations));
		possibleOperations.put('-', new Subtract(subtractOperations));
		
		
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
