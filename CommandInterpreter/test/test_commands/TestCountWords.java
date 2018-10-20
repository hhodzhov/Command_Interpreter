package test_commands;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import org.junit.Test;
import exceptions.CommandNotFoundException;

public class TestCountWords extends TestInitializer {

	@Test
	public void testNormalExpression()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		assertEquals(String.valueOf(5),
				interpretationMethod.invoke(commandInterpreter, "count-words hello this is word counter"));
		interpretationMethod.setAccessible(false);
	}

	@Test
	public void testZeroWords() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assertEquals(String.valueOf(0), interpretationMethod.invoke(commandInterpreter, "count-words"));
		interpretationMethod.setAccessible(false);
	}

	@Test
	public void testZeroWordsSingleSpace()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assertEquals(String.valueOf(0), interpretationMethod.invoke(commandInterpreter, "count-words "));
		interpretationMethod.setAccessible(false);
	}

	@Test
	public void testWordsWithInnerSpace()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assertEquals(String.valueOf(2), interpretationMethod.invoke(commandInterpreter, "count-words abv  jhg"));
		interpretationMethod.setAccessible(false);
	}

	@Test(expected = CommandNotFoundException.class)
	public void testWrongCommand() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		commandInterpreter.startInterpretation("asdasd");

	}

}
