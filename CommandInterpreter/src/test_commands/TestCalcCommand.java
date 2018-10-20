package test_commands;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import exceptions.OperationNotAllowedException;

public class TestCalcCommand extends TestInitializer {

	public void set(String toSet) {
		commandInterpreter.startInterpretation(toSet);
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

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

	@Test
	public void testMultiplyNumbers() {
		set("set first number 3");
		set("set second number 12");
		commandInterpreter.startInterpretation("calc third first * second");
		assertEquals("[number] 36", commandInterpreter.startInterpretation("get third"));
	}

	@Test
	public void testMultiplyStringNumber() {
		set("set first string hello");
		set("set second number 6");
		commandInterpreter.startInterpretation("calc third first * second");
		assertEquals("[string] hellohellohellohellohellohello", commandInterpreter.startInterpretation("get third"));
	}

	@Test
	public void testSubtractNumbers() {
		set("set first number 23");
		set("set second number 12");
		commandInterpreter.startInterpretation("calc third first - second");
		assertEquals("[number] 11", commandInterpreter.startInterpretation("get third"));
	}

	@Test
	public void testSubtracStringsWithDifferentLengthsSecondGreaterThanFirst() {
		set("set first string HelloIamPeter");
		set("set second string HelloIamPeterrr");
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("First variable's length must be greater than second's");
		commandInterpreter.startInterpretation("calc third first - second");
	}
	@Test
	public void testSubtractStringsWithEqualLengths() {
		set("set first string HelloIamPeter");
		set("set second string HelloIamPeter");
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("First variable's length must be greater than second's");
		commandInterpreter.startInterpretation("calc third first - second");	
	}
	@Test
	public void testSubtractStringsFirstNotContainsSecond() {
		set("set first string HelloIamPeter");
		set("set second string hi");
		expectedException.expect(OperationNotAllowedException.class);
		expectedException.expectMessage("First variable does not contain second. Operation cannot be done!");
		commandInterpreter.startInterpretation("calc third first - second");	
	}
	@Test
	public void testSubtractStringsFromTheBegginingOfTheFirst() {
		set("set first string HelloIamPeter");
		set("set second string Hello");
	    assertEquals("Ok",commandInterpreter.startInterpretation("calc third first - second"));
	    assertEquals("[string] IamPeter", commandInterpreter.startInterpretation("get third"));
	}
	
	@Test
	public void testSubtractStringsFromTheMiddleOfTheFirst() {
		set("set first string HelloIamPeter");
		set("set second string oIamP");
	    assertEquals("Ok",commandInterpreter.startInterpretation("calc third first - second"));
	    assertEquals("[string] Helleter", commandInterpreter.startInterpretation("get third"));
	}
	
	@Test
	public void testSubtractStringsFromTheEndOfTheFirst() {
		set("set first string HelloIamPeter");
		set("set second string eter");
	    assertEquals("Ok",commandInterpreter.startInterpretation("calc third first - second"));
	    assertEquals("[string] HelloIamP", commandInterpreter.startInterpretation("get third"));
	}
	
	@Test
	public void testSubtractStringsMoreThanOneOccurence() {
		set("set first string HelloHelloMyNameIsPeter");
		set("set second string Hello");
	    assertEquals("Ok",commandInterpreter.startInterpretation("calc third first - second"));
	    assertEquals("[string] MyNameIsPeter", commandInterpreter.startInterpretation("get third"));
	}

}
