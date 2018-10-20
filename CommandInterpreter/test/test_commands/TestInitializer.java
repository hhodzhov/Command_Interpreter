package test_commands;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;

import commands.Calc;
import commands.CountWords;
import commands.GetVariables;
import commands.Reverse;
import commands.ReverseWords;
import commands.SetVariables;
import general.Command;
import general.CommandInterpreter;
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

public class TestInitializer {

	protected Map<String, Command> commandsToInterpret;
	protected CommandInterpreter commandInterpreter;
	protected Class<? extends CommandInterpreter> cls;
	protected Method interpretationMethod;

	@Before
	public void initialize() throws NoSuchMethodException, SecurityException {

		Map<String, MyType> variableContainer = new HashMap<>();
		Map<String, MyType> availableTypes = new HashMap<>();
		availableTypes.put("string", new MyString());
		availableTypes.put("number", new MyNumber());

		TypeContainer myTypeContainer = new TypeContainer(variableContainer, availableTypes);

		HashMap<Pair<String, String>, Calculable> addOperations = new HashMap<>();
		addOperations.put(new Pair<>(MyNumber.class.getSimpleName(), MyNumber.class.getSimpleName()), new AddNumbers());
		addOperations.put(new Pair<>(MyString.class.getSimpleName(), MyNumber.class.getSimpleName()),
				new AddStringNumber());
		addOperations.put(new Pair<>(MyString.class.getSimpleName(), MyString.class.getSimpleName()),
				new AddStringString());

		HashMap<Pair<String, String>, Calculable> multiplyOperations = new HashMap<>();
		multiplyOperations.put(new Pair<>(MyNumber.class.getSimpleName(), MyNumber.class.getSimpleName()),
				new MultiplyNumbers());
		multiplyOperations.put(new Pair<>(MyString.class.getSimpleName(), MyNumber.class.getSimpleName()),
				new MultiplyStringNumber());

		HashMap<Pair<String, String>, Calculable> subtractOperations = new HashMap<>();
		subtractOperations.put(new Pair<>(MyNumber.class.getSimpleName(), MyNumber.class.getSimpleName()),
				new SubtractNumbers());
		subtractOperations.put(new Pair<>(MyString.class.getSimpleName(), MyString.class.getSimpleName()),
				new SubtractStringString());

		Map<Character, Operation> possibleOperations = new HashMap<>();
		possibleOperations.put('+', new Add(addOperations));
		possibleOperations.put('*', new Multiply(multiplyOperations));
		possibleOperations.put('-', new Subtract(subtractOperations));

		OperationFactory operationFactory = new OperationFactory(possibleOperations);

		commandsToInterpret = new HashMap<>();
		commandsToInterpret.put("count-words", new CountWords());
		commandsToInterpret.put("reverse", new Reverse());
		commandsToInterpret.put("reverse-words", new ReverseWords());
		commandsToInterpret.put("set", new SetVariables(myTypeContainer, availableTypes));
		commandsToInterpret.put("get", new GetVariables(myTypeContainer));
		commandsToInterpret.put("calc", new Calc(myTypeContainer, operationFactory));

		commandInterpreter = new CommandInterpreter(commandsToInterpret);

		cls = commandInterpreter.getClass();
		interpretationMethod = cls.getDeclaredMethod("startInterpretation", String.class);
		interpretationMethod.setAccessible(true);

	}
}
