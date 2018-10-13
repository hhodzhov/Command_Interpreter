package my_types;

public abstract class MyType {
	
	public MyType() {
		
	}
	MyType(String value){
		setValue(value);
	}
	public abstract void setValue(String value);
	public abstract String getType();
	public abstract MyType clone();
	public abstract String getValue();
}
