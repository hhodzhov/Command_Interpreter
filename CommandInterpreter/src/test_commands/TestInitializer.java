package test_commands;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;

import commands.CountWords;
import general.Command;
import general.CommandInterpreter;

public class TestInitializer {

	protected Map<String, Command> commandsToInterpret;
	protected CommandInterpreter commandInterpreter;
	protected Class cls;
	protected Method interpretationMethod;

	@Before
	public void initialize() throws NoSuchMethodException, SecurityException {
		commandsToInterpret = new HashMap<>();
		commandsToInterpret.put("count-words", new CountWords());
		commandInterpreter = new CommandInterpreter(commandsToInterpret);

		cls = commandInterpreter.getClass();
		interpretationMethod = cls.getDeclaredMethod("startInterpretation", String.class);
		interpretationMethod.setAccessible(true);
		
		
		
	}
}
