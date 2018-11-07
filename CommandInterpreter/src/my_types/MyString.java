package my_types;

public class MyString extends MyType {

	private String myString;

	public MyString() {
		super();
	}

	public MyString(String value) {
		super(value);
	}

	@Override
	public void setValue(String value) {
		this.myString = value;
	}

	@Override
	public String getType() {
		return "[string]";
	}

	@Override
	public MyType clone() {
		return new MyString(myString);
	}

	@Override
	public String getValue() {
		return this.myString;
	}

	@Override
	public String toString() {
		return myString;
	}
	
	

}
