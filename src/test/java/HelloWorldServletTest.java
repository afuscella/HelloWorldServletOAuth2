import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.helloworld.controller.HelloWorldServlet;
import com.sap.cloud.security.oauth2.OAuthAuthorization;
import com.sap.cloud.security.oauth2.OAuthSystemException;

public class HelloWorldServletTest {

	@Mock
	HttpServletRequest req;
	@Mock
	HttpServletResponse res;
	@Mock
	OAuthAuthorization oauth;

	private HelloWorldServlet helloServlet;

	@Before
	public void before() {
		this.helloServlet = new HelloWorldServlet();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAuthorizedOk() throws OAuthSystemException, IOException {
		when(oauth.isAuthorized(req)).thenReturn(true);

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(res.getWriter()).thenReturn(pw);

		this.helloServlet.doGet(req, res);
	}

	@Test
	public void testAuthorizedFail() throws OAuthSystemException {
		when(oauth.isAuthorized(req)).thenReturn(false);

		this.helloServlet.doGet(req, res);
	}

	@Test
	public void testOauthRequestException() {
		this.helloServlet = new HelloWorldServlet();
		this.helloServlet.doGet(req, res);
	}
}
