package exceptions;

public class CommandNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommandNotFoundException(String arg0) {
		super(arg0);
	}
}
