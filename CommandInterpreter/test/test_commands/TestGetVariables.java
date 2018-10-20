package test_commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.VariableNotFoundException;

public class TestGetVariables extends TestInitializer {

	public void set(String toSet) {
		commandInterpreter.startInterpretation(toSet);
	}

	@Test
	public void testNormalStringVariable() {
		set("set a string Hello");
		assertEquals("[string] Hello", commandInterpreter.startInterpretation("get a"));
	}

	@Test
	public void testNormalNumberVariable() {
		set("set a number 35");
		assertEquals("[number] 35", commandInterpreter.startInterpretation("get a"));
	}

	@Test
	public void testOverrideVariableFromSameType() {
		set("set a number 35");
		set("set a number 56");
		assertEquals("[number] 56", commandInterpreter.startInterpretation("get a"));
	}

	@Test
	public void testOverrideVariableFromDifferentTypes() {
		set("set a number 35");
		set("set a string hi");
		assertEquals("[string] hi", commandInterpreter.startInterpretation("get a"));
	}

	@Test(expected = VariableNotFoundException.class)
	public void testGettingUndeclaredVariable() {
		set("set a number 35");
		commandInterpreter.startInterpretation("get b");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIncorrectArgumentsOnlyCommand() {
		commandInterpreter.startInterpretation("get");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIncorrectArgumentsWithMoreArguments() {
		commandInterpreter.startInterpretation("get var string");
	}
}
