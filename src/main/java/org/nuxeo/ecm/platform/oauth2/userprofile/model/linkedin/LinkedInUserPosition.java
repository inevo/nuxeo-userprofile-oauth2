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

import org.nuxeo.ecm.platform.oauth2.userprofile.model.AbstractUserPosition;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/**
 * Implementation of LinkedIn API's Job positions according to our interface.
 * 
 * @author <a href="mailto:tiago.cardoso@inevo.pt">Tiago Cardoso</a>
 * @since 5.7
 */

public class LinkedInUserPosition extends AbstractUserPosition {

	@Key
	private Company company;

	@Key
	private boolean isCurrent;

	@Key
	private LinkedInDate startDate;

	@Key
	private LinkedInDate endDate;

	@Key
	private String summary;

	@Key
	private String title;

	@Override
	public String getOrganization() {
		return company.getName();
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getSummary() {
		return summary;
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
	public boolean isCurrent() {
		return isCurrent;
	}

	@Override
	public String getIndustry() {
		return company.getIndustry();
	}

	public static class Company extends GenericJson {
		@Key
		String name;

		@Key
		String industry;

		public String getName() {
			return name;
		}

		public String getIndustry() {
			return industry;
		}

	}

}
