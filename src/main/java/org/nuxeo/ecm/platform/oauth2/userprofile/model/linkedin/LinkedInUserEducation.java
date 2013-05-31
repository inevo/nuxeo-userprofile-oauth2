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

import java.util.Date;

import org.nuxeo.ecm.platform.oauth2.userprofile.model.AbstractUserEducation;

import com.google.api.client.util.Key;

/**
 * Implementation of LinkedIn API's Educations according to our interface.
 * 
 * @author <a href="mailto:tiago.cardoso@inevo.pt">Tiago Cardoso</a>
 * @since 5.7
 */

public class LinkedInUserEducation extends AbstractUserEducation {

	@Key
	private LinkedInDate endDate;

	@Key
	private LinkedInDate startDate;

	@Key
	private String fieldOfStudy;

	@Key
	private String schoolName;

	@Key
	private String degree;

	@Key
	private String activities;

	@Key
	private String notes;

	@Override
	public String getOrganization() {
		return schoolName;
	}

	@Override
	public String getFieldOfStudy() {
		return fieldOfStudy;
	}

	@Override
	public Date getStartDate() {
		return (startDate == null) ? null : startDate.getDate();
	}

	@Override
	public Date getEndDate() {
		return (endDate == null) ? null : endDate.getDate();
	}

	@Override
	public String getDegree() {
		return degree;
	}

	@Override
	public String getActivities() {
		return activities;
	}

	@Override
	public String getNotes() {
		return notes;
	}

	@Override
	public boolean isCurrent() {
		return endDate == null;
	}
}
