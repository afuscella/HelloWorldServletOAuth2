package test;

import org.junit.Assert;
import org.junit.Test;

import util.Constants;
import util.Constants.HttpHeaders;
import util.Constants.MimeType;

public class ConstantsTest {

	private static final String TEXT_HTML = "text/html";
	private static final String CONTENT_TYPE = "Content-Type";

	@Test
	public void testCoverageConstants() {
		MimeType mime_types = new Constants.MimeType();
		HttpHeaders header = new Constants.HttpHeaders();

		Assert.assertEquals(mime_types.TEXT_HTML, TEXT_HTML);
		Assert.assertEquals(header.CONTENT_TYPE, CONTENT_TYPE);
	}

}