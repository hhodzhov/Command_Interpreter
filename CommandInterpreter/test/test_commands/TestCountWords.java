package test_commands;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import exceptions.CommandNotFoundException;

public class TestCountWords extends TestInitializer {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testNormalExpression()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		assertEquals(String.valueOf(5),
				interpretationMethod.invoke(commandInterpreter, "count-words hello this is word counter"));
	}

	@Test
	public void testZeroWords() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Argument expected! count-words <string>");
		commandInterpreter.startInterpretation("count-words");
	}

	@Test
	public void testZeroWordsSingleSpace() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Argument expected! count-words <string>");
		commandInterpreter.startInterpretation("count-words ");

	}

	@Test
	public void testWordsWithInnerSpace()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assertEquals(String.valueOf(2), interpretationMethod.invoke(commandInterpreter, "count-words abv  jhg"));
	}

	@Test(expected = CommandNotFoundException.class)
	public void testWrongCommand() {
		commandInterpreter.startInterpretation("asdasd");
	}

}
