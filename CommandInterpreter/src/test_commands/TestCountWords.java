package test_commands;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import exceptions.CommandNotFoundException;


public class TestCountWords extends TestInitializer {

	@Test
	public void testNormalExpression()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		assertEquals(String.valueOf(5),
				interpretationMethod.invoke(commandInterpreter, "count-words hello this is word counter"));
		//interpretationMethod.setAccessible(false);
	}

	@Test
	public void testZeroWords() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assertEquals(String.valueOf(0), interpretationMethod.invoke(commandInterpreter, "count-words"));
	}

	@Test
	public void testZeroWordsSingleSpace()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assertEquals(String.valueOf(0), interpretationMethod.invoke(commandInterpreter, "count-words "));
	}
	
//	@Test(expected = CommandNotFoundException.class)
//	public void a() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		  interpretationMethod.invoke(commandInterpreter, "asdasd");
//	}

}
