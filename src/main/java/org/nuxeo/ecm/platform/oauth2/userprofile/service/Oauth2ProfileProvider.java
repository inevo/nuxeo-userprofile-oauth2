/*
 * (C) Copyright 2006-2013 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Tiago Cardoso <tiago.cardoso@inevo.pt> - initial API and implementation
 */

package org.nuxeo.ecm.platform.oauth2.userprofile.service;

import static org.nuxeo.ecm.platform.oauth2.userprofile.UserProfileConstants.REDIRECT_URI_SESSION_ATTRIBUTE;
import static org.nuxeo.ecm.platform.oauth2.userprofile.UserProfileConstants.STATE_SESSION_ATTRIBUTE;
import static org.nuxeo.ecm.platform.oauth2.userprofile.UserProfileConstants.STATE_URL_PARAM_NAME;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.platform.oauth2.providers.NuxeoOAuth2ServiceProvider;
import org.nuxeo.ecm.platform.oauth2.userprofile.model.UserProfile;
import org.nuxeo.ecm.platform.web.common.vh.VirtualHostHelper;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson.JacksonFactory;

/**
 * Class that holds info about an Oauth2 provider. Also allows authorization and
 * Oauth2 API information retrival.
 * 
 * @author <a href="mailto:tiago.cardoso@inevo.pt">Tiago Cardoso</a>
 * @since 5.7
 */

public class Oauth2ProfileProvider {

	protected static final Log log = LogFactory
			.getLog(Oauth2ProfileProvider.class);

	/** Global instance of the HTTP transport. */
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();

	private boolean enabled = true;

	NuxeoOAuth2ServiceProvider oauth2Provider;

	private String userProfileURL;

	private String icon;

	private String accessTokenKey;

	private Class<? extends UserProfile> userProfileClass;

	public Oauth2ProfileProvider(NuxeoOAuth2ServiceProvider oauth2Provider,
			String accessTokenKey, String userProfileURL,
			Class<? extends UserProfile> userProfileClass, String icon,
			boolean enabled) {
		this.oauth2Provider = oauth2Provider;
		this.userProfileURL = userProfileURL;
		this.userProfileClass = userProfileClass;
		this.icon = icon;
		this.enabled = enabled;
		this.accessTokenKey = accessTokenKey;

	}

	public String getRedirectUri(HttpServletRequest req) {
		// TODO : this shouldn't be "static" (?!)
		String redirectUri = (String) req.getSession().getAttribute(
				REDIRECT_URI_SESSION_ATTRIBUTE);
		// TODO - Use the requestedUrl for providers with support for wildcards
		// String requestedUrl =
		// request.getParameter(NXAuthConstants.REQUESTED_URL);
		if (redirectUri == null) {
			redirectUri = VirtualHostHelper.getBaseURL(req)
					+ "nxstartup.faces?" + "" + "provider="
					+ this.oauth2Provider.getServiceName()
					+ "&forceAnonymousLogin=true";
		}
		return redirectUri;
	}

	/**
	 * Create a state token to prevent request forgery. Store it in the session
	 * for later validation.
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return
	 */
	public String createStateToken(HttpServletRequest request) {
		String state = new BigInteger(130, new SecureRandom()).toString(32);
		request.getSession().setAttribute(
				STATE_SESSION_ATTRIBUTE + "_" + getName(), state);
		return state;
	}

	/**
	 * Ensure that this is no request forgery going on, and that the user
	 * sending us this connect request is the user that was supposed to.
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return
	 */
	public boolean verifyStateToken(HttpServletRequest request) {
		return request.getParameter(STATE_URL_PARAM_NAME).equals(
				request.getSession().getAttribute(
						STATE_SESSION_ATTRIBUTE + "_" + getName()));
	}

	public String getAuthenticationUrl(HttpServletRequest req) {
		// redirect to the authorization flow
		AuthorizationCodeFlow flow = oauth2Provider.getAuthorizationCodeFlow(
				HTTP_TRANSPORT, JSON_FACTORY);
		AuthorizationCodeRequestUrl authorizationUrl = flow
				.newAuthorizationUrl(); // .setResponseTypes("token");
		authorizationUrl.setRedirectUri(getRedirectUri(req));

		String state = createStateToken(req);
		authorizationUrl.setState(state);
		// req.getSession().setAttribute(PROVIDER_URL_PARAM_NAME,
		// this.getName());
		String authUrl = authorizationUrl.build();

		return authUrl;
	}

	public String getName() {
		return oauth2Provider.getServiceName();
	}

	public String getIcon() {
		return icon;
	}

	public String getAccessToken(HttpServletRequest req, String code) {
		String accessToken = null;

		HttpResponse response = null;

		try {
			AuthorizationCodeFlow flow = oauth2Provider
					.getAuthorizationCodeFlow(HTTP_TRANSPORT, JSON_FACTORY);

			String redirectUri = getRedirectUri(req);
			response = flow.newTokenRequest(code).setRedirectUri(redirectUri)
					.executeUnparsed();
		} catch (IOException e) {
			log.error("Error during OAuth2 Authorization", e);
		}

		String type = response.getContentType();

		try {
			if (type.contains("text/plain")) {
				String str = response.parseAsString();
				String[] params = str.split("&");
				for (String param : params) {
					String[] kv = param.split("=");
					if (kv[0].equals("access_token")) {
						accessToken = kv[1]; // get the token
						break;
					}
				}
			} else { // try to parse as JSON

				TokenResponse tokenResponse = response
						.parseAs(TokenResponse.class);
				accessToken = tokenResponse.getAccessToken();

			}
		} catch (IOException e) {
			log.error("Unable to parse server response", e);
		}

		return accessToken;
	}

	public UserProfile getUserProfile(String accessToken) {
		UserProfile userProfile = null;

		HttpRequestFactory requestFactory = HTTP_TRANSPORT
				.createRequestFactory(new HttpRequestInitializer() {
					@Override
					public void initialize(HttpRequest request)
							throws IOException {
						request.setParser(new JsonObjectParser(JSON_FACTORY));
					}
				});

		GenericUrl url = new GenericUrl(userProfileURL);
		url.set(accessTokenKey, accessToken);

		try {
			HttpRequest request = requestFactory.buildGetRequest(url);
			HttpResponse response = request.execute();
			String body = IOUtils.toString(response.getContent(), "UTF-8");
			userProfile = parseUserProfile(body);

		} catch (IOException e) {
			log.error("Unable to parse server response", e);
		}

		return userProfile;
	}

	public UserProfile parseUserProfile(String userProfileJSON)
			throws IOException {
		return new JsonObjectParser(JSON_FACTORY).parseAndClose(
				new StringReader(userProfileJSON), userProfileClass);
	}

	public boolean isEnabled() {
		return enabled;
	}

}
