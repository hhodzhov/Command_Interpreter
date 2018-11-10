package operations.add_operations;

import java.util.Map;

import javafx.util.Pair;
import operations.Calculable;
import operations.Operation;

public class Add extends Operation {

	public Add(Map<Pair<String, String>, Calculable> operations) {
		super(operations);
	}
}
