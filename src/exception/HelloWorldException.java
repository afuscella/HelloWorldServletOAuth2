package exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class HelloWorldException extends Exception{

	protected Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	public HelloWorldException (String message) {
		super(message);
	}
	
	public HelloWorldException(String message, Throwable cause) {
		super(message, cause);
	}
}
