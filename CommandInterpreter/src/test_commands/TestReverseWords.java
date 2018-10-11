package test_commands;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class TestReverseWords extends TestInitializer{
	
	@Test
	public void testNormalSentence()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		assertEquals("dog lazy the over jumps fox brown quick the",
				interpretationMethod.invoke(commandInterpreter,
						"reverse-words the quick brown fox jumps over the lazy dog"));
	}
	@Test
	public void testEmptyString()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		assertEquals("",
				interpretationMethod.invoke(commandInterpreter, "reverse-words"));
	}

}
