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

import com.google.api.client.json.GenericJson;

/**
 * Abstract class to return null on concrete classes that don't give some
 * informations.
 * @author <a href="mailto:tiago.cardoso@inevo.pt">Tiago Cardoso</a>
 * @since 5.7
 */
public abstract class AbstractUserEducation extends GenericJson implements
		UserEducation {

	@Override
	public String getOrganization() {
		return null;
	}

	@Override
	public String getFieldOfStudy() {
		return null;
	}

	@Override
	public Date getStartDate() {
		return null;
	}

	@Override
	public Date getEndDate() {
		return null;
	}

	@Override
	public String getDegree() {
		return null;
	}

	@Override
	public String getActivities() {
		return null;
	}

	@Override
	public String getNotes() {
		return null;
	}

	@Override
	public String getLocation() {
		return null;
	}

	@Override
	public boolean isCurrent() {
		return false;
	}

}
