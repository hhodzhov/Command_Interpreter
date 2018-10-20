package test_commands;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestReverse extends TestInitializer {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testNormalSentence()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		assertEquals("arbadac arba", interpretationMethod.invoke(commandInterpreter, "reverse abra cadabra"));
		interpretationMethod.setAccessible(false);
	}

	@Test
	public void testOneWord() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		assertEquals("aktok", interpretationMethod.invoke(commandInterpreter, "reverse kotka"));
		interpretationMethod.setAccessible(false);
	}

	@Test
	public void testEmptyString() {

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Argument expected! reverse <SomeString>");
		commandInterpreter.startInterpretation("reverse");
	}

	@Test
	public void testOneWordWithSpaceAfter()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assertEquals("aktok", interpretationMethod.invoke(commandInterpreter, "reverse kotka "));
		interpretationMethod.setAccessible(false);
	}

	@Test
	public void testOneWordWithSpaceBefore()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assertEquals("aktok", interpretationMethod.invoke(commandInterpreter, "reverse  kotka"));
		interpretationMethod.setAccessible(false);
	}

}
