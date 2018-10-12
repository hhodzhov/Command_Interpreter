package test_commands;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class TestReverse extends TestInitializer {

	@Test
	public void testNormalSentence()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		assertEquals("arbadac arba", interpretationMethod.invoke(commandInterpreter, "reverse abra cadabra"));
	}

	@Test
	public void testOneWord() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		assertEquals("aktok", interpretationMethod.invoke(commandInterpreter, "reverse kotka"));
	}

	@Test
	public void testEmptyString() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		assertEquals("", interpretationMethod.invoke(commandInterpreter, "reverse"));
	}

	@Test
	public void testOneWordWithSpaceAfter()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assertEquals("aktok", interpretationMethod.invoke(commandInterpreter, "reverse kotka "));
	}

	@Test
	public void testOneWordWithSpaceBefore()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assertEquals("aktok ", interpretationMethod.invoke(commandInterpreter, "reverse  kotka"));
	}

}
