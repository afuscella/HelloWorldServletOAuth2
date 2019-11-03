package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sap.cloud.security.oauth2.OAuthAuthorization;
import com.sap.cloud.security.oauth2.OAuthSystemException;

import util.Constants;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/HelloWorldServlet")
public class HelloWorldServlet extends HttpServlet {

	public static final String HTML = "text/html";

	private static final long serialVersionUID = 1L;
	public OAuthAuthorization oauth;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloWorldServlet(OAuthAuthorization oauth) {
		super();
		this.oauth = oauth;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		Boolean isAuthorized = null;

		try {
			isAuthorized = this.oauth.isAuthorized(request);
		} catch (OAuthSystemException | NullPointerException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

		if (!isAuthorized) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return;
		}

		response.addHeader(Constants.HttpHeaders.CONTENT_TYPE, Constants.Text.TEXT_HTML);
		pw.println("Contact light");
	}
}
