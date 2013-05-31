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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.platform.oauth2.providers.NuxeoOAuth2ServiceProvider;
import org.nuxeo.ecm.platform.oauth2.providers.OAuth2ServiceProviderRegistry;
import org.nuxeo.runtime.api.Framework;

/**
 * Oauth2 provider component extension registration implementantion. Holds all
 * providers.
 * 
 * @author <a href="mailto:tiago.cardoso@inevo.pt">Tiago Cardoso</a>
 * @since 5.7
 */

public class Oauth2ProfileProviderRegistryImpl implements
		Oauth2ProfileProviderRegistry {

	private static final long serialVersionUID = 1L;

	private final Map<String, Oauth2ProfileProvider> providers = new HashMap<String, Oauth2ProfileProvider>();

	protected static final Log log = LogFactory
			.getLog(Oauth2ProfileProviderRegistryImpl.class);

	@Override
	public void clear() {
		providers.clear();

	}

	@Override
	public void registerProvider(Oauth2ProfileProdiverDescriptor provider)
			throws Exception {

		OAuth2ServiceProviderRegistry oauth2ProviderRegistry = getOAuth2ServiceProviderRegistry();

		if (oauth2ProviderRegistry != null) {

			NuxeoOAuth2ServiceProvider oauth2Provider = oauth2ProviderRegistry
					.getProvider(provider.getName());

			if (oauth2Provider == null) {
				oauth2Provider = oauth2ProviderRegistry.addProvider(
						provider.getName(), provider.getTokenServerURL(),
						provider.getAuthorizationServerURL(),
						provider.getClientId(), provider.getClientSecret(),
						Arrays.asList(provider.getScopes()));
			} else {
				log.warn("Provider "
						+ provider.getName()
						+ " is already in the Database, XML contribution  won't overwrite it");
			}
			providers.put(
					provider.getName(),
					new Oauth2ProfileProvider(oauth2Provider, provider
							.getAccessTokenKey(), provider.getUserProfileURL(),
							provider.getUserProfileClass(), provider.getIcon(),
							provider.isEnabled()));

		}

	}

	@Override
	public void unregisterProvider(Oauth2ProfileProdiverDescriptor provider) {
		providers.remove(provider.getName());

	}

	@Override
	public List<Oauth2ProfileProvider> getProviders() {
		// TODO Auto-generated method stub
		List<Oauth2ProfileProvider> list = new ArrayList<Oauth2ProfileProvider>();
		list.addAll(providers.values());
		return list;
	}

	@Override
	public Oauth2ProfileProvider getProviderByName(String name) {
		return providers.get(name);
	}

	protected OAuth2ServiceProviderRegistry getOAuth2ServiceProviderRegistry() {
		return Framework.getLocalService(OAuth2ServiceProviderRegistry.class);
	}

}
