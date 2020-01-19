package br.com.helloworld.service;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.helloworld.config.OAuth2Configuration;
import br.com.helloworld.exception.HelloWorldException;

public class HelloWorldService {

	private OAuth2Configuration oAuth2Configuration;

	public HelloWorldService() {
		this.oAuth2Configuration = new OAuth2Configuration();
	}

	public void handleGetRequest(HttpServletRequest request, HttpServletResponse response) throws HelloWorldException {
		this.oAuth2Configuration.checkAuthorization(request);

		PrintWriter pw;
		try {
			pw = response.getWriter();
		}
		catch (IOException e) {
			throw new HelloWorldException();
		}
		pw.println("Contact light");
	}
}