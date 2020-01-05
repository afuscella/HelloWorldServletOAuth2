package br.com.helloworld.exception;

public class OAuthException extends HelloWorldException {

	public static final String NOT_AUTHORIZED = "Not authorized to access the resource";

	public OAuthException(String message) {
		super(message);
	}
}
