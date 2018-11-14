package general;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javafx.util.Pair;
import type_container.TypeContainer;
import operations.*;
import operations.add_operations.*;
import operations.multiply_operations.*;
import operations.subtract_operations.*;
import my_types.*;
import commands.*;
import exceptions.*;

public class Main {

	public static void startExecution(Scanner input, CommandInterpreter commandInterpreter) {

		while (input.hasNext()) {
			try {
				System.out.println(commandInterpreter.startInterpretation(input.nextLine()));

			} catch (CommandNotFoundException cmd) {

				System.err.println(cmd.getMessage());
			} catch (IllegalArgumentException illegalArgumentException) {

				System.err.println(illegalArgumentException.getMessage());
			} catch (TypeNotFoundException typeNotFoundException) {

				System.err.println(typeNotFoundException.getMessage());
			} catch (VariableNotFoundException variableNotFoundException) {

				System.err.println(variableNotFoundException.getMessage());

			} catch (OperationNotAllowedException noSuchOperation) {
				System.err.println(noSuchOperation.getMessage());
				
			} catch (NullPointerException ex) {
				System.err.println(ex.getMessage());
				
			}
		}
	}

	public static void main(String[] args) {

		Map<String, MyType> variableContainer = new HashMap<>();
		Map<String, MyType> availableTypes = new HashMap<>();
		availableTypes.put("string", new MyString());
		availableTypes.put("number", new MyNumber());
		availableTypes.put("date", new MyDate());

		TypeContainer myTypeContainer = new TypeContainer(variableContainer, availableTypes);

		HashMap<Pair<String, String>, Calculable> addOperations = new HashMap<>();
		addOperations.put(new Pair<>(MyNumber.class.getSimpleName(), MyNumber.class.getSimpleName()), new AddNumbers());
		addOperations.put(new Pair<>(MyString.class.getSimpleName(), MyNumber.class.getSimpleName()), new AddStringNumber());
		addOperations.put(new Pair<>(MyString.class.getSimpleName(), MyString.class.getSimpleName()), new AddStringString());
		addOperations.put(new Pair<>(MyDate.class.getSimpleName(), MyNumber.class.getSimpleName()), new AddDateNumber());

		HashMap<Pair<String, String>, Calculable> multiplyOperations = new HashMap<>();
		multiplyOperations.put(new Pair<>(MyNumber.class.getSimpleName(), MyNumber.class.getSimpleName()), new MultiplyNumbers());
		multiplyOperations.put(new Pair<>(MyString.class.getSimpleName(), MyNumber.class.getSimpleName()), new MultiplyStringNumber());

		HashMap<Pair<String, String>, Calculable> subtractOperations = new HashMap<>();
		subtractOperations.put(new Pair<>(MyNumber.class.getSimpleName(), MyNumber.class.getSimpleName()), new SubtractNumbers());
		subtractOperations.put(new Pair<>(MyString.class.getSimpleName(), MyString.class.getSimpleName()), new SubtractStringString());
		subtractOperations.put(new Pair<>(MyDate.class.getSimpleName(), MyDate.class.getSimpleName()), new DiffBetweenTwoDates());

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
		commandsToInterpret.put("print-all", new Print(myTypeContainer));
		commandsToInterpret.put("save", new Save(myTypeContainer));
		commandsToInterpret.put("load", new Loader(myTypeContainer, availableTypes));
		
		
		CommandInterpreter commandInterpreter = new CommandInterpreter(commandsToInterpret);
		Scanner input = new Scanner(System.in);
		
		 startExecution(input,commandInterpreter);

//		try {
//			commandInterpreter.startServer();
//		} catch (IOException e) {
//			System.err.println(e.getMessage());
//		}
	}
}
