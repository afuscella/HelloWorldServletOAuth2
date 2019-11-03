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

	HelloWorldServlet helloServlet;

	@Mock
	HttpServletRequest request;

	@Mock
	HttpServletResponse response;

	@Mock
	OAuthAuthorization oauth;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAuthorizedOk() throws ServletException, OAuthSystemException, IOException {
		when(oauth.isAuthorized(request)).thenReturn(true);

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(response.getWriter()).thenReturn(pw);

		helloServlet = new HelloWorldServlet(oauth);
		helloServlet.doGet(request, response);
	}

	@Test
	public void testAuthorizedFail() throws ServletException, IOException {
		helloServlet = new HelloWorldServlet(oauth);
		helloServlet.doGet(request, response);
	}

	@Test
	public void testOauthRequestException() throws ServletException, IOException {
		oauth = null;

		helloServlet = new HelloWorldServlet(oauth);
		helloServlet.doGet(request, response);
	}
}
