package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.OAuthException;
import service.HelloWorldService;

/**
 * Servlet implementation class HelloWorldServlet
 */
@SuppressWarnings("serial")
@WebServlet("/HelloWorldServlet")
public class HelloWorldServlet extends HttpServlet {

	private HelloWorldService helloWorldService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloWorldServlet() {
		super();
		this.helloWorldService = new HelloWorldService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse res)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		try {
			helloWorldService.handleGetRequest(request, response);
			response.setStatus(HttpServletResponse.SC_OK);

		} catch (OAuthException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
