package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import general.Command;
import general.Executable;
import my_types.MyType;
import type_container.TypeContainer;

public class Save extends Command implements Executable {
	

	public Save(TypeContainer myTypeContainer) {
		super(myTypeContainer);
	}

	@Override
	public String execute()  {
		File nameOfFile = new File(expression[0]);
		if(!nameOfFile.exists()) {
			try {
				nameOfFile.createNewFile();
			} catch (IOException e) {
				System.err.println("Cannot create file!");
			}
		}
		OutputStream fileStream = null;
		try {
			 fileStream = new FileOutputStream(nameOfFile);
		} catch (FileNotFoundException e) {
			System.err.println("Cannot open file stream");
		}
		
		
		try {
			startSerialization(myTypeContainer,fileStream);
		} catch (IOException e) {
			System.err.println("Saving went wrong");
		}
		
		
		return "Saved successfuly";
	}

	private void startSerialization(TypeContainer myTypeContainer, OutputStream fileStream) throws IOException {
		
		Map<String, MyType> variables = myTypeContainer.getVariableContainer();
		for(Map.Entry<String, MyType> currentVariable : variables.entrySet()) {
			MyType currentMyType = myTypeContainer.getType(currentVariable.getKey().toString());
			String typeWithoutBrackets = currentMyType.getType().replaceAll("[\\[\\]]", "").trim();
			String toWrite = currentVariable.getKey().toString() + " " + typeWithoutBrackets + " " + currentVariable.getValue().toString() + "\r\n";
			fileStream.write(toWrite.getBytes());
		}
	}

}
