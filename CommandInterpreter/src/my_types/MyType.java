package my_types;

import java.util.Comparator;

public abstract class MyType{

	public MyType() {

	}
	MyType a = null;

	MyType(String value) {
		setValue(value);
	}

	public abstract void setValue(String value);

	public abstract String getType();

	public abstract MyType clone();

	public abstract String getValue();
	
}
