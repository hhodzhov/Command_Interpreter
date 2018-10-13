package test_commands;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.mockito.Mockito.mock;
import org.mockito.Mockito;

import exceptions.CommandNotFoundException;
import general.CommandInterpreter;
import junit.framework.Assert;

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

	@Test(expected = CommandNotFoundException.class)
	public void testWrongCommand() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		commandInterpreter.startInterpretation("asdasd");

	}

}
