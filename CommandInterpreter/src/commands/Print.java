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
		
		StringBuilder allInformation = new StringBuilder();
		String columns = "Variable name\t\tType\t\t\tValue\n\n";
		allInformation.append(columns);
		
		
		Map<String, MyType> allVariables = myTypeContainer.getVariableContainer();
		
		for(Map.Entry<String, MyType> currentVariable : allVariables.entrySet()) {
			if(currentVariable.getValue() instanceof MyNumber) {
				allInformation.append(currentVariable.getKey() + "\t\t"
			+ currentVariable.getValue().getType() + "\t\t" + currentVariable.getValue().toString() + "\n");
			}
		}
		for(Map.Entry<String, MyType> currentVariable : allVariables.entrySet()) {
			if(currentVariable.getValue() instanceof MyString) {
				allInformation.append(currentVariable.getKey() + "\t\t"
			+ currentVariable.getValue().getType() + "\t\t" + currentVariable.getValue().toString() + "\n");
			}
		}
		
		for(Map.Entry<String, MyType> currentVariable : allVariables.entrySet()) {
			if(currentVariable.getValue() instanceof MyDate) {
				allInformation.append(currentVariable.getKey() + "\t\t"
			+ currentVariable.getValue().getType() + "\t\t\t" + currentVariable.getValue().toString() + "\n");
			}
		}
		
		return allInformation.toString();
	}

}
