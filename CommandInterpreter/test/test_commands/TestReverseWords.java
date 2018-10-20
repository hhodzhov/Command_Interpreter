package test_commands;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestReverseWords extends TestInitializer {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testNormalSentence()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		assertEquals("dog lazy the over jumps fox brown quick the", interpretationMethod.invoke(commandInterpreter,
				"reverse-words the quick brown fox jumps over the lazy dog"));
		interpretationMethod.setAccessible(false);
	}

	@Test
	public void testEmptyString() {

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Argument expected! reverse-words <SomeString>");
		commandInterpreter.startInterpretation("reverse-words");
	}

	@Test
	public void testOnlyOneWord() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assertEquals("hello", interpretationMethod.invoke(commandInterpreter, "reverse-words hello"));
		interpretationMethod.setAccessible(false);
	}

	@Test
	public void testOneWordWithSpaceAfter()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assertEquals("hello", interpretationMethod.invoke(commandInterpreter, "reverse-words hello "));
		interpretationMethod.setAccessible(false);
	}

	@Test
	public void testOneWordWithSpaceBefore()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assertEquals("hello", interpretationMethod.invoke(commandInterpreter, "reverse-words  hello"));
		interpretationMethod.setAccessible(false);
	}
}
