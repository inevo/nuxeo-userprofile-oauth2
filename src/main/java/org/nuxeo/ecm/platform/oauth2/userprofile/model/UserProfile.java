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

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * API of user's Profile. Follows Schema.org standards as close as possible.
 * 
 * @see http://schema.org/Person
 * @author <a href="mailto:tiago.cardoso@inevo.pt">Tiago Cardoso</a>
 * @since 5.7
 */

public interface UserProfile {

	// @see http://schema.org/Person fields

	String getGivenName();

	String getFamilyName();

	String getImageUrl();

	UserExternal getUrl();

	String getAwards();

	Date getBirthDate();

	String getEmail();

	String getGender();

	String getHomeLocation();

	UserPosition getWorksFor();

	UserEducation getAlumniOf();

	String getDescription();

	// Extra fields (usual in web profiles)

	String getId();

	String getHeadline();

	String getCurrentstatus();

	String getSpecialties();

	String getInterests();

	List<? extends UserExternal> getExternals();

	List<? extends UserEducation> getEducations();

	List<? extends UserPosition> getPositions();

}
