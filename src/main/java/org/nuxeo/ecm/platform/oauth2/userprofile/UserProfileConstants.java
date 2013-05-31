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

package org.nuxeo.ecm.platform.oauth2.userprofile;

/**
 * Class holding constants use on this addon
 * 
 * @author <a href="mailto:tiago.cardoso@inevo.pt">Tiago Cardoso</a>
 * @since 5.7
 */

public final class UserProfileConstants {

	public static final String PROFILE_PROVIDER_EP = "profileProviders";

	public static final String STATE_URL_PARAM_NAME = "state";

	public static final String STATE_SESSION_ATTRIBUTE = STATE_URL_PARAM_NAME;

	public static final String CODE_URL_PARAM_NAME = "code";

	public static final String PROVIDER_URL_PARAM_NAME = "profileProvider";

	public static final String ERROR_URL_PARAM_NAME = "error";

	public static final String REDIRECT_URI_SESSION_ATTRIBUTE = "OAUTH2_REDIRECT_URI";

}
