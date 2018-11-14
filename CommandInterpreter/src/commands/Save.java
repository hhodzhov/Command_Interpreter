package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		
		if(expression.length != 1) {
			throw new IllegalArgumentException("Wrong arguments! One argument expected : save <file name>");
		}
		
		File nameOfFile = new File(expression[0]);
		if(!nameOfFile.exists()) {
			try {
				nameOfFile.createNewFile();
			} catch (IOException e) {
				Logger.getLogger(Save.class.getName()).log(Level.WARNING, "Cannot create file!", e.getMessage());
			}
		}
		OutputStream fileStream = null;
		try {
			 fileStream = new FileOutputStream(nameOfFile);
		} catch (FileNotFoundException e) {
			Logger.getLogger(Loader.class.getName()).log(Level.WARNING, "Cannot open file stream!", e.getMessage());
		}
		
		try {
			startSerialization(myTypeContainer,fileStream);
		} catch (IOException e) {
			Logger.getLogger(Loader.class.getName()).log(Level.WARNING, "Saving went wrong", e.getMessage());
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
