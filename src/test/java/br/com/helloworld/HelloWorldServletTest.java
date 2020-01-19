package br.com.helloworld;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.helloworld.controller.HelloWorldServlet;
import br.com.helloworld.exception.HelloWorldException;
import br.com.helloworld.exception.OAuthException;
import br.com.helloworld.service.HelloWorldService;
import com.sap.cloud.security.oauth2.OAuthAuthorization;

public class HelloWorldServletTest {

	@Mock
	HttpServletRequest req;
	@Mock
	HttpServletResponse res;
	@Mock
	OAuthAuthorization oauth;

	@Mock
	private HelloWorldService helloWorldService;

	private HelloWorldServlet helloServlet;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		this.helloServlet = new HelloWorldServlet(helloWorldService);
	}

	@Test
	public void testAuthorizedOk() throws IOException {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(res.getWriter()).thenReturn(pw);

		this.helloServlet.doGet(req, res);
	}

	@Test
	public void testAuthorizedFail() throws HelloWorldException {
		Mockito.doThrow(OAuthException.class).when(helloWorldService).handleGetRequest(Mockito.any(), Mockito.any());
		this.helloServlet.doGet(req, res);
	}

	@Test
	public void testOauthRequestException() throws HelloWorldException {
		Mockito.doThrow(HelloWorldException.class).when(helloWorldService)
				.handleGetRequest(Mockito.any(), Mockito.any());
		this.helloServlet.doGet(req, res);
	}
}
