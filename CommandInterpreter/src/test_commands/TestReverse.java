package test_commands;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class TestReverse extends TestInitializer {

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
	public void testEmptyString() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		assertEquals("", interpretationMethod.invoke(commandInterpreter, "reverse"));
		 interpretationMethod.setAccessible(false);
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
