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
 * 
 * @author <a href="mailto:tiago.cardoso@inevo.pt">Tiago Cardoso</a>
 * @since 5.7
 */
public abstract class AbstractUserPosition extends GenericJson implements
		UserPosition {

	@Override
	public String getOrganization() {
		return null;
	}

	@Override
	public String getDepartment() {
		return null;
	}

	@Override
	public String getTitle() {
		return null;
	}

	@Override
	public String getSummary() {
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
	public boolean isCurrent() {
		return false;
	}

	@Override
	public String getBenefits() {
		return null;
	}

	@Override
	public String getIndustry() {
		return null;
	}

	@Override
	public String getWorkHours() {
		return null;
	}

	@Override
	public String getResponsibilities() {
		return null;
	}

	@Override
	public String getEducationRequirements() {
		return null;
	}

	@Override
	public String getJobLocation() {
		return null;
	}

	@Override
	public String getOccupationalCategory() {
		return null;
	}

	@Override
	public String getQualifications() {
		return null;
	}

}
