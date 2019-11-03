package controller;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;

import com.sap.cloud.security.oauth2.OAuthAuthorization;
import com.sap.cloud.security.oauth2.OAuthSystemException;

import util.Constants;
import util.Constants.HttpHeaders;
import util.Constants.MimeType;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/HelloWorldServlet")
public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private HttpHeaders headers = new Constants.HttpHeaders();
	private MimeType mime_type = new Constants.MimeType();

	public OAuthAuthorization oauth;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloWorldServlet() {
		super();
		this.oauth = OAuthAuthorization.getOAuthAuthorizationService();
	}

	public OAuthAuthorization getOAuth() {
		return this.oauth;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse res)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		PrintWriter pw = res.getWriter();
		Boolean isAuthorized = null;

		try {
			isAuthorized = this.getOAuth().isAuthorized(req);
		} catch (OAuthSystemException | NullPointerException e) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

		if (!isAuthorized) {
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return;
		}

		res.addHeader(headers.CONTENT_TYPE, mime_type.TEXT_HTML);
		pw.println("Contact light");
	}
}
