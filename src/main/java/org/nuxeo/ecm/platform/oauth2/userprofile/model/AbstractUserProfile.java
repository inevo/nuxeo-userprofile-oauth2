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

package org.nuxeo.ecm.platform.oauth2.userprofile.model;

import java.util.Date;
import java.util.List;

import com.google.api.client.json.GenericJson;

/**
 * Abstract class to return null on concrete classes that don't give some
 * informations.
 * 
 * @author <a href="mailto:tiago.cardoso@inevo.pt">Tiago Cardoso</a>
 * @since 5.7
 */
public abstract class AbstractUserProfile extends GenericJson implements
		UserProfile {

	@Override
	public String getGivenName() {
		return null;
	}

	@Override
	public String getFamilyName() {
		return null;
	}

	@Override
	public String getImageUrl() {
		return null;
	}

	@Override
	public UserExternal getUrl() {
		return null;
	}

	@Override
	public String getAwards() {
		return null;
	}

	@Override
	public Date getBirthDate() {
		return null;
	}

	@Override
	public String getEmail() {
		return null;
	}

	@Override
	public String getGender() {
		return null;
	}

	@Override
	public String getHomeLocation() {
		return null;
	}

	@Override
	public UserPosition getWorksFor() {
		return null;
	}

	@Override
	public UserEducation getAlumniOf() {
		return null;
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public String getId() {
		return null;
	}

	@Override
	public String getHeadline() {
		return null;
	}

	@Override
	public String getCurrentstatus() {
		return null;
	}

	@Override
	public String getSpecialties() {
		return null;
	}

	@Override
	public String getInterests() {
		return null;
	}

	@Override
	public List<? extends UserExternal> getExternals() {
		return null;
	}

	@Override
	public List<? extends UserEducation> getEducations() {
		return null;
	}

	@Override
	public List<? extends UserPosition> getPositions() {
		return null;
	}

}
