package commands;

import java.util.Map;

import general.Command;
import general.Executable;
import type_container.TypeContainer;
import my_types.*;

public class Print extends Command implements Executable {

	public Print(TypeContainer myTypeContainer) {
		super(myTypeContainer);
	}

	@Override
	public String execute() {
		
		if(expression.length != 0) {
			throw new IllegalArgumentException("No arguments required for this command!");
		}

		StringBuilder allInformation = new StringBuilder();
		String columns = "Variable name\t\tType\t\t\tValue\n\n";
		allInformation.append(columns);

		Map<String, MyType> allVariables = myTypeContainer.getVariableContainer();

		for (Map.Entry<String, MyType> currentVariable : allVariables.entrySet()) {
			String currentVariableInfo;
			if (currentVariable.getValue() instanceof MyDate) {
				currentVariableInfo = currentVariable.getKey() + "\t\t" + currentVariable.getValue().getType() + "\t\t\t"
						+ currentVariable.getValue().toString() + "\n";
				allInformation.append(currentVariableInfo);
				allInformation.append(new String(new char[60]).replace('\0', '-')).append("\n");
				
			} else {
				allInformation.append(currentVariable.getKey() + "\t\t" + currentVariable.getValue().getType() + "\t\t"
						+ currentVariable.getValue().toString() + "\n");
				allInformation.append(new String(new char[60]).replace('\0', '-')).append("\n");
			}
		}
		return allInformation.toString();
	}
}
