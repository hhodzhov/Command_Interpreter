package my_types;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate extends MyType{
	
	private Date myDate;
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
	
	public MyDate() {
		super();
	}

	public MyDate(String date) {
		super(date);
	}
	

	public MyDate(Date myDate) {
		this.myDate = myDate;
	}
	
	public static Date parseGivenDate(String dateAsString) {
		Date date = null;
		try {
			date = simpleDateFormat.parse(dateAsString);
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}
		return date;
	}
	
	public static String format (Date date) {
		return simpleDateFormat.format(date);
	}
	
	
	@Override
	public void setValue(String value) {

		myDate = parseGivenDate(value);
	}

	@Override
	public String getType() {
		return "[date]";
	}

	@Override
	public MyType clone() {
		return new MyDate(myDate);
	}

	@Override
	public String getValue() {
		return format(myDate);
	}

	@Override
	public String toString() {
		return format(myDate);
	}
}
