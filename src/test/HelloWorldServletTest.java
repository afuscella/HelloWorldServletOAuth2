package test;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sap.cloud.security.oauth2.OAuthAuthorization;
import com.sap.cloud.security.oauth2.OAuthSystemException;

import controller.HelloWorldServlet;

public class HelloWorldServletTest {

	private HelloWorldServlet helloServlet;

	@Mock
	HttpServletRequest req;

	@Mock
	HttpServletResponse res;

	@Mock
	OAuthAuthorization oauth;

	@Before
	public void before() {
		this.helloServlet = new HelloWorldServlet();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAuthorizedOk() throws ServletException, OAuthSystemException, IOException {
		when(oauth.isAuthorized(req)).thenReturn(true);

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(res.getWriter()).thenReturn(pw);

		//this.helloServlet.setOAuth(oauth);
		this.helloServlet.doGet(req, res);
	}

	@Test
	public void testAuthorizedFail() throws ServletException, IOException, OAuthSystemException {
		when(oauth.isAuthorized(req)).thenReturn(false);

		//this.helloServlet.setOAuth(oauth);
		this.helloServlet.doGet(req, res);
	}

	@Test
	public void testOauthRequestException() throws ServletException, IOException {
		this.helloServlet = new HelloWorldServlet();
		this.helloServlet.doGet(req, res);
	}
}
