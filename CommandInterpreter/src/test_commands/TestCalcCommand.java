package test_commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestCalcCommand  extends TestInitializer{

	public void set(String toSet) {
		commandInterpreter.startInterpretation(toSet);
	}
	
	@Test
	public void testCalcNumbers() {
		set("set first number 23");
		set("set second number 12");
		commandInterpreter.startInterpretation("calc third first + second");
		assertEquals("[number] 35", commandInterpreter.startInterpretation("get third"));
	}
	
	@Test
	public void testCalcStringNumber() {
		set("set first string hello");
		set("set second number 12");
		commandInterpreter.startInterpretation("calc third first + second");
		assertEquals("[string] hello12", commandInterpreter.startInterpretation("get third"));
	}
	
	@Test
	public void testCalcStringString() {
		set("set first string Hello");
		set("set second string World");
		commandInterpreter.startInterpretation("calc third first + second");
		assertEquals("[string] HelloWorld", commandInterpreter.startInterpretation("get third"));
	}
}
