package my_types;

public class MyNumber extends MyType {

	private int number;
	
	public MyNumber() {
		super();
	}

	public MyNumber(int number) {
		super(String.valueOf(number));
	}

//	public MyNumber(String value) {
//		super(value);
//	}

	@Override
	public void setValue(String value) {
		number = Integer.parseInt(value);

	}

	@Override
	public String getType() {
		return "[number]";
	}

	@Override
	public MyType clone() {
		return new MyNumber(number);
	}

	@Override
	public String getValue() {
		return String.valueOf(number);
	}

//	@Override
//	public String toString() {
//		return String.valueOf(number);
//	}

}
