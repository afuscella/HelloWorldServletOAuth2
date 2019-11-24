package config;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.cloud.security.oauth2.OAuthAuthorization;
import com.sap.cloud.security.oauth2.OAuthSystemException;

import exception.OAuthException;

public class OAuth2Configuration {

	private OAuthAuthorization oAuthAuthorization;
	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	public OAuth2Configuration() {
		this.oAuthAuthorization = OAuthAuthorization.getOAuthAuthorizationService();
	}

	public void checkAuthorization(HttpServletRequest request) throws OAuthException {

		Boolean isAuthorized;
		try {
			isAuthorized = this.oAuthAuthorization.isAuthorized(request);

		} catch (OAuthSystemException e) {
			logger.error(OAuthException.NOT_AUTHORIZED, e);
			isAuthorized = false;
		}

		if (!isAuthorized) {
			logger.error(OAuthException.NOT_AUTHORIZED);
			throw new OAuthException(OAuthException.NOT_AUTHORIZED);
		}
	}
}