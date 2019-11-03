package test;

import static org.mockito.Mockito.when;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.junit.*;
import org.mockito.*;

import com.sap.cloud.security.oauth2.OAuthAuthorization;
import com.sap.cloud.security.oauth2.OAuthSystemException;

import controller.HelloWorldServlet;

public class HelloWorldServletTest {

	HelloWorldServlet helloServlet;

	@Mock
	HttpServletRequest req;

	@Mock
	HttpServletResponse res;

	@Mock
	OAuthAuthorization oauth;

	@Before
	public void before() {
		helloServlet = new HelloWorldServlet();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAuthorizedOk() throws ServletException, OAuthSystemException, IOException {
		helloServlet.oauth = oauth;
		when(helloServlet.oauth.isAuthorized(req)).thenReturn(true);

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(res.getWriter()).thenReturn(pw);

		helloServlet.doGet(req, res);
	}

	@Test
	public void testAuthorizedFail() throws ServletException, IOException, OAuthSystemException {
		helloServlet.oauth = oauth;
		when(helloServlet.oauth.isAuthorized(req)).thenReturn(false);

		helloServlet.doGet(req, res);
	}

	@Test
	public void testOauthRequestException() throws ServletException, IOException {
		helloServlet.oauth = null;
		helloServlet.doGet(req, res);
	}
}
