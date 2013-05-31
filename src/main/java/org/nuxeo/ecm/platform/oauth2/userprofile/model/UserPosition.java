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

/**
 * API of user's Job positions. Should integrate better with schema.org Should
 * integrate better with schema.org Organization and make the organization
 * independent with more information (future work).
 * 
 * @author <a href="mailto:tiago.cardoso@inevo.pt">Tiago Cardoso</a>
 * @since 5.7
 */

public interface UserPosition {

	String getOrganization();

	String getDepartment();

	String getTitle();

	String getSummary();

	Date getStartDate();

	Date getEndDate();

	boolean isCurrent();

	String getBenefits();

	String getIndustry();

	String getWorkHours();

	String getResponsibilities();

	String getEducationRequirements();

	String getJobLocation();

	String getOccupationalCategory();

	String getQualifications();
}
