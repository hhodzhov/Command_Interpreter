package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import exceptions.TypeNotFoundException;
import general.Command;
import general.Executable;
import my_types.MyType;
import type_container.TypeContainer;

public class Loader extends Command implements Executable {

	public Loader(TypeContainer myTypeContainer, Map<String, MyType> availableTypes) {
		super(myTypeContainer, availableTypes);
	}

	@Override
	public String execute() {

		File fileToLoad = new File(expression[0]);
		Scanner reader = null;
		try {
			reader = new Scanner(fileToLoad);
		} catch (FileNotFoundException e) {
			System.err.println("Error occured!");
		}

		try {
			startDeserialization(reader);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new IllegalArgumentException(illegalArgumentException.getMessage());

		} catch (TypeNotFoundException typeNotFound) {
			throw new TypeNotFoundException(typeNotFound.getMessage());
		}

		return "Loading completed successfuly!";
	}

	private void startDeserialization(Scanner reader) {

		while (reader.hasNextLine()) {
			String currentLine = reader.nextLine();
			try {
			setNewVariable(currentLine);
			}catch(IllegalArgumentException illegalArguments){
				System.err.println(illegalArguments.getMessage());
			}catch(TypeNotFoundException typeNotFound) {
				System.err.println(typeNotFound.getMessage());
			}
		}
	}

	private void setNewVariable(String currentLine) {

		String[] variables = currentLine.split(" ");

		if (variables.length != 3) {
			throw new IllegalArgumentException("Wrong arguments! 3 arguments expected: <variableName> <type> <value>");
		}
		if (!availableTypes.containsKey(variables[1])) {
			throw new TypeNotFoundException("Line '" + variables[0] + " " + variables[1] + " " + variables[2]
					+ "' from the file " + " cannot be loaded because type '" + variables[1] + "' is not found");
		} else {
			myTypeContainer.addVariable(variables[0], variables[1], variables[2]);
		}
	}

}
