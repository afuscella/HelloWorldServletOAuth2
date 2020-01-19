package br.com.helloworld.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.helloworld.exception.HelloWorldException;
import br.com.helloworld.exception.OAuthException;
import br.com.helloworld.service.HelloWorldService;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/HelloWorldServlet")
public class HelloWorldServlet extends HttpServlet {

	private HelloWorldService helloWorldService;

	public HelloWorldServlet(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloWorldServlet() {
		this.helloWorldService = new HelloWorldService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse res)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			helloWorldService.handleGetRequest(request, response);
			response.setStatus(HttpServletResponse.SC_OK);
		}
		catch (OAuthException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
		catch (HelloWorldException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
