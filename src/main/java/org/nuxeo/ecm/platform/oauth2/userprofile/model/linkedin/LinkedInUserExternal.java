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

package org.nuxeo.ecm.platform.oauth2.userprofile.model.linkedin;

import org.nuxeo.ecm.platform.oauth2.userprofile.model.AbstractUserExternal;

import com.google.api.client.util.Key;

/**
 * Implementation of LinkedIn API's External links according to our interface.
 * 
 * @author <a href="mailto:tiago.cardoso@inevo.pt">Tiago Cardoso</a>
 * @since 5.7
 */

public class LinkedInUserExternal extends AbstractUserExternal {

	@Key
	public String url;

	@Key
	public String name;

	@Override
	public String getLink() {
		return url;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLink(String link) {
		url = link;
	}

}
