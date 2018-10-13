package test_commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.TypeNotFoundException;

public class TestSetVariables extends TestInitializer {

	@Test
	public void testWithSomeString() {
		assertEquals("Ok", commandInterpreter.startInterpretation("set name string Peter"));
	}

	@Test
	public void testWithSomeNumber() {
		assertEquals("Ok", commandInterpreter.startInterpretation("set digitOne number 1"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOnlyCommand() {
		commandInterpreter.startInterpretation("set");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithCommandAndFirstArgument() {
		commandInterpreter.startInterpretation("set first");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithCommandAndTwoArguments() {
		commandInterpreter.startInterpretation("set first string");
	}

	@Test(expected = TypeNotFoundException.class)
	public void testWithIncorrectVariableType() {
		commandInterpreter.startInterpretation("set first someType Hello");
	}

}
