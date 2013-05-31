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

import java.io.Serializable;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XNodeList;
import org.nuxeo.common.xmap.annotation.XObject;
import org.nuxeo.ecm.platform.oauth2.userprofile.model.UserProfile;

/**
 * Oauth2 Provider Descriptor used to register.
 * 
 * @author <a href="mailto:tiago.cardoso@inevo.pt">Tiago Cardoso</a>
 * @since 5.7
 */

@XObject("profileProvider")
public class Oauth2ProfileProdiverDescriptor implements Serializable {
	protected static final long serialVersionUID = 1L;

	public static final String DEFAULT_ACCESS_TOKEN_KEY = "access_token";

	@XNode("@enabled")
	protected boolean enabled = true;

	@XNode("name")
	protected String name;

	@XNode("tokenServerURL")
	protected String tokenServerURL;

	@XNode("authorizationServerURL")
	protected String authorizationServerURL;

	@XNode("userProfileURL")
	protected String userProfileURL;

	@XNode("accessTokenKey")
	protected String accessTokenKey = DEFAULT_ACCESS_TOKEN_KEY;

	@XNode("clientId")
	protected String clientId;

	@XNode("clientSecret")
	protected String clientSecret;

	@XNodeList(value = "scope", type = String[].class, componentType = String.class)
	public String[] scopes;

	@XNode("icon")
	protected String icon;

	@XNode("userProfileClass")
	protected Class<? extends UserProfile> userProfileClass;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public String getTokenServerURL() {
		return tokenServerURL;
	}

	public String getAuthorizationServerURL() {
		return authorizationServerURL;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public String[] getScopes() {
		return scopes;
	}

	public String getUserProfileURL() {
		return userProfileURL;
	}

	public String getAccessTokenKey() {
		return accessTokenKey;
	}

	public String getIcon() {
		return icon;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Class<? extends UserProfile> getUserProfileClass() {
		return userProfileClass;
	}

}
