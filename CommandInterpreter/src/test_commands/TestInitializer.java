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
import operations.Add;
import operations.AddNumbers;
import operations.Calculable;
import operations.Operation;
import operations.OperationFactory;
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
		
		
		
		Map<Character, Operation> possibleOperations = new HashMap<>();
		possibleOperations.put('+', new Add(addOperations));
		
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
