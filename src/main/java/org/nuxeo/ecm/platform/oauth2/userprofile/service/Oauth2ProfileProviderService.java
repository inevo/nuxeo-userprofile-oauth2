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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nuxeo.ecm.platform.oauth2.userprofile.model.UserProfile;

/**
 * Service API. With minimum possible method for a proper integration and
 * information retrival on Oauth2 profile API.
 * 
 * @author <a href="mailto:tiago.cardoso@inevo.pt">Tiago Cardoso</a>
 * @since 5.7
 */

public interface Oauth2ProfileProviderService {

	UserProfile getUserProfile(String providerName, HttpServletRequest request);

	String getAuthenticationUrl(String providerName, HttpServletRequest request);

	Map<String, String> getProviderIcons();

}
