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

import static org.nuxeo.ecm.platform.oauth2.userprofile.UserProfileConstants.CODE_URL_PARAM_NAME;
import static org.nuxeo.ecm.platform.oauth2.userprofile.UserProfileConstants.ERROR_URL_PARAM_NAME;
import static org.nuxeo.ecm.platform.oauth2.userprofile.UserProfileConstants.PROFILE_PROVIDER_EP;
import static org.nuxeo.ecm.platform.oauth2.userprofile.UserProfileConstants.PROVIDER_URL_PARAM_NAME;
import static org.nuxeo.ecm.platform.ui.web.auth.NXAuthConstants.LOGIN_ERROR;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.platform.oauth2.userprofile.model.UserProfile;
import org.nuxeo.runtime.model.ComponentContext;
import org.nuxeo.runtime.model.DefaultComponent;
import org.nuxeo.runtime.model.Extension;

/**
 * Service implementation. Allows the creation of authorizations codes, the
 * retrival of user profile according to a provider and provider listing with
 * icons.
 * 
 * @author <a href="mailto:tiago.cardoso@inevo.pt">Tiago Cardoso</a>
 * @since 5.7
 */

public class Oauth2ProfileProviderServiceImpl extends DefaultComponent
		implements Oauth2ProfileProviderService {

	protected Oauth2ProfileProviderRegistry providerRegistry;
	private static final Log log = LogFactory
			.getLog(Oauth2ProfileProviderServiceImpl.class);

	@Override
	public UserProfile getUserProfile(String providerName,
			HttpServletRequest request) {
		// Getting the "error" URL parameter
		String error = request.getParameter(ERROR_URL_PARAM_NAME);

		// / Checking if there was an error such as the user denied access
		if (error != null && error.length() > 0) {
			sendError(request, "There was an error: \"" + error + "\".");
			return null;
		}

		// Getting the "code" URL parameter
		String code = request.getParameter(CODE_URL_PARAM_NAME);

		// Checking conditions on the "code" URL parameter
		if (code == null || code.isEmpty()) {
			return null;
		}

		// Getting the "provider" URL parameter
		String serviceProviderName = request
				.getParameter(PROVIDER_URL_PARAM_NAME);

		// Checking conditions on the "provider" URL parameter
		if (serviceProviderName == null || serviceProviderName.isEmpty()) {
			sendError(request, "Missing Oauth Provider ID.");
			return null;
		} else if (!serviceProviderName.equals(providerName)) {
			sendError(request, "Wrong Oauth Provider ID.");
			return null;
		}

		try {
			Oauth2ProfileProvider provider = getProfileProvider(serviceProviderName);

			if (provider == null) {
				sendError(request, "No service provider called: \""
						+ serviceProviderName + "\".");
				return null;
			}

			// Check the state token
			if (!provider.verifyStateToken(request)) {
				sendError(request, "Invalid state parameter.");
			}

			// Validate the token
			String accessToken = provider.getAccessToken(request, code);

			if (accessToken == null) {
				return null;
			}

			return provider.getUserProfile(accessToken);

		} catch (Exception e) {
			log.error("Error while retrieve Identity From OAuth", e);
		}

		return null;

	}

	@Override
	public String getAuthenticationUrl(String providerName,
			HttpServletRequest request) {

		Oauth2ProfileProvider provider = getProfileProvider(providerName);
		return provider.getAuthenticationUrl(request);
	}

	protected Oauth2ProfileProvider getProfileProvider(String name) {
		return providerRegistry.getProviderByName(name);
	}

	@Override
	public void activate(ComponentContext context) throws Exception {
		providerRegistry = new Oauth2ProfileProviderRegistryImpl();

	}

	@Override
	public void deactivate(ComponentContext context) throws Exception {
		providerRegistry.clear();
		providerRegistry = null;
	}

	@Override
	public void registerExtension(Extension extension) throws Exception {
		log.info("Registering notification extension");
		String xp = extension.getExtensionPoint();
		if (PROFILE_PROVIDER_EP.equals(xp)) {
			Object[] contribs = extension.getContributions();
			for (Object contrib : contribs) {
				try {
					Oauth2ProfileProdiverDescriptor providerDesc = (Oauth2ProfileProdiverDescriptor) contrib;
					providerRegistry.registerProvider(providerDesc);
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
	}

	@Override
	public void unregisterExtension(Extension extension) throws Exception {
		String xp = extension.getExtensionPoint();
		if (PROFILE_PROVIDER_EP.equals(xp)) {
			Object[] contribs = extension.getContributions();
			for (Object contrib : contribs) {
				try {
					Oauth2ProfileProdiverDescriptor providerDesc = (Oauth2ProfileProdiverDescriptor) contrib;
					providerRegistry.unregisterProvider(providerDesc);
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
	}

	public Oauth2ProfileProviderRegistry getNotificationRegistry() {
		return providerRegistry;
	}

	protected void sendError(HttpServletRequest req, String msg) {
		req.setAttribute(LOGIN_ERROR, msg);
	}

	@Override
	public Map<String, String> getProviderIcons() {
		Map<String, String> icons = new HashMap<String, String>();
		for (Oauth2ProfileProvider provider : providerRegistry.getProviders()) {
			icons.put(provider.getName(), provider.getIcon());
		}
		return icons;
	}

}
